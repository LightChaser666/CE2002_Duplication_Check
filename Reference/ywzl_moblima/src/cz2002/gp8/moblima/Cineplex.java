package cz2002.gp8.moblima;


import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a Cineplex in the MOBLIMA Application
 */
public class Cineplex {
    private String name;
    private List<Integer> movieListings;
    private List<Showtime> showtimes;
    private List<Cinema> cinemas;

    /**
     *
     * @param name Name of the Cineplex
     */
    public Cineplex(String name) {
        this.name = name;
        this.movieListings = new ArrayList<Integer>();
        this.showtimes = new ArrayList<Showtime>();
        this.cinemas = new ArrayList<Cinema>();
    }

    /**
     * Method to display details of cinemas inside the cineplex.
     */
    public void displayCinemas() {
        for (int i = 0; i < cinemas.size(); i++) {
        	Cinema cinema = cinemas.get(i);
            System.out.println((i+1) + ": " + cinema.getName() + " [" + cinema.getSuite() + "]");
        }
    }
    
    /**
     * Method to display the Showtimes of the movie given by its movie ID.
     * @param movieId The ID of the movie.
     */
    public void displayShowtimesForMovie(int movieId) {
    	List<Showtime> movieShowtimes = getShowtimesForMovie(movieId);
    	for (int i=0; i<movieShowtimes.size(); i++) {
    		System.out.println((i+1) + ". " + movieShowtimes.get(i).getSession());
    	}
    }

    /**
     * Get the Showtimes for a movie given its movie ID.
     * @param movieId the ID of the movie.
     * @return a List of all the showtimes.
     */
    public List<Showtime> getShowtimesForMovie(int movieId) {
        List<Showtime> movieShowtimes = new ArrayList<Showtime>();
        for (int i = 0; i < showtimes.size(); i++) {
            Showtime showtime = showtimes.get(i);
            if (showtime.getMovieId() == movieId) {
                movieShowtimes.add(showtime);
            }
        }
        return movieShowtimes;
    }
    
    /**
     * Add the movie ID to the cineplex's movielisting.
     * @param movieId ID of the movie
     */
    public void addMovieListing(int movieId) {
    	movieListings.add(movieId);
    }
    
    /**
     * Remove the movie ID from the cineplex's movielisting.
     * @param movieId ID of the movie
     */
    public void removeMovieListing(Integer movieId) {
    	movieListings.remove(movieId);
    }

    /**
     * Get Showtime by its index in the cineplex's list of showtimes.
     * @param index index of Showtime in showtimes.
     * @return the Showtime given by the index
     */
    public Showtime getShowtime(int index) {
        return showtimes.get(index);
    }
    
    /**
     * Remove Showtime by its index from the cineplex's list of showtimes.
     * @param showtimeIndex index of Showtime in showtimes.
     */
    public void removeShowtime(int showtimeIndex) {
    	showtimes.remove(showtimeIndex);
    }
    
    /**
     * Get Cinema by its index in the cineplex's list of Cinemas.
     * @param index index of Cinema in cinemas.
     * @return the Cinema indicated by the index.
     */
    public Cinema getCinema(int index) {
    	return cinemas.get(index);
    }
    
    /**
     * Add a new Showtime to the cineplex's list of showtimes.
     * @param showtime the Showtime to be added
     */
    public void addShowtime(Showtime showtime) {
    	showtimes.add(showtime);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the movieListings
     */
    public List<Integer> getMovieListings() {
        return movieListings;
    }

    /**
     * @param movieListings the movieListings to set
     */
    public void setMovieListings(List<Integer> movieListings) {
        this.movieListings = movieListings;
    }

    /**
     * @return the showtimes
     */
    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    /**
     * @param showtimes the showtimes to set
     */
    public void setShowtimes(List<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    /**
     * @return the cinemas
     */
    public List<Cinema> getCinemas() {
        return cinemas;
    }

}
