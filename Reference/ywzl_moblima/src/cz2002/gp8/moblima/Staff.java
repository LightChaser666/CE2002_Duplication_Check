package cz2002.gp8.moblima;



/**
 * This represents the admin that has a username and password
 * @author Lloyd
 */
public class Staff {
	String username;
	String password;
	
    /**
     *This creates a staff member with a username and password
     * @param username
     * @param password
     */
    public Staff(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
    /**
     *This returns the username 
     * @return username of the staff
     */
    public String getUsername() {
		return username;
	}
	
    /**
     *
     * @return password of the staff
     */
    public String getPassword() {
		return password;
	}

}
