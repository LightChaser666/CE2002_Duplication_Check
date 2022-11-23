package cz2002.gp8.moblima;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lloyd
 */
public class Showtime {
	private int movieId;
	private Date session;
	private Cinema cinema;
	private List<Ticket> ticketTypes;
	private List<Booking> bookings;
	private Seat[][] seats;
	
    /**
     * This creates a showtime for the movieId passed in, including the date, type of tickets and creation of seats.
     * @param cinema The cinema that the showtime is at
     * @param movieId the movieId of the movie for that showtime
     * @param session The date and time of the showtime
     * @param tickets The tickets availiable for booking at that showtime
     */
    public Showtime(Cinema cinema, int movieId, Date session, List<Ticket> tickets) {
		this.movieId = movieId;
		this.session = session;
		this.ticketTypes = tickets;
		this.cinema = cinema;
		this.bookings = new ArrayList<Booking>();
		
		int rows = cinema.getRows();
		int cols = cinema.getCols();
		seats = new Seat[rows][cols];
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				seats[i][j] = new Seat(i, j);
			}
		}
	}
	
    /**
     * This returns the movie ID of the movie
     * @return movie ID of the movie (int)
     */
    public int getMovieId() {
		return movieId;
	}
	
    /**
     * This returns the name of the cinema (e.g. Hall 1, Hall 2, etc.)
     * @return name of cinema (string)
     */
    public String getCinemaName() {
		return cinema.getName();
	}
	
    /**
     * This returns a list of existing bookings made for that showtime
     * @return list of bookings
     */
    public List<Booking> getBookings() {
		return bookings;
	}
	
    /**
     * This returns session time of the movie (date).
     * @return date of movie session
     */
    public String getSession() {
		DateFormat df = new SimpleDateFormat("dd-MM EEE HH:mm");
		return df.format(session);
	}
	
    /**
     * This displays the seat layout for user to choose their seats from
     */
    public void displaySeats() {
		System.out.println("- Seat Layout (X denotes taken) -");
		
		for (int i=0; i<seats.length; i++) {
			System.out.print(seats[i][0].getRowAlphabet());
			for (int j=0; j<seats[i].length; j++) {
				String display = (seats[i][j].isTaken()) ? " [X] " : " ["+ j +"] ";
				System.out.print(display);
			}
			System.out.println();
		}
		System.out.println();
	}
	
    /**
     * This allows the user to make a new booking for a movie.
     * @param booking The booking to be added to the list
     */
    public void addBooking(Booking booking) {
		for (Seat seat : booking.getSeats()) {
			assignSeat(seat.getRow(), seat.getCol());
		}
		bookings.add(booking);
	}
	
    /**
     * Assign the seat at that row and col
     * @param row of seat
     * @param col of col
     */
    public void assignSeat(int row, int col) {
		seats[row][col].assignSeat();
	}
	
    /**
     * Checks if the Seat is taken at that row and col
     * @param row of the seat
     * @param col of the seat
     * @return True if the seat is taken
     */
    public boolean isSeatTaken(int row, int col) {
		return seats[row][col].isTaken();
	}
	
    /**
     * Displays the list of Tickets stored in the showtime
     */
    public void displayTicketTypes() {
		for (int i=0; i<ticketTypes.size(); i++) {
			System.out.print((i+1) + ". ");
			ticketTypes.get(i).displayTicket();
		}
	}
	
    /**
     * Get the ticket at that index
     * @param ticketIndex The index of the ticket to get
     * @return the Ticket at that index
     */
    public Ticket getTicket(int ticketIndex) {
		return ticketTypes.get(ticketIndex);
	}

	/**
	 * @return the ticketTypes
	 */
	public List<Ticket> getTicketTypes() {
		return ticketTypes;
	}

}
