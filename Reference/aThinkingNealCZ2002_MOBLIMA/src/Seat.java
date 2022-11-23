import java.io.Serializable;

/**
 * The class containing the information of a seat, like id, state, also providing some operating methods.
 */
public class Seat implements Serializable {
    private static final long serialVersionUID = 1L;
    private String seatId;
    private boolean isOccupied;
    private String seatIDAlphabets = "ABCDEFGHIJ";

    /**
     * Instantiates a new Seat.
     *
     * @param x           the x
     * @param y           the y
     * @param sisOccupied the sis occupied
     */
    Seat(int x, int y, boolean sisOccupied) {
        this.seatId = seatIDAlphabets.charAt(x) + Integer.toString(y);
        isOccupied = sisOccupied;
    }

    /**
     * Get occu boolean.
     *
     * @return the boolean
     */
    public  boolean GetOccu(){
        return isOccupied;}

    /**
     * Get id string.
     *
     * @return the string
     */
    public String GetId(){
        return seatId;
    }

    /**
     * Book seat boolean.
     *
     * @return the boolean
     */
    public boolean bookSeat(){
        if (isOccupied==true){
            System.out.println("This seat is already occupied! Book failed!");
            return false;
        }
        else{
            isOccupied=true;
            return true;
        }
    }

    /**
     * Cancel booking.
     */
    public void cancelBooking(){
        isOccupied=false;
        
    }
}