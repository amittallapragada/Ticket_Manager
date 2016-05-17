//**********************************************************************************************************************
// Name: Amit Tallapragada
// Title: Assignment8.java
// Description: Acts as a menu for the user
// Time spent: 3 DAYS.
// Date:4/25/16
//**********************************************************************************************************************

import java.text.DecimalFormat;
import java.util.*;

public class Assignment8 {

	public static void main(String[] args) {
		int command;
		Scanner keyboard = new Scanner(System.in);
		TicketManager user = new TicketManager();
		printMenu();

		do {
			// ask a user to choose a command
			command = keyboard.nextInt();

			switch (command) {
			case 1:
				user.displaySeats();
				printMenu();
				break;
			case 2:
				System.out.print("Number of seats desired (1 - 30):");
				int numofseats = keyboard.nextInt();
				System.out.print("\nDesired row (1-15):");
				int rows = keyboard.nextInt();
				System.out.print("\nDesired starting seat number in the row:");
				int cols = keyboard.nextInt();
				if (user.requestTickets(rows, cols, numofseats)) {
					System.out
							.println("\nThe seats you have requested are available for purchase.");
					keyboard.nextLine();
					System.out.println("The total purchase price is "
							+ numofseats + " X " + "$" + user.getPrice(rows)
							+ " = " + (numofseats * user.getPrice(rows))
							+ "\nDo you wish to purchase these tickets (Y/N)?");
					String input = keyboard.nextLine().toLowerCase();
					if (input.equals("y")) {
						user.purchaseTicket(rows, cols, numofseats);
						System.out.print("Num Seats: " + numofseats + "\n");
						System.out
								.println("The price for the requested tickets is $ "
										+ (numofseats * user.getPrice(rows)));
						System.out.print("Please input the amount paid:$ ");
						String value = keyboard.nextLine();
						double payment = Double.parseDouble(value);
						System.out.println();
						user.printTickets(rows, cols, numofseats);
						System.out.println();
						System.out.println("Tickets purchased: " + numofseats);
						System.out.println("Payment: $ " + payment);
						System.out.println("Total ticket price: $ "
								+ (numofseats * user.getPrice(rows)));
						System.out
								.println("Change due : $ "
										+ (payment - (numofseats * user
												.getPrice(rows))));
						printMenu();
						break;
					} else {
						printMenu();
						break;
					}
				}

				break;
			case 3:
				user.displaySalesReport();
				printMenu();
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid input!");

			}

		} while (command != 4);

	} // end of the main method

	// this method prints out the menu to a user
	public static void printMenu() {
		System.out.println();
		System.out.print("\tASU Gammage Theater\n"
				+ "-----------------------------------\n"
				+ "1: View Available Seats\n" + "2. Request Tickets\n"
				+ "3. Display Theater Sales Report\n"
				+ "4. Exit the Program\n\n");

	} // end of the printMenu method

} // end of the Assignment7 class
