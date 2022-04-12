package assemAssist.restriction;

import assemAssist.CarModel;
import assemAssist.Catalog;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;

import java.util.HashSet;
import java.util.Map;

public class ModelA extends Constraint{
    public ModelA() {
        super();
    }

    @Override
    protected boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, IllegalConstraintException, IllegalModelException {
        if(!chosenSpecifications.getModelName().equalsIgnoreCase("A")){
            return true;
        }
        else{
            Map<String, HashSet<String>> A = new Catalog().getModelSpecifications("A");
            for (String key : chosenSpecifications.getChosenOptions().keySet()) {
                // every key in chosenOptions must be in A
                if(!A.containsKey(key)){
                    throw new IllegalConstraintException("Model A does not have the component " + key);
                }
                // every value in chosenOptions must be in A
                if(!A.get(key).contains(chosenSpecifications.getChosenOptions().get(key))){
                    throw new IllegalConstraintException("Model A does not have the option " + chosenSpecifications.getChosenOptions().get(key) + " for the component " + key);
                }
                // every key must have a value
                if(chosenSpecifications.getChosenOptions().get(key) == null){
                    throw new IllegalConstraintException("Model A does not have a value for the component " + key);
                }
            }
        }
        return true;
    }
}