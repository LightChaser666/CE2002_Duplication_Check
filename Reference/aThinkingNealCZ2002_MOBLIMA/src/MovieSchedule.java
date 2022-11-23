import java.io.Serializable;

/**
 * The moviescheduel shows how a movie is shecduled, including time,movieinfo,duration,layout,cinema etc.
 */
public class MovieSchedule implements Serializable {
    private static final long serialVersionUID = 1L;
    private String dateStartTime;
    private int movieID;
    private String movieName;
    private boolean is3D;
    private boolean isBlockbuster;
    private double duration;
    private Layout layout;
    private int cinemaID;
    private int cineplexID;
    private Cinema.CinemaClass cinemaClass;

    /**
     * Instantiates a new Movie schedule.
     *
     * @param dateStartTime the date start time
     * @param cineplexID    the cineplex id
     * @param movieID       the movie id
     * @param movieName     the movie name
     * @param is3D          the is 3 d
     * @param isBlockbuster the is blockbuster
     * @param duration      the duration
     * @param cinemaID      the cinema id
     * @param cinemaClass   the cinema class
     */
    public MovieSchedule(String dateStartTime, int cineplexID, int movieID, String movieName, boolean is3D, boolean isBlockbuster, double duration, int cinemaID, Cinema.CinemaClass cinemaClass) {
        this.cineplexID = cineplexID;
        this.cinemaID = cinemaID;
        this.dateStartTime = dateStartTime;
        this.movieID = movieID;
        this.movieName = movieName;
        this.is3D = is3D;
        this.isBlockbuster = isBlockbuster;
        this.duration = duration;
        this.layout = new Layout(cinemaID,cinemaClass);
        this.cinemaClass = cinemaClass;
    }

    /**
     * Gets cineplex id.
     *
     * @return the cineplex id
     */
    public int getCineplexID() {
        return cineplexID;
    }

    /**
     * Gets cinema id.
     *
     * @return the cinema id
     */
    public int getCinemaID() {
        return cinemaID;
    }

    /**
     * Gets cinema class.
     *
     * @return the cinema class
     */
    public Cinema.CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Gets date start time.
     *
     * @return the date start time
     */
    public String getDateStartTime() {
        return this.dateStartTime;
    }

    /**
     * Set movie id.
     *
     * @param newMovieID the new movie id
     */
    public void setMovieID(int newMovieID){this.movieID = newMovieID;}

    /**
     * Set movie name.
     *
     * @param newName the new name
     */
    public void setMovieName(String newName){this.movieName = newName;}

    /**
     * Sets date start time.
     *
     * @param dateStartTime the date start time
     */
    public void setDateStartTime(String dateStartTime) {
        this.dateStartTime = dateStartTime;
    }

    /**
     * Gets is 3 d.
     *
     * @return the is 3 d
     */
    public boolean getIs3D() {
        return this.is3D;
    }

    /**
     * Sets is 3 d.
     *
     * @param is3D the is 3 d
     */
    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    /**
     * Gets is blockbuster.
     *
     * @return the is blockbuster
     */
    public boolean getIsBlockbuster() {
        return this.isBlockbuster;
    }

    /**
     * Get movie id int.
     *
     * @return the int
     */
    public int getMovieID(){return this.movieID;}

    /**
     * Get movie name string.
     *
     * @return the string
     */
    public String getMovieName(){return this.movieName;}

    /**
     * Sets is blockbuster.
     *
     * @param isBlockbuster the is blockbuster
     */
    public void setIsBlockbuster(boolean isBlockbuster) {
        this.isBlockbuster = isBlockbuster;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public double getDuration() {
        return this.duration;
    }

    /**
     * Sets duration.
     *
     * @param newDuration the new duration
     */
    public void setDuration(double newDuration) {
        this.duration = newDuration;
    }

    /**
     * Gets layout.
     *
     * @return the layout
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * Display movie record.
     */
    public void displayMovieRecord()
    {
        System.out.println(dateStartTime + ": " + movieName + "(" + movieID + ") " + "|3D: " + is3D + " |Blockbuster: " + isBlockbuster + " |Duration: " + duration + " |CinemaID: " + this.getCinemaID() + " |CinemaClass: " + this.getCinemaClass());
    }

    /**
     * Cancel booking.
     *
     * @param seatID the seat id
     */
    public void cancelBooking(String seatID) {
        this.getLayout().cancelSeat(seatID);
    }

    /**
     * Book seat.
     *
     * @param seatID the seat id
     */
    public void bookSeat(String seatID) {
        this.getLayout().bookSeat(seatID);
    }

}
