package cz2002.gp8.moblima;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representation of a Holiday in MOBLIMA.
 */
public class Holiday {

    private String name;
    private Date date;

    /**
     * Constructor for a Holiday
     *
     * @param name Name of the Holiday.
     * @param date Date of the Holiday
     */
    public Holiday(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    /**
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date sets the date of the Holiday
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the date as a String.
     * @return the date parsed.
     */
    public String getDateParsed() {
        DateFormat df = new SimpleDateFormat("dd-MM");
        return df.format(date);
    }
}
