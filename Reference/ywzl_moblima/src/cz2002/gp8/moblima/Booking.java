package cz2002.gp8.moblima;

/**
 * Represents a Booking for a Showtime
 * Exists in a collection of Bookings for a Showtime
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Booking {
	/**
	 * The name of the person that booked
	 */
	private String name;
	
	/**
	 * The mobile no. of the person that booked
	 */
	private int mobile;
	
	/**
	 * The email of the person that booked
	 */
	private String email;
	
	/**
	 * The index of the Showtime that this booking belongs to
	 */
	private int showtimeIndex;
	
	/**
	 * The index of the Cineplex the Showtime for this Booking belongs to
	 */
	private int cineplexIndex;
	
	/**
	 * The transaction ID of the booking, generated with the Movie Title and the time of booking
	 */
	private String TID;
	
	/**
	 * The Ticket that this booking is for, determines the price of the booking
	 */
	private Ticket ticketType;
	
	/**
	 * The Seats booked under this booking
	 */
	private List<Seat> seats;
	
	/**
	 * Standard constructor for Booking
	 * @param name of moviegoer
	 * @param mobile of moviegoer
	 * @param email of moviegoer
	 * @param ticketType booked
	 * @param seats list of seats booked
	 * @param XXX code generated from the Movie Title
	 * @param cineplexIndex index of cineplex the showtime is in
	 * @param showtimeIndex index of showtime
	 */
	public Booking(String name, int mobile, String email, Ticket ticketType, List<Seat> seats, String XXX, int cineplexIndex, int showtimeIndex) {
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.ticketType = ticketType;
		this.seats = seats;
		this.cineplexIndex = cineplexIndex;
		this.showtimeIndex = showtimeIndex;
		
		DateFormat df = new SimpleDateFormat("YYYYMMDDhhmm");
		this.TID = XXX + df.format(new Date());
	}
	
	/**
	 * Gets the list of Seat from the booking
	 * @return List of Seats
	 */
	public List<Seat> getSeats() {
		return seats;
	}
	
	/**
	 * Gets the TID of the Booking
	 * @return the TID of the Booking
	 */
	public String getTID() {
		return TID;
	}
	
	/**
	 * Gets the mobile number of the person that booked
	 * @return The mobile number of the person that booked
	 */
	public int getMobile() {
		return mobile;
	}
	
	/**
	 * Get the index of the Cineplex where the Showtime that this Booking belongs to 
	 * @return index of the Cineplex where the Showtime that this Booking belongs to
	 */
	public int getCineplexIndex() {
		return cineplexIndex;
	}
	
	/**
	 * Get the index of the Showtime that this Booking belongs to
	 * @return index of the Showtime that this Booking belongs to
	 */
	public int getShowtimeIndex() {
		return showtimeIndex;
	}
	
	/**
	 * Calculates and returns the total cost of the tickets for the Booking
	 * @return the total cost of the tickets for the Booking
	 */
	public double getTotalAmount() {
		return seats.size() * ticketType.getPrice();
	}
	
	/**
	 * Gets the Ticket that this Booking has
	 * @return Ticket for Booking
	 */
	public Ticket getTicketType() {
		return ticketType;
	}

}
