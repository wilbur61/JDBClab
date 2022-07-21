package org.jdbc.lab;
//
//
// https://www.roseindia.net/tutorial/java/jdbc/parameterisedpreparedstatementexample.html
//
// Please implement
//
// Update   a row
// Delete   a row

//At first create table named student in MySql database and inset values into it as.

//CREATE TABLE student (
//  RollNo int(9)  PRIMARY KEY NOT NULL,
//  Name tinytext NOT NULL,
//  Course varchar(25) NOT NULL,
//  Address text
// );


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementParameterExample {
	Connection connection = null;
	String driverName = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost/classicmodels";
	String userName = "root";
	String userPass = "rootpwd";

	public PreparedStatementParameterExample() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
	}

	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(connectionUrl, userName,
					userPass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] args) throws SQLException {
		PreparedStatementParameterExample ptmtExample = new PreparedStatementParameterExample();
		Connection con = ptmtExample.getConnection();
		ResultSet resultSet = null;
		// Writing a parameterised query in prepared Statement
		String insertQuery = "INSERT INTO student(RollNo,Name,Course,Address) VALUES(?,?,?,?)";
		try {
			// Compiling query String
			PreparedStatement statement = con.prepareStatement(insertQuery);
			// setting parameter to the query
			statement.setInt(1, 2);
			statement.setString(2, "Dinesh");
			statement.setString(3, "MCA");
			statement.setString(4, "Patna");
			//Updating Query
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
}