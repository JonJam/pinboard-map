package com.jonjam.pinboard.common.test.container;

import java.nio.file.Paths;

import com.jonjam.pinboard.common.database.config.DatabaseInfo;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

public class ServiceTestContainer {
    // Creating singleton container as per: https://www.testcontainers.org/test_framework_integration/manual_lifecycle_control/#singleton-containers
    private static final GenericContainer SERVICE_CONTAINER;

    private static final int HTTP_PORT = 8099;

    static {
        SERVICE_CONTAINER = new GenericContainer(new ImageFromDockerfile()
            .withFileFromPath(".", Paths.get(".."))
            .withBuildArg("APP_ID", "99")
            .withBuildArg("VERSION", "prod"))
            // NOTE - last two digits map to TEST_APP_ID.
            .withExposedPorts(HTTP_PORT, 4099)
            .withEnv("ENVIRONMENT", "test");
    }

    public static void start(final DatabaseInfo databaseInfo) {
        SERVICE_CONTAINER.addEnv("DATABASE_DRIVER_DATABASENAME", databaseInfo.getDatabaseName());
        SERVICE_CONTAINER.addEnv("DATABASE_DRIVER_HOST", databaseInfo.getHost());
        SERVICE_CONTAINER.addEnv("DATABASE_DRIVER_USERNAME", databaseInfo.getUsername());
        SERVICE_CONTAINER.addEnv("DATABASE_DRIVER_PASSWORD", databaseInfo.getPassword());

        SERVICE_CONTAINER.start();
    }

    public static String getUrl() {
        return "http://" + SERVICE_CONTAINER.getContainerIpAddress() + ":" + SERVICE_CONTAINER.getMappedPort(HTTP_PORT);
    }
}
