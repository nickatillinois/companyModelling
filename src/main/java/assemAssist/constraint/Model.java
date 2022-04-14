package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.Catalog;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;

import java.util.HashSet;
import java.util.Map;

public class Model extends Constraint{

    public Model() {
        super();
    }

    @Override
    protected boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, IllegalConstraintException, IllegalModelException {
        Catalog catalog = new Catalog();
        String modelName = chosenSpecifications.getModelName();
        // check if this model name is in catalog.getAvailableModelNames(), ignore case of model name
        boolean containsModelName = false;
        for (String availableModelName : catalog.getAvailableModelNames()) {
            if (availableModelName.equalsIgnoreCase(modelName)) {
                containsModelName = true;
            }
        }
        if (!containsModelName) {
            throw new IllegalModelException("Model name " + modelName + " is not in the catalog.");
        }
        Map<String, HashSet<String>> model = catalog.getModelSpecifications(modelName);
        // check if the chosen specifications are in the model
            for (String key : chosenSpecifications.getChosenOptions().keySet()) {
                // every key in chosenOptions must be in model, ignore case of key
                boolean containsKey = false;
                for (String availableKey : model.keySet()) {
                    if (availableKey.equalsIgnoreCase(key)) {
                        containsKey = true;
                    }
                }
                if (!containsKey) {
                    throw new IllegalModelException("Component " + key + " is not in model " + modelName);
                }
                // check if the chosen value is in the model
                boolean containsValue = false;
                for (String availableValue : model.get(key)) {
                    if (availableValue.equalsIgnoreCase(chosenSpecifications.getChosenOptions().get(key))) {
                        containsValue = true;
                    }
                }
                if (!containsValue) {
                    throw new IllegalModelException("Value " + chosenSpecifications.getChosenOptions().get(key) + " is not in model " + modelName + " for component " + key);
                }
                // every key must have a value
                if(chosenSpecifications.getChosenOptions().get(key) == null){
                    throw new IllegalConstraintException("Model " + modelName + " does not have a value for the component " + key);
                }
            }
        return true;
    }
}