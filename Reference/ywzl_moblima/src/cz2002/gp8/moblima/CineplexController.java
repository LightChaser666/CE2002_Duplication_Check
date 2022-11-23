package cz2002.gp8.moblima;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class CineplexController implements JSONFile{
	private List<Cineplex> list;
	private File JSONFile = new File("cineplexes.json");
	private Type Cineplexlist = new TypeToken<ArrayList<Cineplex>>(){}.getType();

    /**
     * Constructor for CineplexController
     */
    public CineplexController() {
		list = (List<Cineplex>) load(JSONFile, Cineplexlist);		
	}
	
    /**
     * Add a new Cineplex into the list.
     * @param cineplex the Cineplex to be added
     */
    public void addCineplex(Cineplex cineplex) {
		list.add(cineplex);
	}
	
    /**
     * Get the Cineplex by its index in the Cineplexlist
     * @param index the index of the Cineplex
     * @return the Cineplex with the index
     */
    public Cineplex getCineplex(int index) {
		return list.get(index);
	}
	
    /**
     * Get the list of all cineplexes.
     * @return list of all cineplexes
     */
    public List<Cineplex> getList() {
		return list;
	}
	
    /**
     * Add a new showtime into a cineplex indicated by its index on Cineplexlist
     * @param cineplexIndex index of Cineplex in Cineplexlist
     * @param showtime the showtime to be added
     */
    public void addShowtime(int cineplexIndex, Showtime showtime) {
		Cineplex cineplex = list.get(cineplexIndex);
		cineplex.addShowtime(showtime);
		save(JSONFile, list);
	}
	
    /**
     * Remove showtime from a cineplex indicated by its index on Cineplexlist and index in the Cineplex's showtime list.
     * @param cineplexIndex index of Cineplex in Cineplexlist
     * @param showtimeIndex the index of the showtime in the Cineplex's showtime listing
     */
    public void removeShowtime(int cineplexIndex, int showtimeIndex) {
		Cineplex cineplex = list.get(cineplexIndex);
		cineplex.removeShowtime(showtimeIndex);
		save(JSONFile, list);
	}
	
    /**
     * Add the a movie given by its id into the movielisting of the Cineplex given by its index in Cineplexlisting
     * @param cineplexIndex index of Cineplex in Cineplexlist
     * @param movieId ID of the movie
     */
    public void addMovieListing(int cineplexIndex, int movieId) {
		Cineplex cineplex = list.get(cineplexIndex);
		cineplex.addMovieListing(movieId);
		save(JSONFile, list);
	}
	
    /**
     * Removes a movie given by its id inside the movielisting of the Cineplex given by its index in Cineplexlisting
     * @param cineplexIndex index of Cineplex in Cineplexlist
     * @param movieId ID of the movie
     */
    public void removeMovieListing(int cineplexIndex, Integer movieId) {
		Cineplex cineplex = list.get(cineplexIndex);
		cineplex.removeMovieListing(movieId);
		save(JSONFile, list);
	}
	
    /**
     * Adds a new booking for a given showtime in the Cineplex given by its index in Cineplexlisting
     * @param cineplexIndex index of Cineplex in Cineplexlist
     * @param showtimeIndex the index of the showtime in the Cineplex's showtime listing
     * @param booking the booking to be added.
     */
    public void addBooking(int cineplexIndex, int showtimeIndex, Booking booking) {
		Cineplex cineplex = list.get(cineplexIndex);
		Showtime showtime = cineplex.getShowtime(showtimeIndex);
		showtime.addBooking(booking);
		save(JSONFile, list);
	}
	
    /**
     * Display the list of cineplexes.
     */
    public void displayList() {
		for (int i=0; i<list.size(); i++) {
			System.out.println((i + 1) + ". " + list.get(i).getName());
		}
	}
}
