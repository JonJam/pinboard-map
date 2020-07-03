package com.jonjam.pinboard.common.database;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;

import java.sql.SQLException;

import com.jonjam.pinboard.common.database.config.ConnectionInfo;
import com.jonjam.pinboard.common.logging.StructuredLogger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.ColumnMappers;
import org.jdbi.v3.core.statement.SqlLogger;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.postgres.PostgresPlugin;

public class JdbiProvider implements Provider<Jdbi> {

    private final ConnectionInfo connectionInfo;

    @Inject
    public JdbiProvider(final ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    @Override
    public Jdbi get() {
        final DataSource dataSource = createDataSource();

        final Jdbi jdbi = Jdbi.create(dataSource);

        jdbi.setSqlLogger(new JdbiLogger());

        // Plugins
        jdbi.installPlugin(new PostgresPlugin());

        // Config
        // Configuring DB null not to map to Java primitive default value. See: http://jdbi.org/#_primitive_mapping
        jdbi.getConfig(ColumnMappers.class).setCoalesceNullPrimitivesToDefaults(false);

        return jdbi;
    }

    // Originally thought to create this in its own provider class, however Guice
    // recommends to avoid binding closeable resources: https://github.com/google/guice/wiki/Avoid-Injecting-Closable-Resources
    private DataSource createDataSource() {
        // Other things that could setup are:
        // - Driver doesn't support slow query logging: https://github.com/brettwooldridge/HikariCP#log-statement-text--slow-query-logging
        // - Caching already enabled by default: https://github.com/brettwooldridge/HikariCP/wiki/FAQ#q-how-to-i-properly-enable-preparedstatement-caching-for-postgresql
        final HikariConfig config = new HikariConfig();
        // Using preferred datasource class name. See: https://github.com/brettwooldridge/HikariCP#essentials
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setMaxLifetime(connectionInfo.getMaxLifetime());
        config.setMaximumPoolSize(connectionInfo.getMaximumPoolSize());
        config.setPoolName(connectionInfo.getDatabaseName());
        config.setLeakDetectionThreshold(connectionInfo.getLeakDetectionThreshold());

        // Same as default value but expliclty setting as value is updated in AutoTransactionHandleContext.
        config.setAutoCommit(true);

        config.setUsername(connectionInfo.getUsername());
        config.setPassword(connectionInfo.getPassword());
        // See https://jdbc.postgresql.org/documentation/head/connect.html for details
        config.addDataSourceProperty("serverName", connectionInfo.getHost());
        config.addDataSourceProperty("databaseName", connectionInfo.getDatabaseName());
        config.addDataSourceProperty("portNumber", connectionInfo.getPort());
        config.addDataSourceProperty("ssl", connectionInfo.getUseSSL());
        // See https://github.com/brettwooldridge/HikariCP/wiki/JDBC-Logging about logging
        config.addDataSourceProperty("loggerLevel", connectionInfo.getLogLevel());
        config.addDataSourceProperty("logUnclosedConnections", connectionInfo.getLogUnclosedConnections());
        config.addDataSourceProperty("reWriteBatchedInserts", true);

        // Setting per guidance on https://jdbc.postgresql.org/documentation/head/callproc.html
        config.addDataSourceProperty("escapeSyntaxCallMode", "callIfNoReturn");

        // Setting per guidance on https://github.com/brettwooldridge/HikariCP/wiki/Rapid-Recovery#tcp-timeouts
        config.addDataSourceProperty("socketTimeout", connectionInfo.getSocketTimeout());

        return new HikariDataSource(config);
    }

    static class JdbiLogger implements SqlLogger {
        private static final StructuredLogger LOGGER = StructuredLogger.getLogger(JdbiLogger.class);

        @Override
        public void logBeforeExecution(final StatementContext context) {
            // TODO Populate
        }

        @Override
        public void logAfterExecution(final StatementContext context) {
            // TODO Populate
        }

        @Override
        public void logException(final StatementContext context, final SQLException ex) {
            // TODO Populate
            LOGGER.error()
                  .write(ex);
        }
    }
}
