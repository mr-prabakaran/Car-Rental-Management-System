package CRMS_Backend;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connect {
	public Connection con;
	String dburl = "jdbc:mysql://localhost:3306/CRMS";
	String dbuser = "root";
	String dbpwd = "Praba@2002";

	public DB_Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dburl, dbuser, dbpwd);
			System.out.println("DB Connected...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
