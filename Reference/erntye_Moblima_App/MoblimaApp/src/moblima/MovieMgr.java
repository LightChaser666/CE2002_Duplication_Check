package moblima;

import java.util.ArrayList;
/** Manages all the Movie-related operations.
 * <p>Implements a Singleton structure. The instance can be called with <code>getInstance()</code>.
 * @see MovieList
 * @see Movie
 */
public class MovieMgr {
	
	//implements singleton pattern
	private static MovieMgr instance;
	public static MovieMgr getInstance() {
		if(instance == null) {
			instance = new MovieMgr();
		}
		return instance;
	}
	
	/**
	 * Adds new <code> Movie</code> object into <code>Movie List</code>.
	 * @see MovieList
	 */
	private void addMovie() {
		MovieList.addMovie(ConsoleBoundary.printAddMovie());
	}
	
	/**
	 * Removes selected <code> Movie</code> object from <code>Movie List</code>.
	 * @see MovieList
	 */
	private void removeMovie() {
		MovieList.removeMovie(ConsoleBoundary.printRemoveMovie());
	}
	
	/**
	 * Edits details of selected <code> Movie</code> object in <code>Movie List</code>.
	 * @see MovieList
	 */
	private void editMovie() {
		Movie movieToEdit = ConsoleBoundary.printEditMovie();
		Movie movietoReplace = ConsoleBoundary.printEditMovie2(movieToEdit);
		int index = MovieList.movieList.indexOf(movieToEdit);
		MovieList.movieList.add(index, movietoReplace);
		MovieList.removeMovie(movieToEdit);
	}

	/**
	 * Controls the <code>MovieMgr</code> logic.
	 * <p> 1. Adds <code>Movie</code>.
	 * <br> 2. Remove <code>Movie</code>.
	 * <br> 3. Edit <code>Movie</code>.
	 * <br> 4. Show Top 5 <code>Movie</code>s by sales.
	 * <br> 5. Show Top 5 <code>Movie</code>s by ratings.
	 */
	public void movieOps() {
		boolean loop = true;
		while(loop) {
			int opsChoice = ConsoleBoundary.printMovieOps();
			switch(opsChoice) {
			case 1: addMovie();break;
			case 2: removeMovie(); break;
			case 3: editMovie(); break;
			case 4: top5Sales(); break;
			case 5: top5Ratings(); break;
			case 0: loop = false; break;
			}
			DataBoundary.saveMovieList(MovieList.movieList);
		}
	}
	/**
	 * Displays the top 5 <code>Movie</code>s in descending order of total sales.
	 */
	public void top5Sales() {
		MovieList.sortBySales();
		ConsoleBoundary.printTopSales();
	}
	
	/**
	 * Displays the top 5 <code>Movie</code>s in descending order of average ratings.
	 */
	public void top5Ratings() {
		MovieList.sortByRating();
		ConsoleBoundary.printTopRatings();
	}
	
}
