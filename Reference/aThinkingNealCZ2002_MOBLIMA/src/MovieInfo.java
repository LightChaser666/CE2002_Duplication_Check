
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class describing the information of movie.
 */
public class MovieInfo implements Serializable {
    /** Serialization
     */
    private static final long serialVersionUID = 1L;
    private int movieId;
    private String title;
    // private Cineplex[] cineplexes;
    // change array to arrayList for future operation
    private ArrayList<Integer> cineplexes; // store a list of cineplexes ID
    private String showingStatus;
    private String synopsis;
    private boolean support2D;
    private boolean support3D;
    private boolean isBlockbluster;
    private String director;
    // private String cast;
    // change String to arrayList of String for future operation
    private ArrayList<String> cast;
    private int numOfSales;
    private float overAllRating;
    private ArrayList<RevNRat> reviewsAndRating;
    private String ageLimit;
    private String movieCategory;

    /**
     * Instantiates a new Movie info.
     *
     * @param movieid        the movieid
     * @param title          the title
     * @param showingStatus  the showing status
     * @param synopsis       the synopsis
     * @param cineplexes     the cineplexes
     * @param support2D      the support 2 d
     * @param support3D      the support 3 d
     * @param isBlockbluster the is blockbluster
     * @param director       the director
     * @param cast           the cast
     * @param ageLimit       the age limit
     * @param movieCategory  the movie category
     */
    public MovieInfo(int movieid, String title, String showingStatus, String synopsis, ArrayList<Integer> cineplexes, boolean support2D,
                     boolean support3D, boolean isBlockbluster, String director, ArrayList<String> cast, String ageLimit, String movieCategory) {
        this.movieId = movieid;
        this.title = title;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.cineplexes = cineplexes;
        this.support2D = support2D;
        this.support3D = support3D;
        this.isBlockbluster = isBlockbluster;
        this.director = director;
        this.cast = cast;
        this.overAllRating = 0;
        this.numOfSales = 0;
        reviewsAndRating = new ArrayList<>();
        this.ageLimit = ageLimit;
        this.movieCategory = movieCategory;
    }


    /**
     * Gets age limit.
     *
     * @return the age limit
     */
    public String getAgeLimit() {
        return ageLimit;
    }

    /**
     * Sets age limit.
     *
     * @param agelimit the agelimit
     */
    public void setAgeLimit(String agelimit) {
        this.ageLimit = agelimit;
    }

    /**
     * Gets movie category.
     *
     * @return the movie category
     */
    public String getMovieCategory() {
        return movieCategory;
    }

    /**
     * Sets movie category.
     *
     * @param movieCategory the movie category
     */
    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    /**
     * Gets movie id.
     *
     * @return the movie id
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Sets movie id.
     *
     * @param movieId the movie id
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets cineplexes.
     *
     * @return the cineplexes
     */
    public ArrayList<Integer> getCineplexes() {
        return cineplexes;
    }


    /**
     *
     * @param cineplexDB    the cineplex DB used
     * @param currentDate   current date
     * @param currentTime   current time
     * @return returns the cineplex that are showing upcoming sessions of this movie
     */
    public ArrayList<Integer> displayCineplexes(CineplexDB cineplexDB, String currentDate, String currentTime) {
        ArrayList<Integer> rst = new ArrayList<>();
        for (int i = 0; i < cineplexes.size(); i++) {
            Cineplex c = cineplexDB.getCineplexByID(cineplexes.get(i));
            if (c.getMovieScheduleByID(this.getMovieId(), currentDate, currentTime).size() != 0) {
                rst.add(c.getCineplexID());
            }
        }
        return rst;
    }


    /**
     * Sets cineplexes.
     *
     * @param cineplexes the cineplexes
     */
    public void setCineplexes(ArrayList<Integer> cineplexes) {
        this.cineplexes = cineplexes;
    }

    /**
     * Gets showing status.
     *
     * @return the showing status
     */
    public String getShowingStatus() {
        return showingStatus;
    }

    /**
     * Sets showing status.
     *
     * @param showingStatus the showing status
     */
    public void setShowingStatus(String showingStatus) {
        this.showingStatus = showingStatus;
    }

    /**
     * Gets synopsis.
     *
     * @return the synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Sets synopsis.
     *
     * @param synopsis the synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Is support 2 d boolean.
     *
     * @return the boolean
     */
    public boolean isSupport2D() {
        return support2D;
    }

    /**
     * Sets num of sales.
     *
     * @param numOfSales the num of sales
     */
    public void setNumOfSales(int numOfSales) {
        this.numOfSales = numOfSales;
    }

    /**
     * Sets over all rating.
     *
     * @param overAllRating the over all rating
     */
    public void setOverAllRating(float overAllRating) {
        this.overAllRating = overAllRating;
    }

    /**
     * Sets reviews and rating.
     *
     * @param reviewsAndRating the reviews and rating
     */
    public void setReviewsAndRating(ArrayList<RevNRat> reviewsAndRating) {
        this.reviewsAndRating = reviewsAndRating;
    }

