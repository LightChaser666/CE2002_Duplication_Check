package moblima;
import java.io.*;

import java.util.ArrayList;

/**
 * Each <code>Cineplex</code> objects contain a list of <code>Cinema</code> objects. Each cineplex has a unique name and
 * cineplex code, which is the first 3 letters of the cineplex name. 
 * @see Cinema
 */
public class Cineplex implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3576833979103642973L;
	/**
	 * Name of the Cineplex
	 */
	private String name;
	/**
	 * The unique cineplex code, which is the first 3 letters of the cineplex name.
	 */
	private String code;
	/**
	 * A list of the <code>Cinema</code> objects stored in each cineplex.
	 */
	private ArrayList<Cinema> cinemaList;
	
	/**
	 * A constructor for the <code>Cineplex</code> class.
	 * @param name Name of the cineplex.
	 * @param code Cineplex code (should be the first 3 letters of cineplex name).
	 */
	public Cineplex (String name, String code) {
		this.name = name;
		this.code = code;
		cinemaList = new ArrayList<Cinema>();
	}
	
	/**
	 * Gets the cineplex code
	 * @return <code>String</code> value of the cineplex code.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Gets the name of the cineplex.
	 * @return <code>String</code> value of the cineplex name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets a reference to the list of <code>Cinema</code> objects stored in the cineplex.
	 * @return A reference to an <code>ArrayList&lt;Cinema&gt;</code> of cinemas.
	 */
	public ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}
	
	/**
	 * Adds a <code>Cinema</code> object to the list of cinemas.
	 * @param cinemaToAdd <code>Cinema</code> to add.
	 */
	public void addCinema(Cinema cinemaToAdd) {
		cinemaList.add(cinemaToAdd);
	}
	
	/**
	 * Removes a <code>Cinema</code> object from the list of cinemas.
	 * @param cinemaToRemove <code>Cinema</code> to remove.
	 */
	public void removeCinema(Cinema cinemaToRemove) {
		cinemaList.remove(cinemaToRemove);
	}
}
