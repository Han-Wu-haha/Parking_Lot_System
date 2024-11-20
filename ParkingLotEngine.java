import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ParkingLotEngine class provides the user interface and control mechanism to manage the parking lot simulation.
 * It interacts with the user through console commands to perform operations
 * like initializing the parking lot, checking in and checking out vehicles, viewing the current parking lot state, and more.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

/**
 * The ParkingLotEngine class drives the operations for parking a vehicle.
 */
public class ParkingLotEngine {
    private final String[] args; // to collect command line arguments
    private ParkingLot parkingLot;
    private String[][] parkingLotArray = null; // Two-dimensional representation of the parking lot
    private int globalLength = Constant.ZERO;
    private int globalWidth = Constant.ZERO;

    private Map<String, Vehicle> checkinMap = new HashMap<>();
    private List<CheckoutVehicle> checkoutVehicleList = new ArrayList<>();

    //Empty and Occupied spaces quantity in the parking lot
    private int empQty = Constant.QTY_INIT_VALUE;
    private int occQty = Constant.QTY_INIT_VALUE;

    /**
     * The main entry point for the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ParkingLotEngine engine = new ParkingLotEngine(args);
        try {
            engine.startParking();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Constructor initializes the parking lot engine with any given arguments.
     *
     * @param args command line arguments
     */
    public ParkingLotEngine(String[] args) {
        this.args = args;
        this.parkingLot = new ParkingLot(this);
    }

    /**
     * Initiates the parking process.
     * Reads user input and handles different commands.
     */
    private void startParking() throws Exception {
        // If provided, initializes parking lot dimensions using command line arguments.
        if (args != null && args.length == 2) {
            boolean status = initParkingLotByInputArgs(args[0], args[1]);
            if (!status) {
                return;
            }
        }
        displayWelcomeText();

        // Read user commands from console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inCmd = reader.readLine();
        // Handling various commands until user exits
        while (inCmd != null) {
            switch (inCmd.toLowerCase()) {
                    case Constant.CMD_HELP:
                        inCmd = doHelp(reader);
                        break;
                    case Constant.CMD_PARKINGLOT:
                        inCmd = doParkinglot(reader);
                        break;
                    case Constant.CMD_CHECKIN:
                        inCmd = doCheckin(reader);
                        break;
                    case Constant.CMD_PARK:
                        inCmd = doPark(reader);
                        break;
                    case Constant.CMD_CHECKOUT:
                        inCmd = doCheckout(reader);
                        break;
                    case Constant.CMD_PARKINGFEELOG:
                        inCmd = doParkingFeeLog(reader);
                        break;
                    case Constant.CMD_EXIT:
                        System.out.println(Constant.PROMPT_EXIT);
                        return;
                    default:
                        System.out.print(formatPrompt(Constant.PROMPT_NOTFOUND_MAIN, empQty, occQty));
            }
            inCmd = reader.readLine();
        }
    }

    /**
     * Initializes parking lot based on input dimensions.
     *
     * @param length length of parking lot
     * @param width  width of parking lot
     * @return whether initialization was successful
     */
    private boolean initParkingLotByInputArgs(String length, String width) {
        if (!parkingLot.isValidSize(length) || !parkingLot.isValidSize(width)) {
            System.out.println("ParkingLot size cannot be less than 5. Goodbye!\n");
            return false;
        }
        globalLength = Integer.valueOf(length);
        globalWidth = Integer.valueOf(width);
        String[][] array = parkingLot.initParkingLot(parkingLotArray, globalLength, globalWidth);
        if (array != null) {
            this.parkingLotArray = array;
            this.empQty = (array.length - 6) * ((array[0].length - 1) / 2);
            this.occQty = 0;
        }
        return true;
    }

