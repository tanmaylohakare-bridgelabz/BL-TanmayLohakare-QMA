package com.bridgelabz.quantitymeasurement.repository;

import com.bridgelabz.quantitymeasurement.config.DatabaseConfig;
import com.bridgelabz.quantitymeasurement.exception.DatabaseException;
import com.bridgelabz.quantitymeasurement.model.QuantityRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

// UC16: JDBC Implementation, Parameterized SQL Queries, Resource Management
public class QuantityRepositoryImpl implements IQuantityRepository {

    public QuantityRepositoryImpl() {
        initializeSchema();
    }

    @Override
    public void initializeSchema() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS quantity_records (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "operation_type VARCHAR(50) NOT NULL, " +
                "value1 DOUBLE, " +
                "unit1 VARCHAR(50), " +
                "value2 DOUBLE, " +
                "unit2 VARCHAR(50), " +
                "target_unit VARCHAR(50), " +
                "result DOUBLE, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        // Resource Management via try-with-resources
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Database Schema initialized correctly via HikariCP.");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to initialize database schema.", e);
        }
    }

    @Override
    public void save(QuantityRecord record) {
        String sql = "INSERT INTO quantity_records (operation_type, value1, unit1, value2, unit2, target_unit, result) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        try {
            conn = DatabaseConfig.getConnection();
            // Transaction Management
            conn.setAutoCommit(false);
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Parameterized SQL Queries to prevent SQL injection
                pstmt.setString(1, record.getOperationType());
                
                if (record.getValue1() != null) pstmt.setDouble(2, record.getValue1());
                else pstmt.setNull(2, java.sql.Types.DOUBLE);
                
                pstmt.setString(3, record.getUnit1());
                
                if (record.getValue2() != null) pstmt.setDouble(4, record.getValue2());
                else pstmt.setNull(4, java.sql.Types.DOUBLE);
                
                pstmt.setString(5, record.getUnit2());
                pstmt.setString(6, record.getTargetUnit());
                
                if (record.getResult() != null) pstmt.setDouble(7, record.getResult());
                else pstmt.setNull(7, java.sql.Types.DOUBLE);
                
                pstmt.executeUpdate();
                
                // Commit transaction
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new DatabaseException("Transaction failed and rolled back during save operation.", ex);
            } finally {
                // Reset auto-commit behavior
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to acquire connection or handle transaction.", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // Returns connection to the pool
                } catch (SQLException e) {
                    System.err.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
}
