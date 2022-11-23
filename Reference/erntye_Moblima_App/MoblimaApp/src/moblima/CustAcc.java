package moblima;
import java.util.ArrayList;

/**
 * The Customer Account class.
 * <br> Contains the Age Category (CHILD, STUDENT, SENIOR_CITIZEN, ADULT), mobile phone number, and email of a customer.
 * <br> List of the <code>Transaction</code> objects, representing movie bookings/ transactions made by a particular customer.
 * @see Account
 * @see StaffAcc
 */
public class CustAcc extends Account{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5983626060596941305L;
	/**
	 * Enumerator list containing <code>{CHILD, STUDENT, SENIOR_CITIZEN, ADULT}</code>
	 */
	public enum Age_Cat {CHILD, STUDENT, SENIOR_CITIZEN, ADULT}
	
	/**
	 * List of <code>Transaction</code> objects. Used to keep track of all transactions made by the customer,
	 * and to check the booking history.
	 * @see Transaction
	 */
	public ArrayList<Transaction> transactionList;
	/**
	 * Mobile Number of the customer stored as a <code>double</code>.
	 */
	private double mobileNumber;
	/**
	 * Email of the customer.
	 */
	private String email;
	/**
	 * Age Category of the customer. Used to calculate prices, with CHILD, STUDENT and SENIOR_CITIZEN having different discounts each.
	 */
	public Age_Cat ageCat; 
	
	/**
	 * Constructor for the <code>CustAcc</code> class.
	 * @param username Username for the account.
	 * @param password Password for the account.
	 * @param name Name of the customer.
	 * @param mobileNumber Mobile phone number of the customer.
	 * @param email Email of the customer.
	 * @param ageCat Age Category the customer belongs to.
	 * @see Transaction
	 */
	public CustAcc(String username, String password, String name, double mobileNumber, String email, Age_Cat ageCat) {
			super(username, password, name);
			this.mobileNumber = mobileNumber;
			this.email = email;
			this.ageCat = ageCat;
			this.transactionList = new ArrayList<Transaction>();
		}
	/**
	 * Gets age category of the customer.
	 * @return Enumerator of AgeCat.
	 */
	public Age_Cat getAgeCat() {
			return ageCat;
		}
	
	/**
	 * Gets <code>String</code> value of the email.
	 * @return <code>String</code> email.
	 */
	public String getEmail() {
			return email;
		}
	
	/**
	 * Gets the <code>double</code> value of the customer's mobile phone number.
	 * @return <code>double</code> Mobile phone number.
	 */
	public double getMobileNumber() {
			return mobileNumber;
		}
	
	/**
	 * Gets a reference to the list of <code>Transaction</code> objects stored in the customer account.
	 * @return <code>ArrayList&lt;Transaction&gt;</code> reference.
	 * @see Transaction
	 */
	public ArrayList<Transaction> getTransactionList(){
			return transactionList;
		}
	
	/**
	 * Adds a <code>Transaction</code> object to the list of transactions.
	 * @param transactionToAdd <code>Transaction</code> object to add.
	 */
	public void addTransaction(Transaction transactionToAdd){
		transactionList.add(transactionToAdd);
		}
		
}


