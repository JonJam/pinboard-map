package com.jonjam.pinboard.common.database;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;
import java.sql.SQLException;

import com.jonjam.pinboard.common.logging.StructuredLogger;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.ColumnMappers;
import org.jdbi.v3.core.statement.SqlLogger;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.postgres.PostgresPlugin;

public class JdbiProvider implements Provider<Jdbi> {

    private final DataSource dataSource;

    @Inject
    public JdbiProvider(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Jdbi get() {
        final Jdbi jdbi = Jdbi.create(dataSource);

        jdbi.setSqlLogger(new JdbiLogger());

        // Plugins
        jdbi.installPlugin(new PostgresPlugin());

        // Config
        // Configuring DB null not to map to Java primitive default value. See: http://jdbi.org/#_primitive_mapping
        jdbi.getConfig(ColumnMappers.class).setCoalesceNullPrimitivesToDefaults(false);

        return jdbi;
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
