package com.jonjam.pinboard.common.database;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;

import com.jonjam.pinboard.common.database.config.DatabaseInfo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceProvider implements Provider<DataSource> {

    private final DatabaseInfo databaseInfo;

    @Inject
    public DataSourceProvider(final DatabaseInfo databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

    // Originally thought to create this in its own provider class, however Guice
    // recommends to avoid binding closeable resources: https://github.com/google/guice/wiki/Avoid-Injecting-Closable-Resources
    @Override
    public DataSource get() {
        // Other things that could setup are:
        // - Driver doesn't support slow query logging: https://github.com/brettwooldridge/HikariCP#log-statement-text--slow-query-logging
        // - Caching already enabled by default: https://github.com/brettwooldridge/HikariCP/wiki/FAQ#q-how-to-i-properly-enable-preparedstatement-caching-for-postgresql
        final HikariConfig config = new HikariConfig();
        // Using preferred datasource class name. See: https://github.com/brettwooldridge/HikariCP#essentials
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setMaxLifetime(databaseInfo.getMaxLifetime());
        config.setMaximumPoolSize(databaseInfo.getMaximumPoolSize());
        config.setPoolName(databaseInfo.getDatabaseName());
        config.setLeakDetectionThreshold(databaseInfo.getLeakDetectionThreshold());

        // Same as default value but expliclty setting as value is updated in AutoTransactionHandleContext.
        config.setAutoCommit(true);
        config.setUsername(databaseInfo.getUsername());
        config.setPassword(databaseInfo.getPassword());
        // See https://jdbc.postgresql.org/documentation/head/connect.html for details
        config.addDataSourceProperty("serverName", databaseInfo.getHost());
        config.addDataSourceProperty("databaseName", databaseInfo.getDatabaseName());
        config.addDataSourceProperty("portNumber", databaseInfo.getPort());
        config.addDataSourceProperty("ssl", databaseInfo.getUseSSL());
        // See https://github.com/brettwooldridge/HikariCP/wiki/JDBC-Logging about logging
        config.addDataSourceProperty("loggerLevel", databaseInfo.getLogLevel());
        config.addDataSourceProperty("logUnclosedConnections", databaseInfo.getLogUnclosedConnections());
        config.addDataSourceProperty("reWriteBatchedInserts", true);

        // Setting per guidance on https://jdbc.postgresql.org/documentation/head/callproc.html
        config.addDataSourceProperty("escapeSyntaxCallMode", "callIfNoReturn");

        // Setting per guidance on https://github.com/brettwooldridge/HikariCP/wiki/Rapid-Recovery#tcp-timeouts
        config.addDataSourceProperty("socketTimeout", databaseInfo.getSocketTimeout());

        return new HikariDataSource(config);
    }
}
