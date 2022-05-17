package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a constraint inspector that inspects a car model for
 * constraints.
 *
 * @author SWOP team 10
 */
public class Inspector {

    /**
     * List of constraints that the inspector validates.
     */
    private static ArrayList<Constraint> constraints;

    /**
     * The car model to be inspected.
     */
    private final CarModel carModel;

    /**
     * Creates a new inspector with the given car model.
     * @param carModel The car model to be inspected.
     *                 The inspector will not be able to inspect the car model if it is null.
     *                 The inspector will not be able to inspect the car model if it is not a valid car model.
     * @throws IllegalModelException thrown if the car model is null.
     */
    public Inspector(CarModel carModel) throws IllegalConstraintException, IllegalModelException {
        if (carModel == null) {
            throw new IllegalModelException("The car model is null.");
        }
        constraints = new ArrayList<>();
        this.carModel = carModel;
        addConstraints();
    }

    /**
     * Adds constraints to the inspector.
     */
    private void addConstraints() throws IllegalConstraintException {
        constraints.add(new Model());
        constraints.add(new RequiredComponent());
        IfOptionAThenOptionB standardConstraint1 = new IfOptionAThenOptionB(new ArrayList<>(Arrays.asList("Sport", "V6", "V8")));
        IfOptionAThenOptionB standardConstraint2 = new IfOptionAThenOptionB(new ArrayList<>(Arrays.asList("V8", "Manual")));
        IfOptionThenComponent standardConstraint3 = new IfOptionThenComponent(new ArrayList<>(Arrays.asList("Sport", "Spoiler")));
        constraints.add(standardConstraint1);
        constraints.add(standardConstraint2);
        constraints.add(standardConstraint3);

    }

    /**
     * Inspects the car model for constraints.
     * @return true if the car model is valid, false otherwise.
     */
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
     * For example, if the first element is "sedan", and the second element is "Green",
     * then a sedan body implies a Green color and vice versa.
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

    public void reset() throws IllegalConstraintException {
        for (Constraint constraint : constraints) {
            constraint.reset();
        }
        constraints.clear();
        addConstraints();
    }
}
