package moblima;

/**
 * Controller class which manages <code>Cineplex</code> objects, namely adding and removing cineplexes.
 * <p>Implements a Singleton structure. The instance can be called with <code>getInstance()</code>.
 * @see Cineplex
 */
public class CineplexMgr {
	
	//implement Singleton structure
	private static CineplexMgr instance;
	public static CineplexMgr getInstance() {
		if(instance == null) {
			instance = new CineplexMgr();
		}
		return instance;
	}
	
	/**
	 * Calls to <code>ConsoleBoundary</code> to ask for user to choose what to do with <code>Cineplex</code> objects. 
	 * <p> Saves to the database every time a change is made.
	 */
	public void CineplexOps() {
		boolean loop = true;
		
		while(loop) {
			int choice = ConsoleBoundary.printCineplexOps();
			switch(choice) {
			case 1: addCineplex(); break;
			case 2: removeCineplex(); break;
			case 0: loop = false; break;
			}
			DataBoundary.saveCineplexList(CineplexList.cineplexList);
		}
	}
	
	/**
	 * Adds a <code>Cineplex</code> object to the static list of cineplexes in the <code>CineplexList</code> class.
	 * Calls to the <code>ConsoleBoundary</code> to interact with the user for the cineplex details.
	 */
	private void addCineplex() {
		String[] cineplexDetails = ConsoleBoundary.printAddCineplex();
		CineplexList.addCineplex(cineplexDetails[0], cineplexDetails[1]);
	}
	
	/**
	 * Removes a <code>Cineplex</code> object from the static list of cineplexes in the <code>CineplexList</code> class.
	 * Calls to the <code>ConsoleBoundary</code> to interact with the user for the cineplex details.
	 */
	private void removeCineplex() {
		CineplexList.removeCineplex(ConsoleBoundary.printRemoveCineplex());
	}
}