    /**
     * Handles the help command, displays available commands.
     *
     * @param reader BufferedReader instance for reading user input
     * @return next command from user
     */
    private String doHelp(BufferedReader reader) throws IOException {
        System.out.println();
        System.out.print(Constant.PROMPT_HELP);
        String inCmd = reader.readLine();
        while (inCmd!=null && !Constant.CMD_MENU.equalsIgnoreCase(inCmd)) {
            if (Constant.CMD_COMMANDS.equalsIgnoreCase(inCmd)) {
                System.out.println();
                System.out.print(Constant.PROMPT_COMMANDS);
            }else{
                System.out.print(Constant.PROMPT_NOTFOUND_HELP);
            }
            inCmd = reader.readLine();
        }
        System.out.println();
        System.out.print(formatPrompt(Constant.PROMPT_MAIN,empQty, occQty));
        return inCmd;
    }

    /**
     * Manages parking lot related commands.
     *
     * @param reader BufferedReader instance for reading user input
     * @return next command from user
     */
    private String doParkinglot(BufferedReader reader)throws Exception {
        System.out.print(Constant.PROMPT_PARKINGLOT);
        String inCmd = reader.readLine();
        while (inCmd!=null && !Constant.CMD_MENU.equalsIgnoreCase(inCmd)) {
            if (Constant.CMD_INIT.equalsIgnoreCase(inCmd)) {
                doParkingLotToInit(reader);
                System.out.print(Constant.PROMPT_PARKINGLOT);
            } else if (Constant.CMD_VIEW.equalsIgnoreCase(inCmd)) {
                doParkingLotToView(reader);
                System.out.print(Constant.PROMPT_PARKINGLOT);
            } else if (!Constant.CMD_MENU.equalsIgnoreCase(inCmd)) {
                System.out.println("Command not found!");
                System.out.print(Constant.PROMPT_PARKINGLOT);
            }
            inCmd = reader.readLine();

        }
        System.out.print(formatPrompt(Constant.PROMPT_MAIN,empQty, occQty));
        return inCmd;
    }

    /**
     * Handles the initialization of parking lot dimensions.
     *
     * @param sc BufferedReader instance for reading user input
     */
    private void doParkingLotToInit(BufferedReader sc) throws IOException {
        if (parkingLotArray != null && occQty>0) {
            System.out.println("There are vehicles in the Parking Lot, you cannot change the space of the parking lot at the moment.");
            return;
        }
        System.out.print("Please enter the length of the parking lot.\n> ");
        String inCmd = sc.readLine();
        while (!parkingLot.isValidSize(inCmd)) {
            System.out.print("The length of the parking lot cannot be less than " + Constant.MINIMUM_SIZE + ". Please re-enter.\n> ");
            inCmd = sc.readLine();
        }
        globalLength = Integer.valueOf(inCmd);
        System.out.print("Please enter the width of the parking lot.\n> ");
        inCmd = sc.readLine();
        while (!parkingLot.isValidSize(inCmd)) {
            System.out.print("The width of the parking lot cannot be less than " + Constant.MINIMUM_SIZE + ". Please re-enter.\n> ");
            inCmd = sc.readLine();
        }
        globalWidth = Integer.valueOf(inCmd);
        System.out.println("Parking Lot Space is setup. Here is the layout -");
        String[][] array = parkingLot.initParkingLotAndDisplay(parkingLotArray, globalLength, globalWidth);
        if (array != null) {
            this.parkingLotArray = array;
            this.empQty = (array.length - 6) * ((array[0].length - 1) / 2);
            this.occQty = 0;
        }

        System.out.print("Press any key to return to parkinglot menu\n");
        inCmd = sc.readLine();
    }

    /**
     * Displays the current view of the parking lot.
     *
     * @param sc BufferedReader instance for reading user input
     */
    private void doParkingLotToView(BufferedReader sc) throws IOException {
        if (parkingLotArray == null) {
            System.out.print("The parking lot is not initialised. Please run init!\n");
            System.out.print("Press any key to return to parkinglot menu\n");
            sc.readLine();
            return;
        }
        parkingLot.displayParkingLot(parkingLotArray);
        System.out.println("Press any key to return to parkinglot menu");
        sc.readLine();
    }

