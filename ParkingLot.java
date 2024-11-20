import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The ParkingLot class provides methods to manage and display a parking lot.
 * It offers the capability to initialize the parking lot, validate inputs, and determine if specific actions or vehicle types are valid.
 *
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

public class ParkingLot {
    private final ParkingLotEngine engine;

    public ParkingLot(ParkingLotEngine parkingLotEngine) {
        this.engine = parkingLotEngine;
    }

    /**
     * Initializes a parking lot with given dimensions and then displays it.
     *
     * @param parkingLotArray Current 2D array representation of the parking lot.
     * @param length Length of the parking lot.
     * @param width Width of the parking lot.
     * @return 2D array representation of the initialized parking lot.
     */
    public String[][] initParkingLotAndDisplay(String[][] parkingLotArray, int length, int width) {
        String[][] lot = initParkingLot(parkingLotArray, length, width);
        if (lot != null) {
            displayParkingLot(lot);
        }
        return lot;
    }

    /**
     * Initializes the parking lot based on provided dimensions.
     *
     * @param parkingLotArray Current 2D array representation of the parking lot.
     * @param length Length of the parking lot.
     * @param width Width of the parking lot.
     * @return 2D array representation of the initialized parking lot or null if initialization fails.
     */
    public String[][] initParkingLot(String[][] parkingLotArray, int length, int width) {
        if (length < Constant.MINIMUM_SIZE || width < Constant.MINIMUM_SIZE) {
            System.out.println("ParkingLot size cannot be less than " + Constant.MINIMUM_SIZE + ". Goodbye!\n");
            return null;
        }
        if (parkingLotArray != null && engine.getOccQty() > 0) {
            System.out.println("There are vehicles in the Parking Lot, you cannot change the space of the parking lot at the moment.\n");
            return null;
        }
        parkingLotArray = new String[width][length];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                parkingLotArray[i][j] = getUnitStr(i, j, width, length);
            }
        }
        return parkingLotArray;
    }

    /**
     * Displays the parking lot in a grid format.
     *
     * @param parkingLotArray 2D array representation of the parking lot.
     */
    public void displayParkingLot(String[][] parkingLotArray) {
        for (String[] itemArray : parkingLotArray) {
            for (String item : itemArray) {
                System.out.print(item);
            }
            System.out.println();
        }
    }


    /**
     * Determines the correct unit character to display at a given parking lot position.
     *
     * @param rowIndex Row index of the parking lot.
     * @param colIndex Column index of the parking lot.
     * @param rows Total number of rows in the parking lot.
     * @param cols Total number of columns in the parking lot.
     * @return The character to be displayed.
     */
    private String getUnitStr(int rowIndex, int colIndex, int rows, int cols) {
        String s = "";
        //1st and Last Column Output |
        if (colIndex == 0 || colIndex == cols - 1) {
            if ((rowIndex == 1 && colIndex == 0) || (rowIndex == rows - 2 && colIndex == cols - 1)) {
                s = Constant.SYMBOL_D;
            } else {
                s = Constant.PIPE;
            }
        } else {
            if (rowIndex == 2 || rowIndex == rows - 3) {
                if (colIndex > 0 && colIndex % 2 == 0) {
                    s = Constant.WAVY_LINE;
                }else {
                    s = Constant.SYMBOL_P;
                }
            } else if (rowIndex == 0 || rowIndex == rows - 1) {
                s = Constant.DASH;
            } else if (rowIndex == 1 || rowIndex == rows - 2) {
                s = Constant.WAVY_LINE;
            } else {
                if (colIndex > 0 && colIndex % 2 == 0) {
                    s = Constant.WAVY_LINE;
                } else {
                    s = Constant.DOT;
                }
            }
        }
        return s;
    }

    /**
     * Validates if the given input command represents a valid size.
     * The size is considered valid if it's a positive integer greater than or equal to 5.
     *
     * @param inCmd The input command to validate.
     * @return true if the size is valid, false otherwise.
     */
    public boolean isValidSize(String inCmd) {
        if (inCmd == null || inCmd.trim().length() <= 0) {
            return false;
        }
        char[] chars = inCmd.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        int value = Integer.valueOf(inCmd).intValue();
        if (value < 7) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the given input command consists of valid digits.
     *
     * @param inCmd The input command to validate.
     * @return true if the input consists only of digits, false otherwise.
     */
    public boolean isValidDigit(String inCmd) {
        if (inCmd == null || inCmd.trim().length() <= 0) {
            return false;
        }
        char[] chars = inCmd.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates if the given input command represents a valid time format (HH:mm).
     *
     * @param inCmd The input command to validate.
     * @return true if the time is valid, false otherwise.
     */
    public boolean isValidTime(String inCmd) {
        if (inCmd == null || inCmd.trim().length() <= 0 || inCmd.indexOf(":") < 0) {
            return false;
        }
        String[] split = inCmd.split(":");
        if (split.length != 2 || split[0].length() != 2 || split[1].length() != 2
                || !isValidDigit(split[0]) || !isValidDigit(split[1])) {
            return false;
        }
        int hour = Integer.valueOf(split[0]);
        int min = Integer.valueOf(split[1]);
        if (hour > 23 || hour < 0 || min > 59 || min < 0) {
            return false;
        }
        return true;
    }

    public int isValidDate(String inCmd) {
        if (inCmd == null || inCmd.trim().length() != 10 || inCmd.indexOf("-") < 0) {
            return 0;
        }
        int value = 0;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");
        try {
            Date date = sdf.parse(inCmd);
            value = inCmd.equals(sdf.format(date)) ? 1 : 0;
            if (value == 1) {
                if (inCmd.compareTo("1970-01-01") < 0 || inCmd.compareTo("2099-12-31") > 0) {
                    value = 2;
                }
            }
        } catch (Exception e) {
            value = 0;
        }
        return value;
    }

    /**
     * Validates if the provided key represents a valid parking action.
     * Valid keys are: w, s, a, d, and q.
     *
     * @param key The key input by the user.
     * @return true if the key is valid, false otherwise.
     */
    public boolean isValidParkActionKey(String key) {
        List<String> parkKeyList = Arrays.asList(new String[] {"w", "s", "a", "d", "q"});
        return parkKeyList.contains(key.toLowerCase());
    }

    /**
     * Validates if the provided vehicle type is valid for the parking lot.
     * Currently, only "car" and "bike" are valid vehicle types.
     *
     * @param vehicleType The type of vehicle.
     * @return true if the vehicle type is valid, false otherwise.
     */
    public boolean isValidVehicleType(String vehicleType) {
        if(vehicleType.equalsIgnoreCase("car") || vehicleType.equalsIgnoreCase("bike")
                || vehicleType.equalsIgnoreCase("truck")
                || vehicleType.equalsIgnoreCase("motorbike")){
            return true;
        }
        return false;
    }


    /**
     * This method calculates the number of available parking slots for the specified vehicle type.
     * It considers the different quantities of vehicle types and the currently checked-in vehicles
     * to return the available quantity for the specified type.
     *
     * @param parkingLotArray Represents the parking lot as a 2D array.
     * @param checkinMap      Contains the vehicles currently checked in with their details.
     * @param vehicleType     Specifies the type of vehicle to calculate available slots for.
     * @return qty            Number of available slots for the specified vehicle type.
     */  
    public int getEmpPlotQtyByVehicleType(String[][] parkingLotArray, Map<String, Vehicle> checkinMap, String vehicleType) {
        int qty = 0;
        if (parkingLotArray == null) {
            return 0;
        }
        int truckQty =0, bikeQty =0, totalQty =0;
        totalQty = (parkingLotArray.length - 6) * ((parkingLotArray[0].length - 1) / 2);
        truckQty = parkingLotArray.length - 6;
        bikeQty = truckQty;
        int checkinTruckQty=0,checkinBikeQty=0,checkinCarQty=0;
        if (checkinMap.size() > 0) {
            for (Map.Entry<String, Vehicle> entry : checkinMap.entrySet()) {
                if (entry.getValue().getType().equals(Constant.VEHICLE_TYPE_TRUCK)) {
                    checkinTruckQty += 1;
                }else if (entry.getValue().getType().equals(Constant.VEHICLE_TYPE_CAR)) {
                    checkinCarQty += 1;
                }else if (entry.getValue().getType().equals(Constant.VEHICLE_TYPE_BIKE) || entry.getValue().getType().equals(Constant.VEHICLE_TYPE_MOTORBIKE)) {
                    checkinBikeQty += 1;
                }
            }
        }

        if (Constant.VEHICLE_TYPE_MOTORBIKE.equals(vehicleType) || Constant.VEHICLE_TYPE_BIKE.equals(vehicleType)) {
            qty = bikeQty - checkinBikeQty;
            return qty;
        }
        if (Constant.VEHICLE_TYPE_TRUCK.equals(vehicleType)) {
            qty = truckQty - checkinTruckQty;
            return qty;
        }
        if (Constant.VEHICLE_TYPE_CAR.equals(vehicleType)) {
            qty = totalQty - checkinCarQty - checkinTruckQty - checkinBikeQty;
            return qty;
        }
        return qty;
    }

    public Vehicle createVehicleByType(String vehicleType) {
        Vehicle vehicle = null;
        if (Constant.VEHICLE_TYPE_CAR.equals(vehicleType)) {
            vehicle= new Car();
        }else if (Constant.VEHICLE_TYPE_BIKE.equals(vehicleType)) {
            vehicle= new Bike();
        }else if (Constant.VEHICLE_TYPE_TRUCK.equals(vehicleType)) {
            vehicle= new Truck();
        }else if (Constant.VEHICLE_TYPE_MOTORBIKE.equals(vehicleType)) {
            vehicle= new Motorbike();
        }
        return vehicle;
    }
    
    /**
     * Checks if the provided string matches any of the predefined symbols.
     *
     * @param mvStr The string to be checked.
     * @return boolean Returns true if the string matches a following symbol, otherwise false.
     */
    public boolean isVehicleSymbol(String mvStr) {
        return Constant.SYMBOL_T.equals(mvStr) || Constant.SYMBOL_C.equals(mvStr) || Constant.SYMBOL_B.equals(mvStr) || Constant.SYMBOL_M
                .equals(mvStr);
    }

    public boolean isPillarSymbol(String mvStr) {
        return Constant.SYMBOL_P.equals(mvStr);
    }

    public boolean isEntryOrExitSymbol(String mvStr) {
        return Constant.SYMBOL_D.equals(mvStr);
    }

    public boolean isWallsSymbol(String mvStr) {
        return Constant.PIPE.equals(mvStr) || Constant.DASH.equals(mvStr);
    }

    public boolean isDrivewayOrSpotsSymbol(String mvStr) {
        return Constant.DOT.equals(mvStr) || Constant.WAVY_LINE.equals(mvStr);
    }
}


