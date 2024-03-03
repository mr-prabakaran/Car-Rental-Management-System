package CRMS_Frontend;

import java.util.Scanner;

import CRMS_Backend.Admin_Functionalities;

public class Admin_Login {
	Scanner sc = new Scanner(System.in);

	public void admin_Login() throws Exception {
		Admin_Functionalities af = new Admin_Functionalities();
		System.out.println("WELCOME TO ADMIN LOGIN");
		boolean isLoggedIn = false;
		while (!isLoggedIn) {
			System.out.println("Enter Username : ");
			String uName = sc.next();
			System.out.println("Enter Password : ");
			String Pswd = sc.next();
			String UserName = uName;
			String Password = af.Admin_Authentication(UserName);
			if (uName.equalsIgnoreCase(UserName) && Pswd.equals(Password)) {
				isLoggedIn = true;
				System.out.println("Login successful!");
				while (true) {
					System.out.println("ADMIN LOGIN");
					System.out.println("1.Add New Admin");
					System.out.println("2.Add New Car Details");
					System.out.println("3.Update Car Details");
					System.out.println("4.Delete car Details");
					System.out.println("5.View All Car Details");
					System.out.println("6.View All Customer Details");
					System.out.println("7.View Rental Transaction");
					System.out.println("0.Log Out");
					int adminChoice = sc.nextInt();
					switch (adminChoice) {
					case 0:
						System.out.println("Exiting Admin Login System...");
						System.out.println("THANK YOU!");
						return;
					case 1: // Add New Admin
						System.out.println("ADD NEW ADMIN");
						System.out.println("Enter Admin's First Name : ");
						String FName = sc.nextLine();
						FName += sc.nextLine();
						System.out.println("Enter Admin's Last Name : ");
						String LName = sc.nextLine();
						LName += sc.nextLine();
						System.out.println("Enter Admin's User Name : ");
						String UName = sc.next();
						System.out.println("Enter Admin's Password : ");
						String Pwd = sc.next();
						int n = af.Add_Admin(FName, LName, UName, Pwd);
						String res = (n > 0) ? "New Admin Added" : "Try Again!";
						System.out.println(res);
						break;
					case 2: // Add New Car Details
						System.out.println("ADD NEW CAR DETAILS");
						System.out.println("Enter Car Name : ");
						String CName = sc.nextLine();
						CName += sc.nextLine();
						System.out.println("Enter Car Brand : ");
						String Brand = sc.nextLine();
						System.out.println("Enter Car Model : ");
						String Model = sc.nextLine();
						System.out.println("Enter Year of Manufacture : ");
						int Year = sc.nextInt();
						System.out.println("Enter Car Color : ");
						String Color = sc.nextLine();
						Color += sc.nextLine();
						System.out.println("Enter Car Rent Per Day : ");
						int PricePerDay = sc.nextInt();
						System.out.println("Enter Car Status['available','rented','maintenance'] : ");
						String Status = sc.next();
						int n1 = af.Add_CarDetails(CName, Brand, Model, Year, Color, PricePerDay, Status);
						String res1 = (n1 > 0) ? "New Car Detail Added" : "Try Again!";
						System.out.println(res1);
						break;
					case 3: // Update Car Details
						System.out.println("UPDATE CAR DETAILS");
						System.out.println("Enter Car ID : ");
						int Car_ID = sc.nextInt();
						System.out.println("Enter Car Status['available','rented','maintenance'] : ");
						String Status1 = sc.next();
						int n2 = af.Edit_CarDetails(Car_ID, Status1);
						String res2 = (n2 > 0) ? "Car Detail Updated" : "Try Again!";
						System.out.println(res2);
						break;
					case 4: // Delete car Details
						System.out.println("DELETE CAR DETAILS");
						System.out.println("Enter Car ID : ");
						int Car_ID1 = sc.nextInt();
						int n3 = af.Delete_CarDetails(Car_ID1);
						String res3 = (n3 > 0) ? "Car Detail Deleted" : "Try Again!";
						System.out.println(res3);
						break;
					case 5: // View All Car Details
						System.out.println("VIEW ALL CAR DETAILS");
						System.out.println(
								"Car_ID \s Car_Name \s Brand \s Model \s Year \s Color \s PricePerDay \s Status");
						af.View_CarDetails();
						break;
					case 6: // View All Customer Details
						System.out.println("VIEW ALL CUSTOMER DETAILS");
						System.out.println("Cus_ID \s First_Name \s Last_Name \s Gender \s Phone \s Address");
						af.View_CustomerDetails();
						break;
					case 7: // View Rental Transaction
						System.out.println("VIEW ALL RENTAL TRANSACTION DETAILS");
						System.out.println(
								"Rent_ID \s Car_ID \s Cus_ID \s Rental_Start_Date \s Rental_End_Date \s Total_days \s Total_Cost \s Status");
						af.View_RentalTransaction();
						break;
					default:
						System.out.println("Invaild Choice");
					}
				}
			} else {
				System.out.println("Invalid username or password. Please try again.");
			}
		}
	}

}
