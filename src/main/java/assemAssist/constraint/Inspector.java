package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Class representing a constraint inspector that inspects a car model for
 * being valid according to all the constraints.
 *
 * @author SWOP team 10
 */
public class Inspector {

    /**
     * List of constraints that the inspector validates.
     */
    private static final HashSet<Constraint> constraints = new HashSet<>();

    /**
     * The car model to be inspected.
     */
    private final CarModel carModel;

    /**
     * Creates a new inspector with the given car model.
     * @param carModel The car model to be inspected.
     * @throws IllegalModelException thrown if the car model is null.
     */
    public Inspector(CarModel carModel) throws IllegalConstraintException, IllegalModelException {
        if (carModel == null) {
            throw new IllegalModelException("The car model is null.");
        }
        this.carModel = carModel;
        addStdConstraints();
    }

    /**
     * Adds the standard constraints specified in the assignment to the inspector.
     */
    private static void addStdConstraints() throws IllegalConstraintException {
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
     * @throws IllegalConstraintException thrown if:
     *                               - a constraint is malformed
     * @throws IllegalModelException thrown if:
     *                               - the car model is not in the list of car models
     * @throws OptionAThenOptionBException thrown if:
     *                               - an option implies another option that is not in the list of options
     * @throws OptionThenComponentException thrown if:
     *                               - an option implies a component that is not in the list of components
     * @throws RequiredComponentException thrown if:
     *                               - a component is required but not in the list of components
     */
    public void inspect() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        for (Constraint constraint : constraints) {
            try{
                constraint.isValidCombo(this.carModel);
            }
            catch (Exception e){
                throw e;
            }
        }
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
        if (pair == null) {
            throw new IllegalConstraintException("The list of strings is null");
        }
        if(pair.size() < 2) {
            throw new IllegalConstraintException("The list must have at least 2 elements");
        }
        constraints.add(new IfOptionAThenOptionB(pair));
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
        if (pair == null) {
            throw new IllegalConstraintException("The list of strings is null");
        }
        if(pair.size() != 2) {
            throw new IllegalConstraintException("The list must have 2 elements");
        }
        constraints.add(new IfOptionThenComponent(pair));
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
        if (pair == null) {
            throw new IllegalConstraintException("The list of strings is null");
        }
        if(pair.size() != 2) {
            throw new IllegalConstraintException("The list must have 2 elements");
        }
        constraints.add(new IfOptionAThenOptionB(pair));
        constraints.add(new IfOptionAThenOptionB(new ArrayList<>(Arrays.asList(pair.get(1), pair.get(0)))));
    }


    /**
     * Function that clears the list of constraints.
     */
    public static void reset() throws IllegalConstraintException {
        constraints.clear();
        addStdConstraints();
    }
}
