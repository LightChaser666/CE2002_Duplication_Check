package moblima;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

import javax.security.auth.callback.ConfirmationCallback;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.print.Printable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

/**
 * <code>ConsoleBoundary</code> is a Boundary class which houses all of the console printing and scanning functionalities
 * needed in the Moblima App.
 * <p>This class is called by every method that needs to obtain user input or to print information. Individual methods within 
 * the Console Boundary class perform specific tasks, mostly to obtain user inputs and
 * return them as created objects or primitive data types.
 * <p> Any new method that requires printing or scanning from the console should call a method within
 * the <code>ConsoleBoundary</code> to perform the task.
 */
public class ConsoleBoundary {
	static Scanner sc = new Scanner(System.in);
		
	//called by MoblimaApp
	/**
	 * Prints out the Main Menu, asking if user wants to log in or to create an account.
	 * @return <code>int</code> Response of user where
	 * <br> 1: Log In. 2: Create Account. 3: Quit.
	 */
	public static int printMainMenu() {
		
		System.out.println("========================================");
		System.out.println("WELCOME TO  MOBLIMA");
		System.out.println("========================================");
		System.out.println("(1) Log In\n(2) Create Account\n(0) Quit");
		int choice = sc.nextInt(); 
		sc.nextLine();

		return choice;
	}
	
	//called by MoblimaApp
	/**
	 * Prints out the menu for creating a new account. Depending on user's choice for creating staff or customer accounts,
	 * this method will call the appropriate method in <code>LoginMgr</code> to add an <code>Account</code> object.
	 */
	public static void printAddAccount() {
		System.out.println("========================================");
		System.out.println("CREATE ACCOUNT");
		System.out.println("========================================");
		boolean loop = true;
		while(loop) {
			System.out.println("(1) Add Staff Account\n(2) Add Customer Account\n(3) Done");
			switch(sc.nextInt()) {
			case 1: sc.nextLine();LoginMgr.getInstance().addStaffAccount(); break;
			case 2: sc.nextLine();LoginMgr.getInstance().addCustAccount(); break;
			case 3: sc.nextLine();loop = false; break;
			}
		}
		
		System.out.println("Done adding accounts..");
		
	}
	
	//called by LoginMgr
	/**
	 * Prints out the login page which scans the username and password of the user.
	 * Returns the details as a <code>String</code> array.
	 * @return <code>String[]</code> where
	 * <br> index[0]: Account type - 1: Staff. 2: Customer.
	 * <br> index[1]: Username.
	 * <br> index[2]: Password.
	 */
	public static String[] printLoginPage() {	
		String[] temploginDetails = new String[3];
		System.out.println("========================================");
		System.out.println("LOGIN");
		System.out.println("========================================");
		System.out.println("Account Type:\n(1) Staff\n(2) Customer");
		temploginDetails[0] = sc.nextLine();
		System.out.println("Username: ");
		temploginDetails[1] = sc.nextLine();
		System.out.println("Password: ");
		temploginDetails[2] = sc.nextLine();
		System.out.println("Verifying...");
		return temploginDetails;
	}
	
	// called by LoginMgr
	/**
	 * Prints out the dialogue for the creation of Staff Account.
	 * The console scans the user inputs into a <code>String</code> array.
	 * @return <code>String[]</code> containing {Username, Password, Name}
	 */
	public static String[] printAddStaffAccount() {
		String[] accountDetails = new String[3];
		
		System.out.println("========================================");
		System.out.println("CREATE STAFF ACCOUNT");
		System.out.println("========================================");
		System.out.print("Enter username: ");
		accountDetails[0] = sc.nextLine();
		System.out.print("Enter password: ");
		accountDetails[1] = sc.nextLine();
		System.out.print("Enter your name: ");
		accountDetails[2] = sc.nextLine();
		
		 
		return accountDetails;
	}
	
	//called by LoginMgr
	/**
	 * Prints out the dialogue for the creation of Customer Account.
	 * The console scans the user inputs into a <code>String</code> array.
	 * @return <code>String[]</code> containing {Username, Password, Name, Mobile Number, Email Address, Age}
	 */
	public static String[] printAddCustAccount() {
		String[] accountDetails = new String[6];
		 
		System.out.println("========================================");
		System.out.println("CREATE CUSTOMER ACCOUNT");
		System.out.println("========================================");
		System.out.print("Enter username: ");
		accountDetails[0] = sc.nextLine();
		System.out.print("Enter password: ");
		accountDetails[1] = sc.nextLine();
		System.out.print("Enter your name: ");
		accountDetails[2] = sc.nextLine();
		System.out.print("Enter mobile number: ");
		accountDetails[3] = sc.nextLine();
		System.out.print("Enter email address: ");
		accountDetails[4] = sc.nextLine();
		System.out.print("Enter your age: ");
		accountDetails[5] = sc.nextLine();
		 
		return accountDetails;
	}
	
