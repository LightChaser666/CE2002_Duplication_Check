import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Class is provided for the staff to operate.
 */
public class StaffOperations {

    private CineplexDB cineplexDB;
    private MovieInfoDB movieInfoDB; //!! if use Database,
    private DateDB date;
    private PriceTable priceTable;
    private StaffRecordDB staffRecordDB;

    /**
     * Instantiates a new Staff operations.
     *
     * @param cineplexDBFile    the cineplex db file
     * @param movieInfoDBFile   the movie info db file
     * @param dateFile          the date file
     * @param priceTableFile    the price table file
     * @param staffRecordDBFile the staff record db file
     */
    public StaffOperations(String cineplexDBFile, String movieInfoDBFile, String dateFile, String priceTableFile, String staffRecordDBFile) {

        this.cineplexDB = new CineplexDB(cineplexDBFile);
        this.movieInfoDB = new MovieInfoDB(movieInfoDBFile);
        this.date = new DateDB(dateFile);
        this.priceTable = new PriceTable(priceTableFile);
        this.staffRecordDB = new StaffRecordDB(staffRecordDBFile);
        if (!staffRecordDB.login())
            System.exit(0);
        startOperations();
    }

    private void displayMainMenu() {
        System.out.println("==========================================================");
        System.out.println("Please enter your choice: ");
        System.out.println("1. Configure the ticket prices system setting.");
        System.out.println("2. Configure the holiday dates system setting.");
        System.out.println("3. List all movies");
        System.out.println("4. List all schedules of a particular movie");
        System.out.println("5. Enter information about forthcoming movies");
        System.out.println("6. Update information about movies");
        System.out.println("7. Show detailed info of a movie");
        System.out.println("8. Add Movie Schedules for Currently Showing Movies");
        System.out.println("9. Update Movie Schedules for Currently Showing Movies");
        System.out.println("10. Delete Movie Schedules for Currently Showing Movies");
        System.out.println("11. Delete movies.");
        System.out.println("12. List the current top 5 ranking movies by ticket sales.");
        System.out.println("13. List the current top 5 ranking movies by rating.");
        System.out.println("14. Update your staff password.");
        System.out.println("15. Add a Staff account for a new staff");
        System.out.println("16. Exit");
        System.out.println("==========================================================");
    }

