package CRMS_Frontend;

import java.util.Scanner;

import CRMS_Backend.Customer_Functionalities;

public class Customer_Login {
	public void customer_Login() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Customer Page");
		while (true) {
			Customer_Functionalities cf = new Customer_Functionalities();
			System.out.println("CUSTOMER LOGIN");
			System.out.println("1.Add New Customer");
			System.out.println("2.List Available Cars");
			System.out.println("3.Rent A Car");
			System.out.println("4.Returning Rental Car");
			System.out.println("0.Log Out");
			int CustomerChoice = sc.nextInt();
			switch (CustomerChoice) {
			case 0:
				System.out.println("Exiting Customer Login System...");
				System.out.println("THANK YOU!");
				return;
			case 1: // Add New Customer
				System.out.println("ADD AS NEW CUSTOMER");
				System.out.println("Enter First Name : ");
				String FName = sc.nextLine();
				FName += sc.nextLine();
				System.out.println("Enter Last Name : ");
				String LName = sc.nextLine();
				System.out.println("Enter your Gender: ");
				String Gender = sc.next();
				System.out.println("Enter Mobile Number : ");
				long Phone = sc.nextLong();
				System.out.println("Enter Address : ");
				String Adress = sc.nextLine();
				Adress += sc.nextLine();
				int n = cf.Add_Admin(FName, LName, Gender, Phone, Adress);
				String res = (n > 0) ? "New Customer Detail Added" : "Try Again!";
				break;
			case 2: // List Available Cars
				System.out.println("LIST AVAILABLE CARS");
				System.out.println("Car_ID \s Car_Name \s Brand \s Model \s Year \s Color \s PricePerDay \s Status");
				cf.Available_Cars();
				break;
			case 3: // Rent A Car
				System.out.println("RENT A CAR");
				System.out.println("Enter Car ID : ");
				int Car_ID = sc.nextInt();
				System.out.println("Enter Customer ID : ");
				int Cus_ID = sc.nextInt();
				int n1 = cf.Taking_Rental(Car_ID, Cus_ID);
				String res1 = (n1 > 0) ? "Rented a Car Successfully" : "Try Again!";
				System.out.println(res1);
				break;
			case 4: // Returning Rental Car
				System.out.println("RETURNING RENTAL CAR");
				System.out.println("Enter Car ID : ");
				int Car_ID1 = sc.nextInt();
				int n2 = cf.Returning_Rental(Car_ID1);
				String res2 = (n2 > 0) ? "Returned a Car Successfully" : "Try Again!";
				System.out.println(res2);
				break;
			default:
				System.out.println("Invaild Choice");
			}
		}
	}

}
