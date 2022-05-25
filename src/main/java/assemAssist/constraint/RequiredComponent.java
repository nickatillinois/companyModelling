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
     */
    @Override
    protected void isValidCombo(CarModel chosenSpecifications) throws IllegalArgumentException, RequiredComponentException {
        if(!
                (chosenSpecifications.getChosenOptions().containsKey("body") &&
                        chosenSpecifications.getChosenOptions().containsKey("color") &&
                        chosenSpecifications.getChosenOptions().containsKey("engine") &&
                        chosenSpecifications.getChosenOptions().containsKey("gearbox") &&
                        chosenSpecifications.getChosenOptions().containsKey("seats") &&
                        chosenSpecifications.getChosenOptions().containsKey("wheels")))
        {
        String warning = "You are missing an essential component: body, color, engine, gearbox, seats or/and wheels.\nPlease choose all of them because we can't build a car without them.";
        String[] warningLines = warning.split("\n");
            String boxMessage = "\n" +
                    "|" +
                    "-".repeat(warningLines[0].length() / 4) +
                    "!" +
                    "-".repeat(warningLines[0].length() / 4) +
                    "|\n" +
                    warning +
                    "\n" +
                    "|" +
                    "-".repeat(warningLines[0].length() / 4) +
                    "!" +
                    "-".repeat(warningLines[0].length() / 4) +
                    "|\n";
        throw new RequiredComponentException(boxMessage);
        }
        return;

    }

    @Override
    protected void reset() {
        //do nothing
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return getClass() == obj.getClass();
    }
}