	//called by MovieMgr 
	/**
	 * Prints out dialogue for adding a movie, creates and returns a <code>Movie</code> object.
	 * @return <code>Movie</code> object created with user inputs.
	 */
	public static Movie printAddMovie() {
		 
		
		System.out.println("========================================");
		System.out.println("ADD MOVIE");
		System.out.println("========================================");
		System.out.println("Enter Title: ");
		String title = sc.nextLine();
		System.out.println("Enter Showing Status: ");
		System.out.println("1. Coming Soon \t 2. Preview \t 3. Now Showing \t 4. Ended");
		int temp = sc.nextInt();
		sc.nextLine();
		Movie.Showing_Status status;
		switch(temp) {
		case 1: status = Movie.Showing_Status.ComingSoon; break;
		case 2: status = Movie.Showing_Status.NowShowing; break;
		case 3: status = Movie.Showing_Status.NowShowing; break;
		case 4: status = Movie.Showing_Status.Ended; break;
		default: status = Movie.Showing_Status.ComingSoon;
		}
		System.out.println("Enter Synopsis: ");
		String synopsis = sc.nextLine();
		System.out.println("Enter Director: ");
		String director = sc.nextLine();
		System.out.println("Enter Number of cast: ");
		int castNumber = sc.nextInt();
		sc.nextLine();
		String[] cast = new String[castNumber];
		for(int i = 0; i < castNumber; i ++) {
			System.out.println("Enter Cast Number " + (i+1));
			cast[i] = sc.nextLine();
		}
		System.out.println("Enter Censorship Rating: ");//G,PG, PG13,NC16,M18,R21
		System.out.println("1. G \t 2. PG \t 3. PG13 \t 4. NC16 \t 5. M18 \t 6. R21");
		temp = sc.nextInt();
		sc.nextLine();
		Movie.Censorship_Rating censorR;
		switch(temp) {
		case 1: censorR = Movie.Censorship_Rating.G; break;
		case 2: censorR = Movie.Censorship_Rating.PG; break;
		case 3: censorR = Movie.Censorship_Rating.PG13; break;
		case 4: censorR = Movie.Censorship_Rating.NC16; break;
		case 5: censorR = Movie.Censorship_Rating.M18; break;
		case 6: censorR = Movie.Censorship_Rating.R21; break;
		default: censorR = Movie.Censorship_Rating.PG;
		}
		Movie movieToAdd = new Movie(title, status, synopsis, director, cast, censorR);
		
		 
		return movieToAdd;
	}
	
	//called by MovieMgr 
	/**
	 * Prints out dialogue for editing a movie. Returns the <code>Movie</code> object which the user wants to edit.
	 * Calls another method to let user choose the movie from a list of all movies to edit.
	 * @return <code>Movie</code> object to edit.
	 */
	public static Movie printEditMovie() {
		System.out.println("========================================");
		System.out.println("EDIT MOVIE");
		System.out.println("========================================");
		return printAllMovieList();
	}
	
	//called by MovieMgr
	/**
	 * Prints out dialogue for editing the <code>Movie</code> object passed in as an argument. Returns the updated object.
	 * Gets user inputs for the details to be updated, creates a new <code>Movie</code> object as returns it.
	 * @param movieToEdit <code>Movie</code> object to be edited.
	 * @return Edited <code>Movie</code> object.
	 */
	public static Movie printEditMovie2(Movie movieToEdit) {
		 
		Movie movieToReplace;
		System.out.println("Edit Showing Status");
		System.out.println("Current Showing Status: " + movieToEdit.getShowingStatus());
		System.out.println("Choose:\n1. Coming Soon \t 2. Preview \t 3. Now Showing \t 4. Ended\t 5. Skip");
		int temp = sc.nextInt();
		sc.nextLine();
		Movie.Showing_Status status;
		switch(temp) {
		case 1: status = Movie.Showing_Status.ComingSoon; break;
		case 2: status = Movie.Showing_Status.Preview; break;
		case 3: status = Movie.Showing_Status.NowShowing; break;
		case 4: status = Movie.Showing_Status.Ended; break;
		case 5: status = movieToEdit.getShowingStatus(); break;
		default: status = Movie.Showing_Status.ComingSoon;
		}
		System.out.println("Edit Censorship Rating");//G,PG, PG13,NC16,M18,R21
		System.out.println("Current Censorship Rating: " + movieToEdit.getCensorshipRating());
		System.out.println("1. G \t 2. PG \t 3. PG13 \t 4. NC16 \t 5. M18 \t 6. R21 \t 7. Skip");
		temp = sc.nextInt();
		Movie.Censorship_Rating censorR;
		switch(temp) {
		case 1: censorR = Movie.Censorship_Rating.G; break;
		case 2: censorR = Movie.Censorship_Rating.PG; break;
		case 3: censorR = Movie.Censorship_Rating.PG13; break;
		case 4: censorR = Movie.Censorship_Rating.NC16; break;
		case 5: censorR = Movie.Censorship_Rating.M18; break;
		case 6: censorR = Movie.Censorship_Rating.R21; break;
		case 7: censorR = movieToEdit.getCensorshipRating(); break;
		default: censorR = Movie.Censorship_Rating.PG;
		}
		movieToReplace = new Movie(movieToEdit.getTitle(), status, movieToEdit.getSynopsis(), movieToEdit.getDirector(), movieToEdit.getCast(), censorR);
		 
		return movieToReplace;
//		System.out.println("Edit Synopsis: ");
//		String synopsis = sc.nextLine();
//		System.out.println("Edit Director: ");
//		String director = sc.nextLine();
//		System.out.println("Edit Number of cast: ");
//		int castNumber = sc.nextInt();
//		sc.nextLine();
//		String[] cast = new String[castNumber];
//		for(int i = 0; i < castNumber; i ++) {
//			System.out.println("Enter Cast Number " + (i+1));
//			cast[i] = sc.nextLine();
//		}
	}
	
