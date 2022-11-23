import java.util.ArrayList;
import java.io.Serializable;

/**
 * The class representing the movieGoer who goes to cinema to watch the movie.
 */
public class MovieGoer implements Serializable {

    /**
     * The Name.
     */
    protected String name;
    /**
     * The Movie goer id.
     */
    protected int movieGoerID;
    /**
     * The Mobile number.
     */
    protected int mobileNumber;
    /**
     * The Email address.
     */
    protected String emailAddress;
    /**
     * The Age.
     */
    protected int age;
    /**
     * The History.
     */
    protected ArrayList <PaymentRecord> History;


    /**
     * Instantiates a new Movie goer.
     *
     * @param name         the name
     * @param movieGoerID  the movie goer id
     * @param mobileNumber the mobile number
     * @param emailAddress the email address
     * @param age          the age
     */
    public MovieGoer(String name, int movieGoerID, int mobileNumber, String emailAddress, int age){
        this.name = name;
        this.movieGoerID = movieGoerID;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.age = age;
    }

    /**
     * Get the booking history of one movie goer
     */
    public void getHistory(){
        PaymentRecord temp;
        int len = History.size();
        if (len == 0) {
            System.out.println("This is no ticket history.");
        }
        else{
            int i;
            for (i = 0; i < len; i++){
                History.get(i).printRecord();
            }
        }
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets mobile number.
     *
     * @param mobileNumber the mobile number
     */
    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets history.
     *
     * @param history the history
     */
    public void setHistory(ArrayList <PaymentRecord> history) {
        History = history;
    }

    /**
     * Gets mobile number.
     *
     * @return the mobile number
     */
    public int getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }



}
