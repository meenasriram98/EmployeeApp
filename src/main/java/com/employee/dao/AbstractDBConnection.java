package com.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDBConnection {

	Connection connection = null;

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		if (connection == null) {
			synchronized (AbstractDBConnection.class) {
				if (connection == null) { // two level locking
					String url = "jdbc:mysql://localhost";
					String user = "root";
					String password = "";

					Class.forName("com.mysql.jdbc.Driver");

					connection = DriverManager.getConnection(url, user, password);
				}
			}
		}
		return connection;
	}
}