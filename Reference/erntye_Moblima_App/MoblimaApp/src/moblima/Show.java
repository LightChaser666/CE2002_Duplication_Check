package moblima;
import java.util.Calendar;
import java.util.Comparator;

import javax.swing.plaf.basic.BasicComboPopup.InvocationKeyHandler;
import javax.xml.crypto.dsig.CanonicalizationMethod;

import java.io.*;
/**
 * <code>Show</code> objects represents the different shows available for a particular <code>Movie</code>.
 * <code> Show</code> objects are stored under a particular <code> Cinema</code>'s <code>Show List</code>.
 * @see Cinema
 */
//change UML to include Date class and baseprice as static
public class Show implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8999338132604198550L;
	/**
	 * Stores the associated <code>Movie Title</code> of the <code>Show</code>.
	 */
	private String movie;
	/**
	 * Stores the associated <code>Date</code> & <code>Time<code> of the <code>Show</code>.
	 */
	private Calendar showTime;
	/**
	 * Stores the associated <code>Booked Seat Layout</code> of the <code>Show</code>, allowing customers to view available seats.
	 * <code>Seat Layout</code> takes the <code>Layout</code> of the <code> Cinema</code> which screens this particular <code>Show</code>. 
	 * @see Cinema
	 */
	private char[][] bookedLayout;
	/**
	 * Stores the <code>Base Price</code> of a <code> Show </code>.
	 */
	private static float basePrice;
	
	/**
	 * <code>Show</code> constructor.
	 * @param movie
	 * @param showTime
	 * @param cinemaLayout
	 */
	public Show(String movie,Calendar showTime,char[][] cinemaLayout){
		this.movie = movie;
		this.showTime = showTime;
		int columns = 0;
		int rows = 0;
		for (char[] row:cinemaLayout) {
			if (row[0] != '|') break;
			rows++;
		}
		this.bookedLayout = new char[rows][cinemaLayout.length];
		for(int i =0; i<rows;i++) {
			for(int j = 0; j<cinemaLayout.length;j++) {
				this.bookedLayout[i][j] = cinemaLayout[i][j];
			}
		}
//		for (char[] row:cinemaLayout) {
//			columns++;
//		}
//		System.out.println(columns);
//		System.out.println(cinemaLayout.length);
//		this.bookedLayout = new char[cinemaLayout.length][columns];
//		for(int i=0; i<cinemaLayout.length;i++) {
//			for(int j=0;j<columns;j++) {
//				this.bookedLayout[i][j] = cinemaLayout[i][j];
//			}
//		}
	}

	/**
	 * Allows <code>StaffAcc</code> to set the date & time of a <code>Show</code>.
	 * @see StaffCtr
	 */

	public void setTime(Calendar movieTiming){
		this.showTime = movieTiming;
	}
	/**
	 * @return <code>float</code> value of <code>Show</code>'s base price.
	 */
	public float getBasePrice() {
		return basePrice;
	}
	
	/**
	 * @return <code>float</code> value of <code>Show</code>'s base price. Method is static for saving in the database.
	 */
	public static float getBasePriceForSaving(){
		return basePrice;
	}
	
	/**
	 * Allows <code>StaffAcc</code> to set the base price of a <code>Show</code>.
	 * @see StaffCtr
	 */
	public static void setBasePrice(float newBasePrice){
		Show.basePrice = newBasePrice;
	}
	
	
	/**
	 * @return <code>Date</code> object containing the <code>Show</code>'s date & time.
	 */
	public Calendar getShowTime() {
		return showTime;
	}
	
	/**
	 * @return <code>String</code> object containing the <code>Show</code>'s date & time.
	 */
	public String getShowTimeString(){
		return String.format("%02d",showTime.get(Calendar.DAY_OF_MONTH)) + "/" + String.format("%02d",(showTime.get(Calendar.MONTH)+1)) + " " + 
				String.format("%02d",showTime.get(Calendar.HOUR_OF_DAY)) + ":"+ String.format("%02d",showTime.get(Calendar.MINUTE));
	}
	
	/**
	 * @return <code>String</code> object containing the <code>Show</code>'s movie title.
	 */
	public String getMovieTitle() {
		return movie;
	}
	/**
	 * Allows <code>StaffAcc</code> to set the movie Title of a <code>Show</code>.
	 * @see StaffCtr
	 */
	public void setMovieTitle(String movieTitle) {
		movie = movieTitle;
	}
	/**
	 * @return <code>char[][]</code> containing this <code>Show</code>'s booked layout.
	 */
	public char[][] getBookedLayout() {
		return bookedLayout;
	}
	

	/**
	 * @return <code>String</code> containing show type.
	 */
	public String getShowType() {
		return " ";
	}

	/**
	 * Checks if the customer's chosen seat has been booked.
	 * If it has been booked, then the booking fails.
	 * Otherwise, the chosen seat will be marked with 'X' in the <code>bookedLayout</code> array.
	 * @param seatNumber
	 * @return <code> boolean</code> value indicating success of seat booking.
	 * @see ShowBookingMgr
	 */

	public boolean setBookedLayout(String[] seatNumber) {
		int tempCol = Integer.parseInt(seatNumber[1]) - 1;
		int tempRow = Character.getNumericValue(seatNumber[0].charAt(0))-10;
		for (int i = 0; i<= tempCol; i++ ){
			if (bookedLayout[tempRow][i] == '|')
				tempCol++;
		}
		if(bookedLayout[tempRow][tempCol] == 'X') {
			return false;
		};

		bookedLayout[tempRow][tempCol] = 'X';

		return true;
	}
	
	/**
	 * Compares the show times between <code> Show</code> objects, which is used to sort them by date & time inside the <code>ArrayList&lt;Show&gt;</code> within the associated <code>Cinema</code> object.
	 * @see Cinema
	 */
	public static Comparator<Show> ShowTimeComparator = new Comparator<Show>() { // not sure how to represent in class diagram

		public int compare(Show s1, Show s2) {
		   Calendar showTime1 = s1.getShowTime();
		   Calendar showTime2 = s2.getShowTime();


		   return showTime1.compareTo(showTime2);
		   

		   
	    }
		
	
	};

	public boolean isWeekend() {
		if(showTime.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || showTime.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}else
			return false;
	}
}