	//called by MovieMgr
	/**
	 * Prints out a list of up to 5 movies, sorted by the average ratings left by the customers.
	 */
	public static void printTopRatings() {
		if(MovieList.movieList.size()>=5) {
			for(int i = 0; i<5; i++) {
				if(MovieList.movieList.get(i).getReviewCount()==0) {
					System.out.println("Title: " + MovieList.movieList.get(i).getTitle() + "\t\t Average Rating: N/A");
				} else {
					System.out.println("Title: " + MovieList.movieList.get(i).getTitle() + "\t\t Average Rating: " + MovieList.movieList.get(i).getAverageRating());
				}
			}
		} else {
			for(int i = 0; i<MovieList.movieList.size(); i++) {
				if(MovieList.movieList.get(i).getReviewCount()==0) {
					System.out.println("Title: " + MovieList.movieList.get(i).getTitle() + "\t\t Average Rating: N/A");
				} else {
					System.out.println("Title: " + MovieList.movieList.get(i).getTitle() + "\t\t Average Rating: " + MovieList.movieList.get(i).getAverageRating());
				}
			}
		}
		
	}
	
	//called by MovieMgr
	/**
	 * Prints out a list of up to 5 movies, sorted by the sales generated by movie bookings of customers.
	 */
	public static void printTopSales() {
		if(MovieList.movieList.size()>=5) {
			for(int i = 0; i<5; i++) {
				System.out.println("Title: " + MovieList.movieList.get(i).getTitle() + "\t Sales: " + MovieList.movieList.get(i).getSales());
			}
		} else {
			for(int i = 0; i<MovieList.movieList.size(); i++) {
				System.out.println("Title: " + MovieList.movieList.get(i).getTitle() + "\t Sales: " + MovieList.movieList.get(i).getSales());
			}
		}
			
		}
	
	//called by MovieMgr
	/**
	 * Prints out the dialogue which allows the management of movies.
	 * Users can choose to add, remove, or edit a move, or to sort and display movies by their sales or ratings.
	 * @return <code>int</code> choice of operations.
	 */
	public static int printMovieOps() {
		 

		System.out.println("========================================");
		System.out.println("MANAGE MOVIES");
		System.out.println("========================================");
		System.out.println("(1) Add Movie");
		System.out.println("(2) Remove Movie");
		System.out.println("(3) Edit Movie");
		System.out.println("(4) Top 5 Movies By Sales");
		System.out.println("(5) Top 5 Movies By Ratings");
		System.out.println("(0) Done");
		int choice = sc.nextInt();
		sc.nextLine();
		
		 
		return choice;
	}
	
	//called by MovieMgr
	/**
	 * Prints out dialogue for user to choose the <code>Movie</code> object to be removed.
	 * @return <code>Movie</code> object to be removed.
	 */
	public static Movie printRemoveMovie() {
		System.out.println("========================================");
		System.out.println("REMOVE MOVIE");
		System.out.println("========================================");
		return printMovieList();
	}
	
	//called by ShowMgr
	/**
	 * Prints out the dialogue which allows for the management of shows.
	 * Users can choose to add, remove, or edit shows 
	 * @return <code>int</code> choice of operations.
	 */
	public static int printShowOps() {
		System.out.println("========================================");
		System.out.println("MANAGE SHOWS");
		System.out.println("========================================");
		 
		
		System.out.println("(1) Add Show");
		System.out.println("(2) Remove Show");
		System.out.println("(3) Edit Show");
		System.out.println("(0) Done");
		int choice = sc.nextInt();
		sc.nextLine();
		 
		return choice;
	}
	
	//called by ShowMgr
	/**
	 * Prints the dialogue which asks the user to choose the <code>Cinema</code> object to add a <code>Show</code> to.
	 * @return <code>Cinema</code> object to add <code>Show</code> to.
	 */
	public static Cinema printAddShow() {
		System.out.println("========================================");
		System.out.println("ADD SHOWS");
		System.out.println("========================================");
		System.out.println("Choose the Cineplex and Cinema you want to add the show to, then choose the movie and enter the show time.");
		Cineplex cineplex = printChooseCineplex();
		if(cineplex == null)
			return null;
		Cinema cinema = printChooseCinema(cineplex);
		return cinema;
	}
	