    /**
     * Sets support 2 d.
     *
     * @param support2D the support 2 d
     */
    public void setSupport2D(boolean support2D) {
        this.support2D = support2D;
    }

    /**
     * Is support 3 d boolean.
     *
     * @return the boolean
     */
    public boolean isSupport3D() {
        return support3D;
    }

    /**
     * Sets support 3 d.
     *
     * @param support3D the support 3 d
     */
    public void setSupport3D(boolean support3D) {
        this.support3D = support3D;
    }

    /**
     * Is blockbluster boolean.
     *
     * @return the boolean
     */
    public boolean isBlockbluster() {
        return isBlockbluster;
    }

    /**
     * Sets blockbluster.
     *
     * @param blockbluster the blockbluster
     */
    public void setBlockbluster(boolean blockbluster) {
        isBlockbluster = blockbluster;
    }

    /**
     * Gets director.
     *
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets director.
     *
     * @param director the director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Gets cast.
     *
     * @return the cast
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * Sets cast.
     *
     * @param cast the cast
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * Gets num of sales.
     *
     * @return the num of sales
     */
    public int getNumOfSales() {
        return numOfSales;
    }

    /**
     * Gets over all rating.
     *
     * @return the over all rating
     */
    public float getOverAllRating() {

        if (this.getReviewsAndRating().size() > 1) return overAllRating;
        else return -1;
    }

    /**
     * Return an ArrayList that contains reviews and rating
     *
     * @return ArrayList of reviews and rating
     */
    public ArrayList<RevNRat> getReviewsAndRating() {
        return reviewsAndRating;
    }

    /**
     * Add one record of reviews and rating
     *
     * @param userID the userID of the one who writes the review and rating
     */
    public void addReviewRating(int userID) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please input your comment: ");
        String review = s.nextLine();
        System.out.println("Please input your rating: (0-5) ");
        Float rating = Float.parseFloat(s.nextLine());
        RevNRat newReview = new RevNRat(userID, review, rating);
        this.overAllRating = ((this.overAllRating * this.reviewsAndRating.size()) + rating) / (this.reviewsAndRating.size() + 1);
        reviewsAndRating.add(newReview);
        System.out.println("Your review has been posted successfully!");
    }

    /**
     * Display all information of the movie
     */
    public void displayMovieInfo() {
        StringBuilder temp = new StringBuilder();
        temp.append("Movie Title: ").append(getTitle());
        temp.append("\nMovie ID: ").append(getMovieId());
        temp.append("\nMovie Category: ").append(getMovieCategory());
        temp.append("\nMovie Age Limit: ").append(getAgeLimit());
        temp.append("\nShowing Status: ").append(getShowingStatus());
        temp.append("\nSynopsis: ").append(getSynopsis());
        temp.append("\nAvailable types: " + "3D: ").append(isSupport3D()).append(" 2D: ").append(isSupport2D());
        temp.append("\nBlockbuster: ").append(isBlockbluster());
        temp.append("\nDirector: ").append(getDirector());

        temp.append("\nCast: ");
        for (int i = 0; i < getCast().size(); i++) {
            temp.append(getCast().get(i));
            temp.append("; ");
        }

        temp.append("\nNumber Of Sales: ").append(getNumOfSales());
        if (getOverAllRating() != -1)
            temp.append("\nOverall Rating: ").append(getOverAllRating());
        else
            temp.append("\nOverall Rating: N.A.");

        if (reviewsAndRating.size() == 0)
            temp.append("\nReviews: N.A.");
        else
            temp.append("\nReviews: ");
        System.out.println(temp);
        for (int i = 0; i < this.reviewsAndRating.size(); i++)
            reviewsAndRating.get(i).displayReviewRating();
    }

    public void deleteRevNRatByMovieGoerID(int movieGoerID){
        int length = this.reviewsAndRating.size();
        for (int i = 0; i < length; i++){
            RevNRat temp = this.reviewsAndRating.get(i);
            if (temp.movieGoerID == movieGoerID) {
                this.reviewsAndRating.remove(i);
                System.out.println("This movieGoerID is found!");
                return;
            }
        }
        System.out.println("This movieGoerID is not found!");
        return;
    }


    /**
     * Class that contains review (String), rating (float) and movieGoerID (int)
     */
    private class RevNRat implements Serializable {
        private static final long serialVersionUID = 1L;
        private int movieGoerID;
        private String review;
        private float rating;

        /**
         * Constructs a RevNRat with specified id, review instance and rating
         *
         * @param id     RevNRat ID
         * @param review a review instance
         * @param rating a rating instance
         */
        public RevNRat(int id, String review, float rating) {
            this.movieGoerID = id;
            this.review = review;
            this.rating = rating;
        }

        /**
         * Display a RevNRat : MovieGoerID, Review and Rating
         */
        public void displayReviewRating() {
            System.out.println("    MoviegoerID: " + movieGoerID);
            System.out.println("    Review: " + review);
            System.out.println("    Rating: " + rating + '\n');
        }
    }
}
