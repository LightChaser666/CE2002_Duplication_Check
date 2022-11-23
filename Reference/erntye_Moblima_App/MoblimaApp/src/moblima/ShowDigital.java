package moblima;
import java.util.Calendar;
/**
 * Inherits from <code> Account</code> class.
 * @see Show
 *
 */
public class ShowDigital extends Show {
	/**
	 * Stores the <code>Base Price</code> of a <code> Digital Show </code>.
	 */
	private static final long serialVersionUID = -6262724137447971700L;
	private static float basePrice;
	/**
	 * <code>ShowDigital</code> constructor.
	 * @param movie
	 * @param showTime
	 * @param cinemaLayout
	 */
	public ShowDigital(String movie, Calendar showTime, char[][] cinemaLayout) {
		super(movie, showTime, cinemaLayout);
	}
	
	/**
	 * @return <code>float</code> value of <code>Show</code>'s base price. Method is static for saving in the database.
	 */
	public static float getBasePriceForSaving() {
		return basePrice;
	}
	
	/**
	 * @return <code>float</code> value of <code>ShowDigital</code>'s base price.
	 */
	public float getBasePrice(){
		return basePrice;
	}
	/**
	 * Allows <code>StaffAcc</code> to set the <code>Base Price</code> of an <code>Digital Show</code>.
	 * @see StaffCtr
	 */
	public static void setBasePrice(float newBasePrice){
		ShowDigital.basePrice = newBasePrice; 
	}
	
	/**
	 * @return <code>String</code> containing show type.
	 */
	public String getShowType() {
		return "Digital";
	}

}
