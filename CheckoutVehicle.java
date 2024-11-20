/**
 * This class represents a vehicle that checked out from a parkinglot. 
 * It holds details about the vehicle, the time of entry, the time of exit, and the applicable parking fee.
 *
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
 
public class CheckoutVehicle {

    // Represents the type of the vehicle, e.g. Car, Bike, Motorbike and Truck.
    private String vehicleType;

    // Represents the registration ID of the vehicle.
    private String registrationId;

    // Represents the date and time when the vehicle entered the parkinglot.
    private java.util.Date entryDateTime;

    // Represents the date and time when the vehicle exited the parkinglot.
    private java.util.Date exitDateTime;

    // Represents the amount charged for parking the vehicle.
    private double parkingFee;

    /**
     * Returns the type of the vehicle.
     * @return A string representing the vehicle type.
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the type of the vehicle.
     * @param vehicleType A string representing the type of the vehicle.
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Returns the registration ID of the vehicle.
     * @return A string representing the registration ID of the vehicle.
     */
    public String getRegistrationId() {
        return registrationId;
    }

    /**
     * Sets the registration ID of the vehicle.
     * @param registrationId A string representing the registration ID of the vehicle.
     */
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    /**
     * Returns the entry date and time of the vehicle.
     * @return A Date object representing the entry date and time of the vehicle.
     */
    public java.util.Date getEntryDateTime() {
        return entryDateTime;
    }

    /**
     * Sets the entry date and time of the vehicle.
     * @param entryDateTime A Date object representing the entry date and time of the vehicle.
     */
    public void setEntryDateTime(java.util.Date entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    /**
     * Returns the exit date and time of the vehicle.
     * @return A Date object representing the exit date and time of the vehicle.
     */
    public java.util.Date getExitDateTime() {
        return exitDateTime;
    }

    /**
     * Sets the exit date and time of the vehicle.
     * @param exitDateTime A Date object representing the exit date and time of the vehicle.
     */
    public void setExitDateTime(java.util.Date exitDateTime) {
        this.exitDateTime = exitDateTime;
    }

    /**
     * Returns the parking fee for the vehicle.
     * @return A double representing the parking fee.
     */
    public double getParkingFee() {
        return parkingFee;
    }

    /**
     * Sets the parking fee for the vehicle.
     * @param parkingFee A double representing the parking fee for the vehicle.
     */
    public void setParkingFee(double parkingFee) {
        this.parkingFee = parkingFee;
    }
}

