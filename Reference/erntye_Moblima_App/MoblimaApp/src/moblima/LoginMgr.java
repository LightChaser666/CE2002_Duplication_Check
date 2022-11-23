package moblima;

import java.util.ArrayList;

/**
 * Controller class for managing <code>Account</code> objects.
 * <br> Stores the <code>ArrayList&lt;Account&gt;</code> of staff accounts and customer accounts. Manages the operations
 * related to the accounts, including adding, removing, and validating logins.
 * <p>Implements a Singleton structure. The instance can be called with <code>getInstance()</code>.
 * @see CustAcc
 * @see StaffAcc
 */
public class LoginMgr {
	/**
	 * <code>ArrayList&lt;Account&gt;</code> of Staff Accounts.
	 * <br><code>StaffAcc</code> objects are created, then upcasted to <code>Account</code> types and stored in the array list.
	 * @see StaffAcc
	 */
	ArrayList<Account> staffArray;
	
	/**
	 * <code>ArrayList&lt;Account&gt;</code> of Customer Accounts.
	 * <br><code>CustAcc</code> objects are created, then upcasted to <code>Account</code> types and stored in the array list.
	 * @see CustAcc
	 */
	ArrayList<Account> custArray;
	
	/**
	 * A reference of the account that is logged into. After the username and passwords are validated, and the user 
	 * is logged into the account, the reference to the <code>CustAcc</code> or <code>StaffAcc</code> is saved in this variable.
	 */
	Account loggedInAccount;
	
	
	//implement Singleton structure
	private static LoginMgr instance;
	public static LoginMgr getInstance() {
		if(instance == null) {
			instance = new LoginMgr();
		}
		return instance;
	}
	
	/**
	 * Manages the log in process by obtaining the user's username and password from <code>ConsoleBoundary</code> and
	 * calling the <code>validate</code> function.
	 */
	public void login() {
		String[] loginDetails = ConsoleBoundary.printLoginPage();
		int validateReturn = validate(loginDetails[0], loginDetails[1], loginDetails[2]);
		switch(validateReturn) {
		case 1:
			switch(loginDetails[0]) {
			case "1": StaffCtr.getInstance().staffOperations(); break;
			case "2": CustCtr.getInstance().custOperations(); break;
			}
			break;
		case 2:
			//wrong password printed in validate function
			break;
		case 3:
			//account not found printed in validate function
			break;
		}
	}
	
	//validates user's log in
	//return 0: the account.verifyLogin functions have failed
	//return 1: login success
	//return 2: wrong password
	//return 3: user name not found in array
	//return 4: account type invalid
	/**
	 * Validates the username and password from the user by calling the <code>verifyLogin</code> function in each <code>Account</code>
	 * object in Staff Account and Customer Account lists respectively.
	 * @param accType The type of account that is being logged in. (Customer or Staff)
	 * @param username Username entered by the user.
	 * @param password Password entered by the user.
	 * @return Integer value of the results of the validation.
	 * <br> 1: Log In successful. 2: Password incorrect. 3: Account not found.
	 * @see Account
	 */
	public int validate(String accType, String username, String password) {
		int result = 0;
		int accountType = Integer.parseInt(accType);
		switch(accountType) {
		case 1:
			for (Account account : staffArray) {
				result = account.verifyLogin(username, password);
				if(result == 1) {
					StaffAcc temp = (StaffAcc)account;
					loggedInAccount = temp;
					return 1;
				}else if(result == 2) {
					System.out.println("Wrong Password!");
					return result;
				}
			}
			System.out.println("Staff Account not found!");
			return 3;
		case 2:
			for (Account account : custArray) {
				result = account.verifyLogin(username, password);
				if(result == 1) {
					CustAcc temp = (CustAcc)account;
					loggedInAccount = temp;
					return 1;
				}else if(result == 2) {
					System.out.println("Wrong Password!");
					return result;
				}
			}
			System.out.println("Customer Account not found!");
			return 3;
		default:
			return 4;
		}
	}
	
	/**
	 * Creates a <code>CustAcc</code> object, upcast it to an <code>Account</code> object and store it in the
	 * customer accounts list.
	 */
	public void addCustAccount() {
		String[] accountDetails = ConsoleBoundary.printAddCustAccount();
		double mobileNumber = Double.parseDouble(accountDetails[3]);
		int age = Integer.parseInt(accountDetails[5]);
		
		CustAcc.Age_Cat ageCategory = CustAcc.Age_Cat.ADULT; //defaults to ADULT
		if(age<11) {
			ageCategory = CustAcc.Age_Cat.CHILD;
		}else if(age< 21) {
			ageCategory = CustAcc.Age_Cat.STUDENT;
		}else if(age< 65) {
			ageCategory = CustAcc.Age_Cat.ADULT;
		}else {
			ageCategory = CustAcc.Age_Cat.SENIOR_CITIZEN;
		}
		
		CustAcc newAcc = new CustAcc(accountDetails[0],accountDetails[1],accountDetails[2],mobileNumber,accountDetails[4],ageCategory);
		if(custArray.add(newAcc)) {
			System.out.println("customer account added successfully!"); //for testing
			DataBoundary.saveCustList(custArray);
		}
	}
	
	/**
	 * Creates a <code>StaffAcc</code> object, upcast it to an <code>Account</code> object and store it in the
	 * staff accounts list.
	 */
	public void addStaffAccount() {
		String[] accountDetails = ConsoleBoundary.printAddStaffAccount();
		StaffAcc newAcc = new StaffAcc(accountDetails[0],accountDetails[1],accountDetails[2]);
		if(staffArray.add(newAcc)) {
			System.out.println("staff account added successfully!"); //for testing
			DataBoundary.saveStaffList(staffArray);
		}
	}
	
	/**
	 * Initialises the <code>LoginMgr</code> singleton object with the staff and customer lists from the database.
	 * @param staffArray List of staff accounts.
	 * @param custArray List of customer accounts.
	 * @see CustAcc
	 * @see StaffAcc
	 */
	public void initialiseAccounts(ArrayList<Account> staffArray, ArrayList<Account> custArray) {
		this.staffArray = staffArray;
		this.custArray = custArray;
	}
	
	/**
	 * Gets a reference to the <code>ArrayList&lt;Account&gt;</code> of customer accounts.
	 * @return <code>ArrayList&lt;Account&gt;</code> Customer Accounts.
	 */
	public ArrayList<Account> getCustList(){
		return custArray;
	}
}