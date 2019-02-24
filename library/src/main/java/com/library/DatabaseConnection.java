package com.library;

import java.sql.*;

public class DatabaseConnection {
	
	Statement s = null;

	// load the driver manager
	DatabaseConnection() {
		try {
			DriverManager.deregisterDriver(new org.postgresql.Driver());
			
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "qwer");
			
			this.s = conn.createStatement();
			
			System.out.println("connected");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("not connected");
		}
	}
}
