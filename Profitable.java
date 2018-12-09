/**
 * Project 5
 *
 * Profitable Interface
 *
 * @author Kathryn Hanagan, Shruti Srinivasan, section 11
 *
 * @version 12/08/18
 *
 */

/**
 * <h1>Profitable</h1>
 * 
 * This interface represents something that can be used to make a profit. Along
 * with returning total profits it must also be able to provide a report.
 */
public interface Profitable {
	
    String report();
    double getProfit();

}