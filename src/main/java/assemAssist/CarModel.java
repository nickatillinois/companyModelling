package assemAssist;

import assemAssist.exceptions.*;
import assemAssist.constraint.Inspector;

import java.util.TreeMap;

/**
 * CarModel is the class that represents the car model.
 */
public class CarModel {


    /**
     * The name of the car model.
     */
    private final String modelName;

    /**
     * The car model's chosen options, as a map of component names to 1 option value.
     */
    private final TreeMap<String, String> chosenOptions;

    /**
     * Returns the name of the car model.
     * @return the name of the car model.
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Constructs a new CarModel with the given name and options.
     * @param modelName the name of the car model.
     *                  Must not be null.
     *                  Must not be empty.
     *                  Must not contain whitespace.
     * @param chosenOptions the options chosen for the car model.
     *                      Must not be null.
     * @throws IllegalArgumentException if the given name contains only whitespace.
     *                                  if the given name is null
     *                                  if the given name is the empty string
     *                                  if the given options are null
     */
    public CarModel(String modelName, TreeMap<String, String> chosenOptions) {
        if (modelName == null) {
            throw new IllegalArgumentException("modelName must not be null.");
        }
        if (modelName.isEmpty()) {
            throw new IllegalArgumentException("modelName must not be empty.");
        }
        if (modelName.contains(" ")) {
            throw new IllegalArgumentException("modelName must not contain whitespace.");
        }
        if (chosenOptions == null) {
            throw new IllegalArgumentException("chosenOptions must not be null.");
        }
        this.modelName = modelName;
        this.chosenOptions = chosenOptions;
    }

    /**
     * Returns true if this car model is specified according to the existing constraints.
     * @return true if this car model is specified according to the existing constraints.
     * @throws IllegalConstraintException if a given constraint does not have the correct format.
     * @throws IllegalModelException if this car model is not specified in the model names
     * @throws OptionThenComponentException if an option is specified implying a component, but the component is not chosen.
     * @throws OptionAThenOptionBException if an option is specified implying another option, but the option is not chosen.
     * @throws RequiredComponentException  if a component is required, but not chosen.
     */
    public boolean inspect() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        return new Inspector(this).inspect();
    }

    /**
     * Returns a map of the options chosen for this car model.
     * @return a map of the options chosen for this car model.
     */
    public TreeMap<String, String> getChosenOptions() {
        return chosenOptions;
    }

    /**
     * Returns a string representation of this car model.
     * @return a string representation of this car model.
     */
    public String getChosenOptionsString() {
        StringBuilder sb = new StringBuilder();
        for (String key : chosenOptions.keySet()) {
            sb.append(key).append(": ").append(chosenOptions.get(key)).append("\n");
        }
        return sb.toString();
    }

}
