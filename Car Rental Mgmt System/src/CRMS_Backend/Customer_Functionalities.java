package CRMS_Backend;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class Customer_Functionalities extends DB_Connect {
	// Create New Customer
	public int Add_Admin(String FName, String LName, String Gender, long Phone, String Address) throws Exception {
		String q = "insert into Customer_Details(First_Name,Last_Name,Gender,Phone,Address) values(?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setString(1, FName);
		pst.setString(2, LName);
		pst.setString(3, Gender);
		pst.setLong(4, Phone);
		pst.setString(5, Address);
		int n = pst.executeUpdate();
		return n;
	}
	
	// Viewing available Cars
	public void Available_Cars() throws Exception {
		String q = "select * from car_details where Status = 'available'";
		PreparedStatement pst = con.prepareStatement(q);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
					+ " " + rs.getInt(5) + " " + rs.getString(6) + " " + rs.getInt(7) + " " + rs.getString(8));
		}
	}

	// Rent A Car
	public int Taking_Rental(int Car_ID, int Cus_ID) throws Exception {
		String q = "insert into Rental_Transaction(Car_ID,Cus_ID,Rental_Start_Date,Status) values(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(q);
		LocalDate CurrentDate = LocalDate.now();
		pstmt.setInt(1, Car_ID);
		pstmt.setInt(2, Cus_ID);
		pstmt.setDate(3, java.sql.Date.valueOf(CurrentDate));
		pstmt.setString(4, "active");
		int n = pstmt.executeUpdate();

		String q1 = "update Car_Details set Status = ? where Car_ID = ? ";
		PreparedStatement pstmt1 = con.prepareStatement(q1);
		pstmt1.setString(1, "rented");
		pstmt1.setInt(2, Car_ID);
		pstmt1.executeUpdate();
		return n;
	}

	// Returning Rental Car
	public int Returning_Rental(int Car_ID) throws Exception {
		String q = "Select Rental_Start_Date from Rental_Transaction where Car_ID = ?";
		PreparedStatement pstm = con.prepareStatement(q);
		pstm.setInt(1, Car_ID);
		ResultSet rs = pstm.executeQuery();
		LocalDate CurrentDate = null;
		if (rs.next()) {
			CurrentDate = rs.getDate(1).toLocalDate();
		} else {
			return -1; // Return -1 to indicate that no rental start date was found
		}
		LocalDate ReturnDate = LocalDate.now();
		Period TD = Period.between(CurrentDate, ReturnDate);

		String q1 = "Select Price_Per_Day from Car_Details where Car_ID = ?";
		PreparedStatement pstm1 = con.prepareStatement(q1);
		pstm1.setInt(1, Car_ID);
		ResultSet rs1 = pstm1.executeQuery();

		int PricePerDay = 0;
		if (rs1.next()) {
			PricePerDay = rs1.getInt("Price_Per_Day");
		}
		int TotalDays = TD.getDays();
		int TotalCost = TotalDays * PricePerDay;
		//
		String q2 = "update Rental_Transaction set Rental_End_Date = ?,Total_Days = ?,Total_Cost = ?,Status = ? where Car_ID = ? ";
		PreparedStatement pstmt2 = con.prepareStatement(q2);
		pstmt2.setDate(1, java.sql.Date.valueOf(ReturnDate));
		pstmt2.setInt(2, TotalDays);
		pstmt2.setInt(3, TotalCost);
		pstmt2.setString(4, "Completed");
		pstmt2.setInt(5, Car_ID);
		int n = pstmt2.executeUpdate();
		String q3 = "update Car_Details set Status = ? where Car_ID = ? ";
		PreparedStatement pstmt = con.prepareStatement(q3);
		pstmt.setString(1, "available");
		pstmt.setInt(2, Car_ID);
		pstmt.executeUpdate();
		// Total_Days and Total_Cost for the Rental_Transaction
		String q4 = "select Total_Days,Total_Cost from rental_transaction where Car_ID = ?";
		PreparedStatement prst = con.prepareStatement(q4);
		prst.setInt(1, Car_ID);
		ResultSet rs2 = prst.executeQuery();
		while (rs2.next()) {
			int totalDays = rs2.getInt("Total_Days");
			int totalCost = rs2.getInt("Total_Cost");
			System.out.println("Total Days: " + totalDays + ", Total Cost: " + totalCost);
		}
		return n;
	}
}
