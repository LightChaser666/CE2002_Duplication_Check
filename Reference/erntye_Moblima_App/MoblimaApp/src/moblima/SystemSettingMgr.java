package moblima;
/**
 * Allows <code> StaffAcc </code> to edit System Settings.
 * Called by the <code> StaffCtr</code>.
 * <p>Implements a Singleton structure. The instance can be called with <code>getInstance()</code>.
 * @see StaffCtr
 *
 */
public class SystemSettingMgr {
	int choice;
	
	//implement Singleton structure
	private static SystemSettingMgr instance;
	public static SystemSettingMgr getInstance() {
		if(instance == null) {
			instance = new SystemSettingMgr();
		}
		return instance;
	}
	
	/**
	 * Controls the Systems Setting logic.
	 * Calls the appropriate methods according to user input.
	 * <p>1. Sets base price of <code> Show </code> object.
	 * <br>2. Sets base price of <code> Show3D </code> object.
	 * <br>3. Sets base price of <code> ShowDigital </code> object.
	 * <br>4. Sets base price of <code> ShowIMAX </code> object.
	 * <br>5. Adds a new public holiday <code> date</code>.
	 * @see PublicHolidayCalendar
	 */
	public void sysSetOps() {
		do {
			choice = ConsoleBoundary.printSystemSettingMenu();
			switch(choice) {
			case 1: 
				Show.setBasePrice(ConsoleBoundary.askBasePrice());
				CSVBoundary.saveBasePrices();
				break;
			case 2: 
				Show3D.setBasePrice(ConsoleBoundary.askBasePrice());
				CSVBoundary.saveBasePrices();
				break;
			case 3:
				ShowDigital.setBasePrice(ConsoleBoundary.askBasePrice());
				CSVBoundary.saveBasePrices();
				break;
			case 4:
				ShowIMAX.setBasePrice(ConsoleBoundary.askBasePrice());
				CSVBoundary.saveBasePrices();
				break;
			case 5: 
				PublicHolidayCalendar.addPubHol(ConsoleBoundary.askPubHol()); 
				break;
			}
		} while (choice != 0);
	}
}
