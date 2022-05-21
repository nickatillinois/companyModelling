package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.Catalog;
import assemAssist.exceptions.*;

import java.util.HashSet;
import java.util.TreeMap;


/**
 * Class representing the constraint that chosen each car model must be in the catalog.
 *
 * @author SWOP team 10
 */
public class Model extends Constraint{

    /**
     * Constructor for the Model constraint.
     */
    public Model() {
        super();
    }


    /**
     * method that checks if the chosen specifications are in line with the constraints
     *
     * @param chosenSpecifications The chosen specifications
     * @throws IllegalArgumentException   | chosenSpecifications = null
     * @throws IllegalModelException | Model is not satisfied
     *
     */
    @Override
    protected void isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, IllegalModelException {
        Catalog catalog = new Catalog();
        String modelName = chosenSpecifications.getModelName();
        // check if this model name is in catalog.getAvailableModelNames(), ignore case of model name
        boolean containsModelName = false;
        for (String availableModelName : catalog.getAvailableModelNames()) {
            if (availableModelName.equalsIgnoreCase(modelName)) {
                containsModelName = true;
                break;
            }
        }
        if (!containsModelName) {
            throw new IllegalModelException("Model name " + modelName + " is not in the catalog.");
        }
        TreeMap<String, HashSet<String>> model = catalog.getOptions(modelName);
        // check if the chosen specifications are in the model
        for (String key : chosenSpecifications.getChosenOptions().keySet()) {
            // every key in chosenOptions must be in model, ignore case of key
            boolean containsKey = false;
            for (String availableKey : model.keySet()) {
                if (availableKey.equalsIgnoreCase(key)) {
                    containsKey = true;
                    break;
                }
            }
            if (!containsKey) {
                throw new IllegalModelException("Component " + key + " is not in model " + modelName);
            }
            // check if the chosen value is in the model
            boolean containsValue = false;
            String chosenValue = chosenSpecifications.getChosenOptions().get(key);
            for (String availableValue : model.get(key)) {
                if (availableValue.equalsIgnoreCase(chosenValue)) {
                    containsValue = true;
                    break;
                }
            }
            if (!containsValue) {
                throw new IllegalModelException("Value " + chosenSpecifications.getChosenOptions().get(key) + " is not in model " + modelName + " for component " + key);
            }
            // every key must have a value
            if(chosenSpecifications.getChosenOptions().get(key) == null){
                throw new IllegalModelException("Model " + modelName + " does not have a value for the component " + key);
            }
        }
    }

    @Override
    protected void reset() {
        // do nothing
    }

    public boolean equals(Object obj) {
        if (! super.equals(obj)) return false;
        if (this.getClass() != obj.getClass())
            return false;
        Model other = (Model) obj;
        return true;
    }
}