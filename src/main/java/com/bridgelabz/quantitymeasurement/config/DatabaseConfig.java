package com.bridgelabz.quantitymeasurement.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.bridgelabz.quantitymeasurement.exception.DatabaseException;
import java.sql.Connection;
import java.sql.SQLException;

// UC16: Configuration Management & Connection Pooling
public class DatabaseConfig {

    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/qma?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
            config.setUsername("root");
            config.setPassword("root");
            
            // Connection Pool Settings
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(30000);
            config.setMaxLifetime(1800000); // 30 minutes
            config.setConnectionTimeout(30000);
            
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            throw new DatabaseException("Failed to initialize database connection pool.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new DatabaseException("Data source is not initialized.");
        }
        return dataSource.getConnection();
    }
    
    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
