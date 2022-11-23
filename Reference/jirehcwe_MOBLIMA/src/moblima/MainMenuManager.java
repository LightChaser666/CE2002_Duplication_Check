package moblima;

import java.util.InputMismatchException;

import java.util.Scanner;


public class MainMenuManager {
	
	public static int choice = 0;

	public static void main(String[] args) {
		
		
		
		Scanner scanner = new Scanner(System.in);
		
		fetchAllDatabasesFromFile();
		
		choice = 0;
		
			while(choice != 3){
				try {
				printMainMenu(scanner);
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input! Please enter choice from 1-3.");
					scanner.nextLine();
				}
			}
			
		System.out.println("Scanner closed. Program properly terminated.");
		scanner.close();
	}
	
	
	private static void printMainMenu(Scanner scanner){
		System.out.print("1. Admin\n"
				+ "2. Customer\n"
				+ "3. Exit\n"
				+ "Please enter choice: ");
		try{
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				AdminModule.Adminlogin(scanner);
				break;
			case 2:
				CustomerModule.customerMain(scanner);
				break;
			case 3:
				System.out.println("Program Terminating...");								// Ends program
				break;
			default:
				System.out.println("\n===========");
				System.out.println("Error Input!");
				System.out.println("===========\n");
				break;
			}
		}catch(Exception e){
			System.out.println("Invalid Input! Please enter choice from 1-3.");
			scanner.nextLine();
		}
	}	
	
	private static void fetchAllDatabasesFromFile() {
		//TicketDatabase.fetchTickets();
		TicketDatabase ticketDb = new TicketDatabase();
		ticketDb.fetchDatabase();
		
		//CineplexDatabase.fetchCineplexes();
		CineplexDatabase cineplexDb = new CineplexDatabase();
		cineplexDb.fetchDatabase();
		
		//MovieDatabase.fetchMovies();
		MovieDatabase movieDb = new MovieDatabase();
		movieDb.fetchDatabase();

		//PublicHolidayDatabase.fetchHolidays();
		PublicHolidayDatabase publicholidayDb = new PublicHolidayDatabase();
		publicholidayDb.fetchDatabase();
		
	}	
	
}
