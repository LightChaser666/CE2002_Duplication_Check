package moblima;
import java.util.*;
import java.util.ArrayList;
/**
 * <code>MovieList</code> class which stores all the available <code>Movie</code>s.
 * @see Movie
 */
public class MovieList {
	/**
	 * <code>ArrayList&lt;Movie&gt;</code> which stores all the available <code>Movie</code> objects.
	 */
	public static ArrayList<Movie> movieList;
	/**
	 * Adds a new <code>Movie</code> object onto <code>movieList</code>.
	 * @param movieToAdd
	 */
	public static void addMovie(Movie movieToAdd){
		movieList.add(movieToAdd);
	}
	/**
	 * Removes a <code>Movie</code> object from <code>movieList</code>.
	 * @param movieToAdd
	 */
	public static void removeMovie(Movie movieToRemove){
		movieList.remove(movieToRemove);
	}
	/**
	 * Sorts all the <code>Movie</code> objects in <code>movieList</code> in descending order of average ratings.
	 * @param movieToAdd
	 * @see Movie
	 */
	public static void sortByRating() {
		Collections.sort(movieList, Movie.MovieRatingComparator);
	}

	/**
	 * Sorts all the <code>Movie</code> objects in <code>movieList</code> in descending order of total sales.
	 * @param movieToAdd
	 * @see Movie
	 */
	public static void sortBySales() {
		Collections.sort(movieList, Movie.MovieSalesComparator);
	}
}
