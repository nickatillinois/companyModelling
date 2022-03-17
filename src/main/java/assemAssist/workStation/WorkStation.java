package assemAssist.workStation;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.*;
import purecollections.PList;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a work station
 */
public abstract class WorkStation {

    /**
     *  A CarOrder object representing the current order at this work station.
     */
    private CarOrder currentOrder;

    /**
     *  A list of AssemblyTask objects representing this work station's list of tasks that need to be completed.
     */
    private List<AssemblyTask> tasks = new ArrayList<>();

    /**
    * An immutable list of mechanics currently working on this work station.
    */
    private PList<String> mechanics;

    /**
     * Creates a work station.
     */
    public WorkStation() {
        super();
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
     * Sets the current order to the given order.
     *
     * @param newOrder new current order
     */
    public void setCurrentOrder(CarOrder newOrder) {
        this.currentOrder = newOrder;
        if (newOrder != null)
            newTasks();
    }

    /**
     *  Assigns the car options of the current car order to this work station
     */
    public abstract void newTasks();

    /**
     * Returns the tasks for this work station.
     *
     * @return The tasks for this work station
     */
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

    /**
     * Adds a given name of a mechanic to the list of mechanics working at this workstation.
     * @param mechanic
     * @throws IllegalArgumentException | mechanic is null
     *                                  | mechanic is the empty string
     */
    public void addMechanic(String mechanic) {
        if(mechanic == null){throw new IllegalArgumentException("A mechanic cannot be null.");}
        if(mechanic.length() == 0){throw new IllegalArgumentException("A mechanic cannot be the empty string.");}
        mechanics.plus(mechanic);
    }

    /**
     * Removes a given name of a mechanic from the list of mechanics working at this workstation.
     * If the given mechanic is not working at this station, the function will return.
     * @param mechanic the mechanic that needs to be removed from this work station
     * @throws IllegalArgumentException | mechanic is null
     *                                  | mechanic is the empty string
     */
    public void removeMechanic(String mechanic) {
        if(mechanic == null){throw new IllegalArgumentException("A mechanic cannot be null.");}
        if(mechanic.length() == 0){throw new IllegalArgumentException("A mechanic cannot be the empty string.");}
        boolean contains = false;
        for(String mechanieker: getMechanics()){
            if(mechanieker.equalsIgnoreCase(mechanic)){
                contains = true;
                break;
            }
        }
        if(!contains) return;
        mechanics.minus(mechanic);
    }

    /**
     * Returns the PList of containing the names of the mechanics currently working at this work station.
     *
     * @return the PList of containing the names of the mechanics currently working at this work station.
     */
    public PList<String> getMechanics() {
        return mechanics;
    }

    /**
     * Returns a list of names of the tasks at this work station that are not yet completed.
     * Returns the empty list if all tasks at this work station are finished.
     *
     * @return the list of names of the tasks at this work station that are not yet completed.
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
    public String getTasksAndStatus() {
        String s = "";
        List<AssemblyTask> assemblyTasks = getTasks();
        for(AssemblyTask assemblyTask : assemblyTasks){
            boolean status = assemblyTask.getIsCompleted();
            s += assemblyTask.getName() + ": ";
            if (status)
                s += "done, ";
            else
                s += "pending, ";

        }
        int j = s.length() - 2;
        return s.substring(0,j);
    }

}
