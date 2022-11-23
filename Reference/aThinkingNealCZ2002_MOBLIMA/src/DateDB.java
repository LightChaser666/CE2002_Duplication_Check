import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/*
  in the class Date, the attribute stored is a hashmap, storing the date of holidays
  day of week is retrieved from system
 */

/**
 * This class contains information related to date operation, such as deciding whether a date is a holiday
 */
public class DateDB {
    private String filename;
    // the value of the hashmap is a boolean array, containing [isHoliday, isHolidayEve]
    private ArrayList<String> dateList = new ArrayList<String>();

    /**
     * Instantiates a new Date db.
     *
     * @param filename the filename
     */
    public DateDB(String filename) {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.println("reading data from " + filename + "...");
            this.dateList = (ArrayList<String>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }


    /**
     * get local date
     *
     * @return local date in String format
     */
    public String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }

    /**
     * get local time
     *
     * @return local time in String format
     */
    public String getCurrentTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");
        return now.format(formatter);
    }

    /**
     * Decide whether an input date is a weekend
     *
     * @param yourDate the date to be determined whether is a weekend
     * @return a boolean value that indicates whether the input date is a weekend or not
     * @throws ParseException the parse exception
     */
    public boolean getIsWeekend(String yourDate) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(yourDate);
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == 1 || dayOfWeek == 7;
    }

    /**
     * Decide whether an input date is holiday
     *
     * @param date the input date
     * @return a boolean value that indicates whether the input date is a holiday or not
     */
    public boolean IsHoliday(String date) { // input date should be in the format of yyyy-mm-dd
        return dateList.contains(date);
    }

    /**
     * Add holiday manually.
     */
    public void addHoliday() {
        displayContent();
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to add a new holiday? Enter Y/y to proceed.");
        char choice = sc.nextLine().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            System.out.println("Enter the date to be set as holiday (yyyy-MM-dd)");
            String date = sc.next();
            if (dateList.contains(date)) {
                System.out.println("Holiday already added!");
            } else {
                dateList.add(date);
                System.out.println("New holiday date successfully added");
            }
            saveToFile();
        }
        return;
    }

    /**
     * Save to file.
     */
    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("saving data to " + filename + "...");
            oos.writeObject(dateList);
            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }

// public static void main(String args[]) throws ParseException {
//     String filename = "dateDB.ser";
//       DateDB date = new DateDB(filename);
//       if (date.IsHoliday("2019-01-01")) System.out.println("1");
//       else System.out.println("0");
//
//     date.displayContent();
//       date.addHoliday();
//       date.addHoliday();
//
//
//       date.saveToFile();
//
// }

    /**
     * Display the dates that are defined as holidays in current database
     */
    public void displayContent() {
        System.out.println("These are the current holidays:");
        for (int i = 0; i < dateList.size(); i++) {
            System.out.println(dateList.get(i));
        }
    }

}
