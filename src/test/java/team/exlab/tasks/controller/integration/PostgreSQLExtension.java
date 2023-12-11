package team.exlab.tasks.controller.integration;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLExtension implements BeforeAllCallback, AfterAllCallback {

    private PostgreSQLContainer<?> postgres;

    @Override
    public void beforeAll(ExtensionContext context) {
        postgres = new PostgreSQLContainer<>("postgres:15");
        postgres.start();

        String jdbcUrl = postgres.getJdbcUrl();
        System.setProperty("spring.datasource.url", jdbcUrl);
    }

    @Override
    public void afterAll(ExtensionContext context) {

    }
}
