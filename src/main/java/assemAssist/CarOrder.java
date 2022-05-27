package assemAssist;

import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.observer.StatisticsObservable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Class representing a car order.
 *
 * @author SWOP Team 10
 */
public class CarOrder implements StatisticsObservable {

    /**
     * Static counter for keeping track of the number of orders in order to assign a unique id to each order.
     */
    private static int counter = 0;

    /**
     * The order's unique identifier.
     */
    private final int ID;

    /**
     * A GarageHolder object representing the client of this order.
     */
    private String garageHolder;

    /**
     * A boolean representing whether this order was completed by the car manufacturing company.
     */
    private boolean completed;

    /**
     * The effective completion date of this car order in LocalDate format.
     */
    private LocalDateTime completionTime;

    /**
     * The estimated completion date of this car order in LocalDate format.
     */
    private LocalDateTime estCompletionTime;

    /**
     * The estimated completion date of this car order in LocalDateTime format.
     */
    public LocalDateTime getOrderingTime() {
        return orderingTime;
    }

    /**
     * The timestamp of ordering this car order in LocalDate format.
     */
    private final LocalDateTime orderingTime;

    /**
     * A CarModel object representing the car model with specifications of this order.
     */
    private CarModel carModel;


    /**
     * Creates a new car model specification with a given body, color, engine, gearbox, seats, airco, wheels.
     *
     * @param garageHolder The client for the car order.
     * @param carModel The car model for the car order.
     * @throws IllegalArgumentException | garageHolder is null
     *                                  | garageHolder is the empty string
     *                                  | carModel is null
     */
    public CarOrder(String  garageHolder, CarModel carModel) throws IllegalArgumentException{
        if(garageHolder == null){throw new IllegalArgumentException("A garage holder cannot be null.");}
        if(garageHolder.equals("")){throw new IllegalArgumentException("A garage holder cannot be the empty string.");}
        if(carModel == null){throw new IllegalArgumentException("A car model cannot be null.");}
        this.garageHolder = garageHolder;
        this.carModel = carModel;
        this.completed = false;
        this.completionTime = null;
        counter++;
        this.ID = counter;
        this.orderingTime = LocalDateTime.now();
    }

    /**
     * Returns the estimated completion date for this car order.
     * @return The estimated completion date for this car order.
     */
    public LocalDateTime getEstCompletionTime() {
        return estCompletionTime;
    }

    /**
     * Sets the garage holder of this car order to the given garage holder.
     *
     * @param garageHolder The garage holder for this car order.
     * @throws IllegalArgumentException | garageHolder is null
     *                                  | garageHolder is the empty string
     *
     */
    public void setGarageHolder(String garageHolder){
        if(garageHolder == null){throw new IllegalArgumentException("A garage holder cannot be null.");}
        if(garageHolder.equals("")){throw new IllegalArgumentException("A garage holder cannot be the empty string.");}
        this.garageHolder = garageHolder;
    }

    /**
     * Returns the name of the garage holder of this carOrder.
     *
     * @return The name of the garage holder of this carOrder.
     */
    public String getGarageHolder() {
        return garageHolder;

    }

    /**
     * Returns the car model of this carOrder.
     *
     * @return The car model of this carOrder.
     */
    public CarModel getCarModel() {
        return carModel;
    }

    /**
     * Sets the car model of this car order to the given car model.
     *
     * @param carModel The carModel of this car order.
     * @throws IllegalArgumentException | carModel is null
     *                                  | carModel is the empty string
     *                                  | carModel.getModelName() is null
     *
     */
    public void setCarModel(CarModel carModel) {
        if(carModel == null){throw new IllegalArgumentException("A car model cannot be null.");}
        if(carModel.getModelName() == null){throw new IllegalArgumentException("A car model cannot be null.");}
        if(Objects.equals(carModel.getModelName(), "")){throw new IllegalArgumentException("A car model cannot be the empty string.");}
        this.carModel = carModel;
    }

