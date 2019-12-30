/**
 * 
 */
package com.ss.lms.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ashian
 *
 */
public class ConnectionUtil 
{
	public final String driver = "com.mysql.cj.jdbc.Driver";
	public final String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
	public final String username = "root";
	public final String password = "kodo08yaku";
	/**
	 * 
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(Boolean.FALSE);
	
		return conn;
	}
}
