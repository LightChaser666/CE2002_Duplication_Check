package moblima;

/**
 * Inherits from <code>Account</code> class.
 * To allow extension for future <code>staffOperations</code>.
 * @see Account
 * @see StaffCtr
 *
 */

public class StaffAcc extends Account{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7942334937585811303L;
	/**
	 * <code>StaffAcc</code> constructor.
	 * @param username
	 * @param password
	 * @param name
	 */
	public StaffAcc(String username, String password, String name) {
		super(username, password, name);

	}
	//open for extension like staff benefits function etc
}

