import java.util.Date;

public class Showtime {
	Movie movie;
	Cinema cinema;
	Date session;
	Ticket[] tickets;
	Seat[][] seats;
	
	public Showtime(Movie movie, Cinema cinema, Date session, Ticket[] tickets, Seat[][] seats) {
		this.movie = movie;
		this.cinema = cinema;
		this.session = session;
		this.tickets = tickets;
		this.seats = seats;
	}
}