	//called by ShowMgr
	/**
	 * Prints the dialogue to get a Show Time from the user.
	 * Asks for the year, month, day, hour, and minute of the show that the user is creating or editing, and uses these details
	 * to create a <code>GregorianCalendar</code> object.
	 * @return <code>Calendar</code> object created with the <code>GregorianCalendar</code> constructor.
	 */
	public static Calendar getShowTime() {
		System.out.println("========================================");
		System.out.println("SHOW TIME");
		System.out.println("========================================");
		 
		System.out.println("Enter Year: ");
		int year = sc.nextInt(); sc.nextLine();
		System.out.println("Enter Month: ");
		int month = sc.nextInt();sc.nextLine();
		System.out.println("Enter Day: ");
		int day = sc.nextInt();sc.nextLine();
		System.out.println("Enter Hour: ");
		int hour = sc.nextInt();sc.nextLine();
		System.out.println("Enter Minutes: ");
		int minute = sc.nextInt();sc.nextLine();
		Calendar showTime = new GregorianCalendar(year, (month-1), day, hour, minute, 0);
		 
		return showTime;
	}
	/**
	 * Requests for user's choice of <code>Cineplex</code> & <code>Cinema</code>.
	 * @return <code>Cinema</code> from which the <code>Show</code> is to be removed.
	 */
	//called by ShowMgr
	public static Cinema printRemoveShow() {
		System.out.println("========================================");
		System.out.println("REMOVE SHOWS");
		System.out.println("========================================");
		System.out.println("Choose the Cineplex and Cinema you want to remove the show from, then select the show.");
		Cinema cinema = printChooseCinema(printChooseCineplex());
		return cinema;
	}
	/**
	 * Requests for user's choice of <code>Cineplex</code> & <code>Cinema</code>.
	 * @return <code>Cinema</code> from which the <code>Show</code> is to be edited.
	 */
	//called by ShowMgr
	public static Cinema printEditShow() {
		System.out.println("========================================");
		System.out.println("EDIT SHOWS");
		System.out.println("========================================");
		System.out.println("Choose the Cineplex and Cinema, followed by the show you want to edit.");
		Cinema cinema = printChooseCinema(printChooseCineplex());
		return cinema;
	}
	/**
	 * Requests user input for the <code>Show</code> details to be edited.
	 * @param show
	 */
	//called by ShowMgr
	public static void printEditShowDetails(Show show) {
		System.out.println("========================================");
		System.out.println("EDIT SHOW");
		System.out.println("========================================");
		 
		
		System.out.println("Change Movie");
		System.out.println("Current Movie: " + show.getMovieTitle());
		System.out.println("1. Change Movie\n2. Skip\n");
		int choice = sc.nextInt();
		sc.nextLine();
		if(choice == 1) {
			Movie movieToChange = printMovieList();
			show.setMovieTitle(movieToChange.getTitle());
		}
		
		System.out.println("Change Show Time");
		System.out.println("Current Show Time: " + show.getShowTimeString());
		System.out.println("1. Change Show Time\n2. Skip\n");
		choice = sc.nextInt();sc.nextLine();
		if(choice == 1) {
			show.setTime(getShowTime());
		}
		
		 
	}
	/**
	 * Displays all available <code>Show</code>s in the particular <code>Cinema</code>.
	 * @param cinema
	 * @return customer's chosen <code>Show</code>. 
	 */
	//called by ShowMgr
	public static Show printShowList(Cinema cinema) {
		System.out.println("========================================");
		System.out.println("CHOOSE SHOW");
		System.out.println("========================================");
		 
		ArrayList<Show> tempShows = new ArrayList<Show>();
		for(int i = 0; i<cinema.getShows().size();i++) {
			System.out.print("(" + (i+1) + ") ");
			System.out.print(cinema.getShows().get(i).getMovieTitle() + " ");
			System.out.print(cinema.getShows().get(i).getShowType() + " ");
			System.out.println(cinema.getShows().get(i).getShowTimeString());
			tempShows.add(cinema.getShows().get(i));
		}
		System.out.println("(0) Cancel ");
		System.out.print("Enter Choice: ");
		int choice = sc.nextInt();
		if(choice==0) return null;
		sc.nextLine();
		 
		return tempShows.get(choice-1);
	}
	/**
	 * Displays all available options under <code>CineplexMgr</code>.
	 * @return <code>int</code> indicating user's chosen menu choice.
	 */
	//called by CineplexMgr
	public static int printCineplexOps() {
		 
		int choice = 0;
		System.out.println("========================================");
		System.out.println("CINEPLEX OPS");
		System.out.println("========================================");
		System.out.println("What would you like to do:");
		System.out.println("(1) Add Cineplex\n(2) Remove Cineplex\n(0) Done");
		choice = sc.nextInt();
		sc.nextLine();
		 
		return choice;
	}
	/**
	 * Asks user for the name of the new <code>Cineplex</code> and its code.
	 * @return <code>String[]</code> containing name of <code>Cineplex</code> and its code.
	 */
	//called by CineplexMgr
	public static String[] printAddCineplex() {
		 
		String[] cineplexDetails = new String[2];
		System.out.println("========================================");
		System.out.println("ADD CINEPLEX");
		System.out.println("========================================");
		System.out.print("Enter name of cineplex: ");
		cineplexDetails[0] = sc.nextLine();
		System.out.print("Enter 3 letter code of cineplex: ");
		cineplexDetails[1] = sc.nextLine();
		 
		return cineplexDetails;
	}
	/**
	 * Asks user which <code>Cineplex</code> to remove.
	 * @return <code>Cineplex</code> to remove.
	 */
	//called by CineplexMgr
	public static Cineplex printRemoveCineplex() {
		System.out.println("========================================");
		System.out.println("REMOVE CINEPLEX");
		System.out.println("========================================");
		System.out.println("Choose the Cineplex you wish to remove.");
		return printChooseCineplex();
	}
	
