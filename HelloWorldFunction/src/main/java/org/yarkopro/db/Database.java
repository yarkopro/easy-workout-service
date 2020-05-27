package org.yarkopro.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.yarkopro.conf.Configuration;
import org.yarkopro.conf.ConfigurationHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Encapsulates creation of {@link DataSource} connection.
 */
public final class Database {

    private Database() {
        throw new AssertionError();
    }

    private static HikariDataSource dataSource;

    static {
        try {
            final Configuration configuration = ConfigurationHolder.INSTANCE.configuration();
            final Configuration.DataSource props = configuration.getDatasource();

            final HikariConfig config = new HikariConfig();
            config.setDriverClassName(props.getDriverClassName());
            config.setJdbcUrl(props.getUrl());
            config.setUsername(props.getUsername());
            config.setPassword(props.getPassword());

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            dataSource = new HikariDataSource();
        }

    }

    public static Connection connection() throws SQLException {
        return dataSource.getConnection();
    }
}
