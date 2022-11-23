package moblima;
import java.io.*;

/**
 * Represents an account, which can be Staff Account or Customer Account
 * StaffAcc and CustAcc are subclasses of the Account class
 * <p>
 * Used for adding/removing accounts, and for logging in
 */

public abstract class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6384389949172773596L;
	/**
	 * a String which stores the username of the account. set when account is created.
	 */
	private String username;
	/**
	 * a String which stores the password of the account. set when account is created.
	 */
	private String password;
	/**
	 * name of the user. no specific format is set
	 */
	private String name;

	/**
	 * Constructor for account class. constructor is not called directly,
	 * but rather from sub classes' constructors using keyword <code>super</code>.
	 * @param username User's chosen username.
	 * @param password User's chosen password.
	 * @param name User's name.
	 * @see StaffAcc
	 * @see CustAcc
	 */
	public Account(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	//return 1: login success
	//return 2: wrong password
	//return 3: wrong user name
	/**
	 * verifies a log in by checking the <code>username</code> and <code>password</code> passed into the function
	 * with the object's stored attributes.<p>
	 * @param username Username captured by the system at log in.
	 * @param password Password captured by the system at log in.
	 * @return Integer value representing results of the method:
	 * <br>1 if login is successful, 2 if password is wrong, 3 if username not found.
	 */
	public int verifyLogin(String username, String password) {
		if (this.username.equals(username)) {
			if(this.password.equals(password)) {
				return 1;
			}
			else {
				return 2;
			}
		}
		else {
			return 3;
		}
	}

	/**
	 * Get the stored value of <code>name</code> in the account.
	 * Used to address user by <code>name</code>
	 * @return Name of the user as a <code>String</code>
	 */
	public String getName() {
		return name;
	}
}
