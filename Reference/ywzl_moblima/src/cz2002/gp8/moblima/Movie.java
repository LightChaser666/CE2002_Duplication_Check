package cz2002.gp8.moblima;


import java.text.DecimalFormat;
import java.util.List;

/**
 * Representation of a Movie in MOBLIMA.
 */
public class Movie {

    /**
     * The collection of Age Rating.
     */
    public enum AgeRating {

        /**
         * G
         */
        G {
                    @Override
                    public String toString() {
                        return "G";
                    }
                },

        /**
         * PG
         */
        PG {
                    @Override
                    public String toString() {
                        return "PG";
                    }
                },

        /**
         * PG13
         */
        PG13 {
                    @Override
                    public String toString() {
                        return "PG13";
                    }
                },

        /**
         * NC16
         */
        NC16 {
                    @Override
                    public String toString() {
                        return "NC16";
                    }
                },

        /**
         * M18
         */
        M18 {
                    @Override
                    public String toString() {
                        return "M18";
                    }
                },

        /** 
         * R21
         */
        R21 {
                    @Override
                    public String toString() {
                        return "R21";
                    }
                }
    }

    /**
     * Collection of showing status.
     */
    public enum ShowingStatus {

        /**
         * COMING_SOON Status for Movie
         */
        COMING_SOON {
                    @Override
                    public String toString() {
                        return "Coming Soon";
                    }
                },
        /**
         * PREVIEW Status for Movie
         */
        PREVIEW {
                    @Override
                    public String toString() {
                        return "Preview";
                    }
                },
        /**
         * NOW_SHOWING Status for Movie
         */
        NOW_SHOWING {
                    @Override
                    public String toString() {
                        return "Now Showing";
                    }
                },
        /**
         * END_OF_SHOWING Status for Movie
         */
        END_OF_SHOWING {
                    @Override
                    public String toString() {
                        return "End of Showing";
                    }
                }
    }

    private int movieId;
    private String title;
    private String synopsis;
    private String director;
    private List<String> cast;
    private String genre;
    private AgeRating ageRating;
    private ShowingStatus showingStatus;
    private List<Review> reviews;
    private int ticketsSold; 

    /**
     * Constructor for a movie.
     * @param movieId movie ID
     * @param title title of the movie
     * @param synopsis synopsis of the movie
     * @param director director of the movie
     * @param casts list of casts for the movie
     * @param genre the genre of the movie
     * @param ageRating the age rating (Movie.AgeRating) of the movie
     * @param showingStatus the showing status (Movie.ShowingStatus) of the movie
     * @param reviews list of reviews for the movie
     */
    public Movie(int movieId, String title, String synopsis, String director, List<String> casts, String genre, AgeRating ageRating, ShowingStatus showingStatus, List<Review> reviews) {
        this.movieId = movieId;
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = casts;
        this.genre = genre;
        this.ageRating = ageRating;
        this.showingStatus = showingStatus;
        this.reviews = reviews;
        this.ticketsSold = 0;
    }

    /**
     * Display the details of a movie.
     */
    public void displayMovieDetails() {
        System.out.println(title + " (" + ageRating.name() + ")");
        System.out.println("- DETAILS -");
        System.out.println("Showing status: " + showingStatus.toString());
        System.out.print("Cast: ");
        String delimiter = "";
        DecimalFormat df = new DecimalFormat("#.#");
        for (int i = 0; i < cast.size(); i++) {
            System.out.print(delimiter + cast.get(i));
            delimiter = ", ";
        }
        System.out.println("\nDirector: " + director);
        System.out.println("Genre: " + genre);
        System.out.print("Overall rating: ");
        if (reviews.size() > 1) {
            System.out.print(df.format(getOverallRating()) + "/5\t" + reviews.size() + " Review(s)");
        } else if (reviews.size() <= 1) {
            System.out.print("N/A");
        }
        System.out.println("");
        System.out.println("- SYNOPSIS -\n" + synopsis);
        System.out.println("- REVIEWS -");
        if (reviews.isEmpty()) {
            System.out.println("No reviews available.");
        }
        for (Review review : reviews) {
            System.out.println("Review by: " + review.getMovieGoer()
                    + "\tRating:" + review.getRating()
                    + "/5\n" + review.getText() + "\n");
        }
    }
    
    /**
     * Get the sum of all the ratings.
     * @return the result.
     */
    public int getSummedRatings() {
    	int summedRatings = 0;
    	for (Review review : reviews) {
    		summedRatings += review.getRating();
    	}
    	return summedRatings;
    }

    /**
     * Get the overall ratings of a movie.
     * @return the overall rating.
     */
    public double getOverallRating() {
    	if (reviews.isEmpty()) return 0;
        return getSummedRatings() / reviews.size();
    }
    
    /**
     * Increase the tickets sold.
     */
    public void incrementSales() {
    	ticketsSold++;
    }
    
    /**
     * Get the number of ticket sold.
     * @return the ticketSold
     */
    public int getTicketsSold() {
    	return ticketsSold;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * @param synopsis the synopsis to set
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * @return the casts
     */
    public List<String> getCasts() {
        return cast;
    }

    /**
     * @param casts the casts to set
     */
    public void setCasts(List<String> casts) {
        this.cast = casts;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the ageRating
     */
    public AgeRating getAgeRating() {
        return ageRating;
    }

    /**
     * @param ageRating the rating to set
     */
    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    /**
     * @return the reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return the showingStatus
     */
    public ShowingStatus getShowingStatus() {
        return showingStatus;
    }

    /**
     * @param showingStatus the showingStatus to set
     */
    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }

    /**
     * @return the movieId
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * @param movieId the movieId to set
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
