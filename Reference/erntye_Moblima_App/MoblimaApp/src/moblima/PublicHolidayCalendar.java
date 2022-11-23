package moblima;

import java.util.ArrayList;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Entity class storing all the public holiday dates.
 * <code>Show</code> dates are checked against this list to add a public holiday premium to its ticket prices.
 * @see Cinema
 * @see SystemSettingMgr
 */
public class PublicHolidayCalendar implements Serializable{					
	/**
	 * The premium to be added to ticket prices for shows screening on public holidays.  
	 */
	private static float publicHolidayPremium = 2.3f;
	
	private static final long serialVersionUID = -7989540302679891401L;
	//Date class has to be created* using gregorion calendar to include pubHolList
	/**
	 * An <code>ArrayList</code> containing all the public holiday dates. 	
	 */
	public static ArrayList<Calendar> pubHolList = new ArrayList<Calendar>();
	
	/**
	 * @return <code>ArrayList&lt;Calendar&gt;</code> which contains the current list of public holidays.
	 */
	public ArrayList<Calendar> getPubHolList() {
		return pubHolList;
	}
		
	/**
	 * Adds a <Calendar> date object to store the new public holiday date.
	 * @param date
	 */
	public static void addPubHol(Calendar date) {
		pubHolList.add(date);
		DataBoundary.savePubHolList(pubHolList);
	}
	
	/**
	 * Checks the <code> Show</code> date to check if it falls on a public holiday.
	 * @param date
	 * @return <code> Boolean</code> indicating whether the <code>Show</code> date falls on a public holiday.
	 */
	public static boolean isPubHol(Calendar date) {
		for (int counter = 0; counter < pubHolList.size(); counter++) { 		      
			if (pubHolList.get(counter).get(Calendar.MONTH) == date.get(Calendar.MONTH)){
				if(pubHolList.get(counter).get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH))
					return true;
			}
		} 
		return false;	
	}
	
	/**
	 * Allows <code>StaffAcc</code> to change the price premium on public holiday tickets.
	 * @param newPremium
	 */
	public static void setPublicHolidayPremium(float newPremium) {
		publicHolidayPremium = newPremium;
	}

	/**
	 * 
	 * @return <code> float</code> value of public holiday price premium.
	 */
	public static float getPublicHolidayPremium() {
		return publicHolidayPremium;
	}

}


