package moblima;
/**
 * Main logic for <code> StaffAcc</code>.
 * Controls <code> staffOperations()</code>.
 * <p>Implements a Singleton structure. The instance can be called with <code>getInstance()</code>.
 * @see MovieMgr
 * @see ShowMgr
 * @see SystemSettingMgr
 * @see CineplexMgr
 * @see CinemaMgr
 */
public class StaffCtr {
	
	//implement Singleton structure
	private static StaffCtr instance;
	public static StaffCtr getInstance() {
		if(instance == null) {
			instance = new StaffCtr();
		}
		return instance;
	}
	
	/**
	 * Redirects the <code> StaffAcc</code> to the appropriate page.
	 * <p> 1. Manage <code>Movies</code>.
	 * <br> 2. Manage <code>Shows</code>.
	 * <br> 3. Manage <code>System Settings</code>.
	 * <br> 4. Manage <code>Cineplexes</code>.
	 * <br> 5. Manage <code>Cinemas</code>.
	 * @see MovieMgr
	 * @see ShowMgr
	 * @see SystemSettingMgr
	 * @see CineplexMgr
	 * @see CinemaMgr
	 */
	public void staffOperations(){
		int choice;
		do {
			choice = ConsoleBoundary.printStaffPage();
			switch(choice) {
			case 1: MovieMgr.getInstance().movieOps(); break;
			case 2: ShowMgr.getInstance().showOps(); break;
			case 3: SystemSettingMgr.getInstance().sysSetOps(); break;
			case 4: CineplexMgr.getInstance().CineplexOps(); break;
			case 5: CinemaMgr.getInstance().CinemaOps(); break;
			}
		} while (choice != 0);
		ConsoleBoundary.printLogout();
	} 
}
