package assemAssist;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

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
    private Map<String, HashSet<String>> availableOptions;

    /**
     * Creates a new car model with a given name and a given car model specification
     *
     * @param modelName The name for the car model.
     * @param availableOptions  The car model specification for the car model.
     * @throws IllegalArgumentException | modelName is null
     *                                  | modelName is the empty string
     *                                  | carModelSpecification is null
     */
    public CarModelSpecification(String modelName, Map<String, HashSet<String>> availableOptions)  {
        if(modelName == null){throw new IllegalArgumentException("A modelName cannot be null.");}
        if(modelName.length() == 0){throw new IllegalArgumentException("A modelName cannot be the empty string.");}
        if(availableOptions == null){throw new IllegalArgumentException("A carModelSpecification cannot be null.");}
        this.modelName = modelName;
        this.availableOptions = availableOptions;
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
    public Map<String, HashSet<String>> getAvailableOptions() {
        return availableOptions;
    }

    /**
     * Sets the car model specification of this car model to the given car model specification.
     *
     * @param availableOptions The car model specification for this car model.
     * @throws IllegalArgumentException | carModelSpecification is null
     */
    public void setAvailableOptions(Map<String, HashSet<String>> availableOptions)  {
        if(availableOptions == null){throw new IllegalArgumentException("A car model specification cannot be null.");}
        this.availableOptions = availableOptions;
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
                ", availableOptions =" + getAvailableOptionsString() +
                '}';
    }
    public String getAvailableOptionsString(){
    	StringBuilder sb = new StringBuilder();
    	for(String key : availableOptions.keySet()){
    		sb.append(key).append(": ").append(availableOptions.get(key).toString()).append("\n");
    	}
    	return sb.toString();
    }

    /**
     * Adds a given option to an existing component of this car model.
     *
     * @param component The component to add an option to.
     * @param option The option to add to the component.
     *
     */
    public void addOption(String component, String option){
    	availableOptions.get(component).add(option);
    }

    /**
     * Adds a given component with given options for this component to the map
     * of specifications
     *
     * @param component The new component to add to the map of specifications.
     * @param options The options for the new component.
     *
     */
    public void addComponent(String component, HashSet<String> options){
    	availableOptions.put(component, options);
    }

    /**
     * Removes a given option from an existing component of this car model.
     *
     * @param component The component to remove an option from.
     * @param option The option to remove from the component.
     *
     */
    public void removeOption(String component, String option){
    	availableOptions.get(component).remove(option);
    }

    /**
     * Removes a given component from the map of specifications
     *
     * @param component The component to remove from the map of specifications.
     *
     */
    public void removeComponent(String component){
    	availableOptions.remove(component);
    }

    /**
     * Returns a deep copy of this car model specification.
     *
     * @return A deep copy of this car model specification.
     */
    public CarModelSpecification deepCopy(){
        return new CarModelSpecification(this.modelName, this.availableOptions);
    }

    /**
     * Returns true if this car model specification is equal to the given object.
     *
     * @param o The object to compare this car model specification to.
     * @return True if this car model specification is equal to the given object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModelSpecification that = (CarModelSpecification) o;
        return Objects.equals(modelName, that.modelName) &&
                Objects.equals(availableOptions, that.availableOptions);
    }

    /**
     * Returns a hash code for this car model specification.
     *
     * @return A hash code for this car model specification.
     */
    @Override
    public int hashCode() {
        return Objects.hash(modelName, availableOptions);
    }
}
