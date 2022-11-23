package cz2002.gp8.moblima;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * MOBLIMA User interface for the public 
 */
public class MOBLIMAUser {

    static MovieController movies = new MovieController();
    static CineplexController cineplexes = new CineplexController();

    /**
     * Main method
     * @param args
     */
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int option;
        boolean exit = false;
        do {
            System.out.println("- MOBLIMA -");
            System.out.println("Select an option below: ");
            System.out.println("1. Make New Booking");
            System.out.println("2. Show Movie Details");
            System.out.println("3. Check Previous Bookings");
            System.out.println("4. View Top Movies by Sales");
            System.out.println("5. View Top Movies by Rating");
            System.out.println("6. Exit");
            System.out.print("Select option: ");
            try {
                option = scanner.nextInt();
                System.out.println("");
                switch (option) {
                    case 1:
                        int cineplexOption = -1;
                        do {
                            System.out.println("- Cineplex Listings -");
                            cineplexes.displayList();
                            System.out.print("Select Cineplex (0 to go back): ");
                            try {
                                cineplexOption = scanner.nextInt();
                                System.out.println();
                                if (cineplexOption > 0 && cineplexOption <= cineplexes.getList().size()) {
                                    Cineplex cineplex = cineplexes.getCineplex(cineplexOption - 1);
                                    List<Movie> movieList = getMovieList(cineplex.getMovieListings());
                                    System.out.println("- " + cineplex.getName() + " -");
                                    System.out.println("- Movie Listing -");
                                    displayMovieList(movieList);
                                    System.out.print("Select Movie (0 to go back): ");
                                    int movieIndex = -1;
                                    do {
                                        try {
                                            movieIndex = scanner.nextInt();
                                            if (movieIndex > 0 && movieIndex <= movieList.size()) {
                                                int movieId = movieList.get(movieIndex - 1).getMovieId();
                                                Movie movie = movies.getMovieById(movieId);
                                                List<Showtime> showtimes = cineplex.getShowtimesForMovie(movieId);

                                                if (!showtimes.isEmpty()) {
                                                    System.out.println("- Showtimes for " + movie.getTitle() + " -");
                                                    cineplex.displayShowtimesForMovie(movieId);
                                                    System.out.print("Select Showtime (0 to go back): ");
                                                    int showtimeIndex = -1;
                                                    do {
                                                        try {
                                                            showtimeIndex = scanner.nextInt();
                                                            if (showtimeIndex > 0 && showtimeIndex <= showtimes.size()) {
                                                                Showtime showtime = showtimes.get(showtimeIndex - 1);
                                                                System.out.println("- " + movie.getTitle() + " " + showtime.getSession() + " at " + cineplex.getName() + " -");
                                                                showtime.displaySeats();

                                                                int seatOption = -1;
                                                                List<Seat> seatsSelected = new ArrayList<Seat>();
                                                                do {

                                                                    System.out.print("Seats Chosen: ");
                                                                    if (seatsSelected.size() <= 0) {
                                                                        System.out.print("None");
                                                                    }
                                                                    for (Seat seat : seatsSelected) {
                                                                        System.out.print(seat.getSeatNum() + " ");
                                                                    }

                                                                    System.out.println();
                                                                    System.out.println("1. Select Seat");
                                                                    System.out.println("2. Remove Selected Seat");
                                                                    System.out.print("Select option (0 when done): ");
                                                                    seatOption = scanner.nextInt();
                                                                    scanner.nextLine();
                                                                    System.out.println();

                                                                    switch (seatOption) {
                                                                        case 1:
                                                                            try {
                                                                                System.out.print("Row: ");
                                                                                char rowChar = scanner.nextLine().charAt(0);
                                                                                int row = Character.getNumericValue(rowChar) - 10;

                                                                                System.out.print("Col: ");
                                                                                int col = scanner.nextInt();

                                                                                boolean prevSeat = false;
                                                                                for (Seat seat : seatsSelected) {
                                                                                    if (seat.getRow() == row && seat.getCol() == col) {
                                                                                        prevSeat = true;
                                                                                    }
                                                                                }

                                                                                Seat seat = new Seat(row, col);

                                                                                if (!showtime.isSeatTaken(row, col) && !prevSeat) {
                                                                                    seatsSelected.add(seat);
                                                                                } else {
                                                                                    System.out.println("Seat Taken");
                                                                                }
                                                                                System.out.println();

                                                                            } catch (InputMismatchException inputMismatchException) {
                                                                                System.out.println("Invalid input! Please enter a seat E.g. A2!");
                                                                                scanner.next();
                                                                            }
                                                                            break;

                                                                        case 2:
                                                                            System.out.println("- Seat to remove -");
                                                                            for (int i = 0; i < seatsSelected.size(); i++) {
                                                                                System.out.println((i + 1) + ". " + seatsSelected.get(i).getSeatNum());
                                                                            }
                                                                            System.out.print("Seat to remove: ");
                                                                            int seatIndex = scanner.nextInt();
                                                                            if (seatIndex > 0 && seatIndex <= seatsSelected.size()) {
                                                                                seatsSelected.remove(seatIndex - 1);
                                                                            } else {
                                                                                System.out.println("Invalid choice");
                                                                            }
                                                                            System.out.println();
                                                                            break;

                                                                        case 0:
                                                                            break;

                                                                        default:
                                                                            System.out.println("Invalid option! Please select again!");
                                                                            System.out.println();
                                                                    }
                                                                    if (seatsSelected.size() <= 0) {
                                                                        System.out.println("Please select at least 1 seat");
                                                                    }

                                                                } while (seatOption != 0 || seatsSelected.size() <= 0);

                                                                System.out.println("Seats selected: " + seatsSelected.size());
                                                                System.out.println();

                                                                System.out.println("- Tickets -");

                                                                try {
                                                                    int ticketIndex = -1;
                                                                    Ticket ticket;
                                                                    do {
                                                                        showtime.displayTicketTypes();
                                                                        System.out.print("Choice: ");
                                                                        ticketIndex = scanner.nextInt();
                                                                        ticket = showtime.getTicket(ticketIndex - 1);
                                                                        scanner.nextLine();

                                                                        if (ticketIndex <= 0 || ticketIndex > showtime.getTicketTypes().size()) {
                                                                            System.out.println("Invalid Choice");
                                                                        }
                                                                    } while (ticketIndex <= 0 || ticketIndex > showtime.getTicketTypes().size());

                                                                    System.out.println("Ticket chosen: " + ticket.getType());
                                                                    double total = ticket.getPrice() * seatsSelected.size();
                                                                    System.out.println("Total: " + seatsSelected.size() + " x $" + ticket.getPrice() + " = $" + total);
                                                                    System.out.println();

                                                                    System.out.println("- Booking Info -");
                                                                    System.out.print("Name: ");
                                                                    String name = scanner.nextLine();

                                                                    System.out.print("Mobile No.: ");
                                                                    int mobileNo = scanner.nextInt();
                                                                    scanner.nextLine();

                                                                    System.out.print("Email: ");
                                                                    String email = scanner.nextLine();
                                                                    String code = movie.getTitle().substring(0, 3).toUpperCase();

                                                                    Booking booking = new Booking(name, mobileNo, email, ticket, seatsSelected, code, cineplexOption - 1, showtimeIndex - 1);
                                                                    String TID = booking.getTID();
                                                                    cineplexes.addBooking(cineplexOption - 1, showtimeIndex - 1, booking);

                                                                    for (int i = 0; i < seatsSelected.size(); i++) {
                                                                        movies.incrementSales(movieId);
                                                                    }

                                                                    System.out.println("Booking Successful, TID: " + TID);
                                                                    showtimeIndex = 0;
                                                                    movieIndex = 0;

                                                                } catch (InputMismatchException inputMismatchException) {
                                                                    System.out.println("Invalid input! Please enter a number!");
                                                                    scanner.next();
                                                                }

                                                            } else if (showtimeIndex != 0 && showtimeIndex > showtimes.size()) {
                                                                System.out.println("Invalid input!");
                                                            }
                                                        } catch (InputMismatchException inputMismatchException) {
                                                            System.out.println("Invalid input! Please enter a number!");
                                                            scanner.next();
                                                        }
                                                    } while (showtimeIndex != 0);
                                                } else {
                                                    // on no showtimes
                                                    System.out.println("No showtimes");
                                                    movieIndex = 0;
                                                }
                                            } else {
                                                System.out.println("Invalid input!");
                                            }
                                        } catch (InputMismatchException inputMismatchException) {
                                            System.out.println("Invalid input! Please enter a number!");
                                            scanner.next();
                                        }
                                    } while (movieIndex != 0);

                                } else if (cineplexOption < 0 || cineplexOption > cineplexes.getList().size()) {
                                    System.out.println("Invalid option! Please enter a valid movie number.");
                                }

                            } catch (InputMismatchException inputMismatchException) {
                                System.out.println("Invalid input! Please enter a number!");
                                scanner.next();
                            }
                        } while (cineplexOption != 0);
                        break;

                    case 2:
                        int movieOption = -1;
                        do {
                            System.out.println("- Movie Listings -");
                            List<Movie> movieList = movies.getList();
                            displayMovieList(movieList);
                            System.out.print("Select movie no. to view details (0 to go back): ");
                            try {
                                movieOption = scanner.nextInt();
                                if (movieOption > 0 && movieOption <= movieList.size()) {
                                    System.out.println("");
                                    Movie m = movieList.get(movieOption - 1);
                                    m.displayMovieDetails();
                                    System.out.print("Press 1 to Add Review else press ENTER to go back: ");

                                    scanner.nextLine();
                                    String line = scanner.nextLine();
                                    if (line.equals("1")) {
                                        System.out.println("- NEW REVIEW -");
                                        String name = "";
                                        int rating = 0;
                                        String reviewText = "";
                                        System.out.print("Enter your name: ");
                                        name = scanner.nextLine();
                                        System.out.println("NAME: " + name);
                                        do {
                                            System.out.print("Enter your rating (out of 5): ");
                                            try {
                                                rating = scanner.nextInt();
                                            } catch (InputMismatchException inputMismatchException) {
                                                rating = -1;
                                                scanner.next();
                                            }
                                            if (rating < 0 || rating > 5) {
                                                System.out.println("Please enter a rating within 0 to 5 (inclusive).");
                                            }
                                        } while (rating < 0 || rating > 5);

                                        System.out.print("Enter your review: ");
                                        scanner.nextLine(); //flush
                                        reviewText = scanner.nextLine();
                                        System.out.println("REVIEW: " + reviewText);

                                        Review review = new Review(name, rating, reviewText);
                                        movies.addReview(m.getMovieId(), review);
                                        System.out.println("Thank you " + name + "! Your review has been submitted!");
                                        System.out.println();
                                    }

                                } else if (movieOption < 0 || movieOption > movieList.size()) {
                                    System.out.println("Invalid option! Please enter a valid movie number.");
                                }
                            } catch (InputMismatchException inputMismatchException) {
                                System.out.println("Invalid input! Please enter a number!");
                                scanner.next();
                            }
                        } while (movieOption != 0);
                        System.out.println();
                        break;

                    case 3:
                        System.out.println("- Check Previous Bookings -");
                        System.out.print("Mobile No.: ");
                        int mobile = scanner.nextInt();
                        displayBooking(getPreviousBookings(mobile));
                        break;

                    case 4:
                        movies.displayTopSales();
                        break;

                    case 5:
                        movies.displayTopRated();
                        break;

                    case 6:
                        exit = true;
                        System.out.println("Thanks for using MOBLIMA!");
                        break;

                    default:
                        System.out.println("Invalid option! Please select again!");
                        break;
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Invalid input! Please enter a number!");
                scanner.next();
            }
        } while (!exit);
    }

