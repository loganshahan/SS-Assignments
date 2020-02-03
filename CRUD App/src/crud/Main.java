/**
 * 
 */
package crud;

import java.util.Scanner;

public class Main {

	static Scanner sc;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sc = new Scanner(System.in);
		Main.menu();
		
		

	}
	public static void menu() {
		
		System.out.println("Welcome to SmoothStack Library Management System. Which category of user are you?");
		System.out.println("Select 1 for Author Services");
		System.out.println("Select 2 for Publisher Services");
		System.out.println("Select 3 for Book Services");
		
		//validates option for being between 1-3 from scanner
		int option = validInt(3);
		
			switch(option) {
			case 1:
				Author.menu();
				break;
			case 2:
				Publisher.menu();
				break;
			case 3:
				Books.menu();
				break;
			default:
				Main.menu();
			}
		
	}
	public static int validInt(int maxOption) {
		//scanner input validator to make sure number is one of options
		String userInput;
		int number = 0;
		boolean hasValidNumber =  false;
		do {
			try {
				userInput = sc.nextLine();
				number = Integer.parseInt(userInput);
				if (number>=1 && number<=maxOption) {
					hasValidNumber = true;
				} else {
					System.out.println("Please enter number in correct range");
				}
			}catch(NumberFormatException e) {
				System.out.println("Please enter valid option");
			}
		}while(!hasValidNumber);
		return number;	
	}
	
	
}

	
		
		

