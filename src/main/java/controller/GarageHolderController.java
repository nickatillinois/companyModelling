package controller;

import assemAssist.Company;
import assemAssist.exceptions.*;

import java.util.*;

/**
 * This class is the controller for the garage holder.
 * It is responsible for the communication between the garage holder and the
 * Company class.
 * @author  Team 10
 */
public class GarageHolderController {

    /**
     * The current garage holder communicating with the company.
     */
    private String garageHolder;

    /**
     * The model the garage holder has chosen.
     */
    private String chosenModel;

    /**
     * The company the garage holder is communicating with.
     */
    private final Company company;

    /**
     * Constructor for the garage holder controller.
     * @param company The company the garage holder is communicating with.
     * @throws IllegalArgumentException If the company is null.
     */
    public GarageHolderController(Company company) {
        if(company == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }
        this.company = company;
    }

    /**
     * Sets the garage holder and returns the orders belonging to the garage holder.
     * @param garageHolder The garage holder.
     * @throws IllegalArgumentException If the garage holder is null.
     *                                  If the garage holder is empty.
     *                                  If the garage holder is only whitespace.
     */
    public ArrayList<String[]>[] newLogin(String garageHolder) {
        if(garageHolder == null) {
            throw new IllegalArgumentException("Garage holder cannot be null");
        }
        if(garageHolder.equals("")) {
            throw new IllegalArgumentException("Garage holder cannot be empty");
        }
        if(garageHolder.trim().equals("")) {
            throw new IllegalArgumentException("Garage holder cannot be whitespace");
        }
        this.garageHolder = garageHolder;
        return this.company.newLogin(garageHolder);
    }

    /**
     * Returns the models offered by the car manufacturer.
     * @return The models offered by the car manufacturer.
     */
    public HashSet<String> wantsToOrder() {
        return company.getAvailableModels();
    }

    public int getWorkingMinutesWorkingStation(String modelName) throws IllegalModelException {
        return this.company.getWorkingTimeWorkingStation(modelName);
    }

    /**
     * Returns a map of the components and their options for the given model.
     * @param carModel The model of the car.
     *                 If the model is null, an IllegalArgumentException is thrown.
     *                 If the model is empty, an IllegalArgumentException is thrown.
     *                 If the model is only whitespace, an IllegalArgumentException is thrown.
     *                 If the model is not offered by the car manufacturer, an IllegalModelException is thrown.
     * @throws IllegalArgumentException If the model is null.
     *                                  If the model is empty.
     *                                  If the model is only whitespace.
     * @throws IllegalModelException If the model is not offered by the car manufacturer.
     * @return A map of the components and their options for the given model.
     */
    public TreeMap<String, HashSet<String>> selectModel(String carModel) throws IllegalModelException {
        this.chosenModel = carModel;
        return company.selectModel(carModel);
    }

    /**
     * Function that accepts a map of specifications, a garage holder name, and a chosen car type.
     * The function generates a new order for the garage holder with the given specifications and
     * adds it to the production queue.
     * @param chosenOptions the specifications of the car to order
     *                       (the specifications must be valid)
     * The function returns the estimated completion time of the order in the format
     *                     "yyyy-MM-dd HH:mm:ss".
     *
     * @return the estimated completion time of the order in the format
     *                     "yyyy-MM-dd HH:mm:ss".
     * @throws IllegalCompletionDateException if the completion date is invalid
     * @throws IllegalConstraintException if there exist constraints not following the correct syntax.
     * @throws IllegalModelException if the chosen model is not offered by the car manufacturer.
     * @throws OptionThenComponentException if there is an option specified but not the implied component.
     * @throws OptionAThenOptionBException if there is an option specified but not the implied option(s).
     * @throws RequiredComponentException if there is a required component not specified.
     */
    public String completeOrderingForm(TreeMap<String, String> chosenOptions, int workingMinutesWorkstation) throws IllegalArgumentException, IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        return company.completeOrderingForm(chosenOptions, this.garageHolder, this.chosenModel, workingMinutesWorkstation);
    }

    /**
     * Returns a list consisting of strings. For a pending CarOrder, these are the specifications, the timestamp of ordering and the
     * estimated production time. For completed orders, these are the specifications, the timestamp
     * of ordering and the timestamp of completion.
     *
     * @return set of CarOrders that are pending or completed and ordered by the given garage holder
     * @param carID the id of the order of which the details will be returned
     * @param garageHolderName the garage holder who ordered the order belonging to the given ID
     * @throws IllegalArgumentException if the given ID is not strictly positive
     *                                  or if the given garage holder is null
     *                                  or if the given garage holder is empty
     *                                  or if only whitespace is given
     */
    public List<String> viewDetails(int carID, String garageHolderName) throws IllegalArgumentException, OrderNotFoundException {
        if (carID <= 0) {
            throw new IllegalArgumentException("Car ID cannot be less than or equal to 0");
        } else {
            return company.getOrderDetails(carID, garageHolderName);
        }
    }
}
