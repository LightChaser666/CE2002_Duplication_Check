package moblima;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import moblima.CinemaSeat.SeatOccupiedException;

public class CustomerModule {
	DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
	Scanner scanner;
	
	static int row, column;

	//Menu
public static void customerMain(Scanner scanner){
		
		int custChoice =0;
		
		while (custChoice!=8) {
		System.out.print(	"=====================Movie-goer Menu=====================\n"
							+ "1. Search/List Movie\n"
							+ "2. View movie details- View/Write Reviews\n"
							+ "3. Check seat availability\n"
							+ "4. Book and purchase tickets\n"
							+ "5. See my booking history\n"
							+ "6. List top 5 movies by sales\n"
							+ "7. List top 5 movies by rating\n"
							+ "8. Back\n"
							+ "=======================================================\n"
							+ "Please enter choice: \n");
			try{
				custChoice = scanner.nextInt();
				scanner.nextLine();
				
				switch (custChoice) {
				case 1:
					customerSearchAndListMovies(scanner);
					break;
				case 2:
					customerReviewMenu(scanner);
					break;
				case 3:
					customerCheckSeatAvailability(scanner);
					break;
				case 4:
					customerBuyProcess(scanner);
					break;
				case 5:
					customerShowHistory(scanner);
					break;
				case 6:
					MovieDatabase.printTop5Sales(scanner);
					break;
				case 7:
					MovieDatabase.printTop5Ratings(scanner);
					break;
				case 8 :
					System.out.println("Logout Successful");
					break;
				default:
					System.out.println("\n===========");
					System.out.println("Error Input!");
					System.out.println("===========\n");				
					break;
				}
			
			}
		
				catch(Exception e){
				e.printStackTrace();
				System.out.println("Invalid input. Please re-enter choice.");
				scanner.nextLine();
			}
		}
		
	}
	
private static void customerSearchAndListMovies(Scanner scanner) {
	
		int choice = 0;
		
		while (choice != 3) {
			try{
				System.out.println("=========Movie Menu=========\n"
								  +"1. View Movie List\n"
								  +"2. Search for movie\n"
								  +"3. Back\n"
								  +"=========================\n"
								  +"Enter choice:");
				choice = scanner.nextInt();
				scanner.nextLine();
				}catch(Exception e){
					System.out.println("Invalid input. Please re-enter choice.");
					scanner.nextLine();
			}
		
			switch(choice) {
			case 1:
				MovieDatabase.printFullMovieList();
				break;
			case 2:
				System.out.println("Please enter movie to search:");
				String tempMovie = scanner.nextLine();
				
				for (Movie m: MovieDatabase.getArrayList()) {				
					
					if (tempMovie.equals(m.getTitle())) {			
						System.out.println("Movie found!");
						MovieDatabase.printSingleMovieDetails(m);
						break;
					}
				}
				
				System.out.println("Movie not found.");
				break;
			case 3:
				break;
			default: 
				System.out.println("Invalid input entered. Please reenter choice.");
			}
		}
		
	}

private static void customerCheckSeatAvailability(Scanner scanner) {
	
	CineplexDatabase.printAllShowtimes();
	
	System.out.println("Which showtime do you want to check seat availability for?");
	
	boolean found = false;
	int showtimeIndex = 1;
	
	do {
		try {
			int choice = scanner.nextInt();
			if (choice > CineplexDatabase.showTimeCount()) {
				throw new ArrayIndexOutOfBoundsException();
			}
			System.out.println();
			
			for (Cineplex cp: CineplexDatabase.getArrayList()) {
				for (Cinema c: cp.getCinemaList()){
					for (CinemaShow cs: c.getCinemaShowList()) {
						
						if (choice == showtimeIndex) {
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							System.out.println("Movie: " + cs.getMovie().getTitle() + " on " + cs.getShowtime().format(DateTimeFormatter.ofPattern("dd-MM, HH:mm")));
							System.out.println("- - - - - - - - - - - - - - - - - ");
							
							cs.printSeating();
							found = true;
						} 
						showtimeIndex++;
								
					}
				}
			}
			
			showtimeIndex = 1;
		} catch (InputMismatchException IMe) {
		System.out.println("Invalid choice entered. Please re-enter choice.");
		scanner.nextLine();
		} catch (ArrayIndexOutOfBoundsException AOe) {
		System.out.println("No such showtime found, please re-enter choice.");
		scanner.nextLine();
		}
	
	} while(!found);
	
}

private static void customerReviewMenu(Scanner scanner) {
		//Have to list description of movie- function yet to implement
		while (MainMenuManager.choice != 4){
		try{
			System.out.println("=========Reviews=========\n"
							  +"1. Read reviews\n"
							  +"2. View overall reviewers' rating\n"
							  +"3. Add review\n"
							  +"4. Back\n"
							  +"=========================\n"
							  +"Do you want to read or add reviews?");
			MainMenuManager.choice = scanner.nextInt();
			scanner.nextLine();
			
			}catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid input. Please re-enter choice.");
			scanner.nextLine();
		}
		
		
			switch(MainMenuManager.choice){
			case 1: 
				customerReadReview(scanner);
				break;
			case 2: 
				customerPrintAverageRating(scanner);
				break;
			case 3: 
				MovieDatabase.addReview(scanner);
				break;
			}
		}
}

	//Process of buying
