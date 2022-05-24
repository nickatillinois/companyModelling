package assemAssist;

import assemAssist.exceptions.*;
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
     * static counter for keeping track of the number of orders in order to assign a unique id to each order.
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
     * Sets whether this order is completed to the given boolean.
     *
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
        if (completionTime == null){throw new IllegalArgumentException("A completion time cannot be null.");}
        if (completionTime.isBefore(LocalDateTime.now())){throw new IllegalCompletionDateException("completion time cannot be set in the past");}
        this.completionTime = completionTime;
    }

    /**
     * Sets the given LocalTimeDate when this order is estimated to be completed to the given LocalTimeDate.
     * @param estCompletionTime The given estimated completion date in LocalTimeDate format.
     * @throws IllegalArgumentException | estCompletionTime is null
     */
    public void setEstCompletionTime(LocalDateTime estCompletionTime) {
        if (estCompletionTime == null){throw new IllegalArgumentException("An estimated completion time cannot be null.");}
        //if (completionTime.isBefore(LocalDate.now())){throw new IllegalCompletionDateException("completion time cannot be set in the past");}
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
     * Function that returns a string representation of this car order.
     * @return A string representation of this car order.
     */
    public String getCarModelAndOptions() {
        String modelAndOptions = "model: " + carModel.getModelName() + ", ";
        modelAndOptions += carModel.getChosenOptions();
        return modelAndOptions;
    }

    /**
     * Function that returns a string representation of the order details of this car order.
     * The format of the string is as follows:
     * "Order ID: <orderID>, Car Model: <carModel>, Options: <options>, Estimated Completion Time: <estCompletionTime>, Completed: <completed>"
     * Only to be used for pending orders.
     * @return A string representation of this car order.
     */
    private ArrayList<String> getPendingOrderDetails(){
        ArrayList<String> orderDetails = new ArrayList<>();
        orderDetails.add("Specifications: " + getCarModelAndOptions ());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedOrderTime = orderingTime.format(formatter);
        String formattedEstProdTime = estCompletionTime.format(formatter);
        if(this.estCompletionTime == null){
            throw new IllegalArgumentException("The given car order's estimated completion time is still set to 'null'");
        }
        orderDetails.add("orderTime: " + formattedOrderTime);
        orderDetails.add("estProdTime: " + formattedEstProdTime);
        return orderDetails;
    }

    /**
     * Function that returns a string representation of the order details of this car order.
     * The format of the string is as follows:
     * Specifications: <carModel>, <options>, order Time: <estCompletionTime>, completionTime: <completionTime>
     * @return A string representation of this car order.
     * Only to be used for completed orders.
     */
    private ArrayList<String> getCompletedOrderDetails(){
        ArrayList<String> orderDetails = new ArrayList<>();
        // format orderingTime in a more readable format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedOrderTime = orderingTime.format(formatter);
        String formattedCompletionTime = completionTime.format(formatter);
        orderDetails.add("Specifications: " + getCarModelAndOptions ());
        orderDetails.add("orderTime: " + formattedOrderTime);
        orderDetails.add("completionTime: " + formattedCompletionTime);
        return orderDetails;
    }

    /**
     * Function that returns a string representation of the order details of this car order.
     * The format of the string depends on the status of the order.
     * If the order is pending, the format is as follows:
     * Specifications: <carModel>, <options>, order Time: <estCompletionTime>, est. completionTime: <estCompletionTime>
     * If the order is completed, the format is as follows:
     * Specifications: <carModel>, <options>, order Time: <estCompletionTime>, completionTime: <completionTime>
     * @return A string representation of this car order.
     */
    public ArrayList<String> getOrderDetails(){
        if(completed){
            return getCompletedOrderDetails();
        }
        else{
            return getPendingOrderDetails();
        }
    }

    /**
     * Function that returns whether the given CarOrder is equal to this CarOrder.
     * @param o The CarOrder to compare to this CarOrder.
     *              If other is null, this function returns false.
     *              If other is not a CarOrder, this function returns false.
     *              If other is a CarOrder, this function returns true if and only if
     *              this CarOrder is equal to other.
     *              Two CarOrders are equal if they have the same orderID, carModel,
     *              options, orderingTime, estCompletionTime, and completed.
     *              If either orderingTime, estCompletionTime, or completionTime is null,
     *              the corresponding value in other must also be null.
     *
     * @return true if and only if this CarOrder is equal to other.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarOrder carOrder = (CarOrder) o;
        return ID == carOrder.ID;
    }

    public int getWorkingMinutesWorkStation() {
        return getCarModel().getWorkingTimeWorkingTime();
    }

    public static void resetCounter() {
        counter = 0;
    }

}
