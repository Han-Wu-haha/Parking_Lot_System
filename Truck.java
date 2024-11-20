/**
 * The Truck class represents a specialized Vehicle object, a Truck.
 * It inherits from the Vehicle class and sets specific properties and
 * behaviors unique to a Truck, such as parking price, hit price, and overnight price.
 * It is part of a parking lot simulation where different types of vehicles
 * have different attributes and behaviors.
 *
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

public class Truck extends Vehicle {

    // Defines the type of vehicle as Truck
    private String type = Constant.VEHICLE_TYPE_TRUCK;

    // Assigns default parking price for a truck
    private int parkingPrice = Constant.PARKING_PRICE_TRUCK;

    // Assigns hit price and initializes number of hits
    private int hitPrice = Constant.HIT_PRICE_TRUCK;

    // Assigns default overnight fees for a truck
    private int overnightPrice = Constant.OVERNIGHT_FEE_TRUCK;

    protected int hitNums; // Stores the number of hits

    /**
     * Retrieves the number of hits
     * @return the number of hits
     */
    @Override
    public int getHitNums() {
        return hitNums;
    }

    /**
     * Updates the number of hits
     * @param hitNums the new number of hits
     */
    @Override
    public void setHitNums(int hitNums) {
        this.hitNums = hitNums;
    }

    /**
     * Retrieves the parking price for the truck
     * @return the parking price
     */
    @Override
    public int getParkingPrice() {
        return this.parkingPrice;
    }

    /**
     * Retrieves the hit price for the truck
     * @return the hit price
     */
    @Override
    public int getHitPrice() {
        return this.hitPrice;
    }

    /**
     * Retrieves the type of the vehicle
     * @return the type of the vehicle
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Retrieves the overnight price for the truck
     * @return the overnight price
     */
    @Override
    public int getOvernightPrice() {
        return overnightPrice;
    }
}

