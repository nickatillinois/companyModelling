package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.RequiredComponentException;

public class RequiredComponent extends Constraint {
    public RequiredComponent() {
        super();
    }
    @Override
    protected boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, RequiredComponentException {
        if(
                chosenSpecifications.getChosenOptions().containsKey("Body") &&
                chosenSpecifications.getChosenOptions().containsKey("Color") &&
                chosenSpecifications.getChosenOptions().containsKey("Engine") &&
                chosenSpecifications.getChosenOptions().containsKey("Gearbox") &&
                chosenSpecifications.getChosenOptions().containsKey("Seats") &&
                chosenSpecifications.getChosenOptions().containsKey("Wheels")) {
            return true;
        }
        throw new RequiredComponentException("You are missing an essential component: body, color, engine, gearbox, seats or/and wheels.");
    }
}
