package moblima;

import java.io.Console;

/**
 * Controller class for managing <code>CustAcc</code> operations. The class links up the user inputs from <code>ConsoleBoundary</code>
 * to the relevant objects.
 * <br>Customers can choose to book movies, add reviews, and view movies sorted by Ratings and Sales.
 * <p>Implements a Singleton structure. The instance can be called with <code>getInstance()</code>.
 * @see CustAcc
 */
public class CustCtr {
	int choice;
	
	//implement Singleton structure
	private static CustCtr instance;
	public static CustCtr getInstance() {
		if(instance == null) {
			instance = new CustCtr();
		}
		return instance;
	}
	
	public void custOperations(){
		do {
			choice = ConsoleBoundary.printCustPage();
			switch(choice) {
			case 1: ShowBookingMgr.getInstance().bookByMovie(); break;
			case 2: ConsoleBoundary.printAllMovieList().addReviews(ConsoleBoundary.printAddReview()); 
					DataBoundary.saveMovieList(MovieList.movieList);		
					break;
			case 3: ConsoleBoundary.printBookingHistory((CustAcc) LoginMgr.getInstance().loggedInAccount); break;
			case 4: MovieMgr.getInstance().top5Ratings(); break;
			case 5: MovieMgr.getInstance().top5Sales();break;
			}
		} while (choice != 0);
		ConsoleBoundary.printLogout();
	}
}
