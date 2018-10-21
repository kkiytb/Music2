package cn.kk.music.utils;

import java.io.IOException;
import java.io.InputStream;import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static BasicDataSource dataSource;
	static {
		Properties properties = new Properties();
		InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(inputStream);
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(properties.getProperty("driver"));
			dataSource.setUrl(properties.getProperty("url"));
			dataSource.setUsername(properties.getProperty("username"));
			dataSource.setPassword(properties.getProperty("password"));
			dataSource.setInitialSize(Integer.parseInt(properties.getProperty("initSize")));
			dataSource.setMaxActive(Integer.parseInt(properties.getProperty("maxActive")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.setAutoCommit(true);
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