	/**
	 * Displays all available options under <code>CinemaMgr</code>.
	 * @param cineplexName
	 * @return <code>int</code> indicating user's chosen menu choice.
	 */
	//called by CinemaMgr
	public static int printCinemaOps(String cineplexName) {
		 
		int choice = 0;
		System.out.println("========================================");
		System.out.println("MANAGE CINEMAS");
		System.out.println("========================================");
		System.out.println("You are managing cinemas in cineplex " + cineplexName);
		System.out.println("What would you like to do:");
		System.out.println("(1) Add Cinema\n(2) Remove Cinema\n(0) Done");
		choice = sc.nextInt();
		sc.nextLine();
		 
		return choice;
	}
	/**
	 * Requests user to input info for the new <code>Cinema</code> object such as name, layout, and class.
	 * @param cineplexName
	 * @return <code>String[]</code> containing information such as <code>Cinema</code> name, layout and class.
	 */
	//called by CinemaMgr
	public static String[] printAddCinema(String cineplexName) {
		 
		String[] cinemaDetails = new String[3];
		
		System.out.println("========================================");
		System.out.println("ADD CINEMA");
		System.out.println("========================================");
		System.out.println("You are adding a cinema into the cineplex " + cineplexName);
		System.out.print("Enter Cinema Name: ");
		cinemaDetails[0] = sc.nextLine();
		System.out.println("Now choose the cinema layout");
		CSVBoundary.printAllLayout();
		System.out.print("Enter your layout choice: ");
		cinemaDetails[1] = sc.nextLine();
		System.out.println("Choose the Cinema Class");
		System.out.print("(1) Normal\n(2) Gold Class\n(3) Platinum Class\n");
		cinemaDetails[2] = sc.nextLine();
		
		 
		return cinemaDetails; //index 0: name; index 1: layout number; index 2: cinema class
	}
	
	/**
	 * Displays all available <code>Cinema</code>s in the particular <code>Cineplex</code>.
	 * @param cineplex
	 * @return <code>Cinema</code> to be removed from the particular <code>Cineplex</code>.
	 */
	//called by CinemaMgr
	public static Cinema printRemoveCinema(Cineplex cineplex) {
		System.out.println("========================================");
		System.out.println("REMOVE CINEMA");
		System.out.println("========================================");
		System.out.println("You are removing a cinema from the cineplex " + cineplex.getName());
		return printChooseCinema(cineplex);
	}
	/**
	 * Requests user to choose the cinema class, and enter its new price premium value.
	 * @return <code>float[]</code> containing the chosen cinema class, and its new price premium. 
	 */
	//called by CinemaMgr
	public static float[] printChangePremium() {
		float[] premiumDetails = new float[2];
		System.out.println("========================================");
		System.out.println("CHANGE CINEMA PREMIUM");
		System.out.println("========================================");
		System.out.println("Which type of cinema are you editing?");
		System.out.println("(1) Normal\n(2) Gold Class\n(3) Platinum Class\n");
		premiumDetails[0] = sc.nextFloat(); sc.nextLine();
		System.out.println("What is the new premium value?");
		premiumDetails[1] = sc.nextFloat(); sc.nextLine();
		
		return premiumDetails;
	}
	
	/**
	 * Shows all available options once the staff logs in the <code>StaffAcc</code>.
	 * @return <code>int</code> indicating staff's chosen menu choice.
	 */
	//called by StaffCtr
	public static int printStaffPage() {
		 
		int choice = 0;
		System.out.println("========================================");
		System.out.println("STAFF PAGE");
		System.out.println("========================================");
		System.out.println("What would you like to do:");
		System.out.println("(1) Modify Movies\n(2) Modify Shows\n(3) Modify System Settings\n(4) Modify Cineplexes\n(5) Modify Cinemas\n(0) Logout");
		choice = sc.nextInt(); sc.nextLine();
		 
		return choice;
	}
	
	
	//unsorted
	/**
	 * Shows all available options once the customer logs in the <code>CustAcc</code>.
	 * @return <code>int</code> indicating customer's chosen menu choice.
	 */
	public static int printCustPage() {
		 
		int choice = 0;
		System.out.println("========================================");
		System.out.println("CUSTOMER PAGE");
		System.out.println("========================================");
		System.out.println("What would you like to do:\n(1) Book A Show\n(2) Add a Review\n(3) View Booking History\n(4) List Top 5 Movie By Ratings\n(5) List Top 5 Movie By Sales\n(0) Logout");
		choice = sc.nextInt(); sc.nextLine();
		 
		return choice;
	}
	/**
	 * Requests user input for <code> Movie</code> rating and review.
	 * @return <code>Reviews</code> object from the customer's review.
	 */
	public static Reviews printAddReview() {
		 
		int rating = 0;
		String review, reviewer;
		System.out.println("========================================");
		System.out.println("ADD A REVIEW");
		System.out.println("========================================");
//		System.out.println("CHOOSE MOVIE TO REVIEW");
//		for(int i = 0; i < MovieList.movieList.size(); i++) {
//			System.out.println(i+1 + ") " + MovieList.movieList.get(i).getTitle() + "\n");
//		}
//		choice = sc.nextInt();
//		Movie movie = MovieList.movieList.get(choice-1);
		System.out.println("Enter Rating (1-5): ");
		rating = sc.nextInt(); sc.nextLine();
		System.out.println("Enter Review: ");
		review = sc.nextLine();
		System.out.println("Enter Name: ");
		reviewer = sc.nextLine();
		Reviews reviews = new Reviews(review, rating, reviewer);
		 
		return reviews;
	}
	
