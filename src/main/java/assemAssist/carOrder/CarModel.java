package assemAssist.carOrder;

/**
 * Class representing a car model with a modelname and a car model specification.
 *
 * @author SWOP Team 10
 */
public class CarModel {

    /**
     * A string with the model Name of this car model.
     */
    private String modelName;

    /**
     * A CarModelSpecification object containing the options for this car model's components.
     */
    private CarModelSpecification carModelSpecification;

    /**
     * Returns the name of this car model.
     *
     * @return Car model name.
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Sets the name of this car model to the given name.
     *
     * @param modelName The name for the car model.
     * @throws IllegalArgumentException | modelName is null
     *                                  | modelName is the empty string
     */
    public void setModelName(String modelName) {
        if(modelName == null){throw new IllegalArgumentException("A modelname cannot be null.");}
        if(modelName.length() == 0){throw new IllegalArgumentException("A modelname cannot be the empty string.");}
        this.modelName = modelName;
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
     *                                  | carModelSpecificatoin is null
     */
    public CarModel(String modelName, CarModelSpecification carModelSpecification) {
        if(modelName == null){throw new IllegalArgumentException("A modelname cannot be null.");}
        if(modelName.length() == 0){throw new IllegalArgumentException("A modelname cannot be the empty string.");}
        if(carModelSpecification == null){throw new IllegalArgumentException("A carModelSpecification cannot be null.");}
        this.modelName = modelName;
        this.carModelSpecification = carModelSpecification;
    }
}
