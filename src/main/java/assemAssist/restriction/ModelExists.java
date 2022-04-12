package assemAssist.restriction;

import assemAssist.CarModel;
import assemAssist.Catalog;
import assemAssist.exceptions.IllegalConstraintException;

public class ModelExists extends Constraint {
    public ModelExists() {
        super();
    }

    @Override
    protected boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, IllegalConstraintException {
        if(!new Catalog().getAvailableModelNames().contains(chosenSpecifications.getModelName())) {
            throw new IllegalConstraintException("Model does not exist.");}
        return true;
    }
}
