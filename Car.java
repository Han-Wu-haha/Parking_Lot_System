/**
 * The Car class is an extension of the Vehicle class, focusing on attributes and behaviors specific to cars in a parking management simulation. 
 * It initializes type-specific attributes like parking price, hit price, and overnight price, essential for calculating charges and managing 
 * the vehicle during the simulation. It also provides specific implementations for the abstract methods in the Vehicle class, allowing proper 
 * interaction and management of car objects within the system.
 *
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

public class Car extends Vehicle {

    // Specifies the type of the vehicle as Car
    private String type = Constant.VEHICLE_TYPE_CAR;

    // Specifies the default parking price for the Car
    private int parkingPrice = Constant.PARKING_PRICE_CAR;

    // Specifies the price incurred by the Car per hit
    private int hitPrice = Constant.HIT_PRICE_CAR;

    // Specifies the overnight fee applicable to the Car
    private int overnightPrice = Constant.OVERNIGHT_FEE_CAR;

    // Holds the number of hits incurred by the Car during the simulation
    protected int hitNums;

    // Returns the number of hits incurred by the Car
    @Override
    public int getHitNums() {
        return hitNums;
    }

    // Updates the number of hits incurred by the Car
    @Override
    public void setHitNums(int hitNums) {
        this.hitNums = hitNums;
    }

    // Returns the parking price for the Car
    @Override
    public int getParkingPrice() {
        return this.parkingPrice;
    }

    // Returns the hit price for the Car
    @Override
    public int getHitPrice() {
        return this.hitPrice;
    }

    // Returns the type of the vehicle as Car
    @Override
    public String getType() {
        return this.type;
    }

    // Returns the overnight price for the Car
    @Override
    public int getOvernightPrice() {
        return overnightPrice;
    }
}

