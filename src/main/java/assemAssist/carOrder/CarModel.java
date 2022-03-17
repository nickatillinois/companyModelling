package assemAssist.carOrder;

import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalModelException;
import purecollections.PList;

/**
 * Class representing a car model with a modelname and a car model specification.
 *
 * @author SWOP Team 10
 */
public class CarModel {

    /**
     * An immutable PList of available car models
     *
     */
    private static PList<String> availableModels = PList.empty();

    /**
     * A string with the model Name of this car model.
     */
    private String chosenModelName;

    /**
     * A CarModelSpecification object containing the options for this car model's components.
     */
    private CarModelSpecification carModelSpecification;

    /**
     * Returns an immutable PList of available car models
     *
     * @return an immutable PList of available car models
     */
    public static PList<String> getAvailableModels(){
        return availableModels;
    }

    /**
     * Adds the given string of a car model name to the end of the PList of car model names.
     * If the given string is already in the PList, the string is not added.
     *
     * @param model  the string that is added to the PList of existing car models.
     * @throws IllegalArgumentException | given model name is null
     *                                  | given model name is the empty string
     */
    public static void addModel(String model){
        if(model == null){throw new IllegalArgumentException("A model name cannot be null.");}
        if(model.length() == 0){throw new IllegalArgumentException("A model name cannot be the empty string.");}
        if(availableModels.contains(model)) return;
        availableModels = availableModels.plus(model);
    }

    /**
     * Returns the name of this car model.
     *
     * @return Car model name.
     */
    public String getModelName() {
        return chosenModelName;
    }

    /**
     * Sets the name of this car model to the given name.
     *
     * @param modelName The name for the car model.
     * @throws IllegalArgumentException | modelName is null
     *                                  | modelName is the empty string
     */
    public void setModelName(String modelName) throws IllegalModelException {
        if(modelName == null){throw new IllegalArgumentException("A modelname cannot be null.");}
        if(modelName.length() == 0){throw new IllegalArgumentException("A modelname cannot be the empty string.");}
        boolean contains = false;
        for(String model: availableModels){
            if(model.equalsIgnoreCase(modelName)){
                contains = true;
                break;
            }
        }
        if(!contains) {
            throw new IllegalModelException("The given model is currently not offered. Please choose an available model or add the model to the list of offered models.");}
        chosenModelName = modelName;
    }

    /**
     * Returns the car model specification of this car model.
     *
     * @return The car model specification of this car model.
     */
    public CarModelSpecification getCarModelSpecification() {
        return carModelSpecification;
    }

    /**
     * Sets the car model specification of this car model to given car model specification.
     *
     * @param carModelSpecification The car model specification for this car model.
     * @throws IllegalArgumentException | car model specification is null
     *
     */
    public void setCarModelSpecification(CarModelSpecification carModelSpecification) {
        if (carModelSpecification == null) throw new IllegalArgumentException("carModelSpecification cannot be null.");
        this.carModelSpecification = carModelSpecification;
    }

    /**
     * Creates a new car model with a given name and a given car model specification
     *
     * @param modelName The name for the car model.
     * @param carModelSpecification  The car model specification for the car model.
     * @throws IllegalArgumentException | modelName is null
     *                                  | modelname is the empty string
     *                                  | carModelSpecification is null
     * @throws IllegalModelException    | the given modelName is not in the list of available model names
     */
    public CarModel(String modelName, CarModelSpecification carModelSpecification) throws IllegalModelException {
        if(modelName == null){throw new IllegalArgumentException("A modelname cannot be null.");}
        if(modelName.length() == 0){throw new IllegalArgumentException("A modelname cannot be the empty string.");}
        if(carModelSpecification == null){throw new IllegalArgumentException("A carModelSpecification cannot be null.");}
        boolean contains = false;
        for(String model: availableModels){
            if(model.equalsIgnoreCase(modelName)){
                contains = true;
                break;
            }
        }
        if(!contains) {
            throw new IllegalModelException("The given model is currently not offered. Please choose an available model or add the model to the list of offered models.");}
        this.chosenModelName = modelName;
        this.carModelSpecification = carModelSpecification;
    }
}
