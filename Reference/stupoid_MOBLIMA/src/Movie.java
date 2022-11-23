import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Movie {
	String title;
	String status; // e.g. (Coming Soon, Preview, Now Showing, End Of Showing)
	String synopsis;
	String director;
	List<String> cast;
	String genre;
	String rating; // e.g.(PG, NC16, R21)
	List<Review> reviews;

	public Movie(String title, String status, String synopsis, String director, List<String> cast, String genre, String rating) {
		this.title = title;
		this.status = status;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.genre = genre;
		this.rating = rating;
		this.reviews = new ArrayList<Review>();
	}
	
	public void viewDetails() {
		System.out.println(title + "(" + rating + ") ");
		System.out.println("Status:   " + status);
		System.out.println("Details");
		System.out.println("Cast:     " + String.join(", ", cast));
		System.out.println("Director: " + director);
		System.out.println("Genre:    " + genre);
		if (reviews.isEmpty()) {
			System.out.println("Overall Rating: No ratings yet");
		} else { 
			System.out.println("Overall Rating: " + getOverallRating());
		}
		System.out.println("Synopsis:");
		System.out.println(synopsis);
	}
	
	public int getOverallRating() {
		if (reviews.isEmpty()) return 0;
		
		int overallRating = 0;
		for(Review review: reviews) {
			overallRating += review.rating;
		}
		
		return overallRating/reviews.size();
	}

	public void viewPastReviews() {
		for(Review review: reviews) {
			System.out.println("Rating: " + review.rating);
			System.out.println("Review: " + review.review);
			System.out.println("User: " + review.user.name);
		}
	}
	
	public void writeReview(User user) {
		System.out.print("Rating (Out of 5) :");
	     Scanner sc = new Scanner(System.in);
	     int rating = sc.nextInt();
	     System.out.println();
	     
	     System.out.print("Review :");
	     String review = sc.nextLine();
	     System.out.println();
	     
	     reviews.add(new Review(user, rating, review));
	     System.out.println("Successfully saved review");
	     sc.close();
	}
}
