package assemAssist.restriction;

import assemAssist.CarModel;
import assemAssist.exceptions.IllegalConstraintException;

public class RequiredComponent extends Constraint {
    public RequiredComponent() {
        super();
    }
    @Override
    protected boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, IllegalConstraintException {
        if(
                chosenSpecifications.getChosenOptions().containsKey("Body") &&
                chosenSpecifications.getChosenOptions().containsKey("Color") &&
                chosenSpecifications.getChosenOptions().containsKey("Engine") &&
                chosenSpecifications.getChosenOptions().containsKey("Gearbox") &&
                chosenSpecifications.getChosenOptions().containsKey("Seats") &&
                chosenSpecifications.getChosenOptions().containsKey("Wheels")) {
            return true;
        }
        throw new IllegalConstraintException("You are missing an essential component: body, color, engine, gearbox, seats or/and wheels.");
    }
}