	/**
	 * Displays the <code>CustAcc</code>'s booking history. 
	 * @param c
	 */
	public static void printBookingHistory(CustAcc c) {
		if (c.getTransactionList().size() == 0)
			System.out.println("No Bookings Found");
		for(int i = c.getTransactionList().size()-1; i>=0;i--) {
			System.out.println("Transaction ID: " + c.getTransactionList().get(i).getTid() + "\n\tDate & Time: " + c.getTransactionList().get(i).getDate() + "\n\tMovie Title:" + c.getTransactionList().get(i).getMovieTitle());
		}
	}
	/**
	 * Displays the available operations within <code>SystemSettingMgr</code> and requests for user input.
	 * @return <code>int</code> indicating the chosen menu choice.
	 */
	public static int printSystemSettingMenu() {
		 
		int choice = 0;
		System.out.println("========================================");
		System.out.println("SYSTEM SETTINGS");
		System.out.println("========================================");
		System.out.println("What would you like to do:\n(1) Change Ticket Base Price of: Show \n(2) Change Ticket Base Price of: 3D Show \n(3) Change Ticket Base Price of: Digital Show \n(4) Change Ticket Base Price of: IMAX Show\n(5) Add Public Holiday\n(0) Done");
		choice = sc.nextInt(); sc.nextLine();
		 
		return choice;
	}
	
	/**
	 * Asks user for the new ticket base price.
	 * @return <code>float</code> indicating the price to set as the <code>Show</code>'s new base price. 
	 */
	public static float askBasePrice() {
		 
		float basePrice = 0;
		System.out.println("========================================");
		System.out.println("CHANGE TICKET BASE PRICE");
		System.out.println("========================================");
		System.out.println("Enter New Ticket Base Price: ");
		basePrice = sc.nextFloat(); sc.nextLine();
		 
		return basePrice;
	}
	
	/**
	 * Asks the staff to input the date to set a public holiday.
	 * @return <code>Calendar</code> object indicating the date to set as a public holiday.
	 */
	public static Calendar askPubHol() {
		 
		System.out.println("========================================");
		System.out.println("ADD PUBLIC HOLIDAY");
		System.out.println("========================================");
		System.out.println("Enter New Public Holiday Date");
		System.out.print("Enter Day:");
		int day = sc.nextInt(); sc.nextLine();
		System.out.print("Enter Month:");
		int month = sc.nextInt() - 1 ; sc.nextLine();
		System.out.print("Enter Year:");
		int year = sc.nextInt(); sc.nextLine();
		Calendar date = new GregorianCalendar(year,month,day);
		 
		return date;
	}
	/**
	 * Prints all movies in <code>MovieList</code> that are either <code>PREVIEW</code> or <code>NOWSHOWING</code>.
	 * @return <code>Movie</code> indicating chosen movie.
	 */
	public static Movie printMovieList() {

		System.out.println("========================================");
		System.out.println("MOVIE LIST");
		System.out.println("========================================");
		int choice = 0;
		int count = 1;
		ArrayList<Movie> temp = new ArrayList<Movie>();
		for(int i = 0; i < MovieList.movieList.size(); i++) {
			if(MovieList.movieList.get(i).getShowingStatus() != Movie.Showing_Status.Ended) {// && MovieList.movieList.get(i).getShowingStatus() != Movie.Showing_Status.ComingSoon) {
				temp.add(MovieList.movieList.get(i));
				System.out.println("("+count + ") " + MovieList.movieList.get(i).getTitle() + " Status: " + MovieList.movieList.get(i).getShowingStatus() +"\n");
				count++;
			}
		}
		boolean loop = false;
		do {
			System.out.println("Choose a movie: ");
			choice = sc.nextInt(); sc.nextLine();
			if(temp.get(choice-1).getShowingStatus() == Movie.Showing_Status.ComingSoon) {
				System.out.println("The Movie is coming soon but not yet available at this point in time. Sorry!");
				loop = true;
			}else
				loop = false;
		}while(loop);

		return temp.get(choice-1);
	}
	
