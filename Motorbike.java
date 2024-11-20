/**
 * The Motorbike class extends the Vehicle class to represent a Motorbike within a parking simulation. 
 * It sets up specific characteristics and behaviours for a Motorbike, such as type, parking price, 
 * hit price, and overnight price, providing the unique implementations for the abstract methods in 
 * the parent Vehicle class. This class aids in simulating the interactions of a Motorbike in the parking system, 
 * calculating fees, and managing its state during the simulation.
 *
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */

public class Motorbike extends Vehicle {

    // Specifies the type of the vehicle as Motorbike
    private String type = Constant.VEHICLE_TYPE_MOTORBIKE;

    // Specifies the default parking price for the Motorbike
    private int parkingPrice = Constant.PARKING_PRICE_MOTORBIKE;

    // Specifies the price incurred by the Motorbike per hit
    private int hitPrice = Constant.HIT_PRICE_MOTORBIKE;

    // Specifies the overnight fee applicable to the Motorbike
    private int overnightPrice = Constant.OVERNIGHT_FEE_MOTORBIKE;

    // Holds the number of hits incurred by the Motorbike during the simulation
    protected int hitNums;
    
    // Returns the number of hits incurred by the Motorbike
    @Override
    public int getHitNums() {
        return hitNums;
    }

    // Updates the number of hits incurred by the Motorbike
    @Override
    public void setHitNums(int hitNums) {
        this.hitNums = hitNums;
    }
    
    // Returns the parking price for the Motorbike
    @Override
    public int getParkingPrice() {
        return this.parkingPrice;
    }
    
    // Returns the hit price for the Motorbike
    @Override
    public int getHitPrice() {
        return this.hitPrice;
    }
    
    // Returns the type of the vehicle as Motorbike
    @Override
    public String getType() {
        return this.type;
    }

    // Returns the overnight price for the Motorbike
    @Override
    public int getOvernightPrice() {
        return overnightPrice;
    }
}

