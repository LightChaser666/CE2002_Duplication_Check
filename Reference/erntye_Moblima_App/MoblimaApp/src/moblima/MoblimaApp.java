package moblima;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class MoblimaApp {
	
	public static void main(String[] args){
		initialiseEverything();
		

		String[] loginDetails = new String[3];
		
		//Main Menu: ask log in or create account
		int mainMenuChoice;
		boolean loop = true;
		while(loop) {
			mainMenuChoice = ConsoleBoundary.printMainMenu();
			switch(mainMenuChoice) {
			case 1: //Log In
				LoginMgr.getInstance().login(); break;
			case 2: //Add Account
				ConsoleBoundary.printAddAccount(); break;
			case 0: //quit
				ConsoleBoundary.printTerminate();
				loop = false; break;
			}
		}

		
		ConsoleBoundary.printLogout();
	}
	
	public static void initialiseEverything() {
		
		

		ArrayList<Account> staffArray = DataBoundary.getStaffList();
		ArrayList<Account> custArray = DataBoundary.getCustList();
		LoginMgr.getInstance().initialiseAccounts(staffArray, custArray);
		CineplexList.cineplexList = DataBoundary.getCineplexList();


		MovieList.movieList = DataBoundary.getMovieList();
		PublicHolidayCalendar.pubHolList = DataBoundary.getPubHolList();
		float[] basePrices = CSVBoundary.retrieveBasePrices();
		Show.setBasePrice(basePrices[0]);
		ShowDigital.setBasePrice(basePrices[1]);
		Show3D.setBasePrice(basePrices[2]);
		ShowIMAX.setBasePrice(basePrices[3]);
		
		float[] cinemaPremiums = CSVBoundary.retrieveCinemaPremium();
		Cinema.setPremium(cinemaPremiums[0]);
		CinemaGold.setPremium(cinemaPremiums[1]);
		CinemaPlatinum.setPremium(cinemaPremiums[2]);
		
	}

}
