package com.ws.Dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class HikariDaoFactory {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ws_ods_dev" ;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "R@@tMysql0270";

    private DataSource dataSource;

    public HikariDaoFactory() {
        dataSource = createDataSource();
    }

    private DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setConnectionTimeout(50000); // in milliseconds
        config.setIdleTimeout(300000); // in milliseconds
        config.setMaxLifetime(900000); // in milliseconds
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(10);
        config.setPoolName("ConnPool");
        config.setConnectionTestQuery("SELECT 1");

        // DataSource specific properties
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        return new HikariDataSource(config);
    }



}
