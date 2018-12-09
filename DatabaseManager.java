/**
 * Project 5
 *
 * DatabaseManager class
 *
 * @author Kathryn Hanagan, Shruti Srinivasan, section 11
 *
 * @version 12/08/18
 *
 */
import java.io.File;
import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * <h1>Database Manager</h1>
 * <p>
 * Used to locally save and retrieve data.
 */
public class DatabaseManager {

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) {
        System.out.println("loadVehicles");
        ArrayList<Vehicle> list = new ArrayList<>();
        String[] parameters;

        if (!file.exists()) {
            return list;
        }

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String string = bufferedReader.readLine();

            while (string != null) {
                parameters = string.split(",");
                Vehicle v = null;
                if (parameters[0].equals("Truck")) {
                    v = new Truck(parameters[1], Double.parseDouble(parameters[2]));
                } else if (parameters[0].equals("Drone")) {
                    v = new Drone(parameters[1], Double.parseDouble(parameters[2]));
                } else if (parameters[0].equals("Cargo Plane")) {
                    v = new CargoPlane(parameters[1], Double.parseDouble(parameters[2]));
                }
                list.add(v);
                string = bufferedReader.readLine();
            } //while

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        return list;
    } //loadVehicles


    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * <p>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) {

        System.out.println("loadPackages");

        ArrayList<Package> list = new ArrayList<>();
        String[] parameters;
        if (!file.exists()) {
            return list;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String string = bufferedReader.readLine();

            while (string != null) {
                parameters = string.split(",");
                ShippingAddress sa = new ShippingAddress(parameters[1], parameters[4], parameters[5],
                        parameters[6], Integer.parseInt(parameters[7]));
                Package p = new Package(parameters[0], parameters[1], Double.parseDouble(parameters[2]),
                        Double.parseDouble(parameters[3]), sa);
                list.add(p);
                string = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        return list;
    } //loadPackages


    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     *
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
        if (!file.exists()) {
            return 0;
        }
        System.out.println("loadProfit");
        double profit = 0.0;
        if (!file.exists()) {
            return 0;
        }

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            profit = Double.parseDouble(line);

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return profit;
    } //loadProfit


    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     *
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
        if (!file.exists()) {
            return 0;
        }
        int numberOfPackages = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            numberOfPackages = Integer.parseInt(line);

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numberOfPackages;
    } //loadPackagesShipped


    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     *
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {
        if (!file.exists()) {
            return false;
        }
        System.out.println("loadPrimeDay");
        boolean primeDay = false;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            if (line.equals("1")) {
                primeDay = true;
            } else if (line.equals("0")) {
                primeDay = false;
            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return primeDay;
    } //loadPrimeDay


    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     *
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        System.out.println("saveVehicles");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i) instanceof Drone) {
                    bw.write("Drone," + vehicles.get(i).getLicensePlate() + "," + vehicles.get(i).getMaxWeight() + "\n");
                } else if (vehicles.get(i) instanceof Truck) {
                    bw.write("Truck," + vehicles.get(i).getLicensePlate() + "," + vehicles.get(i).getMaxWeight() + "\n");
                } else if (vehicles.get(i) instanceof CargoPlane) {
                    bw.write("Cargo Plane," + vehicles.get(i).getLicensePlate() + "," + vehicles.get(i).getMaxWeight() + "\n");
                }
            } //for
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //saveVehicles


    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
        System.out.println("savePackages");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < packages.size(); i++) {
                bw.write(packages.get(i).getID() + ","
                        + packages.get(i).getProduct() + ","
                        + packages.get(i).getWeight() + ","
                        + packages.get(i).getPrice() + ","
                        + packages.get(i).getDestination().getName() + ","
                        + packages.get(i).getDestination().getAddress() + ","
                        + packages.get(i).getDestination().getCity() + ","
                        + packages.get(i).getDestination().getState() + ","
                        + packages.get(i).getDestination().getZipCode() + "\n");
            } //for
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //savePackages


    /**
     * Saves profit to text file.
     *
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
        System.out.println("saveProfits");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(profit + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //saveProfit


    /**
     * Saves number of packages shipped to text file.
     *
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
        System.out.println("savePackagesShipped");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(nPackages + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //savePackageShipped


    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * written, otherwise "0" will be written.
     *
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        System.out.println("savePrimeDay");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            if (primeDay) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //savePrimeDay

} //class
