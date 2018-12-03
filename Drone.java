import java.util.ArrayList;


/**
 * <h1>Drone</h1> Represents a Drone
 */

public class Drone extends Vehicle implements Profitable {

    final private double GAS_RATE = 1.33;
    /**
     * Default Contructor 
     */
    //============================================================================
    public Drone() {
        super();
    } //default constructor
    
    //============================================================================

    /**
     * Constructor
     * 
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    //============================================================================
    public Drone(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    } //constructor
    
    //============================================================================

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */
    /**
     * Returns the profits generated by the packages currently in the Truck.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 1.33)
     * </p>
     */
    @Override
    public double getProfit() {
        // TODO - fix maxRange
        double profit = 0;
        for (int i = 0; i < getPackages().size(); i++) {
            profit += getPackages().get(i).getPrice();
        }
        int maxRange = getPackages().get(getPackages().size() - 1).getDestination()
        profit -= maxRange * GAS_RATE;

        return profit;
    } //getProfit

    /**
     * Generates a String of the Drone report. Drone report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in truck</li>
     * </ul>
     * 
     * @return Truck Report
     */
    @Override
    public String report() {
        String report = "==========Drone Report==========" +
                "\nLicense Plate No.: " + this.getLicensePlate() +
                "\nDestination: " + this.getZipDest() +
                "\nWeight Load: "+ this.getCurrentWeight() + "/" + this.getMaxWeight() +
                "\nNet Profit: " + this.getProfit() +
                "\n=====Shipping Labels=====";
        for (int i = 0; i < this.getPackages().size(); i++) {
            report += "\n====================" +
                    "\nTO: " + this.getPackages().get(i).shippingLabel() +
                    "\nWeight: " + this.getPackages().get(i).getWeight() +
                    "\nPrice: " + this.getPackages().get(i).getPrice() +
                    "\nProduct: " + tthis.getPackages().get(i).getProduct() +
                    "\n====================";
        } //for
        report += "==============================\n";
        return report;
    } //report
    
   

} //class
