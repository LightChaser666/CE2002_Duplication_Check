package cz2002.gp8.moblima;


import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

import com.google.gson.reflect.TypeToken;

/**
 * Controller for Holidays in MOBLIMA
 */
public class HolidayController implements JSONFile {

    List<Holiday> list;
    Type Holidaylist = new TypeToken<ArrayList<Holiday>>() {
    }.getType(); // needed for gson to load into Arraylist
    File JSONFile = new File("holidays.json");

    /**
     * Constructor for HolidayController
     */
    public HolidayController() {
        list = (List<Holiday>) load(JSONFile, Holidaylist);
    }

    /**
     * Adds a new Holiday into the list of Holidays
     * @param holiday holiday to be added.
     */
    public void addHoliday(Holiday holiday) {
        list.add(holiday);
        save(JSONFile, list);
    }

    /**
     * Sets a new date for a holiday given by its index in the holidaylist.
     * @param holidayIndex index of the Holiday in holidaylist
     * @param newDate the new date to be updated
     */
    public void setDate(int holidayIndex, Date newDate) {
        Holiday holiday = list.get(holidayIndex);
        holiday.setDate(newDate);
        save(JSONFile, list);
    }

    /**
     * Removes a holiday given by its index in the holidaylist.
     * @param holidayIndex index of the Holiday in holidaylist
     */
    public void removeHoliday(int holidayIndex) {
        list.remove(holidayIndex);
        save(JSONFile, list);
    }

    /**
     * Displays a list of all the holidays in the list.
     */
    public void displayList() {
        for (int i = 0; i < list.size(); i++) {
            Holiday holiday = list.get(i);
            System.out.println((i + 1) + ": " + holiday.getName() + " " + holiday.getDateParsed());
        }
    }

    /**
     * Checks if a date is a holiday given a date.
     * @param date the date to be checked.
     * @return the Holiday if the given date is a Holiday, else null
     */
    public Holiday isHoliday(Date date) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(date);
        for (Holiday holiday : list) {
            cal2.setTime(holiday.getDate());
            if (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) || cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) - 1) {
                return holiday;
            }
        }
        return null;
    }
}
