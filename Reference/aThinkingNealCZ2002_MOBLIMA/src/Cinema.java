import java.io.Serializable;
import java.util.*;

/**
 * This class contains a information about movie schedules in this cinema
 */
public class Cinema implements Serializable {
    private static final long serialVersionUID = 1L;
    // hashmap: key: "dateStarttime", value: MovieSchedule

    private HashMap<String, MovieSchedule> cinemaSchedule = new HashMap<>();
    private int cineplexID;
    private int cinemaID;
    private CinemaClass cinemaClass;

    /**
     * The enum Cinema class, including Platinum, Golden, Normal.
     */
    public enum CinemaClass {
        /**
         * Platinum cinema class.
         */
        platinum,
        /**
         * Golden cinema class.
         */
        golden,
        /**
         * Normal cinema class.
         */
        normal
    }


    /**
     * Returns a hashMap containing cinema schedule.
     *
     * @return the cinema schedule
     */
    public HashMap<String, MovieSchedule> getCinemaSchedule() {
        return cinemaSchedule;
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
     * Gets cinema class.
     *
     * @return the cinema class
     */
    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Sets cinema class.
     *
     * @param cinemaClass the cinema class
     */
    public void setCinemaClass(CinemaClass cinemaClass) {
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
     * Sets cineplex id.
     *
     * @param cineplexID the cineplex id
     */
    public void setCineplexID(int cineplexID) {
        this.cineplexID = cineplexID;
    }

    /**
     * Initialise with a file containing all the moviescheduleinfo
     *
     * @param cinemaID    the cinema id
     * @param cineplexID  the cineplex id
     * @param cinemaClass the cinema class
     */
    public Cinema(int cinemaID, int cineplexID, CinemaClass cinemaClass) {
        this.cinemaID = cinemaID;
        this.cineplexID = cineplexID;
        this.cinemaClass = cinemaClass;
    }

    /**
     * Allow staff to add a single record of movie schedule
     */
    public void addRecord() {
        System.out.println("Added");
    }

    /**
     * Add a record.
     *
     * @param m the movieinfo added to the record
     */
    public void addRecord(MovieInfo m) {
        Scanner s = new Scanner(System.in);
        String dateStartTime;
        double duration;
        boolean is3D;
        System.out.println("Please enter the date in this format: yyyy-mm-dd.");
        dateStartTime = s.nextLine();
        System.out.println("Please enter the start time of the movie in this format: hh-mm.");
        dateStartTime += "-" + s.nextLine();

        String movieName = m.getTitle();
        System.out.println("This movie supports 3D: " + m.isSupport3D());
        System.out.println("This movie supports 2D: " + m.isSupport2D());
        System.out.println("Is this movie session 3D? Y/N");
        String ans = s.nextLine();
        is3D = ans.equals("Y") || ans.equals("y");

        System.out.println("How long is the duration of this movie? Answer in this format: 2.5");
        duration = Double.parseDouble(s.nextLine());

        this.cinemaSchedule.put(dateStartTime, new MovieSchedule(dateStartTime, cineplexID, m.getMovieId(), movieName, is3D, m.isBlockbluster(), duration, cinemaID, cinemaClass));
        System.out.println("Successfully added!");
    }


    /**
     * Allow staff to delete a single record of movie schedule
     *
     * @param movieID ID of the movie
     */
    public void deleteRecord(int movieID) {
        displayAllSchedulesOfMovie(movieID);
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the date and time of the movie schedule you want to delete in this format: YYYY-mm-dd-hh-mm");
        String key = s.nextLine();
        HashMap<String, MovieSchedule> rst = getAllSchedulesOfMovie(movieID);
        if (rst.keySet().contains(key)) {
            this.cinemaSchedule.remove(key);
            System.out.println("Successfully deleted!");
        } else {
            System.out.println("Invalid datetime! Try again!");
        }


    }


    /**
     * Allow staff to update a single record of movie schedule
     * Only allow staff to update
     *
     * @param movieID ID of the movie
     */
    public void updateRecord(int movieID) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the date and time of the movie schedule you want to update in this format: YYYY-mm-dd-hh-mm");
        String key = s.nextLine();
        HashMap<String, MovieSchedule> rst = getAllSchedulesOfMovie(movieID);
        if (rst.keySet().contains(key)) {
            MovieSchedule newRecord = cinemaSchedule.get(key);
            this.cinemaSchedule.remove(key); // delete original record
            System.out.println("Please input the new date YYYY-mm-dd:");
            String date = s.nextLine();
            System.out.println("Please input the new time HH-MM:");
            String dateStartTime = date + '-' + s.nextLine();
            newRecord.setDateStartTime(dateStartTime);
            this.cinemaSchedule.put(newRecord.getDateStartTime(), newRecord);
            System.out.println("Successfully updated!");
        } else {
            System.out.println("Invalid datetime! Try again!");
        }

    }

    /**
     * Display all records in this cinema
     */
    public void displayAllRecords() {
        for (String key : cinemaSchedule.keySet()) {
            cinemaSchedule.get(key).displayMovieRecord();
        }
    }


    /**
     * Once user has selected movie, location, display all available dates of this movie in this cinema
     * @param movieID the movie id
     */
    public void displayAllSchedulesOfMovie(int movieID) {
        for (String key : cinemaSchedule.keySet()) {
            if (cinemaSchedule.get(key).getMovieID() == movieID)
                System.out.println("Movie ID: " + movieID + ' ' + cinemaSchedule.get(key).getDateStartTime()); // print out date in "YYYY-mm-dd"
        }
    }


//    public HashSet<String> getAvailableDates(int movieID, String currentDate, String currentTime) {
//        HashSet<String> toPrint = new HashSet<>();
//        for (String key : cinemaSchedule.keySet()) {
//            if (cinemaSchedule.get(key).getMovieID() == movieID) {
//                if (cinemaSchedule.get(key).getDateStartTime().compareTo(currentDate + '-' + currentTime) > 0)  //only movie schedules in the future are displayed
//                    toPrint.add(cinemaSchedule.get(key).getDateStartTime().substring(0, 10));
//            } // print out date in "YYYY-mm-dd"
//        }
//        return toPrint;
////
//
//
//    }

    /**
     * get all schedules of a movie in this cinema
     *
     * @param movieID movieID of s aschedule
     * @return returns a hashmap Key: String, Value: Movieschedule that containing schedules of a movie
     */
    public HashMap<String, MovieSchedule> getAllSchedulesOfMovie(int movieID) {
        HashMap<String, MovieSchedule> rst = new HashMap<>();
        for (String key : cinemaSchedule.keySet()) {
            if (cinemaSchedule.get(key).getMovieID() == movieID)
                rst.put(key, cinemaSchedule.get(key));
        }
        return rst;
    }

    /**
     * Gets movie schedule by date starttime.
     *
     * @param dateStarttime the date starttime
     * @return the by date starttime
     */
    public MovieSchedule getByDateStarttime(String dateStarttime) {
        return cinemaSchedule.get(dateStarttime);
    }

}