    /**
     * Manages the checkin process of a vehicle.
     *
     * @param sc BufferedReader instance for reading user input
     * @return next command from user
     */
    private String doCheckin(BufferedReader sc) throws IOException {
        if (parkingLotArray == null) {
            System.out.print(
                    "The parking lot hasn't been initialised. Please set up a space for the parking lot. Taking you back to main menu.\n");
            System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
            return "";
        }
        //If the parking is full you should throw an error
        if (empQty == 0) {
            System.out.println("The parking is full. Please come back later. Taking you back to main menu.");
            System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
            return "";
        }

        String inCmd = null;
        System.out.println("Please enter the vehicle details");
        System.out.print("> Vehicle Type: ");
        inCmd = sc.readLine();

        while (!parkingLot.isValidVehicleType(inCmd)) {
            System.out.println("Invalid detail, please enter detail again!");
            System.out.print("> Vehicle Type: ");
            inCmd = sc.readLine();
        }
        String vehicleType = inCmd.substring(0, 1).toUpperCase() + inCmd.toLowerCase().substring(1);
        int empPlotQty = parkingLot.getEmpPlotQtyByVehicleType(parkingLotArray,checkinMap,vehicleType);
        if (empPlotQty <= 0) {
            System.out.println("Parking full for " + inCmd.toLowerCase() + ". Please come back later. Taking you back to main menu.");
            System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
            return "";
        }
        Vehicle vehicle = parkingLot.createVehicleByType(vehicleType);
        System.out.print("> Regn Id: ");
        inCmd = sc.readLine();

        while (inCmd == null || inCmd.indexOf(" ")>-1 || inCmd.length() != 6 || checkinMap.containsKey(inCmd)) {
            System.out.println("Invalid detail, please enter detail again!");
            System.out.print("> Regn Id: ");
            inCmd = sc.readLine();
        }

        vehicle.setRegnId(inCmd);

        System.out.print("> Vehicle Model: ");
        inCmd = sc.readLine();
        vehicle.setModel(inCmd);

        System.out.print("> Vehicle Colour: ");
        inCmd = sc.readLine();
        vehicle.setColour(inCmd);

        System.out.print("> Date of entry: ");
        inCmd = sc.readLine();

        int dateValidValue = 0;
        while ((dateValidValue = parkingLot.isValidDate(inCmd)) != 1) {
            if (dateValidValue == 2) {
                System.out.print("Incorrect date format, please enter date in yyyy-MM-dd format again between 1970-01-01 and 2099-12-31!\n> Date of entry: ");
            } else {
                System.out.print("Incorrect date format, please enter date in yyyy-MM-dd format again!\n> Date of entry: ");
            }
            inCmd = sc.readLine();
        }
        vehicle.setDateEntry(inCmd);

        System.out.print("> Time of entry: ");
        inCmd = sc.readLine();

        while (!parkingLot.isValidTime(inCmd)) {
            System.out.print("Incorrect time format, please enter time in HH:mm format again!\n> Time of entry: ");
            inCmd = sc.readLine();
        }
        vehicle.setTimeEntry(inCmd);
        checkinMap.put(vehicle.getRegnId(), vehicle);

        empQty--;
        occQty++;
        System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
        return "";
    }

