package moblima;
import java.util.Scanner;
import java.util.Calendar;
/**
 * The <code> Controller</code> class which manages the entire booking process for <code>CustAcc</code>.
 * <p>Implements a Singleton structure. The instance can be called with <code>getInstance()</code>.
 */
public class ShowBookingMgr {
	Scanner sc;
	/**
	 * Reset flag which causes the console to exit the booking screen and return to the main customer menu page.
	 * @see CustCtr
	 */
	private boolean reset = false;
	/**
	 * Stores the <code>Cineplex</code> that the customer has chosen. 
	 */
	private Cineplex cineplex;
	/**
	 * Stores the <code>Cinema</code> that the customer has chosen. 
	 */
	private Cinema cinema;
	/**
	 * Stores the <code>Show</code> that the customer has chosen. 
	 */
	private Show show;
	/**
	 * Stores the <code>Movie</code> that the customer has chosen. 
	 */
	private Movie movie;
	/**
	 * Stores the show type that the customer has chosen. 
	 */
	private String showType;
	/**
	 * Stores the seat number that the customer has chosen. 
	 */
	private String[] seatNumber = new String[2];
	/**
	 * Conditional flag to check if user wants to proceed to choose a <code>Show</code> for the selected <code>Movie</code>. 
	 */
	private boolean proceed;
	/**
	 * Conditional flag to check if user wants to confirm the booking for the chosen <code>Show</code> for the selected <code>Movie</code>. 
	 */
	private boolean confirm;
	/**
	 * Stores the ticket price for the selected <code>Show</code>. 
	 */
	private float price;
	
	
	//implement Singleton structure
	private static ShowBookingMgr instance;
	public static ShowBookingMgr getInstance() {
		if(instance == null) {
			instance = new ShowBookingMgr();
		}
		return instance;
	}
	
	public void setReset(boolean b) {
		this.reset = b;
	}
	
	/**
	 * Manages the movie booking process and displays ticket prices.
	 * Generates <code>Transaction</code> object and adds it into the <code>CustAcc</code>'s booking history.
	 * Adds ticket price onto the <code> Movie</code>'s total sales.
	 * <p> 1. Choose movie.
	 * <br> 2. View movie info.
	 * <br> 3. Choose cineplex.
	 * <br> 4. Choose cinema.
	 * <br> 5. Choose show date & time.
	 * <br> 6. Choose seat number.
	 * <br> 7. Confirm booking.
	 * <br> 8. Displays completed transaction.
	 */
	public void bookByMovie(){ //need to save seat booking into layout files
		do {reset = false;
			proceed = false; confirm = false;
			movie = ConsoleBoundary.printMovieList(); if(reset) break;
			proceed = ConsoleBoundary.printMovieInfo(movie); if(reset) break;
			if(proceed) cineplex = ConsoleBoundary.printChooseCineplex(); if(reset) break;
			cinema = ConsoleBoundary.printChooseCinema(cineplex); if(reset) break;
			cinema.sortShowsByTime();
			show = ConsoleBoundary.printShowsByMovie(movie, cinema); if(reset) break;
			price = cinema.calculatePrice(show,(CustAcc)LoginMgr.getInstance().loggedInAccount);
			char[][] bookedLayout = show.getBookedLayout();
			seatNumber = ConsoleBoundary.printLayout(bookedLayout); if(reset) break;
			confirm = ConsoleBoundary.printBookingConfirmation(price, cinema); if(reset) break;
			if(confirm && show.setBookedLayout(seatNumber)) {
				CustAcc c = (CustAcc) LoginMgr.getInstance().loggedInAccount;
				Transaction latest = new Transaction(cineplex, Calendar.getInstance(), movie.getTitle());
				movie.setSales(movie.getSales()+price);
				DataBoundary.saveMovieList(MovieList.movieList);
				c.transactionList.add(latest);
				DataBoundary.saveCineplexList(CineplexList.cineplexList);
				DataBoundary.saveCustList(LoginMgr.getInstance().getCustList());
				ConsoleBoundary.printTransaction(c,latest, show, showType, seatNumber, price); if(reset) break;
			} else if (!show.setBookedLayout(seatNumber)) {
				ConsoleBoundary.printInvalidSeat();
			}
			reset = true;
		} while (!reset);
		
	}
}
