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
     * @return True if the chosen specifications are in line with the constraints, false otherwise.
     */
    protected abstract boolean isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, IllegalConstraintException, IllegalModelException, RequiredComponentException, OptionAThenOptionBException, OptionThenComponentException;
    protected abstract void reset();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Constraint other = (Constraint) obj;
        return other.equals(this);
    }
}
