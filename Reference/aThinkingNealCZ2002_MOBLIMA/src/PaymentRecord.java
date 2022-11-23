
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class containing users' Payment record.
 */
public class PaymentRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private String TID;
    //XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)
    private int movieGoerID;
    private int movieID;
    private int cinemaID;
    private int cineplexID;
    private int amountOfTickets;
    private String movieDateStartTime;
    private ArrayList<String> seatID;
    private float totalCost;
    private boolean canceled;

    /**
     * Instantiates a new Payment record.
     *
     * @param TID                the tid
     * @param movieGoerID        the movie goer id
     * @param movieID            the movie id
     * @param cinemaID           the cinema id
     * @param cineplexID         the cineplex id
     * @param amountOfTickets    the amount of tickets
     * @param seatID             the seat id
     * @param totalCost          the total cost
     * @param movieDateStartTime the movie date start time
     * @param canceled           the canceled
     */
    public PaymentRecord(String TID, int movieGoerID, int movieID, int cinemaID, int cineplexID, int amountOfTickets, ArrayList<String> seatID, float totalCost, String movieDateStartTime, Boolean canceled) {
        this.TID = TID;
        this.movieGoerID = movieGoerID;
        this.movieID = movieID;
        this.cinemaID = cinemaID;
        this.cineplexID = cineplexID;
        this.amountOfTickets = amountOfTickets;
        this.seatID = seatID;
        this.totalCost = totalCost;
        this.canceled = canceled;
        this.movieDateStartTime = movieDateStartTime;
    }

    /**
     * Gets movie date start time.
     *
     * @return the movie date start time
     */
    public String getMovieDateStartTime() {
        return movieDateStartTime;
    }

    /**
     * Sets movie date start time.
     *
     * @param movieDateStartTime the movie date start time
     */
    public void setMovieDateStartTime(String movieDateStartTime) {
        this.movieDateStartTime = movieDateStartTime;
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
     * Sets cineplex id.
     *
     * @param cineplexID the cineplex id
     */
    public void setCineplexID(int cineplexID) {
        this.cineplexID = cineplexID;
    }

    /**
     * Gets canceled.
     *
     * @return the canceled
     */
    public Boolean getCanceled() {
        return canceled;
    }

    /**
     * Sets canceled.
     *
     * @param canceled the canceled
     */
    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    /**
     * Gets seat id.
     *
     * @return the seat id
     */
    public ArrayList<String> getSeatID() {
        return seatID;
    }

    /**
     * Sets seat id.
     *
     * @param seatID the seat id
     */
    public void setSeatID(ArrayList<String> seatID) {
        this.seatID = seatID;
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
     * Sets cinema id.
     *
     * @param cinemaID the cinema id
     */
    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    /**
     * Gets tid.
     *
     * @return the tid
     */
    public String getTID() {
        return TID;
    }

    /**
     * Sets tid.
     *
     * @param TID the tid
     */
    public void setTID(String TID) {
        this.TID = TID;
    }

    /**
     * Gets movie goer id.
     *
     * @return the movie goer id
     */
    public int getMovieGoerID() {
        return movieGoerID;
    }

    /**
     * Sets movie goer id.
     *
     * @param movieGoerID the movie goer id
     */
    public void setMovieGoerID(int movieGoerID) {
        this.movieGoerID = movieGoerID;
    }

    /**
     * Gets movie id.
     *
     * @return the movie id
     */
    public int getMovieID() {
        return movieID;
    }

    /**
     * Sets movie id.
     *
     * @param movieID the movie id
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }


    /**
     * Gets amount of tickets.
     *
     * @return the amount of tickets
     */
    public int getAmountOfTickets() {
        return amountOfTickets;
    }

    /**
     * Sets amount of tickets.
     *
     * @param amountOfTickets the amount of tickets
     */
    public void setAmountOfTickets(int amountOfTickets) {
        this.amountOfTickets = amountOfTickets;
    }

    /**
     * Gets total cost.
     *
     * @return the total cost
     */
    public float getTotalCost() {
        return totalCost;
    }

    /**
     * Sets total cost.
     *
     * @param totalCost the total cost
     */
    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }


    /**
     * print the information of one PaymentRecord
     */
    public void printRecord() {
        String temp = "";
        temp += " |TransactionID: " + getTID();
        temp += " |MovieGoerID: " + getMovieGoerID();
        temp += " |MovieID: " + getMovieID();
        temp += " |CinemaID: " + getCinemaID();
        temp += " |MovieDateTime: " + getMovieDateStartTime();
        temp += " |No.Tickets: " + getAmountOfTickets();
        temp += " |SeatIDs: ";
        for (int i = 0; i < seatID.size(); i++)
            temp += seatID.get(i) + ' ';
        temp += " |TotalPrice: $" + getTotalCost();
        System.out.println(temp);
    }
}