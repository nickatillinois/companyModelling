package assemAssist.workStation;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a work station on an assembly line.
 */
public abstract class WorkStation {

    /* A CarOrder object representing the current order at this work station. */
    private CarOrder currentOrder;
    /* A list of AssemblyTask objects representing this work station's list of tasks that need to be completed. */
    private List<AssemblyTask> tasks = new ArrayList<>();
    /* A list of mechanics currently working on this work station. */
    private List<String> mechanics;

    /**
     * Creates a work station.
     */
    public WorkStation() { }

    /**
     * Returns the current order that's being worked on in this work station.
     *
     * @return current order
     */
    public CarOrder getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Sets the current order to the new given order.
     *
     * @param newOrder new current order
     */
    public void setCurrentOrder(CarOrder newOrder) {
        currentOrder = newOrder;
        if (newOrder != null)
            newTasks();
    }

    public abstract void newTasks();

    /* Returns the tasks for this work station. */
    public List<AssemblyTask> getTasks() {
        return tasks;
    }

    /**
     * Adds the given task to this work station's task list.
     *
     * @param task the task to be added
     * @throws IllegalArgumentException | task is null
     */
    public void addTask(AssemblyTask task){
        if (task == null) { throw new IllegalArgumentException("The given task cannot be null."); }

        tasks.add(task);
    }

    /**
     * Returns whether the tasks at this work station are all finished.
     *
     * @return true if all tasks are finished
     */
    public boolean isFinished() {
        for (AssemblyTask task : tasks) {
            if (!task.getIsCompleted()) {
                return false;
            }
        }
        return true;
    }

    public void addMechanic(String mechanic) {
        mechanics.add(mechanic);
    }

    public void removeMechanic(String mechanic) {
        mechanics.remove(mechanic);
    }

    public List<String> getMechanics() {
        return mechanics;
    }

    /**
     * Returns a list of names of the tasks at this work station that are not yet completed.
     */
    public List<String> getPendingTasks() {
        List<String> pendingTasks = new ArrayList<>();
        if ( this.isFinished() ) { return pendingTasks; }
        for (AssemblyTask task : tasks) {
            if (!task.getIsCompleted()) { pendingTasks.add(task.getName()); }
        }
        return pendingTasks;
    }

    /**
     * Returns the given task's description.
     *
     * @param task the AssemblyTask of which the description is needed
     * @return the description of the given AssemblyTask
     * @throws IllegalArgumentException | task is null
     */
    public String getInformationFromTask(AssemblyTask task) {
        if (task == null) { throw new IllegalArgumentException("The given task cannot be null."); }

        return task.getTaskDefinition();
    }

    // TODO necessary?
    /**
     * Completes the given task in this work station.
     *
     * @param task the task to be completed
     */
    public void performAssemblyTask(AssemblyTask task) {
        if (task == null) { throw new IllegalArgumentException("The given task cannot be null."); }
        if (!tasks.contains(task)) {
            throw new IllegalArgumentException("The given task needs to be part of this work station."); }
        task.setIsCompleted(true);
    }

    // TODO implement or remove
    public void getTasksAndStatus() {}

}
