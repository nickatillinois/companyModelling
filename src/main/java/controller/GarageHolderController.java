package controller;

import assemAssist.CarOrder;
import assemAssist.CarOrderData;
import assemAssist.Company;
import assemAssist.exceptions.*;

import java.time.format.DateTimeFormatter;
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
     * Returns an array consisting of two elements:
     * the first element is a list of pending orders that were not completed.
     * the second element is a list of completed orders.
     * @return an array consisting of two elements.
     *         the first element is a list of pending orders that were not completed.
     *         the second element is a list of completed orders.
     *         if there are not any pending orders, the first element is empty.
     *         if there are not any completed orders, the second element is empty.
     *         if there are not any pending or completed orders, both elements are empty.
     * @param garageHolder the garage holder to get the orders from
     * @throws IllegalArgumentException if the given garage holder is null
     *                                  or if the given string is empty
     *                                  or if only whitespace is given
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
        // create an array of 2 lists each containing a number of arrays of strings
        ArrayList<String[]>[] result = new ArrayList[2];
        ArrayList<String[]> pendingOrdersString = new ArrayList<>();
        ArrayList<String[]> completedOrdersString = new ArrayList<>();
        // get the pending and completed orders from this garage holder
        List<CarOrder>[] orders = company.getOrdersFromGarageHolder(garageHolder);
        List<CarOrder> pendingOrders = orders[0];
        List<CarOrder> completedOrders = orders[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // take the ID and the estimated completion date from each pending order
        for (CarOrder carOrder : pendingOrders) {
            pendingOrdersString.add(new String[]{Integer.toString(carOrder.getOrderID()), carOrder.getEstCompletionTime().format(formatter)});
        }
        // take the ID and the completion date from each completed order
        for (CarOrder carOrder : completedOrders) {
            completedOrdersString.add(new String[]{Integer.toString(carOrder.getOrderID()), carOrder.getCompletionTime().format(formatter)});
        }
        result[0] = pendingOrdersString;
        result[1] = completedOrdersString;
        return result;
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
    public String completeOrderingForm(TreeMap<String, String> chosenOptions) throws IllegalArgumentException, IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        try{
            return company.completeOrderingForm(chosenOptions, this.garageHolder, this.chosenModel);
        }
        catch (Exception e){
            throw e;
        }

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
            try {
                CarOrderData carOrderData = company.getOrderDetails(carID, garageHolderName);

                ArrayList<String> orderDetails = new ArrayList<>();

                String modelOptionsAndName = "model: " + carOrderData.getMODELNAME() + ", ";
                modelOptionsAndName += carOrderData.getCAROPTIONS();
                orderDetails.add("Specifications: " + modelOptionsAndName);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedOrderTime = carOrderData.getORDERINGTIME().format(formatter);
                orderDetails.add("orderTime: " + formattedOrderTime);

                if (carOrderData.isCOMPLETED()) {
                    String formattedCompletionTime = carOrderData.getCOMPLETIONTIME().format(formatter);
                    orderDetails.add("completionTime: " + formattedCompletionTime);
                } else {
                    String formattedEstProdTime = carOrderData.getESTCOMPLETIONTIME().format(formatter);
                    orderDetails.add("estProdTime: " + formattedEstProdTime);
                }

                return orderDetails;
            } catch (OrderNotFoundException e) {
                throw e;
            }
        }
    }
}
