package cz2002.gp8.moblima;


/**
 *
 * @author Lloyd
 */
public class MovieStatus {
    
    /**
     * Flags for the showing status of the movie
     */
    public enum Status {

        /**
         * COMING_SOON Status for Movie
         */
        COMING_SOON,

        /**
         * PREVIEW Status for Movie
         */
        PREVIEW,

        /**
         * NOW_SHOWING Status for Movie
         */
        NOW_SHOWING,

        /**
         * END_OF_SHOWING Status for Movie
         */
        END_OF_SHOWING
    }

    private int movie_id;
    private Status status;

    /**
     * Empty constructor for MovieStatus
     */
    public MovieStatus() {
    }

    /**
     * Constructor for MovieStatus
     * @param movie_id the ID of the Movie
     * @param status the showing Status (from the enum STATUS) of the movie
     */
    public MovieStatus(int movie_id, Status status) {
        this.movie_id = movie_id;
        this.status = status;
    }

    /**
     * REturns movie ID of a movie
     * @return the movie_id
     */
    public int getMovie_id() {
        return movie_id;
    }

    /**
     * Sets movie ID of a movie
     * @param movie_id the movie_id to set
     */
    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    /**
     * Returns status of a movie.
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status of a movie.
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
