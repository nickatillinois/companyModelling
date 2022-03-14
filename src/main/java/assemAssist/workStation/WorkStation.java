package assemAssist.workStation;
import assemAssist.AssemblyLine;
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
    /* An AssemblyLine object representing the assembly line this work station is on. */
    private AssemblyLine assemblyLine;
    /* A list of AssemblyTask objects representing this work station's list of tasks that need to be completed. */
    private List<AssemblyTask> tasks = new ArrayList<>();
    /* A list of mechanics currently working on this work station. */
    private List<String> mechanics;

    /**
     * Creates a work station with a given current order and a given assembly line.
     *
     * @param currentOrder the current order for this work station
     * @param assemblyLine the assembly line this work station is part of
     * @throws IllegalArgumentException | assemblyLine is null
     */
    public WorkStation(CarOrder currentOrder, AssemblyLine assemblyLine){
        if (assemblyLine == null) { throw new IllegalArgumentException("The given assembly line can't be null."); }

        this.currentOrder = currentOrder;
        this.assemblyLine = assemblyLine;
    }

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
     * @throws IllegalArgumentException | newOrder is null
     */
    public void setCurrentOrder(CarOrder newOrder) {
        if (newOrder == null) { throw new IllegalArgumentException("The given order can't be null."); }

        this.currentOrder = newOrder;
    }

    /**
     * Returns the assembly line this work station is a part of.
     *
     * @return current assembly line
     */
    public AssemblyLine getAssemblyLine() {
        return assemblyLine;
    }

    /**
     * Sets the current assembly line to the new given assembly line.
     *
     * @param line new current assembly line
     * @throws IllegalArgumentException | line is null
     */
    public void setAssemblyLine(AssemblyLine line) {
        if (line == null) { throw new IllegalArgumentException("The given line can't be null."); }

        this.assemblyLine = line;
    }

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
            if (!task.getIsCompleted()) { return false; }
        }
        return true;
    }

    public void addMechanic(String mech) {
        mechanics.add(mech);
    }

    public void removeMechanic(String mech) {
        mechanics.remove(mech);
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
