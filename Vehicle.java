/**
 * Vehicle is an abstract class representing a generic vehicle in a parking simulation.
 * This class encompasses common attributes and methods shared by all types of vehicles.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

public abstract class Vehicle {
    // Default coordinates (row and column) for the car
    protected int row = Constant.ROW_INIT_VALUE;
    protected int col = Constant.COL_INIT_VALUE;

    protected String oldSymbol=Constant.SYMBOL_D;

    // Registration ID, model, and colour of the car
    protected String regnId;
    protected String model;
    protected String colour;

    // Start and end times for parking
    protected String dateEntry;
    protected String dateExit;
    protected String timeEntry;
    protected String timeExit;

    // Getter method for row attribute
    public int getRow() {
        return row;
    }

    // Setter method for row attribute
    public void setRow(int row) {
        this.row = row;
    }

    // Getter method for col attribute
    public int getCol() {
        return col;
    }

    // Setter method for col attribute
    public void setCol(int col) {
        this.col = col;
    }

    public String getOldSymbol() {
        return oldSymbol;
    }

    public void setOldSymbol(String oldSymbol) {
        this.oldSymbol = oldSymbol;
    }

    // Getter method for parkingPrice attribute

    public abstract int getParkingPrice();

    public abstract int getHitNums();

    public abstract void setHitNums(int hitNums);

    // Getter method for hitPrice attribute
    public abstract int getHitPrice();

    public abstract int getOvernightPrice();

    // Getter method for time of entry (parking start time)
    public String getTimeEntry() {
        return timeEntry;
    }

    // Setter method for time of entry (parking start time)
    public void setTimeEntry(String timeEntry) {
        this.timeEntry = timeEntry;
    }

    // Getter method for time of exit (parking end time)
    public String getTimeExit() {
        return timeExit;
    }

    // Setter method for time of exit (parking end time)
    public void setTimeExit(String timeExit) {
        this.timeExit = timeExit;
    }

    // Setter method for the registration ID
    public void setRegnId(String regnId) {
        this.regnId = regnId;
    }

    // Getter method for the registration ID
    public String getRegnId() {
        return this.regnId;
    }

    // Getter method for the car's model
    public String getModel() {
        return model;
    }

    // Setter method for the car's model
    public void setModel(String model) {
        this.model = model;
    }

    // Getter method for the car's colour
    public String getColour() {
        return colour;
    }

    // Setter method for the car's colour
    public void setColour(String colour) {
        this.colour = colour;
    }

    // Getter method for the car type
    public abstract String getType();

    // Method to compute the parking fee based on the number of hours parked
    public double getParkingFee() {
        return getParkingHours() * getParkingPrice();
    }

    public String getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(String dateEntry) {
        this.dateEntry = dateEntry;
    }

    public String getDateExit() {
        return dateExit;
    }

    public void setDateExit(String dateExit) {
        this.dateExit = dateExit;
    }

    private long getDiffHours() {
        java.util.Date stdate = Date.parseDateFormat(getDateEntry() + " " + getTimeEntry(), Date.FORMAT_DATETIME);
        java.util.Date eddate = Date.parseDateFormat(getDateExit() + " " + getTimeExit(), Date.FORMAT_DATETIME);

        Long diff = eddate.getTime() - stdate.getTime();
        long diffMin = diff / 1000 / 60;
        long diffHours = diffMin % 60 > 0 ? (diffMin / 60 + 1) : diffMin / 60;
        return diffHours;
    }

    // Method to calculate the number of hours the car has been parked
    public long getParkingHours() {
        long diffHours = getDiffHours();
        if (diffHours % 24 == 0) {
            return 0;
        } else {
            return diffHours % 24;
        }

    }

    // Method to compute the total hit fee
    public int getHitFee() {
        return getHitNums() * getHitPrice();
    }

    // Method to compute the total overnight fee
    public long getOvernightFee() {
        return getOvernightPrice() * getOvernight();
    }

    public long getOvernight(){
        return getDiffHours() /24 ;
        
    }

    public CheckoutVehicle toCheckoutVehicle() {
        CheckoutVehicle vehicle = new CheckoutVehicle();
        vehicle.setRegistrationId(this.getRegnId());
        vehicle.setVehicleType(this.getType());
        vehicle.setEntryDateTime(Date.parseDateFormat(this.getDateEntry() + " " + this.getTimeEntry(), Date.FORMAT_DATETIME));
        vehicle.setExitDateTime(Date.parseDateFormat(this.getDateExit() + " " + this.getTimeExit(), Date.FORMAT_DATETIME));
        vehicle.setParkingFee(this.calculateParkingFee());
        return vehicle;
    }

    // Method to compute the total fee (parking + hit)
    public double calculateParkingFee() {
        return getHitFee() + getParkingFee() + getOvernightFee();
    }
}

