import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * The class is provided for movieGoers to do operations.
 */

public class MovieGoerOperations {
    private MovieInfoDB movieInfoDB;  // You can not declare this DB as Database because of Static binding
    private CineplexDB cineplexDB;    // If that is the case, you can only use the methods declared in Database.
    private PaymentRecordDB paymentRecordDB;
    private PriceTable priceTable;
    private MovieGoerDB movieGoerDB;
    private MovieGoer movieGoer;
    private DateDB todayDate;


    /**
     * Instantiates a new Movie goer operations.
     *
     * @param movieInfoDBFile     the movie info db file
     * @param cineplexDBFile      the cineplex db file
     * @param paymentRecordDBFile the payment record db file
     * @param priceTableFile      the price table file
     * @param movieGoerDBFile     the movie goer db file
     * @param dateFile            the date file
     */
    public MovieGoerOperations(String movieInfoDBFile, String cineplexDBFile, String paymentRecordDBFile, String priceTableFile, String movieGoerDBFile, String dateFile) {
        // Login process, we assume that the MovieGoerID
        movieInfoDB = new MovieInfoDB(movieInfoDBFile);
        cineplexDB = new CineplexDB(cineplexDBFile);
        paymentRecordDB = new PaymentRecordDB(paymentRecordDBFile);
        priceTable = new PriceTable(priceTableFile);
        movieGoerDB = new MovieGoerDB(movieGoerDBFile);
        todayDate = new DateDB(dateFile);
        int movieGoerID = getID();
        // we assume this ID already exists, or we create a new movieGoer for this new ID.
        movieGoer = movieGoerDB.findRecordByMovieGoerID(movieGoerID);
        startOperations();
    }

    private void saveToFile() {
        movieInfoDB.saveToFile();
        cineplexDB.saveToFile();
        paymentRecordDB.saveToFile();
        priceTable.saveToFile();
        movieGoerDB.saveToFile();
        todayDate.saveToFile();
        System.out.println("You have successfully saved all the files :)");
    }

    /**
     * Display the main menu of operations that a movie goer can perform
     */
    private void displayMainMenu() {
        System.out.println("==============================================================");
        System.out.println("You are at Main Menu. Please enter your choice: ");
        System.out.println("1. Search movies."); // Check sear availability, selection of seats and Booking tickets are inside this option
        System.out.println("2. List movies"); // when listing movies, we list all movies at one time but organize them in different groups by their type
        System.out.println("3. List the Top 5 ranking movies by ticket sales");
        System.out.println("4. List the Top 5 ranking movies by overall reviewers' ratings");
        System.out.println("5. Check Seat Availability and Book tickets"); // as long as you know the movie name you can book seats, user check the seat availability in this.
        System.out.println("6. View booking history");
        System.out.println("7. Cancel Booking");
        System.out.println("8. Write review and give rating for movies you have seen");
        System.out.println("9. Update your profile(Email, mobile no, age)");
        System.out.println("10. Exit");
        System.out.println("==============================================================");

    }

