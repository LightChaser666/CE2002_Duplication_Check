import java.util.ArrayList;
import java.util.List;

public class Cineplex {
	String name;
	List<Integer> movieListing;
	List<Showtime> showtimes;
	List<Cinema> cinemas;
	
	public Cineplex(String name) {
		this.name = name;
		this.movieListing = new ArrayList<Integer>();
		this.showtimes = new ArrayList<Showtime>();
		this.cinemas = new ArrayList<Cinema>();
	}
	
	public void addCinemas(String name, int row, int col) {
		cinemas.add(new Cinema(name,row, col));
	}
	
	public void addMovie(Integer movieIndex) {
		movieListing.add(movieIndex);
	}
	
	public void viewDetails() {
		System.out.println(name);
		System.out.println("No. of Screens " + cinemas.size());
	}
	
	public List<Integer> getMovieListing() {
		return movieListing;		
	}
}
