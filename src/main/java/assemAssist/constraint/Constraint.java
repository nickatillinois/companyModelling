package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.*;

/**
 * Class representing a restriction.
 * @author SWOP team 10
 */
public abstract class Constraint {

    /**
     * Creates a new restriction.
     */
    public Constraint() {
    }

    /**
     * method that checks if the chosen specifications are in line with the constraints
     *
     * @param chosenSpecifications The chosen specifications
     * @throws IllegalArgumentException   | chosenSpecifications = null
     * @throws IllegalConstraintException | the chosen specifications are not in line with the constraints
     * @return True if the chosen specifications are in line with the constraints, false otherwise.
     */
    protected abstract boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, IllegalConstraintException, IllegalModelException, RequiredComponentException, OptionAThenOptionBException, OptionThenComponentException;

}
