package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.*;

/**
 * Class representing a restriction on a CarModel.
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
     * @throws IllegalConstraintException | a given constraint is not a correct constraint
     * @throws OptionAThenOptionBException | IfOptionAThenOptionB is not satisfied
     * @throws OptionThenComponentException | IfOptionThenComponent is not satisfied
     * @throws RequiredComponentException   | IfRequiredComponent is not satisfied
     * @throws IllegalModelException | Model is not satisfied
     *
     */
    protected abstract void isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, IllegalConstraintException, IllegalModelException, RequiredComponentException, OptionAThenOptionBException, OptionThenComponentException;
    protected abstract void reset();


}
