package cz2002.gp8.moblima;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

/**
 * StaffController manages Staff object and is used to check the credentials for login
 * @author Lloyd
 */
public class StaffController implements JSONFile {
	List<Staff> list;
	File JSONFile = new File("staff.json");
	Type Stafflist = new TypeToken<ArrayList<Staff>>(){}.getType();
	
    /**
     * Loads the list of Staff from JSONFile into list
     */
    public StaffController() {
		list = (List<Staff>) load(JSONFile, Stafflist);		
	}
	
    /**
     * Checks if the login credentials are correct by going through the entire list of staff
     * @param username of the staff logging in
     * @param password of the staff logging in
     * @return True if the credentials match
     */
    public boolean login(String username, String password) {
		for (Staff staff : list) {
			if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) return true;
		}
		return false;
	}

}
