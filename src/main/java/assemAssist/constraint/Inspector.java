package assemAssist.constraint;

import assemAssist.CarModel;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;

import java.util.ArrayList;

public class Inspector {

    /**
     * List of constraints that the inspector validates.
     */
    private static ArrayList<Constraint> constraints;
    private CarModel carModel;

    /**
     * Creates a new inspector.
     */
    public Inspector(CarModel carModel) throws IllegalConstraintException {
        constraints = new ArrayList<Constraint>();
        this.carModel = carModel;
        addConstraints();
    }
    private void addConstraints() {
        constraints.add(new Model());
        constraints.add(new RequiredComponent());
        constraints.add(new EngineAirco());
        constraints.add(new SportBody());
    }
    public boolean inspect() throws IllegalConstraintException, IllegalModelException {
        for (Constraint constraint : constraints) {
            if(!constraint.isValidCombo(this.carModel)) {
                return false;
            }
        }
        return true;
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
