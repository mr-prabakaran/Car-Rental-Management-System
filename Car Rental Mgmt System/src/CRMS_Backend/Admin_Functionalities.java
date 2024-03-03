package CRMS_Backend;

import java.sql.*;

public class Admin_Functionalities extends DB_Connect {
	// Create New Admin
	public int Add_Admin(String FName, String LName, String UName, String Pwd) throws Exception {
		String q = "insert into admin(First_Name,Last_Name,User_Name,Password) values(?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setString(1, FName);
		pst.setString(2, LName);
		pst.setString(3, UName);
		pst.setString(4, Pwd);
		int n = pst.executeUpdate();
		return n;
	}

	// Create New Car Details
	public int Add_CarDetails(String CName, String Brand, String Model, int Year, String Color, int PricePerDay,
			String Status) throws Exception {
		String q = "insert into car_details(Car_Name,Brand,Model,Year,Color,Price_Per_Day,Status) values(?,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setString(1, CName);
		pst.setString(2, Brand);
		pst.setString(3, Model);
		pst.setInt(4, Year);
		pst.setString(5, Color);
		pst.setInt(6, PricePerDay);
		pst.setString(7, Status);
		int n = pst.executeUpdate();
		return n;
	}

	// Update Car Details
	public int Edit_CarDetails(int ID, String Status) throws Exception {
		String q = "update car_details set Status = ? where Car_ID = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setString(1, Status);
		pst.setInt(2, ID);
		int n = pst.executeUpdate();
		return n;
	}

	// Delete Car Details
	public int Delete_CarDetails(int ID) throws Exception {
		String q = "Delete from car_details where Car_ID = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1, ID);
		int n = pst.executeUpdate();
		return n;
	}

	// View Car Details
	public void View_CarDetails() throws Exception {
		String q = "Select * from car_details";
		PreparedStatement pst = con.prepareStatement(q);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " \t" + rs.getString(2) + " \t" + rs.getString(3) + " \t" + rs.getString(4)
					+ " \t" + rs.getInt(5) + " \t" + rs.getString(6) + " \t" + rs.getInt(7) + " \t" + rs.getString(8));
		}
	}

	// View Customer Details
	public void View_CustomerDetails() throws Exception {
		String q = "Select * from customer_details";
		PreparedStatement pst = con.prepareStatement(q);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " \t" + rs.getString(2) + " \t" + rs.getString(3) + " \t" + rs.getString(4)
					+ " \t" + rs.getLong(5) + " \t" + rs.getString(6));
		}
	}

	// View Rental Transaction
	public void View_RentalTransaction() throws Exception {
		String q = "Select * from rental_transaction";
		PreparedStatement pst = con.prepareStatement(q);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getInt(3) + " " + rs.getDate(4) + " "
					+ rs.getDate(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getString(8));
		}
	}

	// Admin Authentication
	public String Admin_Authentication(String UserName) throws Exception {
		String q = "Select Password from admin where User_Name = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setString(1, UserName);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getString("Password");
		} else {
			throw new Exception("User Not Found!");
		}
	}
}
