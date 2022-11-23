package moblima;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Movie implements Serializable{

	private static final long serialVersionUID = 41302788253857504L;
	private String title;
	private String synopsis;
	private String director;
	private String cast;
	private ArrayList<MovieReview> reviews;
	private ShowingStatus showingStatus;	
	private MovieType movieType;
	
	private float ticketSales;
	
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	public Movie(String title, String synopsis, String director, String cast, MovieType movieType){
		this.title = title;
		this.showingStatus = ShowingStatus.NOW_SHOWING;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		
		this.movieType = movieType;
		this.ticketSales = 0;
		
		reviews = new ArrayList<MovieReview>();
	};

	public String getTitle(){
		return this.title;
	}	

	public String getSynopsis(){
		return this.synopsis;
	}
	
	public String getDirector(){
		return this.director;
	}
	
	public String getCast(){
		return this.cast;
	}

	public float getAvgRating()
	{
		float sum = 0;
		for(MovieReview o : reviews){
			sum += (float) o.getRating();
		}
		
		return sum/reviews.size();
	}
	
	public ShowingStatus getShowingStatus() {
		return showingStatus;
	}
	
	public void printAvgRating()
	{
		//print avg rating for that movie if there is >1 ratings
				if (reviews.size() > 1)
				{
					System.out.printf("%.1f", getAvgRating());
					System.out.println("\n");
				}
		//NA when there is one or less rating
				else
				{
					System.out.println("NA - there is only one individual rating/review to display.");
				}
	}

	
	public void printReviews(){
			System.out.println("- - - - - - - - - - - - - - - - - ");
		for(MovieReview o : reviews){
			
			System.out.println("Review:			"+ "Rating: " + o.getRating());
			System.out.println(o.getReview());
			System.out.println("- - - - - - - - - - - - - - - - - ");
		}
	}
	
	public ArrayList<MovieReview> getReviewList() {
		return reviews;
	}
	
	public MovieType getMovieType(){
		return movieType;
	}
	
	public float getTicketSales(){
		return this.ticketSales;
	}

	public void setTitle(String title){
		this.title = title;
	}
	
	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}
	
	public void setDirector(String director){
		this.director = director;
	}
	
	public void setCast(String cast){
		this.cast = cast;
	}
	
	public void addReview(String review, int rating){
		reviews.add(new MovieReview(review, rating));
	}
	
	public void setShowingStatus (ShowingStatus status){
		this.showingStatus = status;
	}
		
	public void addTicketSale(float ticketSalePrice){
		this.ticketSales += ticketSalePrice;
	}

	
	static Comparator<Movie> salesComparator() {
		
		return new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				if (o1.getTicketSales()==o2.getTicketSales()) {
					return o1.getTitle().compareTo(o2.getTitle());
				} else {
					int comp = (int) (o2.getTicketSales()-o1.getTicketSales());
					return comp;
				}
			}
		};
	}

	static Comparator<Movie> ratingComparator() {
		return new Comparator<Movie>()	 {
			@Override
			public int compare(Movie o1, Movie o2) {
				if (o1.getAvgRating() == o2.getAvgRating()) {
					return o1.getTitle().compareTo(o2.getTitle());
				}else {
					float comp = (o2.getAvgRating()-o1.getAvgRating());
					if (comp > 0) {
						return 1;
					} else if (comp < 0) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		};
	}

}

