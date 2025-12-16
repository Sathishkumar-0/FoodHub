package com.tap.conect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static String URL="jdbc:mysql://localhost:3306/foodapp";
	private static String USERNAME="root";
	private static String PASSWORD="samuel";
	static Connection con=null;

	public static Connection getConnection(){
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return con;
		
	}
}
