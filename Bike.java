/**
 * The Bike class is a specialized extension of the Vehicle class, representing a Bike in a parking simulation. 
 * It initializes unique attributes for a Bike, such as type, parking price, hit price, and overnight price, 
 * and provides implementations for the abstract methods defined in the parent Vehicle class. This class helps 
 * in simulating the actions and states of a Bike in the parking system, enabling the calculation of fees and management 
 * of its state throughout the simulation.
 *
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

public class Bike extends Vehicle {

    // Specifies the type of the vehicle as Bike
    private String type = Constant.VEHICLE_TYPE_BIKE;

    // Specifies the default parking price for the Bike
    private int parkingPrice = Constant.PARKING_PRICE_BIKE;

    // Specifies the price incurred by the Bike per hit
    private int hitPrice = Constant.HIT_PRICE_BIKE;

    // Specifies the overnight fee applicable to the Bike
    private int overnightPrice = Constant.OVERNIGHT_FEE_BIKE;

    // Holds the number of hits incurred by the Bike during the simulation
    protected int hitNums;
    
    // Returns the number of hits incurred by the Bike
    // Note: Currently, this method always returns 0, which may be incorrect. 
    @Override
    public int getHitNums() {
        return 0; // This should probably return hitNums instead of 0.
    }

    // Updates the number of hits incurred by the Bike
    @Override
    public void setHitNums(int hitNums) {
        this.hitNums = hitNums;
    }
    
    // Returns the parking price for the Bike
    @Override
    public int getParkingPrice() {
        return this.parkingPrice;
    }
    
    // Returns the hit price for the Bike
    @Override
    public int getHitPrice() {
        return this.hitPrice;
    }
    
    // Returns the type of the vehicle as Bike
    @Override
    public String getType() {
        return this.type;
    }

    // Returns the overnight price for the Bike
    @Override
    public int getOvernightPrice() {
        return overnightPrice;
    }
}

