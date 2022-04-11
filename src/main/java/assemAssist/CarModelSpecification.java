package main.java.assemAssist;

import java.util.List;
import java.util.Map;

/**
 * Class representing a car model with a modelName and a car model specification.
 *
 * @author SWOP Team 10
 */
public class CarModelSpecification {

    /**
     * A string with the model Name of this car model.
     */
    private String modelName;

    /**
     * A map containing the options for this car model's components.
     */
    private Map<String, List<String>> carModelSpecification;

    /**
     * Creates a new car model with a given name and a given car model specification
     *
     * @param modelName The name for the car model.
     * @param carModelSpecification  The car model specification for the car model.
     * @throws IllegalArgumentException | modelName is null
     *                                  | modelName is the empty string
     *                                  | carModelSpecification is null
     */
    public CarModelSpecification(String modelName, Map<String, List<String>> carModelSpecification)  {
        if(modelName == null){throw new IllegalArgumentException("A modelName cannot be null.");}
        if(modelName.length() == 0){throw new IllegalArgumentException("A modelName cannot be the empty string.");}
        if(carModelSpecification == null){throw new IllegalArgumentException("A carModelSpecification cannot be null.");}
        this.modelName = modelName;
        this.carModelSpecification = carModelSpecification;
    }
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
        if(modelName == null){throw new IllegalArgumentException("A modelName cannot be null.");}
        if(modelName.length() == 0){throw new IllegalArgumentException("A modelName cannot be the empty string.");}
        this.modelName = modelName;
    }

    /**
     * Returns the car model specification of this car model.
     *
     * @return The car model specification of this car model.
     */
    public Map<String, List<String>> getSpecifications() {
        return carModelSpecification;
    }

    /**
     * Sets the car model specification of this car model to the given car model specification.
     *
     * @param carModelSpecification The car model specification for this car model.
     * @throws IllegalArgumentException | carModelSpecification is null
     */
    public void setCarModelSpecification(Map<String, List<String>> carModelSpecification)  {
        if(carModelSpecification == null){throw new IllegalArgumentException("A car model specification cannot be null.");}
        this.carModelSpecification = carModelSpecification;
    }

    /**
     * Returns a string representation of this car model.
     *
     * @return A string representation of this car model.
     */
    @Override
    public String toString() {
        return "CarModelSpecification{" +
                "modelName='" + modelName + '\'' +
                ", carModelSpecification=" + getCarModelSpecificationString() +
                '}';
    }
    public String getCarModelSpecificationString(){
    	StringBuilder sb = new StringBuilder();
    	for(String key : carModelSpecification.keySet()){
    		sb.append(key).append(": ").append(carModelSpecification.get(key).toString()).append("\n");
    	}
    	return sb.toString();
    }
}
