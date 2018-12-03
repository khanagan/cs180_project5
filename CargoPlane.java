import java.util.ArrayList;

/**
 * <h1>CargoPlane</h1> Represents a Cargo Plane
 */
public class CargoPlane extends Vehicle {
    final double GAS_RATE = 2.33;

    /**
     * Default Constructor
     */
    //============================================================================
    public CargoPlane() {
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
    public CargoPlane(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    } //constructor
    
    //============================================================================

    /**
     * Overides its superclass method. Instead, after each iteration, the range will
     * increase by 10.
     *
     * @param warehousePackages List of packages to add from
     */
    @Override
    public void fill(ArrayList<Package> warehousePackages) {
        //TODO

    } //fill

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Cargo Plane.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 2.33)
     * </p>
     */
    @Override
    public double getProfit() {
        //TODO - fix max range
        double profit = 0;
        for (int i = 0; i < getPackages().size(); i++) {
            profit += getPackages().get(i).getPrice();
        }
        //int maxRange = Math.abs(Integer.parseInt(getPackages().get(getPackages().size() - 1).getDestination()) - 1);
        //profit -= maxRange * GAS_RATE;

        return profit;
    } //getProfit

    /**
     * Generates a String of the Cargo Plane report. Cargo plane report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in cargo plane</li>
     * </ul>
     *
     * @return Cargo Plane Report
     */
    @Override
    public String report() {
        String report = "==========Cargo Plane Report==========" +
                "\nLicense Plate No.: " + this.getLicensePlate() +
                "\nDestination: " + this.getZipDest() +
                "\nWeight Load: "+ this.getCurrentWeight() + "/" + this.getMaxWeight() +
                "\nNet Profit: " + this.getProfit() +
                "\n=====Shipping Labels=====\n";
        for (int i = 0; i < this.getPackages().size(); i++) {
            report += this.getPackages().get(i).shippingLabel();
        } //for
        report += "==============================\n";
        return report;
       
    } //report
   
} //class