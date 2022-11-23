package moblima;
import java.io.*;
import java.util.Calendar;
import java.util.ArrayList;

/**
 * Boundary class which reads and writes objects to save instances of <code>Cineplex</code>, <code>Cinema</code>, <code>Show</code>, <code>Movie</code>, <code>Calendar</code> and <code>Account<code> created to a .tmp file
 * 
 *<p>Methods in the <code>DataBoundary</code> class are all declared static and hence, no instantiation of <code>DataBoundary</code> is required
 *For each of the respective entity classes, there is a get method that reads the respective .tmp files to return an <code>ArrayList</code> of objects of that class.
 *The get methods are usually called when the application is first initiated.
 *There is also a save method which saves the <code>ArrayList</code> passed in.
 *The save methods are usually called after new instances are added to the <code>ArrayList</code> or when modifications have been made to the objects in the <code>ArrayList</code>.
 */

public class DataBoundary {
	
	/**
	 * Reads the file and gets an <code>ArrayList</code> of <code>StaffAcc</code>instances.
	 * @return <code>ArrayList&lt;Account&gt;</code> staffArray.
	 */
	public static ArrayList<Account> getStaffList() {
		ArrayList<Account> staffArray = new ArrayList<Account>();
		try {
			FileInputStream fis = new FileInputStream("staff.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			staffArray = (ArrayList<Account>) ois.readObject();
			ois.close();
		}
		catch (EOFException e) {
			//just to test
			e.printStackTrace();
			System.out.println("All Staff Accounts Added.");
		}
		catch (FileNotFoundException e) {
			System.out.println("No Staff Accounts Found.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return staffArray;
	}
	
	/**
	 * Reads the file and gets an <code>ArrayList</code> of <code>CustAcc</code> instances.
	 * @return <code>ArrayList&lt;Account&gt;</code> custArray.
	 */
	public static ArrayList<Account> getCustList() {
		ArrayList<Account> custArray = new ArrayList<Account>();
		try {
			FileInputStream fis = new FileInputStream("cust.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			custArray = (ArrayList<Account>) ois.readObject();
			ois.close();
		}
		catch (EOFException e) {
			//just to test
			e.printStackTrace();
			System.out.println("All Customer Accounts Added.");
		}
		catch (FileNotFoundException e) {
			System.out.println("No Customer Accounts Found.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return custArray;
	}
	
	/**
	 * Reads the file and gets an <code>ArrayList</code> of <code>Movie</code> instances.
	 * @return <code>ArrayList&lt;Movie&gt;</code> movieArray.
	 */
	public static ArrayList<Movie> getMovieList() {
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		try {
			FileInputStream fis = new FileInputStream("movie.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			movieArray = (ArrayList<Movie>) ois.readObject();
			ois.close();
		}
		catch (EOFException e) {
			//just to test
			e.printStackTrace();
			System.out.println("All Movies Added.");
		}
		catch (FileNotFoundException e) {
			System.out.println("No Movies Found.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return movieArray;
	}
	
	/**
	 * Reads the file and gets an <code>ArrayList</code> of <code>Cineplex</code> instances.
	 * As <code>Cinema</code> instances are attributes of <code>Cineplex</code> and <code>Show</code> instances are
	 * attributes of <code>Cinema</code>, thay are read together with <code>Cineplex</code>.
	 * @return <code>ArrayList&lt;Cineplex&gt;</code> cineplexArray.
	 */
	public static ArrayList<Cineplex> getCineplexList() {
		ArrayList<Cineplex> cineplexArray = new ArrayList<Cineplex>();
		try {
			FileInputStream fis = new FileInputStream("cineplex.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			cineplexArray = (ArrayList<Cineplex>) ois.readObject();
			ois.close();
		}
		catch (EOFException e) {
			//just to test
			e.printStackTrace();
			System.out.println("All Cineplexes, Cinemas and Shows Added.");
		}
		catch (FileNotFoundException e) {
			System.out.println("No Cineplexes Found.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cineplexArray;
	}
	
	
	/**
	 * Reads the file and gets an <code>ArrayList</code> of <code>Calendar</code> instances.
	 * @return <code>ArrayList&lt;Calendar&gt;</code> pubHolArray.
	 */
	public static ArrayList<Calendar> getPubHolList() {
		ArrayList<Calendar> pubHolArray = new ArrayList<Calendar>();
		try {
			FileInputStream fis = new FileInputStream("pubHols.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			pubHolArray = (ArrayList<Calendar>) ois.readObject();
			ois.close();
		}
		catch (EOFException e) {
			//just to test
			e.printStackTrace();
			System.out.println("All Public Holidays Added.");
		}
		catch (FileNotFoundException e) {
			System.out.println("No Public Holidays Found.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pubHolArray;
	}
	
	/**Saves a <code>ArrayList</code> of <code>Account</code> into a "staff.tmp" file.
	 * @param staffArray containing a <code>ArrayList</code> of <code>StaffAcc</code>. 
	 */
	public static void saveStaffList(ArrayList<Account> staffArray) {
		try{
			FileOutputStream fos = new FileOutputStream("staff.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(staffArray);
			oos.close();
		}
		catch (FileNotFoundException e) {}
		catch(IOException e) {}
	}
	
	/**Saves a <code>ArrayList</code> of <code>Account</code> into a "cust.tmp" file.
	 * @param custArray containing a <code>ArrayList</code> of <code>CustAcc</code>. 
	 */
	public static void saveCustList(ArrayList<Account> custArray) {
		try{
			FileOutputStream fos = new FileOutputStream("cust.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(custArray);
			oos.close();
		}
		catch (FileNotFoundException e) {}
		catch(IOException e) {}
	}
	
	
	/**Saves a <code>ArrayList</code> of <code>Movie</code> into a "movie.tmp" file.
	 * @param movieArray containing a <code>ArrayList</code> of <code>Movie</code>. 
	 */
	public static void saveMovieList(ArrayList<Movie> movieArray) {
		try{
			FileOutputStream fos = new FileOutputStream("movie.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(movieArray);
			oos.close();
		}
		catch (FileNotFoundException e) {}
		catch(IOException e) {}
	}
	
	/**Saves a <code>ArrayList</code> of <code>Cineplex</code> into a "cineplex.tmp" file.
	 * @param cineplexArray containing a <code>ArrayList</code> of <code>Cineplex</code>. 
	 */
	public static void saveCineplexList(ArrayList<Cineplex> cineplexArray) {
		try{
			FileOutputStream fos = new FileOutputStream("cineplex.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(cineplexArray);
			oos.close();
		}
		catch (FileNotFoundException e) {}
		catch(IOException e) {}
	}
	
	/**Saves a <code>ArrayList</code> of <code>Calendar</code> into a "pubHols.tmp" file.
	 * @param cineplexArray containing a <code>ArrayList</code> of <code>Calendar</code>. 
	 */
	public static void savePubHolList(ArrayList<Calendar> pubHolArray) {
		try{
			FileOutputStream fos = new FileOutputStream("pubHols.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(pubHolArray);
			oos.close();
		}
		catch (FileNotFoundException e) {}
		catch(IOException e) {}
	}
}
