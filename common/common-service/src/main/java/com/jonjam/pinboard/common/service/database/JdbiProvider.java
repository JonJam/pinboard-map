package com.jonjam.pinboard.common.service.database;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.ColumnMappers;
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

        // Plugins
        jdbi.installPlugin(new PostgresPlugin());

        // Config
        // Configuring DB null not to map to Java primitive default value. See: http://jdbi.org/#_primitive_mapping
        jdbi.getConfig(ColumnMappers.class).setCoalesceNullPrimitivesToDefaults(false);

        return jdbi;
    }
}