    /**
     * Record a movie goer's choice of operation and execute the corresponded operation
     */
    private void startOperations() {
        try {
            Scanner sc = new Scanner(System.in);
            displayMainMenu();
            int choice;
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    searchMovies();
                    break;
                case 2:
                    listMovies();
                    break;
                case 3:
                    listCurrentTopBySales();
                    break;
                case 4:
                    listCurrentTopByRating();
                    break;
                case 5:
                    checkSeatAvailability();
                    break;
                case 6:
                    viewBookingHistory();
                    break;
                case 7:
                    cancelBooking();
                    break;
                case 8:
                    writeReviewRating();
                    break;
                case 9:
                    updateProfile();
                    break;
                case 10:
                    System.out.println("Good Bye! Have a good day!");
                    saveToFile();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice! Try again!");
                    pressToReturn();
            }
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            startOperations();
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            startOperations();
        }
    }

    /**
     * Return the movie goer's ID which is asked to be input by the movie goer himself
     *
     * @return movie goer ID
     */
    private int getID() {
        System.out.println("Please enter your MovieGoer ID or a new ID of you own: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();

    }

    /**
     * Allow the movie goer to search a movie by inputting movie name
     * Allow the movie goer to see the details about the movie only if it is currently showing
     */
    private void searchMovies() { // display a single movie info
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the Movie Name you want to Search: "); // example: Joker, Iron Man
        String movieName = sc.nextLine();
        MovieInfo movieInfo = movieInfoDB.getMovieInfoByName(movieName);
        // return a MovieInfo object or a null pointer
        if (movieInfo == null) {
            System.out.println("This movie does not exist yet!");
            pressToReturn();
        } else if (movieInfo.getShowingStatus().equals("End of Showing")) {
            System.out.println("This movie has ended showing!");
            pressToReturn();
        } else {
            System.out.println("The movie you entered is currently showing!");
            System.out.println("Do you want to view more details about this movie? Enter Y to view more details; Enter N to return to main menu.");
            char choice = sc.nextLine().charAt(0);
            if (choice == 'Y' || choice == 'y')
                viewMovieDetails(movieInfo.getMovieId());
            else {
                startOperations();
            }
        }
    }

    /**
     * List out all movies and display them into three parts based on their showing status:
     * "Currently showing", "Preview", "Forthcoming"
     */
    private void listMovies() { // display all the movie's name, user can query further details from here
        movieInfoDB.listAllMovies();
        Scanner s = new Scanner(System.in);
        System.out.println("Do you want to view more details? Enter Y to view more details; Enter N to return to main menu.");
        char choice = s.nextLine().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            System.out.println("Which movie detail do you want to view? Enter movie ID.");
            int movieID = s.nextInt();
            if (movieInfoDB.checkMovieIDExists(movieID)) {
                viewMovieDetails(movieID);
            } else {
                System.out.println("No such movieID exists!");
                pressToReturn();
            }

        } else {
            startOperations();
        }
    }

    /**
     * Display the movie's detailed information including:
     * Title, Movie ID, Movie Category, Movie Age Limit, Showing Status, Synopsis, Available types (2D/3D),
     * Blockbuster(True/False), Director, Cast, Number of Sales, Overall Rating, Reviews
     *
     * @param movieID ID of the movie
     */
    private void viewMovieDetails(int movieID) { // inside searchMovies and listMovies
        Scanner sc = new Scanner(System.in);
        MovieInfo movieInfo = movieInfoDB.getMovieInfoByMovieID(movieID);
        movieInfo.displayMovieInfo();

        if (movieInfo.getShowingStatus().equals("Currently Showing") || movieInfo.getShowingStatus().equals("Preview")) {
            System.out.println("This movie's showing status: " + movieInfo.getShowingStatus());
            System.out.println("Do you want to check the seat availability for this movie? Enter Y to do so; Enter N to return to main menu.");
            char choice = sc.nextLine().charAt(0);
            if (choice == 'Y' || choice == 'y')
                checkSeatAvailability(movieID);
            else {
                startOperations();
            }
        } else if (movieInfo.getShowingStatus().equals("Forthcoming")) {
            System.out.println("This movie is forthcoming. It does not have any schedules. Check again at a later date!");
            pressToReturn();
        } else {
            System.out.println("This movie has ended showing.");
            pressToReturn();
        }
    }

    /**
     * Check the availability of seat for one movie, display the layout of seats, the available date and time
     * Ask the user to choose the seat, date and time he/she wants to buy
     */
    private void checkSeatAvailability() {
        System.out.println("These are the movies that are showing: ");
        // only currently showing & preview movies are displayed
        System.out.println("===========================================");
        movieInfoDB.listMoviesByStatus("Currently Showing");
        System.out.println("===========================================");
        movieInfoDB.listMoviesByStatus("Preview");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the movieID you want to book: ");
        int movieID = sc.nextInt();
        if (movieInfoDB.checkMovieIDExists(movieID)) {
            MovieInfo movieInfo = movieInfoDB.getMovieInfoByMovieID(movieID);
            if (movieInfo.getShowingStatus().equals("Currently Showing") || movieInfo.getShowingStatus().equals("Preview"))
                checkSeatAvailability(movieID);
            else {
                System.out.println("This movie is neither currently showing nor preview");
                pressToReturn();
            }
        }
        else {
            System.out.println("This movieID is invalid.");
            pressToReturn();
        }
    }

    private void checkSeatAvailability(int movieID) {
        MovieInfo m = movieInfoDB.getMovieInfoByMovieID(movieID);
        ArrayList<Integer> validCineplexes = m.displayCineplexes(cineplexDB, todayDate.getCurrentDate(), todayDate.getCurrentTime());
        // check if there are cineplexes
        if (validCineplexes.size() == 0) {
            System.out.println("No current cineplexes are showing this movie!");
            pressToReturn();
        }

        System.out.println("These are the cineplexes that are showing the movie: ");
        for (Integer i : validCineplexes) {
            System.out.println("Cineplex ID: " + i + " Name: " + cineplexDB.getCineplexByID(i).getName() + " Location: " + cineplexDB.getCineplexByID(i).getLocation());
        }
        System.out.println("Please select the cineplexID: ");
        Scanner sc = new Scanner(System.in);
        int cineplexID = Integer.parseInt(sc.nextLine());
        // check cineplex input
        if (!validCineplexes.contains(cineplexID)) {
            System.out.println("Invalid cineplex ID!");
            pressToReturn();
        }
        HashMap<String, MovieSchedule> movieSchedules = cineplexDB.getCineplexByID(cineplexID).getMovieScheduleByID(movieID, todayDate.getCurrentDate(), todayDate.getCurrentTime());
        HashSet<String> toPrint = new HashSet<>();
        for (String s : movieSchedules.keySet()) {
            toPrint.add(s.substring(0, 10));
        }

        System.out.println("The available showing dates are as follows: ");
        for (String date : toPrint) {
            System.out.println(date);
        }

        System.out.println("Please select the date in this format yyyy-mm-dd:  ");
        String date = sc.nextLine();
        // check date input
        if (!toPrint.contains(date)) {
            System.out.println("Invalid date!");
            pressToReturn();
        }

        for (String ms : movieSchedules.keySet()) {
            if (ms.substring(0, 10).equals(date))
                movieSchedules.get(ms).displayMovieRecord();
        }

        System.out.println("Please select the start time of the movie session in this format hh-mm: ");
        String dateStarttime = date + '-' + sc.nextLine();
        if (!movieSchedules.keySet().contains(dateStarttime)) {
            System.out.println("Invalid datetime!");
            pressToReturn();
        }

        MovieSchedule selectedSchedule = movieSchedules.get(dateStarttime);

        // display the seat layout
        System.out.println("Please check the seat availability below:");
        selectedSchedule.getLayout().displayLayout();
        try {
            double price = priceTable.getPrice(selectedSchedule.getIs3D(), selectedSchedule.getIsBlockbuster(), selectedSchedule.getCinemaClass(), movieGoer.getAge(), todayDate.IsHoliday(date), todayDate.getIsWeekend(date));

            System.out.println("The price of this movie session is: $" + price + " per ticket");
            System.out.println("Do you want to proceed to book a seat? Enter Y to proceed/ Enter N to return to Main Menu");
            char choice = sc.nextLine().charAt(0);
            if (choice == 'Y' || choice == 'y')
                bookTickets(selectedSchedule, price);
            else {
                startOperations();
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format!");
        }

    }

    /**
     * Allow the movie goer to buy tickets
     * @param ms an instance of MovieSchedule which contains the information of the tickets such as the name of the movie, the time the movie begins
     * @param price total cost of the tickets bought
     */
    private void bookTickets(MovieSchedule ms, double price) {
        System.out.println("How many tickets you would like to book?");
        Scanner s = new Scanner(System.in);
        int numTickets = Integer.parseInt(s.nextLine());
        ArrayList<String> seatIDs = new ArrayList<>();
        int i = 0;
        while (i < numTickets) {
            String temp = "This is the " + (i + 1);
            if (i + 1 == 1)
                temp += "st";
            else if (i + 1 == 2)
                temp += "nd";
            else if (i + 1 == 3)
                temp += "rd";
            else
                temp += "th";
            System.out.println(temp + " ticket you are booking");
            System.out.println("Please enter the seat ID you would like to book: ");
            String seatID = s.nextLine();
            if (!ms.getLayout().checkValidSeatID(seatID)) {
                System.out.println("This seatID is invalid!");
                continue;
            }
            seatIDs.add(seatID);
            System.out.println("This seat is successfully added to your cart!");
            i += 1;
        }

        System.out.println("Would you like to make payment? Enter Y to proceed, N to return to main menu");
        char choice = s.nextLine().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            for (String seatID : seatIDs) {
                ms.bookSeat(seatID);
            }
            addToPaymentRecordDB(ms, seatIDs, price); // add to record
            // add sales to the corresponded movie info
            MovieInfo temp = movieInfoDB.getMovieInfoByMovieID(ms.getMovieID());
            int newNumberOfSales = temp.getNumOfSales() + numTickets;
            temp.setNumOfSales(newNumberOfSales);

            System.out.println("Booking complete!");
            cineplexDB.saveToFile();
            movieInfoDB.saveToFile();
            paymentRecordDB.saveToFile();
            pressToReturn();
        } else {
            startOperations();
        }
    }

    /**
     * Print the receipt after the movie goer books the ticket
     *
     * @param ms an instance of Movie Schedule which contains sufficient information for the ticket
     * @param seatIDs arraylist of string that contains seatIDs selected by user
     * @param price price of a single ticket
     */
    private void printReceipt(MovieSchedule ms, ArrayList<String> seatIDs, double price) {
        System.out.println("Here is your receipt!");
        String seatIDString = "";
        for (String id : seatIDs)
            seatIDString += id + ' ';

        String receipt = "Moviegoer ID: " + movieGoer.movieGoerID + "\n"
                + "Movie Booked: " + movieInfoDB.getMovieInfoByMovieID(ms.getMovieID()).getTitle() + "\n"
                + "Cineplex ID: " + ms.getCineplexID() + "\n"
                + "Cinema ID: " + ms.getCinemaID() + "\n"
                + "Movie Date and Start time: " + ms.getDateStartTime() + "\n"
                + "Seats booked: " + seatIDString + "\n"
                + "Date of payment: " + todayDate.getCurrentDate() + "\n"
                + "Total price: " + price * seatIDs.size() + "\n";
        System.out.println(receipt);
    }

    /**
     * Update the booking history to current database
     *
     * @param ms MovieSchedule that the user selected
     * @param seatIDs arraylist of string that contains seatIDs selected by user
     * @param price  price of a single ticket
     */
    private void addToPaymentRecordDB(MovieSchedule ms, ArrayList<String> seatIDs, double price) {
        // need to check valid input
        String dateStartTime = ms.getDateStartTime();
        String currentDate = todayDate.getCurrentDate();
        String currentTime = todayDate.getCurrentTime();

        String TID = ms.getCinemaID() + currentDate.substring(0, 4) + currentDate.substring(5, 7) + currentDate.substring(8, 10) + currentTime.substring(0, 2) + currentTime.substring(3, 5); // TID needs to be updated based on added time stamp, find a way to do this.
        //XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)

        PaymentRecord temp = new PaymentRecord(TID, movieGoer.getMovieGoerID(), ms.getMovieID(), ms.getCinemaID(),
                ms.getCineplexID(), seatIDs.size(), seatIDs, (float) price * seatIDs.size(), dateStartTime, false);

        paymentRecordDB.addRecord(TID, temp);
        System.out.println("Your booking is successful! You can cancel your booking or check payment history in the main menu!");
        printReceipt(ms, seatIDs, price);
    }

    /**
     * Display the booking history of one movie goer into three parts:
     * "Upcoming Movies", "Movies Already Watched", "Cancelled Payment"
     */
    private void viewBookingHistory() {
        ArrayList<PaymentRecord> recordCancelled = paymentRecordDB.findRecordByMovieGoerIDCancelled(movieGoer.getMovieGoerID());
        ArrayList<PaymentRecord> recordUpcomingMovies = paymentRecordDB.findRecordByMovieGoerIDUpcoming(movieGoer.getMovieGoerID(), todayDate.getCurrentDate(), todayDate.getCurrentTime());
        ArrayList<PaymentRecord> recordMoviesWatched = paymentRecordDB.findRecordByMovieGoerIDWatched(movieGoer.getMovieGoerID(), todayDate.getCurrentDate(), todayDate.getCurrentTime());

        if (recordCancelled != null || recordMoviesWatched != null || recordUpcomingMovies != null) {
            if (recordUpcomingMovies != null) {
                System.out.println("Record of Upcoming Movies:");
                for (PaymentRecord p : recordUpcomingMovies) {
                    p.printRecord();
                }
            }

            if (recordMoviesWatched != null) {
                System.out.println("\nrecord of Movies Already Watched:");
                for (PaymentRecord p1 : recordMoviesWatched) {
                    p1.printRecord();
                }
            }

            if (recordCancelled != null) {
                System.out.println("\nCancelled Payment: ");
                for (PaymentRecord p1 : recordCancelled) {
                    p1.printRecord();
                }
            }
        }
        pressToReturn();
    }

    /**
     * Cancel one movie goer's booking
     */
    private void cancelBooking() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please view your active booking of upcoming movies: ");
        ArrayList<PaymentRecord> recordUpcoming = paymentRecordDB.findRecordByMovieGoerIDUpcoming(movieGoer.getMovieGoerID(), todayDate.getCurrentDate(), todayDate.getCurrentTime());
        if (recordUpcoming == null) {
            System.out.println("You have no upcoming movies to cancel.");
        } else {
            for (PaymentRecord p : recordUpcoming) {
                p.printRecord();
            }
            System.out.println("Please enter the TID of the booking you want to cancel: ");
            String TID = sc.nextLine();
            ArrayList<String> validTID = new ArrayList<>();
            for (PaymentRecord p : recordUpcoming) {
                validTID.add(p.getTID());
            }
            while (!validTID.contains(TID)) {
                System.out.println("Invalid TID! Try again");
                System.out.println("Please enter the TID of the booking you want to cancel: ");
                TID = sc.nextLine();
            }

            // cancel booking of sests(mark seats as unoccupied)
            PaymentRecord r = paymentRecordDB.getRecordByTID(TID);
            MovieSchedule m = cineplexDB.getCineplexByID(r.getCineplexID()).getCinemaByCinemaID(r.getCinemaID()).getByDateStarttime(r.getMovieDateStartTime());
            ArrayList<String> seatIDs = r.getSeatID();
            for (String seatID : seatIDs) {
                m.cancelBooking(seatID);
            }
            paymentRecordDB.updateRecord(TID, true);

            // need to decrease the sales of tickets
            MovieInfo temp = movieInfoDB.getMovieInfoByMovieID(r.getMovieID());
            int newNumberOfSales = temp.getNumOfSales() - r.getSeatID().size();
            temp.setNumOfSales(newNumberOfSales);

            System.out.println("You have successfully canceled your booking!");
            cineplexDB.saveToFile();
            paymentRecordDB.saveToFile();
        }
        pressToReturn();

    }

    /**
     * Allow the movie goer to write reviews and give rating for the movies he/she has watched.
     */
    private void writeReviewRating() {
        ArrayList<PaymentRecord> paymentRecordArrayList = paymentRecordDB.findRecordByMovieGoerIDWatched(movieGoer.getMovieGoerID(), todayDate.getCurrentDate(), todayDate.getCurrentTime());
        if (paymentRecordArrayList == null)
            System.out.println("You have not watched any movies!");
        else {
            HashSet<Integer> movieIDWatched = new HashSet<>();
            for (PaymentRecord p : paymentRecordArrayList) {
                if (p.getMovieDateStartTime().compareTo(todayDate.getCurrentDate() + '-' + todayDate.getCurrentTime()) < 0)
                    movieIDWatched.add(p.getMovieID());
            }
            System.out.println("You have watched the following movies: ");
            for (Integer movieID : movieIDWatched) {
                System.out.println(movieInfoDB.getMovieInfoByMovieID(movieID).getTitle() + " MovieID: " + movieID);
            }
            System.out.println("For which movie would you like to enter your review? Enter movieID:");
            Scanner s = new Scanner(System.in);
            int mid = Integer.parseInt(s.nextLine());
            movieInfoDB.getMovieInfoByMovieID(mid).addReviewRating(movieGoer.getMovieGoerID());
            movieInfoDB.saveToFile();
            System.out.println("Thank you! Review added!");
        }
        pressToReturn();
        }

    /**
     * Allow the movie goer to update his/her own profile information
     */
    private void updateProfile() {
        movieGoerDB.updateRecord(movieGoer.getMovieGoerID());
        movieGoerDB.saveToFile();
        pressToReturn();
    }

    /**
     * List current top movies by sales
     */
    private void listCurrentTopBySales() {
        movieInfoDB.listTopMovies("sales");
        pressToReturn();
    }

    /**
     * List current top movies by ratings
     */
    private void listCurrentTopByRating() {
        movieInfoDB.listTopMovies("rating");
        pressToReturn();
    }

    /**
     * Allow the user to return to the main menu
     */
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
