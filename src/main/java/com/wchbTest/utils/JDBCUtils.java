package com.wchbTest.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wchbTest.exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JDBCUtils.class);

    private static DataSource dataSource = null;
    private static final String CONFIG_FILE_NAME = "test_app";

    static {
        dataSource = new ComboPooledDataSource(CONFIG_FILE_NAME);
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("dataSource.getConnection error!", e);
            throw new DBException("获取连接异常!");
        }
    }

    public static void release(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("connection.close() error!", e);
            throw new DBException("关闭连接!");
        }
    }

    public static void releaseResultSetAndStatement(ResultSet rs, Statement statement) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOG.error("ResultSet close() error!", e);
            throw new DBException("ResultSet 关闭连接异常!");
        }

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOG.error("Statement close() error!", e);
            throw new DBException("Statement 关闭连接异常!");
        }
    }

    public static void main(String[] args) {
        LOG.info("Connection:{}", JDBCUtils.getConnection());
    }
}