    /**
    * Executes the checkout process for a vehicle.
    * @param sc BufferedReader for user input.
    * @return Returns an empty string after processing.
    * @throws IOException
    */
    private String doCheckout(BufferedReader sc) throws IOException {
        if (parkingLotArray == null || checkinMap.size() <= 0) {
            System.out.println("Invalid command! The parking is empty. Taking you back to main menu.");
            System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
            return "";
        }
        String inCmd = null;
        System.out.println("Please enter your vehicle details");
        System.out.print("> Regn Id: ");
        inCmd = sc.readLine();

        while (inCmd == null || inCmd.indexOf(" ")>-1 || inCmd.length() != 6) {
            System.out.println("Invalid detail, please enter detail again!");
            System.out.print("> Regn Id: ");
            inCmd = sc.readLine();
        }

        if (checkinMap.size() <= 0 || !checkinMap.containsKey(inCmd)) {
            System.out.println("The selected vehicle type is not present in the parking lot. Taking you back to main menu");
            System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
            return "";
        }

        //the vehicle has to move to the exit door
        if (!(checkinMap.get(inCmd).getCol()==globalLength-2 && checkinMap.get(inCmd).getRow()==globalWidth-2)) {
            System.out.println("The selected vehicle type is not at the checkout door. Please proceed to checkout door. Taking you back to main menu.");
            System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
            return "";
        }

        Vehicle vehicle = checkinMap.get(inCmd);
        boolean checkOutStatus = false;
        boolean validDatetime=false;
        while(!validDatetime){
            System.out.print("> Date of exit: ");
            inCmd = sc.readLine();

            int dateValidValue = 0;
            while ((dateValidValue = parkingLot.isValidDate(inCmd)) != 1) {
                if (dateValidValue == 2) {
                    System.out.print("Incorrect date format, please enter date in yyyy-MM-dd format again between 1970-01-01 and 2099-12-31!\n> Date of exit: ");
                } else {
                    System.out.print("Incorrect date format, please enter date in yyyy-MM-dd format again!\n> Date of exit: ");
                }
                inCmd = sc.readLine();
            }

            String exitDateStr = inCmd;
            System.out.print("> Time of exit: ");
            inCmd = sc.readLine();

            while (!parkingLot.isValidTime(inCmd)) {
                System.out.print("Incorrect time format, please enter time in HH:mm format again!\n> Time of exit: ");
                inCmd = sc.readLine();
            }
            java.util.Date datetimeEntry = Date.parseDateFormat(vehicle.getDateEntry() + " " + vehicle.getTimeEntry(), Date.FORMAT_DATETIME);
            java.util.Date datetimeExit = Date.parseDateFormat(exitDateStr + " " + inCmd, Date.FORMAT_DATETIME);
            if (datetimeEntry == null || datetimeExit == null || datetimeExit.before(datetimeEntry)) {
                System.out.println("Checkout datetime cannot be less than checkin datetime for the vehicle. Please re-enter.");
            } else {
                validDatetime = true;
                vehicle.setDateExit(exitDateStr);
                vehicle.setTimeExit(inCmd);
            }
        }

        System.out.println("Please verify your details.");
        System.out.println("Total number of hours: " + vehicle.getParkingHours());
        //checking whether there is overnight fee needing to be printed out
        if (vehicle.getOvernight() > 0) {
            System.out.println("Total number of overnight parking: " + vehicle.getOvernight());
        }
        System.out.println("Total number of hits:" + vehicle.getHitNums());
        System.out.println("Vehicle Type: " + vehicle.getType());
        System.out.println("Regn Id: " + vehicle.getRegnId());
        System.out.println("Total Parking Fee: $" + new BigDecimal(vehicle.calculateParkingFee()).setScale(1));
        System.out.print("Type Y to accept the fee or menu to return to main menu\n> ");

        inCmd = sc.readLine();
        while (!"Y".equalsIgnoreCase(inCmd) && !"menu".equalsIgnoreCase(inCmd)) {
            System.out.print("You cannot checkout your vehicle. Please accept the fee by pressing Y or type menu to return to main menu and park your vehicle.\n> ");
            inCmd = sc.readLine();
        }
        if ("menu".equalsIgnoreCase(inCmd)) {
            vehicle.setTimeExit(null);
            vehicle.setDateExit(null);
            System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
            checkOutStatus = false;
        }else{
            checkOutStatus=true;
        }
        if (!checkOutStatus) {
            return "";
        }
        checkoutVehicleList.add(vehicle.toCheckoutVehicle());
        parkingLotArray[vehicle.getRow()][vehicle.getCol()] = Constant.WAVY_LINE;
        checkinMap.remove(vehicle.getRegnId());

        System.out.println("Thank you for visiting Java Parking Lot. See you next time!");
        empQty++;
        occQty--;
        System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
        return "";
    }

