package moblima;

//import java.util.ArrayList;

/**
 * A subclass of <code>Cinema</code>. Represents the Gold Class cinemas, which have more comfortable seats and better service.
 * Gold Class cinemas have a higher premium compared to standard cinemas. The implementation of Gold Class Cinemas as a separate class is 
 * to allow for extendability to create functionalities exclusive to Gold Class such as special requests.
 * @see Cinema
 * @see CinemaPlatinum
 */
public class CinemaGold extends Cinema{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4127083264271994397L;
	/**
	 * A <code>float</code> value of the premium for Gold Class cinemas, to be added for price calculation.
	 */
	private static float premium;
	
	/**
	 * A constructor of the <code>CinemaGold</code> class.
	 * @param name Name of the cinema.
	 * @param layoutNumber Choice of the layout.
	 */
	public CinemaGold(String name, int layoutNumber){
		super(name, layoutNumber);
	}
	
	/**
	 * Sets the value of premium for Gold Class cinemas
	 * @param newPremium New premium to be added.
	 */
	public static void setPremium(float newPremium){
		CinemaGold.premium = newPremium;
	}
	
	public float calculatePrice(Show show, CustAcc custAcc) { 
		float price = show.getBasePrice() + premium;
		if(PublicHolidayCalendar.isPubHol(show.getShowTime()))
			price += PublicHolidayCalendar.getPublicHolidayPremium();
		switch(custAcc.getAgeCat()) {
		case CHILD:
			price *= 0.25;
			break;
		case STUDENT:
			price *= 0.50;
			break;
		case ADULT:
			break;
		case SENIOR_CITIZEN:
			price *= 0.50;
			break;
		}
		
		return price;
	}
	
	/**
	 * Gets the premium for the <code>Cinema</code> instance.
	 * @return float <code>premium</code>
	 */
	public static float getPremium() {
		return premium;
	}
	
	public String printCinemaClass() {
		return "Gold Class";
	}
}

