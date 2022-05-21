package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.*;



public class RequiredComponent extends Constraint {

    /**
     * Constructor for the class.
     */
    public RequiredComponent() {
        super();
    }


    /**
     * method that checks if the chosen specifications are in line with the constraints
     *
     * @param chosenSpecifications The chosen specifications
     * @throws IllegalArgumentException   | chosenSpecifications = null
     * @throws RequiredComponentException   | IfRequiredComponent is not satisfied
     *
     * @return True if the chosen specifications are in line with the constraints, false otherwise.
     */
    @Override
    protected void isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, RequiredComponentException {
        if(
                chosenSpecifications.getChosenOptions().containsKey("Body") &&
                        chosenSpecifications.getChosenOptions().containsKey("Color") &&
                        chosenSpecifications.getChosenOptions().containsKey("Engine") &&
                        chosenSpecifications.getChosenOptions().containsKey("Gearbox") &&
                        chosenSpecifications.getChosenOptions().containsKey("Seats") &&
                        chosenSpecifications.getChosenOptions().containsKey("Wheels")) {
            return;
        }
        throw new RequiredComponentException("You are missing an essential component: body, color, engine, gearbox, seats or/and wheels.");
    }

    @Override
    protected void reset() {
        //do nothing
    }

    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass())
            return false;
        RequiredComponent other = (RequiredComponent) obj;
        return true;
    }
}