private static void customerBuyProcess(Scanner scanner) {
		
		int i = 1; //Choose
		int showtimeIndex;
		int cineplexIndex;
		int cinemaIndex;
		int age = 0;
		int mobileNum;
		String name;
		String emailAddress;
		String seat;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM : HH:mm");	
		
		
		System.out.println("===================================");
		System.out.println("===================================");
		System.out.println("======TICKET PURCHASE COUNTER======");
		System.out.println("===================================");
		System.out.println("===================================");
		
		//Get Name
		
		do {
			try{
				System.out.println("Enter Name:");
				name = scanner.nextLine();
				break;
				}catch(Exception e){
				System.out.println("Invalid input. Please re-enter choice.");
				scanner.nextLine();
			}
		} while (true);		
		
		//Get mobile number
		do {
			try{
				System.out.println("Enter Mobile Number:");
				mobileNum = scanner.nextInt();
				scanner.nextLine();
				break;
				}catch(Exception e){
				System.out.println("Invalid input. Please re-enter choice.");
				scanner.nextLine();
			}
		} while (true);
		
		//Get email address
		
		do {
			try{
				System.out.println("Enter email address:");
				emailAddress = scanner.nextLine();
				break;
				}catch(Exception e){
				e.printStackTrace();
				System.out.println("Invalid input. Please re-enter choice.");
				scanner.nextLine();
			}
		} while(true);
		
		//Get age
		
		do {
			try{
				System.out.println("Enter age:");
				age = scanner.nextInt();
				scanner.nextLine();
				break;
				}catch(Exception e){
				System.out.println("Invalid input. Please re-enter choice.");
				scanner.nextLine();
			}
		} while (true);
		
		//printCineplex choices********
		
		do {
			try {
		CineplexDatabase.printAllCineplexes();
		System.out.print("Select cineplex: ");	
		cineplexIndex = scanner.nextInt();
		scanner.nextLine();
		break;
			} catch (Exception e) {
				System.out.println("Invalid input. Please re-enter choice.");
			}
		
		} while(true);
		
		i = 1;
		System.out.println("\n===============================");
		for(Cinema c : CineplexDatabase.cineplexList.get(cineplexIndex-1).getCinemaList()){
			System.out.println(i + ".\t" + c.getCinemaID());
			System.out.println("===============================");
			i++;
		}
		
		do {
			try {		
				System.out.print("Select cinema: ");
				cinemaIndex = scanner.nextInt();
				scanner.nextLine();
				if (CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().size() == 0) {
					throw new InputMismatchException();
				}
				break;
			} catch (InputMismatchException IMe) {
				System.out.println("Cinema has no showing times. Please select another cinema.");
			} catch (Exception e) {
				System.out.println("Invalid input. Please re-enter choice.");
			}
		} while(true);
		
		i = 1;
		System.out.println("\n========Movie===================Movie Date and Time===");
		for(CinemaShow cs : CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList()){
			System.out.println(i + ".\t" + cs.getMovie().getTitle() + "			" + cs.getShowtime().format(formatter));
			System.out.println("======================================================");
			i++;
		}
		
		
		do {
			try {	
		System.out.print("Select showtime: ");
		showtimeIndex = scanner.nextInt();
		scanner.nextLine();
		break;
			} catch (Exception e) {
				System.out.println("Invalid input. Please re-enter choice.");
			}
		} while(true);

		//implement seat
		CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).printSeating();

		String rowString="";
		
		do {
			try{
				do {
					try {
						System.out.println("Select row:");
						rowString = scanner.nextLine();
						switch(rowString.toUpperCase()){
						case "A": row=1;
							break;
						case "B": row=2;
							break;
						case "C": row=3;
							break;
						case "D": row=4;
							break;
						case "E": row=5;
							break;
						case "F": row=6;
							break;
						case "G": row=7;
							break;
						case "H": row=8;
							break;
						default: 
							throw new Exception();
						}
						break;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Error input!");
					}
				} while (true);
					
				
				do {
					try {
						System.out.println("Select column:");
						column = scanner.nextInt();
						scanner.nextLine();
						break;
					} catch (Exception e) {
						System.out.println("Invalid input, re-enter column.");
					}
				} while (true);
						
				//if seat not taken
				if (CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).isSeatOccupied(column-1, row-1)){
					
					throw new SeatOccupiedException();
					//exit loop if seat is not taken
				}
				
				} catch (SeatOccupiedException e) {
					System.out.println("Seat is taken, please book another seat."); //prints seat is taken.
				} catch(Exception e){
					e.printStackTrace();
					System.out.println("Invalid input. Please re-enter choice.");
					scanner.nextLine();
				
				}
		} while(CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).isSeatOccupied(column-1, row-1));
			
		
			String tempColumn = Integer.toString(column);
			seat = rowString+ tempColumn  ;
			
			Ticket ticket;
			
			if(age < 12){
				ticket = new ChildTicket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
			}else if(age > 65){
				ticket = new ElderlyTicket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
			}else{
				ticket= new AdultTicket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
			}
			
			//need Edit this
			//Ticket ticket= new Ticket(seat, age, cinemaIndex, cineplexIndex, showtimeIndex, name, emailAddress, mobileNum);
			System.out.println("Price:" + ticket.calculateTicketPrice());
						
			System.out.println("Would you like to pay?: Y/N");
			
			
			do {
				
				try {
					String confirmBooking;
					confirmBooking = scanner.next();
					scanner.nextLine();
					
					switch(confirmBooking.toUpperCase()) {
					
					case "Y":
						System.out.println("Making payment...");		
		
						ticket.printTicket();
						TicketDatabase.add(ticket);
						
						Movie tempM = CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie();
						
						int index = 0;
						int movieIndex = 0;
						for (Movie m: MovieDatabase.getArrayList()) {
							if (tempM.getTitle().equals(m.getTitle())) {
								movieIndex = index;
								break;
							} else {
								index++;
							}
						}
						
						//adds ticket sale to the movie's total sales
						MovieDatabase.getArrayList().get(movieIndex).addTicketSale(ticket.calculateTicketPrice());
						//set seat to occupied
						CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).setSeat(column-1, row-1);
						
						System.out.println("You have paid for your ticket!");
						
						TicketDatabase.updateTickets();
						CineplexDatabase.updateCineplexes();
						MovieDatabase.updateMovies();
						
						break;
					case "N": 
						System.out.println("Going back to menu...");
						break;
					default:
						throw new InputMismatchException();
					}
					break;
				
				} catch (InputMismatchException IMe){
					System.out.println("Invalid input. Please enter again.");
					scanner.nextLine();
				}
			} while (true);
			
			
	}
	
	//need a way to read review
	private static void customerReadReview(Scanner scanner) {	
		MovieDatabase.printMovieTitles();
			
		do {
			try {
			System.out.println("Which movie's reviews do you want to read?");
			int movieIndex = scanner.nextInt();
			
			System.out.println("===Reviews for " + MovieDatabase.getArrayList().get(movieIndex-1).getTitle()+ "=============");
			MovieDatabase.getArrayList().get(movieIndex-1).printReviews();
			
			break;
			} catch (InputMismatchException IMe) {
				System.out.println("Invalid input. Try again");
			}
		} while (true);
	}
		
	private static void customerPrintAverageRating(Scanner scanner) {
			
		MovieDatabase.printFullMovieList();
		System.out.print("Which movie's average rating would you like to view?");	//ask which movie user wants to see avg rating of
		int choice = scanner.nextInt();
			
		MovieDatabase.getArrayList().get(choice-1).printAvgRating();
			
			
		}
	
	private static void customerShowHistory(Scanner scanner) {
			
			
		System.out.println("Enter your name to search for booking history:");
		
		
		//take in customer's mobile number
		String name;
		
		do {
			try {
				name = scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				
			}
		} while (true);
		
		if (TicketDatabase.getTicketArrayList().size()== 0) {
			System.out.println("No booking history found.");
			return;
		}
		
		ArrayList<Ticket> tempList = new ArrayList<Ticket>();	
		
		
		
		
		for (int i=0;i<TicketDatabase.getTicketArrayList().size();i++) {
			if (TicketDatabase.getTicketArrayList().get(i).getTicketholderName().equals(name)) {
				tempList.add(TicketDatabase.getTicketArrayList().get(i));
			}		
		}
		
		if (tempList.size() > 0) {
		
			for(int i=0;i <tempList.size()  ; i++) {
				if(tempList.get(i) != null){
				tempList.get(i).printTicket();		
					}
		
			}
		} else {
			System.out.println("No booking history found.");
		}
	}
	


}
