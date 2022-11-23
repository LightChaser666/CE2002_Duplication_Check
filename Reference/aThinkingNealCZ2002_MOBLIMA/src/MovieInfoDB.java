import java.io.*;
import java.util.*;


/**
 * The class of the MovieInfo database.
 */
public class MovieInfoDB implements Database, Serializable {
    // key: movieID, value: MovieInfo object
    /**
     * Serial Version UID for serialization
     */
    private static final long serialVersionUID = 1L;
    private HashMap<Integer, MovieInfo> movieInfoRecord;
    private String filename;


    /**
     * Constructs a MovieInfoDB with the source binary file path = "filename"
     *
     * @param filename the filename
     */
    public MovieInfoDB(String filename) {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.println("reading data from " + filename + "...");
            this.movieInfoRecord = (HashMap<Integer, MovieInfo>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        if (this.movieInfoRecord == null)
            this.movieInfoRecord = new HashMap<Integer, MovieInfo>();
    }


//    public static void main(String args[]) {
//        String filename = MoblimaApp.movieInfoDBFile;
//        CineplexDB cineplexDB = new CineplexDB(MoblimaApp.cineplexDBFile);
//        MovieInfoDB movieInfoDB = new MovieInfoDB(filename);
////        movieInfoDB.getMovieInfoByMovieID(10004).setNumOfSales(4);
////
////        movieInfoDB.listPermittedCineplex(10004);
////        movieInfoDB.addRecord(cineplexDB);
////        movieInfoDB.addRecord(cineplexDB);
////        movieInfoDB.addRecord(cineplexDB);
////        movieInfoDB.saveToFile();
////        System.out.println(movieInfoDB.getMovieInfoByMovieID(10001).getTitle());
////         System.out.println(movieInfoDB.getMovieInfoByName("Joker").getTitle());
////        for (Integer i : movieInfoDB.getMovieInfoByMovieID(10007).getCineplexes()) {
////            System.out.println(i);
////        }
////
//        movieInfoDB.deleteRevNRatByMovieGoerID(0);
////      movieInfoDB.deleteRevNRatByMovieGoerID(10);
//        movieInfoDB.saveToFile();
//
//
//    }

//
    public void deleteRevNRatByMovieGoerID(int movieGoerID){
        for (Map.Entry<Integer,MovieInfo> entry: movieInfoRecord.entrySet()){
            entry.getValue().deleteRevNRatByMovieGoerID(movieGoerID);
        }
    }
    /**
     * Updates a movie record in specified cineplexDB
     *
     * @param cineplexDB Specified cineplexDB
     */
    public void updateRecord(CineplexDB cineplexDB) {
        Scanner sc = new Scanner(System.in);
        Integer movieId;
        do {
            System.out.println("Please enter the movieID for the movie to be updated");
            movieId = Integer.parseInt(sc.nextLine());
            if (!movieInfoRecord.containsKey(movieId))
                System.out.println("Sorry no such movie ID exist. Please enter again");
            else
                break;
        } while (true);
        System.out.println("============================================");
        System.out.println("Please enter your choice: ");
        System.out.println("1. Update movie ID");
        System.out.println("2. Update movie title");
        System.out.println("3. Update movie showing status");
        System.out.println("4. Update 2D support status for the movie");
        System.out.println("5. Update 3D support status for the movie");
        System.out.println("6. Update blockbuster status for the movie");
        System.out.println("7. Update cineplexes showing the movie");
        System.out.println("8. Update synopsis for the movie");
        System.out.println("9. Update director for the movie");
        System.out.println("10. Update cast for the movie");
        System.out.println("11. Update movie age limit");
        System.out.println("12. Update movie Category");
        System.out.println("13. Go back to Main Menu");
        System.out.println("============================================");

        int choice = Integer.parseInt(sc.nextLine());
//        String dummy = sc.nextLine();
        while (choice != 13) {
            switch (choice) {
                case 1:
                    System.out.println("Enter new movieID for the movie: ");
                    Integer newID = Integer.parseInt(sc.nextLine());
//                    dummy = sc.nextLine();
                    MovieInfo temp = movieInfoRecord.get(movieId);
                    temp.setMovieId(newID);
                    movieInfoRecord.remove(movieId);
                    movieInfoRecord.put(newID, temp);
                    break;
                case 2:
                    System.out.println("Enter new title for the movie: ");
                    String newTitle = sc.nextLine();
                    movieInfoRecord.get(movieId).setTitle(newTitle);
                    break;
                case 3:
                    System.out.println("Enter new showing status for the movie: ");
                    String newStatus = sc.nextLine();
                    movieInfoRecord.get(movieId).setShowingStatus(newStatus);
                    break;
                case 4:
                    System.out.println("Enter new 2D support for the movie (Y/N): ");
                    char yes = sc.nextLine().charAt(0);
                    if (yes == 'Y' || yes == 'y')
                        movieInfoRecord.get(movieId).setSupport2D(true);
                    else
                        movieInfoRecord.get(movieId).setSupport2D(false);
                    break;
                case 5:
                    System.out.println("Enter new 3D support for the movie (Y/N): ");
                    yes = sc.nextLine().charAt(0);
                    if (yes == 'Y' || yes == 'y')
                        movieInfoRecord.get(movieId).setSupport3D(true);
                    else
                        movieInfoRecord.get(movieId).setSupport3D(false);
                    break;
                case 6:
                    System.out.println("Enter new blockbuster status for the movie: ");
                    yes = sc.nextLine().charAt(0);
                    if (yes == 'Y' || yes == 'y')
                        movieInfoRecord.get(movieId).setBlockbluster(true);
                    else
                        movieInfoRecord.get(movieId).setBlockbluster(false);
                    break;
                case 7: // cineplex
                    System.out.println("Please enter your choice");
                    System.out.println("1. Remove a cineplex showing this movie");
                    System.out.println("2. Add a cineplex showing this movie");
                    System.out.println("3. Change a cineplex with another");
                    choice = Integer.parseInt(sc.nextLine());
//                    dummy = sc.nextLine();
                    // Cineplex[] cineplexes = movieInfoRecord.get(movieId).getCineplexes();
                    ArrayList<Integer> cineplexes = movieInfoRecord.get(movieId).getCineplexes();
                    int size = cineplexes.size();

                    System.out.println("Current cineplexes for movieID" + movieId);
                    for (int i = 0; i < cineplexes.size(); i++)
                        System.out.println(cineplexes.get(i));

                    // remove a cineplex showing the movie
                    if (choice == 1) {
                        System.out.println("Please enter the cineplexID you want to remove: ");
                        int i;
                        if (sc.hasNextInt()) {
                            for (i = 0; i < cineplexes.size(); i++) {
                                if (cineplexes.get(i) == Integer.parseInt(sc.nextLine())) {
                                    cineplexes.remove(i);
                                    System.out.println("Cineplex successfully removed!");
                                    break;
                                }
                            }
                            if (i == size) System.out.println("Cineplex to be removed does not exist");
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }


                        // add a cineplex showing the movie
                    } else if (choice == 2) {
                        System.out.println("Please enter the cineplexID you want to add: ");
                        int i = 0;
                        if (sc.hasNextInt()) {
                            int newCine = Integer.parseInt(sc.nextLine());
                            for (i = 0; i < cineplexes.size(); i++) {
                                if (cineplexes.get(i) == newCine) {
                                    System.out.println("Cineplex already exists!");
                                    break;
                                }
                            }
                            if (i == cineplexes.size()) {
                                if (cineplexDB.findCineplexByID(newCine)) {
                                    cineplexes.add(newCine);
                                    System.out.println("Cineplex successfully added!");
                                } else System.out.println("No Cineplex with ID" + newCine);
                            }
                        } else System.out.println("Invalid input. Please try again.");

                    } else {
                        // change a cineplex for another
                        int i;
                        int sub;
                        System.out.println("Please enter the CineplexID to be replaced: ");

                        if (sc.hasNextInt()) {
                            for (i = 0; i < cineplexes.size(); i++)
                                if (cineplexes.get(i) == Integer.parseInt(sc.nextLine())) // to-be-replaced exists
                                    break;
                            if (i == cineplexes.size()) {
                                System.out.println("Cineplex to be replaced does not exist");
                                break;
                            } else {
                                System.out.println("Enter new cineplexID");
                                if (sc.hasNextInt()) {
                                    sub = Integer.parseInt(sc.nextLine());
                                    if (cineplexDB.findCineplexByID(sub))
                                        cineplexes.set(i, sub);
                                } else System.out.println("Invalid input.");
                            }
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    }
                    movieInfoRecord.get(movieId).setCineplexes(cineplexes);
                    break;
                case 8:
                    System.out.println("Enter new synopsis for the movie");
                    String newSyn = sc.nextLine();
                    movieInfoRecord.get(movieId).setSynopsis(newSyn);
                    break;
                case 9:
                    System.out.println("Enter new director for the movie");
                    String newDir = sc.nextLine();
                    movieInfoRecord.get(movieId).setDirector(newDir);
                    break;
                case 10:
                    System.out.println("Please enter your choice");
                    System.out.println("1. Remove a cast member for this movie");
                    System.out.println("2. Add a cast member for this movie");
                    System.out.println("3. Change a cast with another");
                    choice = Integer.parseInt(sc.nextLine());
//                    dummy = sc.nextLine();
                    int i;
                    String name;
                    ArrayList<String> cast = movieInfoRecord.get(movieId).getCast();
                    System.out.println("The current casts are:");
                    for (String castmem : cast) {
                        System.out.println(castmem);

                    }
                    int sizeCast = cast.size();
                    // remove a cast member for this movie
                    if (choice == 1) {
                        System.out.println("Enter the cast name to be removed: ");
                        name = sc.nextLine();
                        for (i = 0; i < cast.size(); i++) {
                            if (name.equals(cast.get(i))) {
                                cast.remove(i);
                                break;
                            }
                        }
                        if (i == sizeCast) System.out.println("Cast to be removed does not exist");

                        // add a cast member for this movie
                    } else if (choice == 2) {
                        System.out.println("Enter the cast name to be added: ");
                        name = sc.nextLine();
                        for (i = 0; i < cast.size(); i++) {
                            if (name.equals(cast.get(i))) {
                                System.out.println("Cast already exists!");
                                break;
                            }
                        }
                        if (i == cast.size())
                            cast.add(name);

                        // change a cast member with another
                    } else {
                        System.out.println("Enter the cast member to be replaced");
//                        dummy = sc.nextLine();
                        name = sc.nextLine();
                        for (i = 0; i < cast.size(); i++) {
                            if (name.equals(cast.get(i)))
                                break;
                        }
                        if (i == cast.size()) System.out.println("Cast does not exist");
                        else {
                            System.out.println("Please enter new cast member");
                            cast.set(i, sc.nextLine());
                        }
                    }
                    movieInfoRecord.get(movieId).setCast(cast);
                    break;
                case 11:
                    do {
                        System.out.println("Enter new age limit for the movie (PG/PG13/M18/NC21/R): ");
                        String newAgeLimit = sc.nextLine();
                        if (!newAgeLimit.equals("PG") && !newAgeLimit.equals("PG13") && !newAgeLimit.equals("M18") && !newAgeLimit.equals("NC21") && !newAgeLimit.equals("R"))
                            System.out.println("Invalid input, please try again. ");
                        else {
                            movieInfoRecord.get(movieId).setAgeLimit(newAgeLimit);
                            System.out.println("Successfully Updated!");
                            break;
                        }
                    } while (true);
                    break;
                case 12:
                    ArrayList<String> categories = new ArrayList<>();
                    categories.add("Comedy");
                    categories.add("Thriller");
                    categories.add("Science Fiction");
                    categories.add("Adventure");
                    categories.add("Love");
                    categories.add("War");

                    do {
                        System.out.println("Please enter new movie category for this movie: (Comedy/Thriller/Science Fiction/Adventure/Love/War)");
                        String newMovieCategory = sc.nextLine();
                        if (!categories.contains(newMovieCategory))
                            System.out.println("Invalid input, please try again. ");
                        else {
                            movieInfoRecord.get(movieId).setAgeLimit(newMovieCategory);
                            System.out.println("Successfully Updated!");
                            break;
                        }
                    } while (true);
                    break;
            }
            System.out.println("===========================================");
            System.out.println("Please enter your choice: ");
            System.out.println("1. Update movie ID");
            System.out.println("2. Update movie title");
            System.out.println("3. Update movie showing status");
            System.out.println("4. Update 2D support status for the movie");
            System.out.println("5. Update 3D support status for the movie");
            System.out.println("6. Update blockbuster status for the movie");
            System.out.println("7. Update cineplexes showing the movie");
            System.out.println("8. Update synopsis for the movie");
            System.out.println("9. Update director for the movie");
            System.out.println("10. Update cast for the movie");
            System.out.println("11. Update movie age limit");
            System.out.println("12. Update movie Category");
            System.out.println("13. Go back to Main Menu");
            System.out.println("===========================================");

            choice = Integer.parseInt(sc.nextLine());
//            dummy = sc.nextLine();
        }
    }

    /**
     * enable staff to delete movieInfo record
     */
    public void deleteRecord() {
        Scanner sc = new Scanner(System.in);
        Integer movieId;
        do {
            System.out.println("Please enter the movieID for the movie to be deleted");
            movieId = Integer.parseInt(sc.nextLine());
            if (!movieInfoRecord.containsKey(movieId))
                System.out.println("Sorry no such movie ID exist. Please enter again");
            else
                break;
        } while (true);
        movieInfoRecord.get(movieId).setShowingStatus("End of Showing");
    }

    /**
     * get movie by status
     *
     * @param status status of one movie
     * @return An array list containing movies of the input status.
     */
    private ArrayList<MovieInfo> getByStatus(String status) {
        ArrayList<MovieInfo> result = new ArrayList<MovieInfo>();
        for (Map.Entry<Integer, MovieInfo> entry : movieInfoRecord.entrySet()) {
            if (entry.getValue().getShowingStatus().equals(status)) {
                result.add(entry.getValue());
            }
        }
        return result;
        // System.out.println("The movieGoer you are looking for does not exist");
    }

    /**
     * list top movies
     *
     * @param priority the priority
     */
    public void listTopMovies(String priority) {
        ArrayList<MovieInfo> result = new ArrayList<>();

        List<Map.Entry<Integer, MovieInfo>> list = new LinkedList<>(movieInfoRecord.entrySet());
        if (priority.equals("sales")) {
            for (Map.Entry<Integer, MovieInfo> entry : movieInfoRecord.entrySet()) {
                Collections.sort(list, new Comparator<Map.Entry<Integer, MovieInfo>>() {
                    public int compare(Map.Entry<Integer, MovieInfo> o1,
                                       Map.Entry<Integer, MovieInfo> o2) {
                        return (-Integer.valueOf(o1.getValue().getNumOfSales()).compareTo(Integer.valueOf(o2.getValue().getNumOfSales())));
                    }
                });
            }
        } else {
            for (Map.Entry<Integer, MovieInfo> entry : movieInfoRecord.entrySet()) {
                Collections.sort(list, new Comparator<Map.Entry<Integer, MovieInfo>>() {
                    public int compare(Map.Entry<Integer, MovieInfo> o1,
                                       Map.Entry<Integer, MovieInfo> o2) {
                        return (-Float.valueOf(o1.getValue().getOverAllRating()).compareTo(Float.valueOf(o2.getValue().getOverAllRating())));
                    }
                });
            }
        }

        for (Map.Entry<Integer, MovieInfo> aa : list) {
            result.add(aa.getValue());
        }

        int numLoops = Math.min(5, result.size());
        if (priority.equals("sales")) {
            for (int i = 0; i < numLoops; i++)
                System.out.println("Top " + (i + 1) + ": " + result.get(i).getTitle() + " Total sales: " + result.get(i).getNumOfSales());
        } else {
            for (int i = 0; i < numLoops; i++) {
                if (result.get(i).getOverAllRating() == -1)
                    System.out.println("Top " + (i + 1) + ": " + result.get(i).getTitle() + " Overall Rating: " + "N.A.");
                else
                    System.out.println("Top " + (i + 1) + ": " + result.get(i).getTitle() + " Overall Rating: " + result.get(i).getOverAllRating());

            }

        }

    }

    /**
     * save to file after modifying the database
     *
     * @param movieID the movie id
     * @return the movie info by movie id
     */
    public MovieInfo getMovieInfoByMovieID(Integer movieID) {
        return movieInfoRecord.get(movieID);
    }

    /**
     * search movie by movie name
     *
     * @param movieName the movie name
     * @return the movie info by name
     */
    public MovieInfo getMovieInfoByName(String movieName) {
        for (MovieInfo m : movieInfoRecord.values()) {
            if (m.getTitle().equals(movieName))
                return m;
        }
        return null;
    }

    /**
     * List all movies.
     */
    public void listAllMovies() { // need to be listed by status
        ArrayList<MovieInfo> currentMovies = getByStatus("Currently Showing");
        ArrayList<MovieInfo> previewMovies = getByStatus("Preview");
        ArrayList<MovieInfo> forthcomingMovies = getByStatus("Forthcoming");

        System.out.println("|Currently showing movies| ");
        for (int i = 0; i < currentMovies.size(); i++)
            System.out.println("Name: " + currentMovies.get(i).getTitle() + " [MovieID: " + currentMovies.get(i).getMovieId() + "]");
        System.out.println();
        System.out.println("|Movies for preview| ");
        for (int i = 0; i < previewMovies.size(); i++)
            System.out.println("Name: " + previewMovies.get(i).getTitle() + " [MovieID: " + previewMovies.get(i).getMovieId() + "]");
        System.out.println();
        System.out.println("|Forthcoming movies| ");
        for (int i = 0; i < forthcomingMovies.size(); i++)
            System.out.println("Name: " + forthcomingMovies.get(i).getTitle() + " [MovieID: " + forthcomingMovies.get(i).getMovieId() + "]");
    }

    /**
     * List permitted cineplex.
     *
     * @param movieID the movie id
     */
    public void listPermittedCineplex(int movieID) {
        for (int i : movieInfoRecord.get(movieID).getCineplexes())
            System.out.println("Cineplex " + i);
    }

    /**
     * List all schedules of movie.
     *
     * @param cineplexDB the cineplex db
     */
    public void listAllSchedulesOfMovie(CineplexDB cineplexDB) {
        System.out.println("Please enter movie ID");
        Scanner s = new Scanner(System.in);
        int mid = Integer.parseInt(s.nextLine());
        if (this.checkMovieIDExists(mid)) {
            MovieInfo info = movieInfoRecord.get(mid);
            cineplexDB.displayAllMovieSchedules(info);
        } else {
            System.out.println("Invalid MovieID!");
        }
    }

    /**
     * List movies by status.
     *
     * @param status the status
     */
    public void listMoviesByStatus(String status) {
        ArrayList<MovieInfo> movies = getByStatus(status);

        System.out.println(status + " movies:");
        for (int i = 0; i < movies.size(); i++)
            System.out.println("Name: " + movies.get(i).getTitle() + " [MovieID: " + movies.get(i).getMovieId() + "]");
    }

    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("saving data to " + filename + "...");
            oos.writeObject(movieInfoRecord);
            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }

    /**
     * Enable adding of a new movieInfo record;
     * new movieID must be different than existing movieIDs;
     *
     * @param cineplexDB the cineplex db
     */
    public void addRecord(CineplexDB cineplexDB) {
        Scanner sc = new Scanner(System.in);
        Integer movieId = -1;

        // add movie ID
        System.out.println("Please enter a new movie ID: ");
        do {
            if (sc.hasNextInt()) {
                movieId = Integer.parseInt(sc.nextLine());
                if (movieInfoRecord.containsKey(movieId))
                    System.out.println("Movie ID already exists! Please enter a new movie ID");
                else {
                    break;
                }
            } else
                System.out.println("Invalid movie ID. Please try again.");
        } while (true);

        // add movie title
        System.out.println("Please enter movie title: ");
        String movieTitle = sc.nextLine();

        // add cineplexes showing the movie
        System.out.println("Please enter the number of cineplexes for this movie: ");
        int noCine = Integer.parseInt(sc.nextLine());
        // Cineplex[] cineplexes = new Cineplex[noCine];
        ArrayList<Integer> cineplexes = new ArrayList<Integer>();
        Integer newCineplexID;
        for (int i = 0; i < noCine; i++) {
            do {
                System.out.println("Please enter cineplex ID " + (i + 1));
                newCineplexID = Integer.parseInt(sc.nextLine());
                if (cineplexDB.findCineplexByID(newCineplexID)) {
                    // cineplexes[i] = CineplexDB.CineplexMap.get(sc.nextInt());
                    cineplexes.add(newCineplexID);
                    break;
                } else
                    System.out.println("Cinexplex ID does not exist");
            } while (true);
        }

        // add showing status for the movie
        System.out.println("Please enter showing status for the new movie: ");
        String showingStatus = sc.nextLine();
//        String dummy = sc.nextLine();

        // add synopsis for the movie
        System.out.println("Please enter synopsis for the movie: ");
        String synopsis = sc.nextLine();

        // check whether 2d supported
        boolean support2D;
        do {
            System.out.println("Please indicate whether this movie is available in 2D (Y/N): ");
            char yes = sc.nextLine().charAt(0);
            if (yes == 'Y' || yes == 'y') {
                support2D = true;
                break;
            } else if (yes == 'N' || yes == 'n') {
                support2D = false;
                break;
            } else
                System.out.println("Invalid input, please try again. ");
        } while (true);

        // check whether 2d supported
        boolean support3D;
        do {
            System.out.println("Please indicate whether this movie is available in 3D (Y/N): ");
            char yes = sc.nextLine().charAt(0);
            if (yes == 'Y' || yes == 'y') {
                support3D = true;
                break;
            } else if (yes == 'N' || yes == 'n') {
                support3D = false;
                break;
            } else
                System.out.println("Invalid input, please try again. ");
        } while (true);

        // check if the movie is a blockbuster
        boolean isBlockbuster;
        do {
            System.out.println("Please indicate whether this movie is a blockbuster (Y/N): ");
            char yes = sc.nextLine().charAt(0);
            if (yes == 'Y' || yes == 'y') {
                isBlockbuster = true;
                break;
            } else if (yes == 'N' || yes == 'n') {
                isBlockbuster = false;
                break;
            } else
                System.out.println("Invalid input, please try again. ");
        } while (true);

        // add director for the movie
        System.out.println("Enter the name of the director for this movie: ");
//        dummy = sc.nextLine();
        String director = sc.nextLine();

        // add cast for the movie
        ArrayList<String> cast = new ArrayList<String>();
        do {
            System.out.println("Please enter the number of cast for this movie");
//            if (sc.nextInt() < 3) {
//                System.out.println("You must enter at least 3 cast for the movie");
//                continue;
//            }
            int noCast = Integer.parseInt(sc.nextLine());
//            dummy = sc.nextLine();
            // String[] cast = new String[noCast];
            for (int i = 0; i < noCast; i++) {
                System.out.println("Enter the name of cast" + (i + 1));
                cast.add(sc.nextLine());
            }
            break;
        } while (true);

        // add ageLimit
        String ageLimit;
        do {
            System.out.println("Please indicate the age limit for this movie: PG/PG13/M18/NC21/R");
            ageLimit = sc.nextLine();
            if (!ageLimit.equals("PG") && !ageLimit.equals("PG13") && !ageLimit.equals("M18") && !ageLimit.equals("NC21") && !ageLimit.equals("R"))
                System.out.println("Invalid input, please try again. ");
            else
                break;
        } while (true);

        // add type
        String movieCategory;
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Comedy");
        categories.add("Thriller");
        categories.add("Science Fiction");
        categories.add("Adventure");
        categories.add("Love");
        categories.add("War");
        do {
            System.out.println("Please enter movie category for this movie: (Comedy/Thriller/Science Fiction/Adventure/Love/War)");
            movieCategory = sc.nextLine();
            if (!categories.contains(movieCategory))
                System.out.println("Invalid input, please try again. ");
            else
                break;
        } while (true);

        // note better change cast to type of String[] rather than String
        MovieInfo newMovie = new MovieInfo(movieId, movieTitle, showingStatus, synopsis, cineplexes, support2D, support3D,
                isBlockbuster, director, cast, ageLimit, movieCategory);
        movieInfoRecord.put(movieId, newMovie);
        System.out.println("New movie info successfully added! ");
    }

    /**
     * Check movie id exists boolean.
     *
     * @param movieID the movie id
     * @return the boolean
     */
    public boolean checkMovieIDExists(int movieID) {
        return movieInfoRecord.containsKey(movieID);
    }


    public void addRecord() {
        System.out.println("Add record");
    }

    public void updateRecord() {
        System.out.println("Update record");
    }

}
