package com.aurora.backend.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
@RequiredArgsConstructor
@Slf4j
@Profile({"dev", "local"})
public class PgAdminSeedRunner {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Value("${app.seed.pg-admin.enabled:true}")
    private boolean seedEnabled;

    @Bean
    @Order(1)
    public CommandLineRunner runPgAdminSeedScript() {
        return args -> {
            if (!seedEnabled) {
                log.info("Skipping init-db-pg-admin.sql because app.seed.pg-admin.enabled=false");
                return;
            }

            Integer branchCount = null;
            try {
                branchCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM branches", Integer.class);
            } catch (Exception e) {
                log.warn("Could not read branches count before seeding: {}", e.getMessage());
            }

            if (branchCount != null && branchCount > 0) {
                log.info("Skipping init-db-pg-admin.sql because branches already exist: {}", branchCount);
                return;
            }

            ClassPathResource resource = new ClassPathResource("db/init-db-pg-admin.sql");
            if (!resource.exists()) {
                log.warn("Seed script not found: classpath:db/init-db-pg-admin.sql");
                return;
            }

            try {
                String sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
                    statement.execute(sql);
                }
                log.info("Successfully executed seed script: init-db-pg-admin.sql");
            } catch (Exception e) {
                log.error("Failed to execute init-db-pg-admin.sql: {}", e.getMessage(), e);
            }
        };
    }
}
