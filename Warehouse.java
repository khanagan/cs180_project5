/**
 * Project 5
 *
 * Warehouse class
 *
 * @author Kathryn Hanagan, Shruti Srinivasan, section 11
 *
 * @version 12/08/18
 *
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
	final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    private static String id = "";

    /**
     * Main Method
     * 
     * @param args list of command line arguments
     */
    public static void main(String[] args) {
        //1) load data (vehicle, packages, profits, packages shipped and primeDay) from files using DatabaseManager
        ArrayList <Vehicle> vehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
        ArrayList <Package> packages = DatabaseManager.loadPackages(PACKAGE_FILE);
        double profit = DatabaseManager.loadProfit(PROFIT_FILE);
        int numOfPackages = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
        boolean primeDay = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);



        //2) Show menu and handle user inputs
        boolean isPrimeDay = false;
        boolean continuing = true;
        do {
            if (isPrimeDay) {
                System.out.println("==========Options==========\n" +
                        "1) Add Package\n" +
                        "2) Add Vehicle\n" +
                        "3) Deactivate Prime Day\n" +
                        "4) Send Vehicle\n" +
                        "5) Print Statistics\n" +
                        "6) Exit\n" +
                        "===========================");
            } else {
                System.out.println("==========Options==========\n" +
                        "1) Add Package\n" +
                        "2) Add Vehicle\n" +
                        "3) Activate Prime Day\n" +
                        "4) Send Vehicle\n" +
                        "5) Print Statistics\n" +
                        "6) Exit\n" +
                        "===========================");
            }
            Scanner scanner = new Scanner(System.in);
            int choice1 = scanner.nextInt();
            switch (choice1) {
                case 1: //add package
                    System.out.println("Enter Package ID:");
                    id = scanner.nextLine();
                    System.out.println("Enter Product Name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Weight:");
                    double weight = scanner.nextDouble();
                    System.out.println("Enter Price:");
                    scanner.nextLine();
                    double price = scanner.nextDouble();
                    System.out.println("Enter Buyer Name:");
                    scanner.nextLine();
                    String addressName = scanner.nextLine();
                    System.out.println("Enter Address:");
                    String address = scanner.nextLine();
                    System.out.println("Enter City:");
                    String city = scanner.nextLine();
                    System.out.println("Enter State:");
                    String state = scanner.nextLine();
                    System.out.println("Enter ZIP Code:");
                    int zip = scanner.nextInt();
                    scanner.nextLine();

                    Package p = new Package(id, name, weight, price, new ShippingAddress(addressName, address, city, state, zip));
                    numOfPackages++;
                    System.out.println();
                    System.out.println(p.shippingLabel());

                    break;
                case 2: //add vehicle
                    System.out.println("Vehicle Options\n1) Truck\n2) Drone\n3) Cargo Plane");
                    int vType = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter License Plate No.:");
                    String plate = scanner.nextLine();
                    System.out.println("Enter Maximum Carry Weight:");
                    double maxWeight = scanner.nextDouble();
                    scanner.nextLine();
                    Vehicle v = null;
                    if (vType == 1) {
                        v = new Truck(plate, maxWeight);
                    } else if (vType == 2) {
                        v = new Drone(plate, maxWeight);
                    } else if (vType == 3) {
                        v = new CargoPlane(plate, maxWeight);
                    }
                    vehicles.add(v);

                    break;
                case 3: //primeDay
                    if (isPrimeDay) { //DE-activate
                        isPrimeDay = false;
                        for (int i = 0; i < packages.size(); i++) {
                            packages.get(i).setPrice(packages.get(i).getPrice() / .85);
                        }
                    } else { //RE-activate
                        isPrimeDay = true;
                        for (int i = 0; i < packages.size(); i++) {
                            packages.get(i).setPrice(packages.get(i).getPrice() * .85);
                        }
                    }

                    break;
                case 4: //send vehicle
                    //TODO

                    //error handling:
                    if (vehicles.size() == 0) {
                        System.out.println("Error: No vehicles available.");
                    } else if (packages.size() == 0) {
                        System.out.println("Error: No packages available.");
                    } else {

                        //get vehicle type
                        System.out.println("Options:\n" +
                                "1) Send Truck\n" +
                                "2) Send Drone\n" +
                                "3) Send Cargo Plane\n" +
                                "4) Send First Available");
                        int vChoice = scanner.nextInt();
                        scanner.nextLine();
                        //error: no vehicles of selected type are available
                        boolean foundIt = false;
                        int index = 0;
                        if (vChoice == 1) {
                            for (int i = 0; i < vehicles.size(); i++) {
                                if (vehicles.get(i) instanceof Truck) {
                                    foundIt = true;
                                    index = i;
                                } //if you find a truck!
                            } //for - loops through vehicles to check for Trucks

                        } else if (vChoice == 2) {
                            for (int i = 0; i < vehicles.size(); i++) {
                                if(vehicles.get(i) instanceof Drone) {
                                    foundIt = true;
                                    index = i;
                                } //if
                            } //for

                        } else if (vChoice == 3) {
                            for (int i = 0; i < vehicles.size(); i++) {
                                if (vehicles.get(i) instanceof CargoPlane) {
                                    foundIt = true;
                                    index = i;
                                } //if
                            } //for

                        } else if (vChoice == 4) {
                            index = 0;
                            foundIt = true;
                        }

                        if (!foundIt) {
                            System.out.println("Error: No vehicles of selected type are available.");
                        } else {
                            // TODO - finish send to mode of zip codes
                            //decide zip code
                            System.out.println("ZIP Code Options:\n" +
                                    "1) Send to first ZIP Code\n" +
                                    "2) Send to mode of ZIP Code");
                            int zipChoice = scanner.nextInt();


                            if (zipChoice == 1) { //send to first zip code
                                vehicles.get(index).setZipDest(packages.get(0).getDestination().getZipCode());
                                vehicles.get(index).fill(packages);
                                System.out.println(id + "has been added.");
                                vehicles.get(index).report();
                                vehicles.remove(vehicles.get(index));
                            } else if (zipChoice == 2) { //send to the mode of zip codes
                                int highestCount = 1;
                                int mode = packages.get(0).getDestination().getZipCode();
                                int currentZip = mode;
                                for (int i = 0; i < packages.size(); i++) {
                                    int currentCount = 1;
                                    for (int j = i + 1; j < packages.size(); j++) {
                                        if (packages.get(i).equals(packages.get(j))) {
                                            currentZip = packages.get(j).getDestination().getZipCode();
                                            currentCount++;
                                        }

                                    } //for
                                    if (currentCount > highestCount) {
                                        highestCount++;
                                        mode = currentZip;
                                    }
                                } //for
                                //TODO : remove vehicle
                            }
                            //fill vehicle
                            //vehicle report
                            //add to statistics
                        }
                    }


                    break;

                case 5: //print statistics
                    printStatisticsReport(profit, numOfPackages, packages.size());
                    break;
                case 6: //exit
                    //save: packages, vehicles, profits, num of packages, prime day to corresponding files
                    continuing = false;
                    break;
                default:
                    System.out.println("Error: Option not available.");
                    break;
            } //big switch :)

        } while(continuing);
        //3) save data (vehicle, packages, profits, packages shipped, and primeDay) to files (overwriting them) using DatabaseManager
    	DatabaseManager.saveVehicles(VEHICLE_FILE, vehicles);
    	DatabaseManager.savePackages(PACKAGE_FILE, packages);
    	DatabaseManager.saveProfit(PROFIT_FILE, profit);
    	DatabaseManager.savePackagesShipped(N_PACKAGES_FILE, numOfPackages);
    	DatabaseManager.savePrimeDay(PRIME_DAY_FILE, primeDay);
    
    } //main

    public static void printStatisticsReport(double profit, int numOfPackages, int size) {
        System.out.println("==========Statistics==========" +
                "\nProfits:                   " + profit +
                "\nPackages Shipped:          " + numOfPackages +
                "\nPackages in Warehouse      " + size +
                "\n==============================");
    } //printStatisticsReport


} //Warehouse class