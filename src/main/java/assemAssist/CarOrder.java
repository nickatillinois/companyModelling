package assemAssist;

import java.time.LocalDateTime;
import assemAssist.exceptions.IllegalCompletionDateException;


/**
 * Class representing a car order.
 *
 * @author SWOP Team 10
 */
public class CarOrder {


    static int counter = 0;

    /**
     * The order's unique identifier.
     */
    private final int ID;

    /**
     * The estimated completion date of this carorder in LocalDateTime format.
     */
    private LocalDateTime completionTime;

    /**
     * A GarageHolder object representing the client of this order.
     */
    private String garageholder;


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
     *                                  | garageholder is the empty string
     *
     */
    public void setGarageholder(String garageholder){
        if(garageholder == null){throw new IllegalArgumentException("garageholder cannot be null.");}
        if(garageholder.equals("")){throw new IllegalArgumentException("A garage holder cannot be the empty string.");}
        this.garageholder = garageholder;
    }

    /**
     * Returns the name of the garage holder of this carOrder.
     *
     * @return The name of the garage holder of this carOrder.
     */
    public String getGarageholder() {
        return garageholder;

    }

    /**
     * Returns the carmodel of this carOrder.
     *
     * @return The carmodel of this carOrder.
     */
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

    public boolean isCompleted(){
        return this.completed;
    }

    /**
     * Sets whether this order is completed to the given boolean.
     *
     * @param completed Boolean representing whether the order is completed by the car manufacturing company.
     *
     */
    public void setCompleted(boolean completed){
        this.completed = completed;
        if (completed)
            this.completionTime = LocalDateTime.now();
    }

    /**
     * Returns the immutable LocalTimeDate of this carOrder.
     *
     * @return The immutable LocalTimeDate of this carOrder.
     */
    public LocalDateTime getCompletionTime(){
        return this.completionTime;
    }

    /**
     * Sets the current LocalTimeDate when this order is estimated to be completed to the given LocalTimeDate.
     *
     * @param completionTime The given estimated completion date in LocalTimeDate format.

     */

    public void setCompletionTime(LocalDateTime completionTime) throws IllegalCompletionDateException {

        if (completionTime == null){throw new IllegalArgumentException("completion time cannot be set to null");}
        //if (completionTime.isBefore(LocalDateTime.now())){throw new IllegalCompletionDateException("completion time cannot be set in the past");}
        this.completionTime = completionTime;
    }


    public int getID(){
        return this.ID;
    }



    /**
     * Creates a new car model specification with a given body, color, engine, gearbox, seats, airco, wheels.
     *
     * @param garageHolder The client for the car order.
     * @param carModel The car model for the car order.
     * @throws IllegalArgumentException | garageHolder is null
     *                                  | garageHolder is the empty string
     *                                  | carModel is null
     */
    public CarOrder(String  garageHolder, CarModel carModel){
        if(garageHolder == null){throw new IllegalArgumentException("A garage holder cannot be null.");}
        if(garageHolder.equals("")){throw new IllegalArgumentException("A garage holder cannot be the empty string.");}
        if(carModel == null){throw new IllegalArgumentException("A car model cannot be null.");}
        this.garageholder = garageHolder;
        this.carModel = carModel;
        this.completed = false;
        this.completionTime = null;
        counter++;
        this.ID = counter;
    }

    public String getCarModelAndOptions () {
        String modelAndOptions = "model: " + carModel.getChosenModelName() + ", ";
        modelAndOptions += carModel.getChosenOptions();
        return modelAndOptions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarOrder carOrder = (CarOrder) o;

        return ID == carOrder.ID;
    }

}
