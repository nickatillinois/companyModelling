package assemAssist;
import assemAssist.carOrder.Option;
import assemAssist.workStation.WorkStation;

/** Class representing an assembly task. */
public class AssemblyTask {

    /* A boolean representing whether this assembly task is completed. */
    private boolean isCompleted;
    /* A WorkStation object representing the work station this task is to be performed at. */
    private final WorkStation workStation;
    /* An Option object representing the object of the car this task is working on. */
    private final Option component;
    /* A string containing this task's description. */
    private final String taskDefinition;

    /**
     * Creates an assembly task at the given work station for a given component, with the given task definition.
     *
     * @param workStation the work station this assembly task needs to be performed at
     * @param component the component this assembly task will work on
     * @param taskDefinition the description for this assembly task
     * @throws IllegalArgumentException | workStation is null
     *                                  | component is null
     *                                  | taskDefinition is null
     */
    public AssemblyTask(WorkStation workStation, Option component, String taskDefinition) {
        if (workStation == null) { throw new IllegalArgumentException("The work station for this task cannot be null."); }
        if (component == null) { throw new IllegalArgumentException("The component for this task cannot be null."); }
        if (taskDefinition == null) { throw new IllegalArgumentException("The definition for this task cannot be null."); }

        this.isCompleted = false;
        this.workStation = workStation;
        this.component = component;
        this.taskDefinition = taskDefinition;
    }

    /**
     * Returns whether this task is completed or not.
     *
     * @return if this task is complete
     */
    public boolean getIsCompleted() {
        return isCompleted;
    }

    /**
     * Sets this task's status to complete or not complete.
     *
     * @param isCompleted whether this task needs to be complete or not
     */
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /* Returns the component this assembly task works on. */
    public Option getComponent() {
        return component;
    }

    /* Returns the work station this assembly task is performed at. */
    public WorkStation getWorkStation() {
        return workStation;
    }

    /* Returns the task definition belonging to this task. */
    public String getTaskDefinition(){
        return taskDefinition;
    }

}
