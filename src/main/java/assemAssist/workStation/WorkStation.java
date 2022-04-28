package assemAssist.workStation;
import assemAssist.AssemblyTask;
import assemAssist.CarOrder;
import assemAssist.observer.TaskObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a single work station.
 */
public abstract class WorkStation implements TaskObservable {

    /**
     *  A CarOrder object representing the current order at this work station.
     */
    private CarOrder currentOrder;

    /**
     *  A list of AssemblyTask objects representing this work station's list of tasks that need to be completed.
     */
    private List<AssemblyTask> tasks = new ArrayList<>();

    /**
     * A list of mechanics currently working at this work station.
     */
    private List<String> mechanics = new ArrayList<>();

    /**
     * The name of this work station.
     */
    private final String name;

    /**
     * Creates a work station.
     *
     * @param name the name for this work station.
     */
    public WorkStation(String name) {
        super();
        this.name = name;
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
        if (newOrder != null){
            tasks.clear();
            newTasks();
        }
    }

    /**
     * Returns the name of this work station.
     */
    public String getName() {return this.name;}

    /**
     *  Assigns the car options of the current car order to this work station and creates new tasks to be completed.
     */
    public abstract void newTasks();

    /**
     * Returns the list of tasks for this work station.
     *
     * @return the tasks for this work station
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
    void addTask(AssemblyTask task){
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
     * Adds a given mechanic's name to the list of mechanics working at this workstation.
     *
     * @param mechanic the mechanic that will be added to the list of mechanics for this work station
     * @throws IllegalArgumentException | mechanic is null
     *                                  | mechanic is the empty string
     */
    public void addMechanic(String mechanic) {
        if(mechanic == null){throw new IllegalArgumentException("A mechanic cannot be null.");}
        if(mechanic.length() == 0){throw new IllegalArgumentException("A mechanic cannot be the empty string.");}
        mechanics.add(mechanic);
    }

    /**
     * Removes a given name of a mechanic from the list of mechanics working at this workstation.
     * If the given mechanic is not working at this station, the function will return.
     *
     * @param mechanic the mechanic that needs to be removed from this work station
     * @throws IllegalArgumentException | mechanic is null
     *                                  | mechanic is the empty string
     */
    public void removeMechanic(String mechanic) {
        if(mechanic == null){throw new IllegalArgumentException("A mechanic cannot be null.");}
        if(mechanic.length() == 0){throw new IllegalArgumentException("A mechanic cannot be the empty string.");}
        boolean contains = false;
        for(String mech : getMechanics()){
            if(mech.equalsIgnoreCase(mechanic)){
                contains = true;
                break;
            }
        }
        if(!contains) return;
        mechanics.remove(mechanic);
    }

    /**
     * Returns the list containing the names of the mechanics currently working at this work station.
     *
     * @return the list containing the names of the mechanics currently working at this work station.
     */
    public List<String> getMechanics() {
        return mechanics;
    }

    /**
     * Returns a list of names of tasks at this work station that are not yet completed.
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
     * Returns a list of names of tasks at this work station that are completed.
     * Returns the empty list if no tasks at this work station are finished.
     *
     * @return the list of names of the tasks at this work station that are finished
     */
    public List<String> getFinishedTasks() {
        List<String> finishedTasks = new ArrayList<>();
        for (AssemblyTask task : tasks) {
            if (task.getIsCompleted()) { finishedTasks.add(task.getName()); }
        }
        return finishedTasks;
    }

    /**
     * Returns the given task's description.
     *
     * @param taskName the name of the AssemblyTask of which the description is needed
     * @return the description of the given AssemblyTask
     * @throws IllegalArgumentException | taskName is not a valid name of a task at this work station.
     */
    public String getInformationFromTask(String taskName) throws IllegalArgumentException{
        for (AssemblyTask task : tasks) {
            if (task.getName().equals(taskName)) {
                return task.getTaskDefinition();
            }
        }
        throw new IllegalArgumentException("This is not a task at this work station!");
    }

    /**
     * Completes the given task at this work station.
     *
     * @param taskName the name of the task to be completed
     * @param time the time it took to complete the given task
     * @throws IllegalArgumentException | taskName is not a valid name of a task at this work station.
     */
    public void performAssemblyTask(String taskName,int time) throws IllegalArgumentException{
        for (AssemblyTask task : tasks) {
            if (task.getName().equals(taskName)) {
                task.setIsCompleted(true);
                notifyObservers(time);
                return;
            }
        }
        throw new IllegalArgumentException("This is not a task at this work station!");
    }

    /**
     * Returns a string containing all the different tasks at this work station and their current status.
     *
     * @return a string with all the tasks at this work station and their current status
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkStation that = (WorkStation) o;

        if (currentOrder != null ? !currentOrder.equals(that.currentOrder) : that.currentOrder != null) return false;
        if (!tasks.equals(that.tasks)) return false;
        if (!mechanics.equals(that.mechanics)) return false;
        return name.equals(that.name);
    }

}