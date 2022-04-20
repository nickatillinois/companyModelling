package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.IllegalConstraintException;

public class SportBody extends Constraint{
    public SportBody() {
        super();
    }
    @Override
    protected boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, IllegalConstraintException {
        if (chosenSpecifications.getChosenOptions().get("Body").equalsIgnoreCase("sport")) {
            return(checkEngine(chosenSpecifications) && checkSpoiler(chosenSpecifications));
        }
        else {
            return true;
        }
    }
    private boolean checkEngine(CarModel chosenSpecifications) throws IllegalConstraintException {
        if (chosenSpecifications.getChosenOptions().get("Engine").equalsIgnoreCase("v6") ||
                        chosenSpecifications.getChosenOptions().get("Engine").equalsIgnoreCase("v8")) {
            return true;
        }
        else {
            throw new IllegalConstraintException("A sport car must have a V6 or V8 engine.");
        }
    }
    private boolean checkSpoiler(CarModel chosenSpecifications) throws IllegalConstraintException {
        if (chosenSpecifications.getChosenOptions().containsKey("Spoiler")) {
            return true;
        }
        else {
            throw new IllegalConstraintException("A sport car must have a spoiler.");
        }
    }
}