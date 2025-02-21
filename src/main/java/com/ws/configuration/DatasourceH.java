package com.ws.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatasourceH {

	private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl( "jdbc:mysql://localhost:3306/ws_ods_dev" );
        config.setUsername( "root" );
        config.setPassword( "R@@tMysql0270" );
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

    }


    public static Connection getConnection() throws SQLException {
    	try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }
    	ds = new HikariDataSource( config );
        return ds.getConnection();
    }

}