    /**
     * Gets the list of Movies based on a list of IDs.
     * @param movieListing List of Movie IDs
     * @return List of Movies given the IDs.
     */
    public static List<Movie> getMovieList(List<Integer> movieListing) {
        List<Movie> movieList = new ArrayList<Movie>();
        for (Integer id : movieListing) {
            Movie movie = movies.getMovieById(id);
            movieList.add(movie);
        }
        return movieList;
    }

    /**
     * Displays a list of movie and their names.
     * @param movieList the list of movies to display
     */
    public static void displayMovieList(List<Movie> movieList) {
        if (movieList.size() > 0) {
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println((i + 1) + ". " + movieList.get(i).getTitle());
            }
        } else {
            System.out.println("No Movies");
        }
        System.out.println();
    }
    
    /**
     * Display a list of bookings and the booking details.
     * @param bookings The list of bookings to display.
     */
    private static void displayBooking(List<Booking> bookings) {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found");
            System.out.println();
            return;
        }
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            Cineplex cineplex = cineplexes.getCineplex(booking.getCineplexIndex());
            Showtime showtime = cineplex.getShowtime(booking.getShowtimeIndex());
            String cineplexName = cineplex.getName();
            String cinemaName = showtime.getCinemaName();
            String movieTitle = movies.getMovieById(showtime.getMovieId()).getTitle();

            System.out.println(booking.getTID());
            System.out.println(cineplexName + " - " + cinemaName);
            System.out.println(movieTitle + " " + showtime.getSession());
            System.out.print("Ticket: ");
            booking.getTicketType().displayTicket();
            System.out.print("Seats: [ ");
            for (Seat seat : booking.getSeats()) {
                System.out.print(seat.getSeatNum() + " ");
            }
            System.out.println("]");
            System.out.println("Total: $" + booking.getTotalAmount());
            System.out.println();
        }
    }
    
    /**
     * Get the previous booking of a user via his/her mobile number.
     * @param mobile The mobile number of the user.
     * @return The list of bookings by the user.
     */
    private static List<Booking> getPreviousBookings(int mobile) {
        List<Booking> previousBookings = new ArrayList<Booking>();
        for (Cineplex cineplex : cineplexes.getList()) {
            for (Showtime showtime : cineplex.getShowtimes()) {
                for (Booking booking : showtime.getBookings()) {
                    if (booking.getMobile() == mobile) {
                        previousBookings.add(booking);
                    }
                }
            }
        }

        return previousBookings;
    }
}