    private void startOperations() {
        try {

            displayMainMenu();
            Scanner s = new Scanner(System.in);
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    configureTicketPrice();
                    break;
                case 2:
                    configureHolidaySetting();
                    break;
                case 3:
                    listAllMovies();
                    break;
                case 4:
                    listAllSchedulesOfMovie();
                    break;
                case 5:
                    enterNewMovieInfo();
                    break;
                case 6:
                    updateMovieInfo();
                    break;
                case 7:
                    checkMovieInfo();
                    break;
                case 8:
                    addMovieSchedule();
                    break;
                case 9:
                    updateMovieSchedule();
                    break;
                case 10:
                    deleteMovieSchedule();
                    break;
                case 11:
                    deleteMovies();
                    break;
                case 12:
                    listCurrentTopBySales();
                    break;
                case 13:
                    listCurrentTopByRating();
                    break;
                case 14:
                    changePassword();
                    break;
                case 15:
                    addAccount();
                    break;
                case 16:
                    System.out.println("Have a nice day! Good bye!");
                    saveToFile();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again");
                    pressToReturn();
            }
        }
        catch (NullPointerException e){
            //System.out.println(e.getMessage());
            //e.printStackTrace();
            System.out.println("You have entered an invalid input, please try again!");
            startOperations();
        }
        catch (RuntimeException e){
            //System.out.println(e.getMessage());
            //e.printStackTrace();
            System.out.println("You have entered an invalid input, please try again!");
            startOperations();
        }
    }


    private void saveToFile() {
        cineplexDB.saveToFile();
        movieInfoDB.saveToFile();
        date.saveToFile();
        priceTable.saveToFile();
        staffRecordDB.saveToFile();
    }

    private void changePassword() {
        staffRecordDB.updateRecord();
        staffRecordDB.saveToFile();
        pressToReturn();
    }

    private void addAccount() {
        staffRecordDB.addRecord();
        staffRecordDB.saveToFile();
        pressToReturn();
    }

    private void configureTicketPrice() {
        this.priceTable.updatePriceTable();
        priceTable.saveToFile();
        pressToReturn();
    }

    private void configureHolidaySetting() {
        this.date.addHoliday();
        pressToReturn();
    }

    private void listAllMovies() {
        movieInfoDB.listAllMovies();
        pressToReturn();
    }

    private void listAllSchedulesOfMovie() {
        movieInfoDB.listAllSchedulesOfMovie(cineplexDB);
        pressToReturn();
    }

    private void enterNewMovieInfo() {
        movieInfoDB.addRecord(cineplexDB);
        movieInfoDB.saveToFile();
        pressToReturn();
    }

    private void updateMovieInfo() {
        movieInfoDB.updateRecord(cineplexDB);
        movieInfoDB.saveToFile();
        pressToReturn();
    }

    private void checkMovieInfo() {
        System.out.println("Which movie detail do you want to view? Enter movie ID.");
        Scanner s = new Scanner(System.in);
        int movieID = s.nextInt();
        if (movieInfoDB.checkMovieIDExists(movieID)) {
            MovieInfo movieInfo = movieInfoDB.getMovieInfoByMovieID(movieID);
            movieInfo.displayMovieInfo();
            pressToReturn();
        } else {
            System.out.println("This movieID does not exist!");
            pressToReturn();
        }

    }

    private void addMovieSchedule() {
        System.out.println("Add new schedule for which movie? Enter movie ID");
        Scanner s = new Scanner(System.in);
        int movieID = s.nextInt();
        if (movieInfoDB.checkMovieIDExists(movieID)) {
            MovieInfo info = movieInfoDB.getMovieInfoByMovieID(movieID);
            System.out.println("These are the cineplexes schedules to show this movie: ");
            ArrayList<Integer> validCineplexes = info.getCineplexes();
            for (Integer cineplexID : validCineplexes) {
                System.out.println("Cineplex ID: " + cineplexID);
            }
            System.out.println("Add new schedule for which cineplex? Enter cineplex ID");
            int cineplexID = s.nextInt();
            if (validCineplexes.contains(cineplexID)) {
                System.out.println("Add new schedule for which cinema? Enter cinema ID");
                int cinemaID = s.nextInt();
                if (cineplexDB.getCineplexByID(cineplexID).containCinemaByCinemaID(cinemaID)) {
                    cineplexDB.getCineplexByID(cineplexID).getCinemaByCinemaID(cinemaID).addRecord(movieInfoDB.getMovieInfoByMovieID(movieID));
                    cineplexDB.saveToFile();
                    pressToReturn();
                } else {
                    System.out.println("Invalid cinema ID!");
                    pressToReturn();
                }
            } else {
                System.out.println("Invalid cineplex ID!");
                pressToReturn();
            }


        } else {
            System.out.println("This movieID does not exist!");
            pressToReturn();
        }

    }

    private void updateMovieSchedule() {
        System.out.println("Update schedule for which movie? Enter movie ID");
        Scanner s = new Scanner(System.in);
        int movieID = s.nextInt();
        if (movieInfoDB.checkMovieIDExists(movieID)) {
            MovieInfo info = movieInfoDB.getMovieInfoByMovieID(movieID);
            cineplexDB.displayAllMovieSchedules(info);
            System.out.println("which cineplex? Enter cineplex ID");
            int cineplexID = s.nextInt();
            if (cineplexDB.findCineplexByID(cineplexID) && info.getCineplexes().contains(cineplexID)) {
                System.out.println("which cinema? Enter cinema ID");
                int cinemaID = s.nextInt();
                // if it'd a valid cinema ID
                if (cineplexDB.getCineplexByID(cineplexID).containCinemaByCinemaID(cinemaID)) {
                    cineplexDB.getCineplexByID(cineplexID).getCinemaByCinemaID(cinemaID).updateRecord(movieID);
                    cineplexDB.saveToFile();
                    pressToReturn();
                } else {
                    System.out.println("Invalid cinema ID!");
                    pressToReturn();
                }
            } else {
                System.out.println("Invalid cineplex ID!");
                pressToReturn();
            }
        } else {
            System.out.println("This movieID does not exist!");
            pressToReturn();
        }
    }

    private void deleteMovieSchedule() {
        System.out.println("delete schedule for which movie? Enter movie ID");
        Scanner s = new Scanner(System.in);
        int movieID = s.nextInt();
        if (movieInfoDB.checkMovieIDExists(movieID)) {
            MovieInfo info = movieInfoDB.getMovieInfoByMovieID(movieID);
            cineplexDB.displayAllMovieSchedules(info);

            System.out.println("which cineplex to delete from? Enter cineplex ID");
            int cineplexID = s.nextInt();
            // if it's a valid cineplexID and this movie is scheduled in this cineplex
            if (cineplexDB.findCineplexByID(cineplexID) && info.getCineplexes().contains(cineplexID)) {
                System.out.println("which cinema to delete from? Enter cinema ID");
                int cinemaID = s.nextInt();

                // if it's a valid cinema ID
                if (cineplexDB.getCineplexByID(cineplexID).containCinemaByCinemaID(cinemaID)) {
                    cineplexDB.getCineplexByID(cineplexID).getCinemaByCinemaID(cinemaID).deleteRecord(movieID);
                    cineplexDB.saveToFile();
                    pressToReturn();
                } else {
                    System.out.println("Invalid cinema ID!");
                    pressToReturn();
                }
            } else {
                System.out.println("Invalid cineplex ID!");
                pressToReturn();
            }
        } else {
            System.out.println("This movieID does not exist!");
            pressToReturn();
        }


    }

    private void deleteMovies() {
        movieInfoDB.deleteRecord();
        movieInfoDB.saveToFile();
        pressToReturn();
    }

    private void listCurrentTopBySales() {
        movieInfoDB.listTopMovies("sales");
        pressToReturn();
    }

    private void listCurrentTopByRating() {
        movieInfoDB.listTopMovies("rating");
        pressToReturn();
    }

    private void pressToReturn() {
        System.out.println("Press R/r to return to the main menu:");
        Scanner s = new Scanner(System.in);
        char r = s.nextLine().charAt(0);
        while (r != 'R' && r != 'r') {
            System.out.println("Invalid Input! Try again!");
            r = s.nextLine().charAt(0);
        }
        startOperations();
    }


}
