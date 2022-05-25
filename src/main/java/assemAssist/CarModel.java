package assemAssist;

import assemAssist.exceptions.*;
import assemAssist.constraint.Inspector;

import java.util.TreeMap;

/**
 * CarModel is the class that represents the car model.
 */
public class CarModel {


    private final int workingTimeWorkingTime;
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
     *                      Must not be null. This parameter is copied.
     * @param workingTimeWorkPost the time that a model spend in a workstation.
     *                            Must not be null.
     * @throws IllegalArgumentException if the given name contains only whitespace.
     *                                  if the given name is null
     *                                  if the given name is the empty string
     *                                  if the given options are null
     */
    public CarModel(String modelName, TreeMap<String, String> chosenOptions, int workingTimeWorkPost) throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        if (modelName == null) {
            throw new IllegalArgumentException("modelName must not be null.");
        }
        if (modelName.isEmpty()) {
            throw new IllegalArgumentException("modelName must not be empty.");
        }
        if (modelName.contains(" ")) {
            throw new IllegalArgumentException("modelName must not contain only whitespace.");
        }
        if (chosenOptions == null) {
            throw new IllegalArgumentException("chosenOptions must not be null.");
        }
        if(workingTimeWorkPost < 0){
            throw new IllegalArgumentException("workingTimeWorkPost must not be negative.");
        }
        this.modelName = modelName;
        this.chosenOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.chosenOptions.putAll(chosenOptions);
        this.inspect();
        this.workingTimeWorkingTime = workingTimeWorkPost;
    }

    /**
     * @throws IllegalConstraintException if a given constraint does not have the correct format.
     * @throws IllegalModelException if this car model is not specified in the model names
     * @throws OptionThenComponentException if an option is specified implying a component, but the component is not chosen.
     * @throws OptionAThenOptionBException if an option is specified implying another option, but the option is not chosen.
     * @throws RequiredComponentException  if a component is required, but not chosen.
     */
    public void inspect() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        try{
            new Inspector(this).inspect();
        }
        catch (Exception e){
            throw e;
        }

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
    public int getWorkingTimeWorkingTime(){
        return workingTimeWorkingTime;
    }

}
