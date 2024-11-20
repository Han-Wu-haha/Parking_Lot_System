/**
 * The Constant class provides a centralized location for storing especially some display texts that repetitively used across multiple classes.
 * These include initialization values, prompt texts, command keywords, and pricing details for a parking lot application.
 *
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

public class Constant {

    // Initialization values for columns and rows in the parking lot.
    public static final int COL_INIT_VALUE = 0;
    public static final int ROW_INIT_VALUE = 1;
    public static final int ZERO = 0; // Constant for zero value
    public static final int QTY_INIT_VALUE = -1; // Constant for initializing quantities, such as available spots, set at -1 to differentiate the [None] case

    // Pricing details for different types of vehicles
    public static final int PARKING_PRICE_BIKE = 2;
    public static final int PARKING_PRICE_MOTORBIKE = 3;
    public static final int PARKING_PRICE_CAR = 4;
    public static final int PARKING_PRICE_TRUCK = 10;

    public static final int HIT_PRICE_BIKE = 0; // Penalty price for bikes
    public static final int HIT_PRICE_MOTORBIKE = 10;
    public static final int HIT_PRICE_CAR = 20; // Penalty price for cars
    public static final int HIT_PRICE_TRUCK = 50;

    public static final int OVERNIGHT_FEE_BIKE = 5;
    public static final int OVERNIGHT_FEE_MOTORBIKE = 5;
    public static final int OVERNIGHT_FEE_CAR = 10;
    public static final int OVERNIGHT_FEE_TRUCK = 20;

    // Constants for vehicle types
    public static final String VEHICLE_TYPE_CAR = "Car";
    public static final String VEHICLE_TYPE_BIKE = "Bike";
    public static final String VEHICLE_TYPE_MOTORBIKE = "Motorbike";
    public static final String VEHICLE_TYPE_TRUCK = "Truck";

    // symbol constants for generating the parking lot
    public static final String DOT = ".";
    public static final String DASH = "-";
    public static final String PIPE = "|";
    public static final String WAVY_LINE = "~";
    public static final String SYMBOL_P = "P";
    public static final String SYMBOL_D = "D";
    public static final String SYMBOL_T = "T";
    public static final String SYMBOL_C = "C";
    public static final String SYMBOL_B = "B";
    public static final String SYMBOL_M = "M";
    public static final int MINIMUM_SIZE = 7;

    // Prompt texts that guide the user on how to proceed in the main menu
    public static final String PROMPT_MAIN = "Empty Lots: %s | Occupied: %s\n"
            + "Please enter a command to continue.\n"
            + "Type 'help' to learn how to get started.\n"
            + "> ";

    // Error prompt texts for various scenarios
    public static final String PROMPT_NOTFOUND_MAIN = "Command not found!\n" + PROMPT_MAIN;

    public static final String PROMPT_NOTFOUND_HELP = "Command not found!\n"
            + "\n"
            + "Type 'commands' to list all the available commands\n"
            + "Type 'menu' to return to the main menu\n"
            + "> ";

    // Command keywords for various functionalities
    public static final String CMD_PARKINGLOT = "parkinglot";
    public static final String PROMPT_PARKINGLOT = "Type 'init' to initialise the parking space\n"
            + "Type 'view' to view the layout of the parking space\n"
            + "Type 'menu' to return to the main menu\n"
            + "> ";
    public static final String CMD_CHECKIN = "checkin";
    public static final String CMD_CHECKOUT = "checkout";
    public static final String CMD_PARKINGFEELOG = "parkingfeelog";
    public static final String CMD_PARK = "park";
    public static final String CMD_INIT = "init";
    public static final String CMD_VIEW = "view";
    public static final String CMD_EXIT = "exit";

    // Exit prompt
    public static final String PROMPT_EXIT = "Good bye from the Java Parking Lot! See you next time!";

    // Help and commands prompts
    public static final String CMD_HELP = "help";
    public static final String PROMPT_HELP = "Type 'commands' to list all the available commands\n"
            + "Type 'menu' to return to the main menu\n"
            + "> ";
    public static final String CMD_COMMANDS = "commands";
    public static final String PROMPT_COMMANDS = "help: shows you list of commands that you can use.\n"
            + "parkinglot: initialise the space for parking lot or view the layout of parking lot.\n"
            + "checkin: add your car details while entering the parking lot.\n"
            + "park: park your car to one of the empty spot.\n"
            + "checkout: view the parking fee while exiting the parking lot.\n"
            + "parkingfeelog: view the transaction log for parking lot.\n"
            + "exit: To exit the program.\n"
            + "\n"
            + "Type 'commands' to list all the available commands\n"
            + "Type 'menu' to return to the main menu\n"
            + "> ";
    public static final String CMD_MENU = "menu"; // Command keyword for main menu
}

