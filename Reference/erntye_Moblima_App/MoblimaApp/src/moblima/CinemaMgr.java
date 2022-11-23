package moblima;

/**
 * Controller class which manages <code>Cinema</code> objects, including adding, removing, and editing the premium values.
 * <p>Implements a Singleton structure. The instance can be called with <code>getInstance()</code>.
 * @see Cinema
 */
public class CinemaMgr {

	//implement Singleton structure
	private static CinemaMgr instance;
	public static CinemaMgr getInstance() {
		if(instance == null) {
			instance = new CinemaMgr();
		}
		return instance;
	}
	
	/**
	 * Calls to <code>ConsoleBoundary</code> to ask for user to choose what to do with <code>Cinema</code> objects. 
	 * <p> Saves to the database every time a change is made.
	 */
	public void CinemaOps() {
		boolean loop = true;
		Cineplex cineplex = ConsoleBoundary.printChooseCineplex();
		
		while(loop) {
			int choice = ConsoleBoundary.printCinemaOps(cineplex.getName());
			switch(choice) {
			case 1: addCinema(cineplex); break;
			case 2: removeCinema(cineplex); break;
			case 3: changePremium(); break;
			case 0: loop = false; break;
			}
			DataBoundary.saveCineplexList(CineplexList.cineplexList);
		}
	}
	
	/**
	 * Adds a <code>Cinema</code> object to the list of cinemas in a <code>Cineplex</code> object.
	 * @param cineplex <code>Cineplex</code> object to add the cinema to.
	 */
	private void addCinema(Cineplex cineplex) {
		String[] movieDetails = ConsoleBoundary.printAddCinema(cineplex.getName());
		int layoutNumber = Integer.parseInt(movieDetails[1]);
		switch(Integer.parseInt(movieDetails[2])) {
		case 1: //normal
			cineplex.addCinema(new Cinema(movieDetails[0], layoutNumber));
			System.out.println("normal cinema added");
			break;
		case 2: //gold
			cineplex.addCinema(new CinemaGold(movieDetails[0], layoutNumber));
			System.out.println("Gold Class cinema added");
			break;
		case 3: //platinum
			cineplex.addCinema(new CinemaPlatinum(movieDetails[0], layoutNumber));
			System.out.println("Platinum Class cinema added");
			break;
		default: //normal
			cineplex.addCinema(new Cinema(movieDetails[0], layoutNumber));
			System.out.println("default cinema (normal) added");
			break;
		}
	}
	
	/**
	 * Removes a <code>Cinema</code> object from the list of cinemas in a <code>Cineplex</code> object.
	 * @param cineplex <code>Cineplex</code> object to remove the cinema from.
	 */
	private void removeCinema(Cineplex cineplex) {
		Cinema cinemaToRemove = ConsoleBoundary.printRemoveCinema(cineplex);
		cineplex.removeCinema(cinemaToRemove);
		System.out.println("Cinema removed");
	}
	
	/**
	 * Change the premium value of the cinema type (Standard, Gold Class, Platinum Class).
	 * <br>Makes a call to the <code>ConsoleBoundary</code> to get the user input.
	 */
	private void changePremium() {
		float[] changePremiumDetails = ConsoleBoundary.printChangePremium();
		switch((int)changePremiumDetails[0]) {
		case 1: //normal
			Cinema.setPremium(changePremiumDetails[1]);
			System.out.println("Normal Cinema premium updated!");
			break;
		case 2: //Gold Class
			CinemaGold.setPremium(changePremiumDetails[1]);
			System.out.println("Gold Class Cinema premium updated!");
			break;
		case 3: //Platinum Class
			CinemaPlatinum.setPremium(changePremiumDetails[1]);
			System.out.println("Platinum Class Cinema premium updated!");
			break;
		}
		CSVBoundary.saveCinemaPremium();
	}
}
