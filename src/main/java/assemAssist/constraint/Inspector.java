package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.*;

import java.util.ArrayList;

public class Inspector {

    /**
     * List of constraints that the inspector validates.
     */
    private static ArrayList<Constraint> constraints;
    private static boolean initialized = false;
    private final CarModel carModel;

    /**
     * Creates a new inspector.
     */
    public Inspector(CarModel carModel) throws IllegalConstraintException {
        constraints = new ArrayList<>();
        this.carModel = carModel;
        if (!initialized) {
            addConstraints();
            initialized = true;
        }
    }
    private void addConstraints() throws IllegalConstraintException {
        constraints.add(new Model());
        constraints.add(new RequiredComponent());
        constraints.add(new IfOptionAThenOptionB());
        constraints.add(new IfOptionThenComponent());
    }
    public boolean inspect() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        for (Constraint constraint : constraints) {
            if(!constraint.isValidCombo(this.carModel)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Function that accepts a list of options strings, so that the first element implies one of the latter elements.
     * For example, if the first element is "Green", and the two other elements "V6" and "V8",
     * then Green implies V6 or V8.
     * @param pair The list of options that are implied by the first element in the list.
     * @throws IllegalConstraintException thrown if:
     *                                   - the list has less than 2 elements
     *                                   - One of the strings is null
     *                                   - Some strings are duplicated
     *                                   - The list is already in the list of constraints
     */
    public void addOptionAThenOptionBPair(ArrayList<String> pair) throws IllegalConstraintException {
        for (Constraint constraint : constraints) {
            if (constraint instanceof IfOptionAThenOptionB) {
                ((IfOptionAThenOptionB) constraint).addOptionAThenOptionBPair(pair);
            }
        }
    }

    /**
     * Function that accepts a list of 1 option string followed by 1 component string, so that the first element implies the second element.
     * For example, if the first element is "Green", and the second element is "wheels",
     * then a Green color implies that the car has wheels.
     * @param pair A list of 1 option string followed by 1 component string
     * @throws IllegalConstraintException thrown if:
     *                                   - the list doesn't have 2 elements
     *                                   - One of the strings is null
     *                                   - Some strings are duplicated
     *                                   - The list is already in the list of constraints
     */
    public void addOptionThenComponentPair(ArrayList<String> pair) throws IllegalConstraintException {
        for (Constraint constraint : constraints) {
            if (constraint instanceof IfOptionThenComponent) {
                ((IfOptionThenComponent) constraint).addOptionThenComponentPair(pair);
            }
        }
    }

    /**
     * Function that accepts a list of 1 option string followed by 1 option string, so that the first element implies
     * the second element and vice versa.
     * For example, if the first element is "wheels", and the second element is "Green",
     * then a wheels implies a Green color and vice versa.
     * @param pair A list of two option strings
     * @throws IllegalConstraintException thrown if:
     *                                   - the list doesn't have 2 elements
     *                                   - One of the strings is null
     *                                   - Some strings are duplicated
     *                                   - The list is already in the list of constraints
     */
    public void addOptionAAndOptionBPair(ArrayList<String> pair) throws IllegalConstraintException {
        for (Constraint constraint : constraints) {
            if (constraint instanceof IfOptionAThenOptionB) {
                ((IfOptionAThenOptionB) constraint).addOptionAAndOptionBPair(pair);
            }
        }
    }





    /**
     * Adds a constraint to the list of constraints.
     *
     * @param constraint The constraint to be added to the list of constraints
     */
    public void addConstraint(Constraint constraint) {
        if (constraint == null) throw new IllegalArgumentException("A constraint cannot be null");
        constraints.add(constraint);
    }
}
