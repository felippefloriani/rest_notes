package br.com.felippefloriani.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 


public class BDconfig {
	
	 public static Connection getConnection() throws SQLException, ClassNotFoundException {
	        Class.forName("com.mysql.jdbc.Driver");
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/notes_db", "root", "q1w2e3r4");
	    }

}