    private String doParkingFeeLog(BufferedReader sc) throws IOException {
        System.out.println("============ Here are the Transaction logs for the Java Parking Lot =============");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("| Vehicle Type | Registration Id | Entry DateTime | Exit DateTime | Parking Fee |");
        System.out.println("---------------------------------------------------------------------------------");

        if (checkoutVehicleList == null || checkoutVehicleList.size() <= 0) {
            System.out.println("No records found!");
        }else{
            for (CheckoutVehicle v : checkoutVehicleList) {
                System.out.printf("|%14s|%17s|%16s|%16s|%13.1f|",
                        v.getVehicleType(),
                        v.getRegistrationId(),
                        Date.getDateFormat(v.getEntryDateTime(), Date.FORMAT_DATETIME),
                        Date.getDateFormat(v.getExitDateTime(), Date.FORMAT_DATETIME),
                        v.getParkingFee());
                System.out.println();
            }
        }
        System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
        return "";
    }

    /**
    * Parks a checked-in vehicle in the parking lot.
    * @param sc BufferedReader for user input.
    * @return Returns an empty string after processing.
    * @throws IOException
    */
    private String doPark(BufferedReader sc) throws IOException {
        System.out.println("To park a vehicle provide the details.");
        if (parkingLotArray == null || checkinMap.size() <= 0) {
            System.out.println("No vehicle checked in the parking lot, taking you back to main menu");
            System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
            return "";
        }

        String inCmd = null;
        System.out.print("> Regn Id: ");
        inCmd = sc.readLine();

        while (inCmd == null || inCmd.indexOf(" ")>-1 || inCmd.length() != 6) {
            System.out.println("Invalid detail, please enter detail again!");
            System.out.print("> Regn Id: ");
            inCmd = sc.readLine();
        }

        while (!checkinMap.containsKey(inCmd)) {
            System.out.println("The vehicle mentioned is not parked in the parking lot.");
            System.out.print("> Regn Id: ");
            inCmd = sc.readLine();
        }
        parkingLot.displayParkingLot(parkingLotArray);
        Vehicle vehicle=checkinMap.get(inCmd);
        doParkMove(sc,vehicle);
        System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));
        return "";
    }


    /**
    * Similar to the above method but for a Bike object.
    *
    * @param sc The BufferedReader for reading user input.
    * @param vehicle The Bike object being parked.
    * @throws IOException If there's an error reading the input.
    */
    private void doParkMove(BufferedReader sc, Vehicle vehicle) throws IOException {
        System.out.print("Type w/s/a/d to move the vehicle to up/down/left/right or else press q to exit.\n> ");
        String inCmd = sc.readLine();
        int mvCol=0, mvRow=0;
        String mvStr=null;
        while (true) {
            if (!parkingLot.isValidParkActionKey(inCmd)) {
                System.out.println("Invalid command!");
                System.out.print("Type w/s/a/d to move the vehicle to up/down/left/right or else press q to exit.\n> ");
            } else {
                if ("q".equalsIgnoreCase(inCmd)) {
                    break;
                }
                mvCol=vehicle.getCol();
                mvRow=vehicle.getRow();
                if ("a".equalsIgnoreCase(inCmd)) {
                    mvCol=vehicle.getCol()-1;
                } else if ("s".equalsIgnoreCase(inCmd)) {
                    mvRow=vehicle.getRow()+1;
                } else if ("d".equalsIgnoreCase(inCmd)) {
                    mvCol=vehicle.getCol()+1;
                } else if ("w".equalsIgnoreCase(inCmd)) {
                    mvRow=vehicle.getRow()-1;
                }
                mvStr = parkingLotArray[mvRow][mvCol];

                if (parkingLot.isEntryOrExitSymbol(mvStr)) {
                    System.out.println("You cannot exit the parking lot without checkout.");
                }else if (parkingLot.isPillarSymbol(mvStr)) {
                    vehicle.setHitNums(vehicle.getHitNums() + 1);
                    if (Constant.VEHICLE_TYPE_BIKE.equalsIgnoreCase(vehicle.getType())) {
                        System.out.println("You have hit the pillar!");
                    }else {
                        System.out.println("You have hit the pillar, there will be a damage fee!");
                    }
                }else if (parkingLot.isWallsSymbol(mvStr)) {
                    vehicle.setHitNums(vehicle.getHitNums() + 1);
                    if (Constant.VEHICLE_TYPE_BIKE.equalsIgnoreCase(vehicle.getType())) {
                        System.out.println("You have hit the wall!");
                    }else {
                        System.out.println("You have hit the wall, there will be a damage fee!");
                    }
                }else if (parkingLot.isVehicleSymbol(mvStr)) {
                    vehicle.setHitNums(vehicle.getHitNums() + 1);
                    if (Constant.VEHICLE_TYPE_BIKE.equalsIgnoreCase(vehicle.getType())) {
                        System.out.println("You have hit a vehicle!");
                    }else{
                        System.out.println("You have hit a vehicle, there will be a damage fee!");
                    }
                }else if (parkingLot.isDrivewayOrSpotsSymbol(mvStr)) {
                    if (vehicle.getType().equalsIgnoreCase(Constant.VEHICLE_TYPE_TRUCK) && Constant.DOT.equalsIgnoreCase(mvStr) && mvCol!=1) {
                        System.out.println("You cannot park a truck in the parking lot anywhere except the parking spots near the entry.");
                    }else if ((vehicle.getType().equalsIgnoreCase(Constant.VEHICLE_TYPE_BIKE) ||vehicle.getType().equalsIgnoreCase(Constant.VEHICLE_TYPE_MOTORBIKE))
                            && Constant.DOT.equalsIgnoreCase(mvStr) && mvCol!=globalLength-(globalLength%2==0?3:2)) {
                        System.out.println("You cannot park a bike or motorbike in the parking lot anywhere except the parking spots near the exit.");
                    }else {
                        parkingLotArray[vehicle.getRow()][vehicle.getCol()] = vehicle.getOldSymbol();
                        vehicle.setOldSymbol(mvStr);
                        parkingLotArray[mvRow][mvCol] = vehicle.getType().substring(0, 1).toUpperCase();
                        vehicle.setCol(mvCol);
                        vehicle.setRow(mvRow);
                    }
                }
                parkingLot.displayParkingLot(parkingLotArray);
                System.out.print("Type w/s/a/d to move the vehicle to up/down/left/right or else press q to exit.\n> ");
            }
            inCmd = sc.readLine();
        }
    }

    /*
     *  Displays the welcome text.
     */
    private void displayWelcomeText() {
        String titleText =
                " _     _  _______  ___      _______  _______  __   __  _______ \n" +
                        "| | _ | ||       ||   |    |       ||       ||  |_|  ||       |\n" +
                        "| || || ||    ___||   |    |      _||   _   ||       ||    ___|\n" +
                        "|       ||   |___ |   |    |     |  |  | |  ||       ||   |___ \n" +
                        "|       ||    ___||   |___ |     |  |  |_|  || ||_|| ||    ___|\n" +
                        "|   _   ||   |___ |       ||     |_ |       || |\\/|| ||   |___ \n" +
                        "|__| |__||_______||_______||_______||_______||_|   |_||_______|\n" +
                        "_________________________ TO JAVA PARKING _____________________";

        System.out.println(titleText);
        System.out.println();

        System.out.print(formatPrompt(Constant.PROMPT_MAIN, empQty, occQty));

    }

    private String formatPrompt(String prompt, int empQty, int occQty) {
        return String.format(prompt, empQty <= 0 ? "[None]" : empQty, occQty < 0 ? "[None]" : occQty);
    }

    public int getOccQty() {
        return occQty;
    }
}