    /**
     * Returns whether this car order is completed or not.
     * @return true if this car order is completed, false otherwise.
     *
     */
    public boolean isCompleted(){
        return this.completed;
    }

    /**
     * Sets whether this order's isCompleted status to true.
     * Notifies the observers of this order that it has been completed.
     * @throws NullPointerException | estCompletionTime is null
     *
     */
    public void setCompleted(){
        if(this.estCompletionTime == null){throw new NullPointerException("Estimated completion time has not been set.");}
        this.completionTime = LocalDateTime.now();
        this.completed = true;
        double delayInMinutes = ChronoUnit.MINUTES.between(estCompletionTime,completionTime);
        this.notifyObservers(delayInMinutes);
    }

    /**
     * Returns the immutable LocalTimeDate of this carOrder.
     * @return The immutable LocalTimeDate of this carOrder.
     */
    public LocalDateTime getCompletionTime(){
        return this.completionTime;
    }

    /**
     * Sets the current LocalTimeDate when this order is estimated to be completed to the given LocalTimeDate.
     * @param completionTime The given estimated completion date in LocalTimeDate format.
     * @throws IllegalArgumentException | completionTime is null
     * @throws IllegalCompletionDateException | completionTime is before the current LocalTimeDate
     */
    public void setCompletionTime(LocalDateTime completionTime) throws IllegalCompletionDateException {
        if (completionTime == null){throw new IllegalArgumentException("A completion time cannot be null.");}
        if (completionTime.isBefore(LocalDateTime.now().minusMinutes(1))){throw new IllegalCompletionDateException("completion time cannot be set in the past");}
        //is immutable, so we don't need to clone it
        this.completionTime = completionTime;
    }

    /**
     * Sets the given LocalTimeDate when this order is estimated to be completed to the given LocalTimeDate.
     * @param estCompletionTime The given estimated completion date in LocalTimeDate format.
     * @throws IllegalArgumentException | estCompletionTime is null
     *                                  | estCompletionTime is before the current LocalTimeDate
     */
    public void setEstCompletionTime(LocalDateTime estCompletionTime) throws IllegalCompletionDateException {
        if (estCompletionTime == null){throw new IllegalArgumentException("An estimated completion time cannot be null.");}
        if (estCompletionTime.isBefore(LocalDateTime.now().minusMinutes(1))){throw new IllegalCompletionDateException("completion time cannot be set in the past");}
        this.estCompletionTime = estCompletionTime;
    }

    /**
     * Returns the unique id of this carOrder.
     *
     * @return The unique id of this carOrder.
     */
    public int getOrderID(){
        return this.ID;
    }


    /**
     * Method that returns the data object of this car order.
     * @return CarOrderData object representing this car order.
     */
    public CarOrderData carOrderData() {
        return new CarOrderData(carModel.getModelName(), carModel.getChosenOptions(), ID, garageHolder, orderingTime, estCompletionTime, completionTime, completed);
    }

    /**
     * Function that returns whether the given CarOrder is equal to this CarOrder.
     * @param o The CarOrder to compare to this CarOrder.
     *              If other is null, this function returns false.
     *              If other is not a CarOrder, this function returns false.
     *              If other is a CarOrder, this function returns true if and only if
     *              this CarOrder is equal to the other.
     *              Two CarOrders are equal if they have the same orderID, carModel,
     *              options, orderingTime, estCompletionTime, and completed.
     *              If either orderingTime, estCompletionTime, or completionTime is null,
     *              the corresponding value in other must also be null.
     *
     * @return true if and only if this CarOrder is equal to the other.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarOrder carOrder = (CarOrder) o;
        return ID == carOrder.ID;
    }

    /**
     * Returns the standard task time for this car order.
     * @return The standard task time for this car order.
     */
    public int getWorkingMinutesWorkStation() {
        return getCarModel().getStandardWorkingTime();
    }

    /**
     * Method that resets the counter used for generating a unique ID for each car order.
     */
    public static void resetCounter() {
        counter = 0;
    }

}
