package assemAssist.carOrder;

import assemAssist.user.GarageHolder;

/**
 * Class representing a car order.
 *
 * @author SWOP Team 10
 */
public class CarOrder {

    /**
     * A GarageHolder object representing the client of this order.
     */
    private GarageHolder garageholder;

    /**
     * A CarModel object representing the Carmodel with specifications of this order.
     */
    private CarModel carModel;

    /**
     * A boolean representing whether this order was completed by the car manufacturing company.
     */
    private boolean completed;

    /**
     * Sets the garage holder of this car order to the given garage holder.
     *
     * @param garageholder The garageholder for this car order.
     * @throws IllegalArgumentException | garageholder is null
     *
     */
    public void setGarageholder(GarageHolder garageholder){
        if(garageholder == null){throw new IllegalArgumentException("garageholder cannot be null.");}
        this.garageholder = garageholder;
    }
    public GarageHolder getGarageholder() {
        return garageholder;
    }
    public CarModel getCarModel() {
        return carModel;
    }

    /**
     * Sets the car model of this car order to the given car model.
     *
     * @param carModel The carModel of this car order.
     * @throws IllegalArgumentException | carModel is null
     *
     */
    public void setCarModel(CarModel carModel) {
        if(carModel == null){throw new IllegalArgumentException("carModel cannot be null.");}
        this.carModel = carModel;
    }

    public boolean isCompleted(){return this.completed;}

    /**
     * Sets whether this order is completed to the given boolean.
     *
     * @param completed Boolean representing whether the order is completed by the car manufacturing company.
     *
     */
    public void setCompleted(boolean completed){this.completed = completed;}

    /**
     * Creates a new car model specification with a given body, color, engine, gearbox, seats, airco, wheels.
     *
     * @param garageHolder The client for the car order.
     * @param carModel The car model for the car order.
     * @throws IllegalArgumentException | garageHolder is null
     *                                  | carModel is null
     */
    public CarOrder(GarageHolder garageHolder, CarModel carModel){
        if(garageHolder == null){throw new IllegalArgumentException("A garage holder cannot be null.");}
        if(carModel == null){throw new IllegalArgumentException("A car model cannot be null.");}
        this.garageholder = garageHolder;
        this.carModel = carModel;
        this.completed = false;
    }
}