	/**
	 * Prints all movies in <code>MovieList</code> regardless of showing status.
	 * @return <code>Movie</code> indicating chosen movie.
	 */
	public static Movie printAllMovieList() {
		 
		System.out.println("========================================");
		System.out.println("MOVIE LIST");
		System.out.println("========================================");
		int choice = 0;
		int count = 1;
		for(int i = 0; i < MovieList.movieList.size(); i++) {
				System.out.println("(" + count + ") " + MovieList.movieList.get(i).getTitle() + "\n");
				count++;
		}
		System.out.println("Choose a movie: ");
		choice = sc.nextInt(); sc.nextLine();
		 
		return MovieList.movieList.get(choice-1);
	}
	
	/**
	 * Displays information about the selected <code>Movie</code>.
	 * @param movie
	 * @return <code>boolean</code> indicating if customer wishes to proceed with booking a <code>Show</code> for the selected <code>Movie</code>.
	 */
	public static boolean printMovieInfo(Movie movie) {
		 
		System.out.println("========================================");
		System.out.println(movie.getTitle().toUpperCase() );
		System.out.println("========================================");
		System.out.println("Movie Title: " + movie.getTitle());
		System.out.println("Showing Status: " + movie.getShowingStatus());
		System.out.println("Synopsis: " + movie.getSynopsis());
		System.out.println("Director: " + movie.getDirector());
		System.out.print("Cast: " + movie.getCast()[0]);
		for(int i = 1; i<movie.getCast().length; i++) {
			System.out.print(", " + movie.getCast()[i]);
		}
		System.out.println();
		if(movie.getReviewCount()==0) {
			System.out.println("Average Rating: N/A");
		} else {
			System.out.println("Average Rating: " + movie.getAverageRating());
			System.out.println("Past Reviews: ");
			for(int i = 0; i<movie.getReviews().size();i++) {
				System.out.println("Reviewer: " + movie.getReviews().get(i).getReviewer());
				System.out.println("\tRating: " + movie.getReviews().get(i).getRating());
				System.out.println("\tReview: " + movie.getReviews().get(i).getContent());
			}
		}
		
		System.out.println("(1) Proceed to book tickets\n(0) Cancel");
		int choice = sc.nextInt(); sc.nextLine();
		if(choice==1) {
			 
			return true;
		} else if (choice == 0) {
			ShowBookingMgr.getInstance().setReset(true);
		} 
		 
		return false;
		
	}
	/**
	 * Prints all available <code>Cineplex</code> and requests for user input.
	 * @return <code>Cineplex</code> indicating chosen cineplex.
	 */
	public static Cineplex printChooseCineplex() {
		 
		System.out.println("========================================");
		System.out.println("CHOOSE CINEPLEX");
		System.out.println("========================================");
		for(int i = 0 ; i < CineplexList.cineplexList.size();i++) {
			System.out.println("("+ (i+1) + ") " + CineplexList.cineplexList.get(i).getName());
		}
		System.out.println("(0) Cancel");
		int choice = sc.nextInt(); sc.nextLine();
		if(choice == 0) {
			ShowBookingMgr.getInstance().setReset(true);
			 
			return null;
		} else {
			 
			return CineplexList.cineplexList.get(choice-1);
		}
		
	}
	/**
	 * Displays all available <code>Cinema</code>s in a particular <code>Cineplex</code> and requests for user input.
	 * @param cineplex
	 * @return <code>Cinema</code> indicating chosen cinema. 
	 */
	public static Cinema printChooseCinema(Cineplex cineplex) {
		 
		System.out.println("========================================");
		System.out.println("CHOOSE CINEMA");
		System.out.println("========================================");
		for(int i = 0 ; i < cineplex.getCinemaList().size();i++) {
			System.out.println("("+ (i+1) + ") Cinema " +  cineplex.getCinemaList().get(i).getName()+ "\t\tClass: " + cineplex.getCinemaList().get(i).printCinemaClass());
		}
		System.out.println("(0) Cancel");
		int choice = sc.nextInt(); sc.nextLine();
		if(choice == 0) {
			ShowBookingMgr.getInstance().setReset(true);
			 
			return null;
		} else {
			 
			return cineplex.getCinemaList().get(choice-1);
		}
	}
	/**
	 * Prints current available show types and requests for user input.
	 * @return <code>String</code> indicating chosen show type.
	 */
	public static String printChooseShowType() {
		 
		System.out.println("========================================");
		System.out.println("CHOOSE SHOW TYPE");
		System.out.println("========================================");
		System.out.println("(1) Digital\n(2) 3D\n(3) IMAX\n(0) Cancel");
		int choice = sc.nextInt(); sc.nextLine();
		String choiceS = null;
		switch(choice){
			case 1: choiceS = "Digital"; break;
			case 2: choiceS = "3D"; break;
			case 3: choiceS = "IMAX"; break;
			case 0: choiceS = null; break;
		}
		 
		return choiceS;
	}
	/**
	 * Prints all available <code>Show</code>s for the chosen <code>Movie</code> and <code>Cinema</code>.
	 * @param movie
	 * @param cinema
	 * @return The chosen <code>Show</code>.
	 */
	// Called by MovieBookingMgr
	public static Show printShowsByMovie(Movie movie, Cinema cinema) {
		ArrayList<Show> tempShows = new ArrayList<Show>();
		 
		int count = 1;
		System.out.println("========================================");
		System.out.println("CHOOSE SHOW TIME");
		System.out.println("========================================");
		System.out.println("Show Times:\n");
		for(int i = 0; i<cinema.getShows().size();i++) {
			if(cinema.getShows().get(i).getMovieTitle().equals(movie.getTitle())) {
				tempShows.add(cinema.getShows().get(i));
				System.out.println("(" + count + ") " + cinema.getShows().get(i).getShowTimeString()+ " (" + cinema.getShows().get(i).getShowType() + ")");
				count++;
			}
		}
		if(tempShows.size()==0){
			System.out.println("No Shows Available");
			ShowBookingMgr.getInstance().setReset(true);
			 
			return null;
		}
		int choice = sc.nextInt(); sc.nextLine();
		 
		return tempShows.get(choice-1);
	}
	
