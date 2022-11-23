package moblima;

import java.util.Calendar;
/**
 * Manage <code> Shows</code> by adding to, editing or removing from <code>Cinema</code>'s <code>showList</code>.
 * <p>Implements a Singleton structure. The instance can be called with <code>getInstance()</code>.
 * @see Show
 * @see Cinema
 */
public class ShowMgr {
	
	//implement Singleton structure
	private static ShowMgr instance;
	public static ShowMgr getInstance() {
		if(instance == null) {
			instance = new ShowMgr();
		}
		return instance;
	}
	
	/**
	 * Add a <code>Show</code> object to a chosen <code>Cinema</code> and <code> Cineplex</code>.
	 * Asks user to input <code>Movie Title</code>, <code>Cinema</code>, <code>Show Time</code> and <code>Show Type</code>.
	 */
	private void addShow() {
		Cinema cinemaToAddTo = ConsoleBoundary.printAddShow();
		if(cinemaToAddTo == null)
			return;
		Movie movieToAdd = ConsoleBoundary.printMovieList();
		if(movieToAdd == null)
			return;
		Calendar showTime = ConsoleBoundary.getShowTime();
		if(showTime == null)
			return;
		String showType = ConsoleBoundary.printChooseShowType();
		if(showType == null)
			return;
		cinemaToAddTo.createShow(movieToAdd.getTitle(), showTime, showType);
		cinemaToAddTo.sortShowsByTime();
	}
	
	/**
	 * Removes a particular <code> Show</code> object from a <code> Cinema</code>'s <code>Show List</code>.
	 * @see Cinema
	 */
	private void removeShow() {
		Cinema cinemaToRemoveFrom = ConsoleBoundary.printRemoveShow();
		if(cinemaToRemoveFrom == null)
			return;
		Show showToRemove = ConsoleBoundary.printShowList(cinemaToRemoveFrom);
		if(showToRemove == null)
			return;
		cinemaToRemoveFrom.removeShow(showToRemove);
	}
	/**
	 * Edits a particular <code> Show</code> object from a <code> Cinema</code>'s <code>Show List</code>.
	 * @see Cinema
	 */
	private void editShow() {
		Cinema cinemaToEditFrom = ConsoleBoundary.printEditShow();
		if(cinemaToEditFrom == null)
			return;
		Show showToEdit = ConsoleBoundary.printShowList(cinemaToEditFrom);
		if(showToEdit == null)
			return;
		ConsoleBoundary.printEditShowDetails(showToEdit);
	}
	/**
	 * Controls the <code>ShowMgr</code> logic.
	 * Calls the appropriate methods according to user input.
	 * <p>1. Adds a <code> Show </code> object.
	 * <br>2. Removes a <code> Show </code> object.
	 * <br>3. Edits a <code> Show </code> object.
	 */
	public void showOps() {
		boolean loop = true;
		while(loop) {
			int opsChoice = ConsoleBoundary.printShowOps();
			switch(opsChoice) {
			case 1: addShow();break;
			case 2: removeShow(); break;
			case 3: editShow(); break;
			case 0: loop = false; break;
			}
			DataBoundary.saveCineplexList(CineplexList.cineplexList);
		}
	}
}
