package moblima;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;
import java.io.*;

/**
 * <code>Cinema</code> objects are part of a list in a <code>Cineplex</code> object. Each cinema contains details about the list of <code>Show</code> objects, 
 * its premium, and the seat layout in the form of a <code>char[][]</code> array.
 * <br>
 * Each <code> Cinema </code> contains an <code>ArrayList</code> of <Show> objects, as each <code>Cinema</code> has its unique
 * list of shows.
 * <p>
 * <code>Cinema</code> has 2 subclasses, <code>CinemaGold</code> and <code>CinemaPlatinum</code> which 
 * corresponds to the Gold Class and Platinum Class cinemas.
 * 
 * @see CinemaGold
 * @see CinemaMgr
 * @see CinemaPlatinum
 * @see Cineplex
 * @see Show
 */

public class Cinema implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1100625453212704609L;
	/**
	 * <code>ArrayList</code> of <code>Show</code> objects unique to the <code>Cinema</code> it is stored in.
	 * @see Show
	 */
	private ArrayList<Show> shows;
	/**
	 * Premium price related to the standard cinema type. Each cinema type (standard, gold class, platinum class) has its
	 * own premium.
	 */
	private static float premium;
	/**
	 * Name of the cinema
	 */
	private String name;
	/**
	 * Integer value of the number of rows and columns in the seat layout of this cinema.
	 */
	private int rows, columns;
	/**
	 * 2D character array which stores the blank layout of the cinema. Individual <code>Show</code> objects will take reference from this
	 * to create their own arrays to store the layout specific to the particular show, which displays available and booked seats.
	 * @see Show
	 */
	private char[][] seatLayout;




	/**
	 * Constructor for the <code>Cinema</code> class.
	 * <br>
	 * Creates 
	 * @param name Name of the cinema.
	 * @param layoutNumber Choice of layout. Each layout has a unique ID mapped to it, and is printed to the screen for the user to choose.
	 */
	public Cinema (String name, int layoutNumber)
	{
		this.shows = new ArrayList<Show>();
		this.name = name;
		this.seatLayout = CSVBoundary.getLayout(layoutNumber);
		
	}

	/**
	 * Calculates the price of the ticket that the customer pays.
	 * Takes into account the cinema premium and the base price from <code>Show</code>, as well as other price modifiers
	 * derived from customer's age or <code>Movie</code> type. Date price modifiers such as public holidays and weekend are in play as well.
	 * @param show The show for which the price is being quoted.
	 * @param custAcc The customer account sending in the booking request.
	 * @return a <code>float</code> containing the value of the calculated price.
	 */
	public float calculatePrice(Show show, CustAcc custAcc) { 
		float price = show.getBasePrice() + premium;
		if(PublicHolidayCalendar.isPubHol(show.getShowTime())) {
			price += PublicHolidayCalendar.getPublicHolidayPremium();
		}else if(show.isWeekend()) {
			price += 1.85f;
		}
		
		
		switch(custAcc.getAgeCat()) {
		case CHILD:
			price *= 0.25;
			break;
		case STUDENT:
			price *= 0.50;
			break;
		case ADULT:
			break;
		case SENIOR_CITIZEN:
			price *= 0.50;
			break;
		}
		
		return price;
	}
	
	/**
	 * 	Sets the premium for the Standard Cinema.
	 * @param newPremium <code>float</code> value of the new premium.
	 */
	public static void setPremium(float newPremium){
		Cinema.premium = newPremium;
	}
	
	/**
	 * Creates a <code>Show</code> object to be added to the <code>Cinema</code> object's list of shows. 
	 * @param movie A <code>String</code> containing the movie title.
	 * @param showTime A <code>Calendar</code> object which stores the date and time details of the show to be added.
	 * @param showType Type of show (3D, Digital, IMAX)
	 */
	public void createShow(String movie, Calendar showTime, String showType){
		switch(showType) {
		case "Digital":
			shows.add(new ShowDigital(movie,showTime, seatLayout.clone())); break;
		case "3D":
			shows.add(new Show3D(movie,showTime, seatLayout.clone())); break;
		case "IMAX":
			shows.add(new ShowIMAX(movie,showTime, seatLayout.clone())); break;
		}
	}
	
	/**
	 * Remove a <code>Show</code> from the show list.
	 * @param showToRemove <code>Show</code> object to remove.
	 */
	public void removeShow(Show showToRemove) {
		if(shows.remove(showToRemove)) {
			System.out.println("Show successfully removed!");
		}
	}
	
	/**
	 * Gets the value of the stored name as a <code>String</code>.
	 * @return <code>String</code> name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets a reference to the list of <code>Show</code> objects in this <code>Cinema</code>.
	 * @return Shows list.
	 */
	public ArrayList<Show> getShows() {
		return shows;
	}
	
	/**
	 * A call to the console boundary to print out the available <code>Show</code> objects, sorted by time.
	 */
	public void sortShowsByTime() {
		Collections.sort(shows, Show.ShowTimeComparator);
	}
	
	/**
	 * Gets the stored 2D array representing the layout of seats for the current show, which indicates available and booked seats.
	 * @return 2D Array representing the seat layout.
	 */
	public char[][] getSeatLayout() {
		return seatLayout;
	}
	
	/**
	 * Gets the premium for the <code>Cinema</code> instance.
	 * @return float <code>premium</code>
	 */
	public static float getPremium() {
		return premium;
	}
	
	public String printCinemaClass() {
		return "Standard Class";
	}
}
