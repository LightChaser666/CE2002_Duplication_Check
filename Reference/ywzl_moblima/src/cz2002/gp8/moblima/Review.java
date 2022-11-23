package cz2002.gp8.moblima;


/**
 *
 * @author Lloyd
 */
public class Review {

    private String movieGoer;
    private int rating;
    private String text;

    /**
     * This creates a review object in which each review is identified by the user who wrote the review, its rating and review text itself.
     * @param movieGoer
     * @param rating
     * @param text
     */
    public Review(String movieGoer, int rating, String text) {
        this.movieGoer = movieGoer;
        this.rating = rating;
        this.text = text;
    }
    
    /**
     * Returns name of the user/moviegoer.
     * @return the movieGoer
     */
    public String getMovieGoer() {
        return movieGoer;
    }

    /**
     * Sets the name of the moviegoer.
     * @param movieGoer the movieGoer to set
     */
    public void setMovieGoer(String movieGoer) {
        this.movieGoer = movieGoer;
    }

    /**
     * Sets the rating of the movie (1 to 5)
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Returns text of a review.
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Enters review text written by a user.
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns rating of a movie.
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

}
