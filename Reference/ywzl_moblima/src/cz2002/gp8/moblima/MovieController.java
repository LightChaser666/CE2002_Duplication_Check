package cz2002.gp8.moblima;


import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.google.gson.reflect.TypeToken;

/**
 * Controller for Movie in MOBLIMA.
 */
public class MovieController implements JSONFile {

    private List<Movie> list;
    private Type Movielist = new TypeToken<ArrayList<Movie>>() {
    }.getType(); // needed for gson to load into Arraylist
    private File JSONFile = new File("movies.json");

    /**
     * Constructor for a movie controller.
     */
    public MovieController() {
        list = (List<Movie>) load(JSONFile, Movielist);
    }

    /**
     * Adds a new movie.
     *
     * @param movie the movie to be added.
     */
    public void addMovie(Movie movie) {
        list.add(movie);
        save(JSONFile, list);
    }

    /**
     * Adds a new review for a movie given its ID.
     *
     * @param movieId ID of the movie.
     * @param review the review to be added.
     */
    public void addReview(int movieId, Review review) {
        for (Movie movie : list) {
            if (movie.getMovieId() == movieId) {
                movie.getReviews().add(review);
                break;
            }
        }
        save(JSONFile, list);
    }

    /**
     * Updates a movie details given the index in the list.
     *
     * @param movieIndex The index of the movie of the movie to be updated
     * @param updatedMovie The modified movie
     */
    public void updateMovie(int movieIndex, Movie updatedMovie) {
        list.set(movieIndex, updatedMovie);
        save(JSONFile, list);
    }

    /**
     * Gets the movie by its index in the movie list.
     *
     * @param index the index of the movie
     * @return the movie
     */
    public Movie getMovie(int index) {
        return list.get(index);
    }

    /**
     * Gets the movie by its ID.
     *
     * @param id the id of the movie
     * @return the movie
     */
    public Movie getMovieById(int id) {
        Movie movie = null;
        for (Movie m : list) {
            if (m.getMovieId() == id) {
                return m;
            }
        }
        return movie;
    }

    /**
     * Generate the id for a new movie.
     *
     * @return ID of the new movie
     */
    public int getNewMovieId() {
        return list.size() + 1;
    }

    /**
     * Increase the sales for a movie given its ID.
     *
     * @param movieId the ID of the movie
     */
    public void incrementSales(int movieId) {
        getMovieById(movieId).incrementSales();
        save(JSONFile, list);
    }

    /**
     * Get the list of movies.
     *
     * @return the list of movies.
     */
    public List<Movie> getList() {
        return list;
    }

    /**
     * Display the movies with top sales.
     */
    public void displayTopSales() {
        List<Movie> sortedList = getListTopSales();
        for (int i = 0; i < sortedList.size(); i++) {
            Movie movie = sortedList.get(i);
            System.out.println((i + 1) + ". " + movie.getTitle() + " Sales: " + movie.getTicketsSold());
        }
        System.out.println();
    }

    /**
     * Get the list of movies that has the top sales.
     *
     * @return the list of movies with top sales.
     */
    public List<Movie> getListTopSales() {
        List<Movie> sortedList = list;
        Collections.sort(sortedList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.getTicketsSold() - o1.getTicketsSold();
            }
        });
        if (sortedList.size() > 5) {
            sortedList = sortedList.subList(0, 5);
        }
        return sortedList;
    }

    /**
     * Display the list of movies that has the top ratings.
     */
    public void displayTopRated() {
        List<Movie> sortedList = getListTopRated();
        for (int i = 0; i < sortedList.size(); i++) {
            Movie movie = sortedList.get(i);
            System.out.println((i + 1) + ". " + movie.getTitle() + " Rating: " + movie.getOverallRating() + "/5");
        }
        System.out.println();
    }

    /**
     * Gets the list of movies that has the top ratings.
     *
     * @return the list of movies that has the top ratings.
     */
    public List<Movie> getListTopRated() {
        List<Movie> sortedList = list;
        Collections.sort(sortedList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return (int) (o2.getOverallRating() - o1.getOverallRating());
            }
        });
        if (sortedList.size() > 5) {
            sortedList = sortedList.subList(0, 5);
        }
        return sortedList;
    }

    /**
     * Remove a movie given its index in the list.
     *
     * @param movieIndex the index of the movie
     */
    public void removeMovie(int movieIndex) {
        list.remove(movieIndex);
        save(JSONFile, list);
    }

    // Showing list = movies that can be bought (NOW_SHOWING and PREVIEW)
    /**
     * Display the list of movies that are currently showing.
     */
    public void displayListShowing() {
        List<Movie> showingList = getListShowing();
        for (int i = 0; i < showingList.size(); i++) {
            System.out.println((i + 1) + ". " + showingList.get(i).getTitle());
        }
        System.out.println();
    }

    /**
     * Get the list of movies that are currently showing.
     *
     * @return get the list of movies that are currently showing.
     */
    public List<Movie> getListShowing() {
        List<Movie.ShowingStatus> statuses = new ArrayList<Movie.ShowingStatus>();
        statuses.add(Movie.ShowingStatus.NOW_SHOWING);
        statuses.add(Movie.ShowingStatus.PREVIEW);
        return getFilteredList(statuses);
    }

    /**
     * Display the list of movies that are filtered by their showing status.
     *
     * @param statuses a list of filters.
     */
    public void displayFilteredList(List<Movie.ShowingStatus> statuses) {
        List<Movie> filteredList = getFilteredList(statuses);
        for (int i = 0; i < filteredList.size(); i++) {
            System.out.println((i + 1) + ". " + filteredList.get(i).getTitle());
        }
        System.out.println();
    }

    /**
     * Get the list of movies that are filtered by their showing status.
     *
     * @param statuses a list of filters.
     * @return the list of movies that are filtered by their showing status.
     */
    public List<Movie> getFilteredList(List<Movie.ShowingStatus> statuses) {
        List<Movie> filteredList = new ArrayList<Movie>();
        for (Movie movie : list) {
            Movie.ShowingStatus status = movie.getShowingStatus();
            if (statuses.contains(status)) {
                filteredList.add(movie);
            }
        }
        return filteredList;
    }

    /**
     * Display the list of movies.
     */
    public void displayList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getTitle());
        }
    }

    /**
     * Display the list of movies and their showing status.
     */
    public void displayListStatus() {
        for (int i = 0; i < list.size(); i++) {
            Movie movie = list.get(i);
            System.out.println((i + 1) + ". " + movie.getTitle() + " [" + movie.getShowingStatus() + "]");
        }
        System.out.println();
    }
}
