package com.hari.Dao;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection databaseConnection() throws ClassNotFoundException, SQLException
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection=DriverManager.getConnection("jdbc:mysql://101.53.133.59:3306/revature_training_db","rev_user","rev_user");
	return connection;
	}
	


}
