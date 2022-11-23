

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.Serializable;

/**
 * This class contains information about all the cineplexes
 */
public class CineplexDB implements Database, Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<Integer, Cineplex> cineplexMap;
    private String filename;


    /**
     * Constructs the CineplexDB using deserialization from a binary file.
     *
     * @param filename the path of the binary file
     */
    public CineplexDB(String filename) {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.println("reading data from " + filename + "...");
            this.cineplexMap = (HashMap<Integer, Cineplex>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        if (this.cineplexMap == null)
            this.cineplexMap = new HashMap<Integer, Cineplex>();
    }

    /**
     * Deletes one cineplex in the database
     *
     * @param cineplexID the cineplex id
     */
    public void deleteRecord(int cineplexID){
        if (cineplexMap.containsKey(cineplexID) == false) {
            System.out.println("You have entered an invalid cineplex ID");
        }
        else{
            cineplexMap.remove(cineplexID);
        }
    }


    public void deleteRecord() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the TID of the PaymentRecord you want to delete: ");
        int cineplexID = sc.nextInt();
        deleteRecord(cineplexID);
    }


    // The Cineplex information  is seldom changed and is not required for the staff. Therefore we don't need to implement this method.

    /**
     * Allow the staff to update information about a Cineplex
     */
    public void updateRecord() {
        System.out.println("Update succesfully!");
    }

//    public static void main(String args[]) {
//        CineplexDB cineplexDB = new CineplexDB(MoblimaApp.cineplexDBFile);
////        for (int i = 0; i < 3; i++) {
////            cineplexDB.addRecord();
////        }
////
////        cineplexDB.saveToFile();
//
//        ArrayList<Cinema> c = cineplexDB.getCineplexByID(2).getCinemas();
//        for (int i = 0; i < c.size(); i++) {
//            System.out.println(c.get(i).getCinemaID());
//        }
//    }


    /**
     * Given a cineplexID, decide whether this cineplex is in current database
     *
     * @param cineplexID the cineplex id
     * @return a boolean value that indicates whether the cineplex is in current database
     */
    public boolean findCineplexByID(int cineplexID) {
        if (cineplexMap.containsKey(cineplexID))
            return true;
        else
            return false;
    }

    /**
     * Gets cineplex by id.
     *
     * @param cineplexID the cineplex id
     * @return the cineplex by id
     */
    public Cineplex getCineplexByID(int cineplexID) {
        if (findCineplexByID(cineplexID))
            return this.cineplexMap.get(cineplexID);
        else return null;
    }

    /**
     * Allow the staff to add a new Cineplex to the database
     */
    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        int cineplexID;
        String name;
        String location;
        System.out.println("Creating a new cineplex: ");
        System.out.println("Please enter the cineplexID: ");
        cineplexID = sc.nextInt();
        String dummy = sc.nextLine();
        System.out.println("Please enter the name of the cineplex: ");
        name = sc.nextLine();
        System.out.println("Please enter the location of the cineplex: ");
        location = sc.nextLine();
        Cineplex temp = new Cineplex(cineplexID, name, location);
        cineplexMap.put(cineplexID, temp);
        System.out.println("You have successfully added a new cineplex!");
        System.out.println("Now let's add cinemas to this cineplex!");
        System.out.println("How many cinemas you want to add at this cineplex?");
        int noCinemas = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < noCinemas; i++) {
            temp.addCinema();
        }

        System.out.println(noCinemas + " cinemas have been created in this cineplex!");

    }

    /**
     * Save the data into the serialized file
     */
    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("saving data to " + filename + "...");
            oos.writeObject(cineplexMap);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }

    /**
     * Display all movie schedules of a movie in all cineplexes
     *
     * @param info MovieInfo of a movie
     */

    public void displayAllMovieSchedules(MovieInfo info) {
        System.out.println("These are the available schedules:");
        for (int cid : info.getCineplexes()) {
            System.out.println("In Cineplex " + cid + " :");
            for (Cinema cinema : this.getCineplexByID(cid).getCinemas()) {
                System.out.println("   In cinema " + cinema.getCinemaID() + " :");
                cinema.displayAllSchedulesOfMovie(info.getMovieId());
            }
        }
    }
}
