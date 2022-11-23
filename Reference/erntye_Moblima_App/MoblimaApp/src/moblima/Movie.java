package moblima;
import java.io.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * <code>Movie</code> objects represent different movies, containing details of Movie Title, Showing Status, Synpopsis, Director,
 * Cast, Average Ratings, List of Reviews, Censorship Ratings, and Sales.
 * <p><code>Movie</code> objects are different from <code>Show</code> objects in that shows are the screening of movies are
 * certain times and certain cinemas, while movies are unique instances of the movie.
 *
 */
public class Movie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5835831193063619356L;
	
	/**
	 * Enumerator list of Showing Statuses of the <code>Movie</code> objects, containing {ComingSoon, Preview, NowShowing, Ended}.
	 * <br>Movies that are coming soon or have ended are not displayed when customer is booking a movie.
	 */
	public enum Showing_Status {
		ComingSoon, Preview, NowShowing,Ended;
	}
	
	/**
	 * Enumerator list of the censorship ratings of the <code>Movie</code> objects, containing {G, PG, PG13, NC16, M18, R21}.
	 */
	public enum Censorship_Rating {
		G,PG, PG13,NC16,M18,R21
	}
	
	/**
	 * Title of the movie.
	 */
	private String title;
	
	/**
	 * Showing Status of the movie.
	 * @see Showing_Status
	 */
	private Showing_Status showingStatus;
	
	/**
	 * Short plot summary to introduce the movie.
	 */
	private String synopsis;
	
	/**
	 * Director of the movie.
	 */
	private String director;
	
	/**
	 * Names of the cast members.
	 */
	private String[] cast;
	
	/**
	 * Average value of the ratings given by customers. Initial value set to 0.
	 */
	private float aveRating = 0; //initialize to 0
	
	/**
	 * <code>ArrayList&lt;Reviews&gt;</code> which contains the <code>Reviews</code> objects created when customers
	 * leave reviews for indivdual movies.
	 * @see Reviews
	 */
	private ArrayList<Reviews> reviews;
	
	/**
	 * Number of reviews for the particular movie.
	 * Used to calculate the average rating.
	 */
	private int reviewCount = 0; //initialize to 0
	
	/**
	 * Dollar value of sales that the movie has generated. When a customer books a movie, the <code>calculatePrice</code> method in 
	 * <code>Cinema</code> is called to calculate ticket price, and this price is added to the total sales value of the
	 * particular movie.
	 * <p> Initialised to 0.
	 * @see Cinema
	 */
	private float sales = 0;	//initialize to 0
	
	/**
	 * The censorship rating for the movie, containing {G, PG, PG13, NC16, M18, R21}.
	 */
	private Censorship_Rating censorshipRating;
	/**
	 * Constructor for the <code>Movie</code> class.
	 * @param title Movie title.
	 * @param showingStatus Showing Status of the movie. {ComingSoon, Preview, NowShowing, Ended}.
	 * @param synopsis Synopsis for the movie.
	 * @param director Director of the movie.
	 * @param cast Cast of the movie.
	 * @param censorshipRating Censorship Rating of the movie. {G, PG, PG13, NC16, M18, R21}.
	 * @see Showing_Status
	 * @see Censorship_Rating
	 */
	public Movie(String title, Showing_Status showingStatus, String synopsis,
			String director, String[] cast, Censorship_Rating censorshipRating){
		this.title = title; 
		this.showingStatus =  showingStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast; 
		this.censorshipRating = censorshipRating;
		this.reviews = new ArrayList<Reviews>();
	}
	
	

	/**
	 * Change the showing status of the movie.
	 * @param showingStatus
	 * @see Showing_Status
	 */
	public void changeStatus(Showing_Status showingStatus){
		this.showingStatus = showingStatus;
	}
	

	/**
	 * Add a <code>Reviews</code> object to the list of reviews stored in the <code>Movie</code> object.
	 * Updates the review count variable in the <code>Movie</code> object.
	 * @param reviewToAdd <code>Reviews</code> object to add.
	 * @see Reviews
	 */
	public void addReviews(Reviews reviewToAdd){
		this.reviews.add(reviewToAdd);
		updateRatings(reviewToAdd.getRating());
		//reviewCount updated in updateRatings
	}
	
	/**
	 * Update the average ratings after a customer adds a rating and review.
	 * <br> Multiplies the previous average rating with the previous number of review counts, add the new rating and
	 * divide it by the new total review count.
	 * @param ratingToAdd New rating to add.
	 */
	public void updateRatings(int ratingToAdd){
		this.aveRating = (aveRating*reviewCount+ ratingToAdd)/++reviewCount;
	}
	
	/**
	 * Gets <code>String</code> value of the movie title.
	 * @return <code>String</code> Movie Title.
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * Gets the showing status of the movie.
	 * @return <code>Showing_Status</code> Enumerator {ComingSoon, Preview, NowShowing, Ended}.
	 * @see Showing_Status 
	 */
	public Showing_Status getShowingStatus(){
		return showingStatus;
	}
	
	/**
	 * Gets the censorship rating of the movie.
	 * @return <code>Censorship_Rating</code> Enumerator {G, PG, PG13, NC16, M18, R21}.
	 * @see Censorship_Rating
	 */
	public Censorship_Rating getCensorshipRating(){
		return censorshipRating;
	}
	
	/**
	 * Gets <code>String</code> value of the synopsis of the movie.
	 * @return <code>String</code> Synopsis.
	 */
	public String getSynopsis(){
		return synopsis;
	}
	
	/**
	 * Gets <code>String</code> value of the Director of the movie.
	 * @return <code>String</code> Director.
	 */
	public String getDirector(){
		return director;
	}
	
	/**
	 * Gets <code>String</code> array of the cast members of the movie.
	 * @return <code>String[]</code> Cast members.
	 */
	public String[] getCast(){
		return cast;
	}
	
	/**
	 * Gets <code>float</code> value of the average ratings for the movie.
	 * @return <code>float</code> Average Ratings.
	 */
	public float getAverageRating(){
		return aveRating;
	}
	
	/**
	 * Gets <code>int</code> number of reviews for the movie.
	 * @return <code>int</code> Reviews Count.
	 */
	public int getReviewCount() {
		return reviewCount;
	}
	
	/**
	 * Gets a reference to the <code>ArrayList&lt;Reviews&gt;</code> for the movie.
	 * @return <code>ArrayList&lt;Reviews&gt;</code> reference.
	 * @see Reviews
	 */
	public ArrayList<Reviews> getReviews(){
		return reviews;
	}
	
	/**
	 * Gets <code>float</code> value of the dollar value of the sales of the movie.
	 * @return <code>float</code> Sales.
	 */
	public float getSales() {
		return sales;
	}
	
	/**
	 * Sets <code>float</code> value for the Sales in dollars for the movie.
	 * @param newSales New Sales to update.
	 */
	public void setSales(float newSales){
		this.sales = newSales;
	}
	
	// Sort by Rating
	/**
	 * Creates a comparator to compare ratings of <code>Movie</code> objects. Used for the sorting of movies by ratings.
	 * @see Movie
	 */
	public static Comparator<Movie> MovieRatingComparator = new Comparator<Movie>() {
		
		public int compare(Movie m1, Movie m2) {
		   float movieAveRating1 = m1.getAverageRating();
		   float movieAveRating2 = m2.getAverageRating();
		   return (int) (movieAveRating2 - movieAveRating1);
		   

	    }
	};
	
	// Sort by Sales
	/**
	 * Creates a comparator to compare sales of <code>Movie</code> objects. Used for the sorting of movies by sales.
	 * @see Movie
	 */
	public static Comparator<Movie> MovieSalesComparator = new Comparator<Movie>() {

		public int compare(Movie m1, Movie m2) {
		   float movieSales1 = m1.getSales();
		   float movieSales2 = m2.getSales();	
		   return (int) (movieSales2 - movieSales1);	   

	    }
	};

}	
	
//	
