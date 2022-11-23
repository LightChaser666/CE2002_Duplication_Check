// This class is incomplete yet

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

/**
 * This class contains information about cinemas in this cineplex
 */
public class Cineplex implements Serializable {
    private static final long serialVersionUID = 1L;
    private int cineplexID;
    private String name;
    private String location;
    private HashMap<Integer, Cinema> cinemaHashMap = new HashMap<Integer, Cinema>();

    /**
     * Instantiates a new Cineplex.
     *
     * @param cineplexID the cineplex id
     * @param name       the name
     * @param location   the location
     */
    public Cineplex(int cineplexID, String name, String location) {
        this.cineplexID = cineplexID;
        this.name = name;
        this.location = location;
    }


    // the user already selects the movieID, date, and time already.
    // we assume that one movie will only exist in one MovieSchedule at a specific time.

    /**
     * returns upcoming movie schedules according to movie time and time
     *
     * @param movieID     the movie id
     * @param currentDate date when the user is booking ticket
     * @param currentTime time when the user is booking ticket
     * @return schedules a HashMap of MovieSchedules which will display the required Movie in the future
     */
    public HashMap<String, MovieSchedule> getMovieScheduleByID(int movieID, String currentDate, String currentTime) {
        HashMap<String, MovieSchedule> schedules = new HashMap<>();
        for (Cinema c : cinemaHashMap.values()) {
            HashMap<String, MovieSchedule> m = c.getCinemaSchedule();
            for (String key : m.keySet()) {
                if (m.get(key).getMovieID() == movieID) {
                    if (m.get(key).getDateStartTime().compareTo(currentDate + '-' + currentTime) > 0)  //only movie schedules in the future are displayed
                        schedules.put(key, m.get(key));
                }
            }
        }
        return schedules;
    }

    /**
     * Allow the staff to add one Cinema to the Database by manual input
     */
    public void addCinema() {
        Scanner sc = new Scanner(System.in);
        int cinemaID;
        Cinema.CinemaClass cinemaClass = Cinema.CinemaClass.normal; // initialize MovieClass to normal
        int temp;
        System.out.println("Please enter the cinema ID");
        cinemaID = sc.nextInt();
        System.out.println("Please enter the cinemaClass (enter corresponded integer): ");
        System.out.println("1. Normal");
        System.out.println("2. Golden");
        System.out.println("3. Platinum");
        temp = sc.nextInt();
        if (temp == 1) {
            cinemaClass = Cinema.CinemaClass.normal;
        } else if (temp == 2) {
            cinemaClass = Cinema.CinemaClass.golden;
        } else if (temp == 3) {
            cinemaClass = Cinema.CinemaClass.platinum;
        } else {
            System.out.println("You have entered an invalid class");
        }

        Cinema cinemaAdded = new Cinema(cinemaID = cinemaID, cineplexID, cinemaClass = cinemaClass);
        cinemaHashMap.put(cinemaID, cinemaAdded);
        System.out.println("You have successfully added this cinema to this cineplex");
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets cinemas.
     *
     * @return the cinemas
     */
    public ArrayList<Cinema> getCinemas() {
        return new ArrayList<>(cinemaHashMap.values());
    }

    /**
     * Gets cinema by cinema id.
     *
     * @param cinemaID the cinema id
     * @return the cinema by cinema id
     */
    public Cinema getCinemaByCinemaID(int cinemaID) {
        if (cinemaHashMap.containsKey(cinemaID))
            return cinemaHashMap.get(cinemaID);
        else
            return null;
    }

    /**
     * Checks whether the input cinemaID is a valid cinema ID
     *
     * @param cinemaID input cinemaID
     * @return boolean of whether the cinemaID is valid
     */
    public boolean containCinemaByCinemaID(int cinemaID) {
        return cinemaHashMap.containsKey(cinemaID);
    }


}
