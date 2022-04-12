package assemAssist.restriction;

import assemAssist.CarModel;
import assemAssist.exceptions.IllegalConstraintException;

/**
 * Class representing the restriction that a large engine and large airco do not fit in the hood.
 * @author SWOP team 10
 */
public class EngineAirco extends Constraint {

    public EngineAirco() {
        super();
    }
    @Override
    protected boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, IllegalConstraintException{
        if(chosenSpecifications.getChosenOptions().get("Engine").equalsIgnoreCase("V8") && chosenSpecifications.getChosenOptions().get("Airco").equalsIgnoreCase("Automatic")){
            throw new IllegalConstraintException("Engine and Airco combination is not allowed.");
        }
        return true;
    }
}