	/**
	 * Displays the seats availability for the chosen show and requests for the desired seat number.
	 * @param bookedLayout
	 * @return <code>String[]</code> indicating the chosen seat number.
	 */
	// Called by MovieBookingMgr
	public static String[] printLayout (char[][] bookedLayout) {
		 
		String[] seatNumber = new String[2];
		System.out.println("========================================");
		System.out.println("CHOOSE SEAT");
		System.out.println("========================================");
		int column = 1;
		System.out.print("  ");
		for (int i=0; i<bookedLayout[1].length; i++) {
			if (bookedLayout[1][i] != '|')
				System.out.format("%-3d", (column++));
			else
				System.out.print("   ");
		}
		System.out.println();
		int i = 0;
		for (char[] row:bookedLayout) {
			if (row[0] != '|') break;
			System.out.print(String.valueOf((char)(i+65)));
			for (int j=0; j<row.length; j++) {
				if (row[j] == '_')
					System.out.print("[ ]");
				else if (row[j] == 'X')
					System.out.print("[X]");
				else if	(row[j] == '|')
					System.out.print("   ");
			}
			System.out.println();
			i++;
		}
		
		System.out.println("Enter choice of row:");
		seatNumber[0] = sc.nextLine();
		System.out.println("Enter choice of column:");
		seatNumber[1] = sc.nextLine();

		return seatNumber;
	}
	
	/**
	 * Displays ticket price and asks for booking confirmation.
	 * @param price
	 * @param cinema
	 * @return <code>Boolean</code> indicating if the user confirms the booking.
	 */
	public static boolean printBookingConfirmation(Float price, Cinema cinema) {
		 
		System.out.println("========================================");
		System.out.println("CONFIRM BOOKING?");
		System.out.println("========================================");
		System.out.println("Price: " + price);
		System.out.println("(1) Confirm\n(2) Cancel");
		int choice = sc.nextInt(); sc.nextLine();
		if(choice==1) {
			 
			return true;
		} else {
			ShowBookingMgr.getInstance().setReset(true);
			 
			return false;
		}
	}
	/**
	 * Prints <code>Transaction</code> details.
	 * @param c
	 * @param latest
	 * @param show
	 * @param showType
	 * @param seatNumber
	 * @param price
	 */
	// Called by MovieBookingMgr & CustCtr
	public static void printTransaction(CustAcc c,Transaction latest, Show show,String showType,String[] seatNumber, float price) {
		System.out.println("========================================");
		System.out.println("BOOKING CONFIRMED!");
		System.out.println("========================================");
		System.out.println("Transaction ID: " + latest.getTid());
		System.out.println("Name: " + c.getName());
		System.out.println("Email: " + c.getEmail());
		System.out.println("Movie Title: " + show.getMovieTitle());
		System.out.println("Show Time: " + show.getShowTimeString());
		System.out.println("Seat Number : " + seatNumber[0] + seatNumber[1]);
		System.out.println("Price: " + price);
	}
	
	/**
	 * Prints out log out message.
	 */
	public static void printLogout() {
		System.out.println("========================================");
		System.out.println("GOOD BYE! SEE YOU SOON!");
		System.out.println("========================================");
	}
	
	/**
	 * Displays the seat arrangements of a chosen layout.
	 * @param seatLayout
	 * @param height
	 * @param width
	 * @param choice
	 */
	//called by CSV Boundary
	public static void printEmptyLayout(char[][] seatLayout, int height, int width, int choice) {
		System.out.println(choice + ". Layout "+choice);
		for(int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
			System.out.print(seatLayout[i][j]);
			System.out.print(" ");
			}
			System.out.println();
		}
	}
	/**
	 * Indicates when a customer choose a seat which is already booked. 
	 */
	//Called by MovieBookingMgr
	public static void printInvalidSeat() {
		System.out.println("The seat you chose is invalid!");
	}
	
	/**
	 * Shows "Terminating..." when exiting application.
	 */
	//Called by MoblimaApp
	public static void printTerminate() {
		System.out.println("Terminating...");
	}
}