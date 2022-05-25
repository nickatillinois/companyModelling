package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.*;


/**
 * Class representing the constraint that certain components must be chosen.
 *
 * @author SWOP team 10
 */
public class RequiredComponent extends Constraint {

    /**
     * Constructor for the class.
     */
    protected RequiredComponent() {
        super();
    }


    /**
     * Method that checks if the chosen specifications contain the required components.
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
        throw new RequiredComponentException(putInBox(warning));
        }

    }


    /**
     * Method that compares 2 RequiredComponent constraints.
     * @param obj The constraint to compare with.
     * @return True if the constraints are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return getClass() == obj.getClass();
    }

    private String putInBox(String warning){
        String[] warningLines = warning.split("\n");
        return "\n" +
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
    }
}
