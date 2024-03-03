package CRMS_Frontend;

import java.util.Scanner;

import CRMS_Backend.Admin_Functionalities;
import CRMS_Backend.Customer_Functionalities;

public class Log_in {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Admin_Login al = new Admin_Login();
		Customer_Login cl = new Customer_Login();
		while(true) {
			System.out.println("WELCOME TO CAR RENTAL MANAGEMENT SYSTEM");
			System.out.println("Select User Type to Login \n1.ADMIN \n2.CUSTOMER \n0.EXIT");
			int choice = sc.nextInt();
			switch (choice) {
			case 0:
				System.out.println("Exiting User Login System...");
				System.out.println("THANK YOU!");
				return;
			case 1:
				al.admin_Login();
				break;
			case 2:
				cl.customer_Login();
				break;
			default:
				System.out.println("Invalid Choice");
			}
		}
	}

}
