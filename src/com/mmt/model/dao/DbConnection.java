package com.mmt.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	public static Connection dbConnection() throws IOException, ClassNotFoundException, SQLException {


		Connection con = null;
	

		 try {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521",
					"system", "sapient@123");
		 
		 } catch (ClassNotFoundException | SQLException e) {
		
		 e.printStackTrace();
		
		 }

		 return con;
	}
}
