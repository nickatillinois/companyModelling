package assemAssist.workStation;
import assemAssist.AssemblyTask;
import assemAssist.CarOrder;
import assemAssist.observer.TaskObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private final List<AssemblyTask> tasks = new ArrayList<>();

    /**
     * The name of this work station.
     */
    private final String name;

    /**
     * Creates a work station.
     *
     * @param name the name for this work station.
     * @throws IllegalArgumentException | name == null
     *                                  | name.isEmpty()
     */
    public WorkStation(String name) {
        super();
        if (name == null) throw new IllegalArgumentException("The given name cannot be null.");
        if (name.isEmpty()) throw new IllegalArgumentException("The given name cannot be empty.");
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
        tasks.clear();
        if (newOrder != null){
            newTasks();
        }
    }

    /**
     * Returns the name of this work station.
     *
     * @return the name of this work station
     */
    public String getName() { return name; }

    /**
     *  Assigns the car options of the current car order to this work station and creates new tasks to be completed.
     */
    abstract void newTasks();

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
     * Returns a list of names of tasks at this work station that are not yet completed.
     * Returns the empty list if all tasks at this work station are finished.
     *
     * @return the list of names of the tasks at this work station that are not yet completed.
     */
    public List<String> getPendingTasks() {
        List<String> pendingTasks = new ArrayList<>();
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
        if (time <= 0) throw new IllegalArgumentException("The time spent on this task must be positive.");
        for (AssemblyTask task : tasks) {
            if (task.getName().equals(taskName)) {
                task.setCompleted();
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
        StringBuilder s = new StringBuilder();
        List<AssemblyTask> assemblyTasks = getTasks();
        for(AssemblyTask assemblyTask : assemblyTasks){
            boolean status = assemblyTask.getIsCompleted();
            s.append(assemblyTask.getName()).append(": ");
            if (status)
                s.append("done, ");
            else
                s.append("pending, ");
        }
        int j = s.length();
        if (j >= 2)
            j -= 2;
        return s.substring(0,j);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkStation that = (WorkStation) o;

        if (!Objects.equals(currentOrder, that.currentOrder)) return false;
        if (!tasks.equals(that.tasks)) return false;
        return name.equals(that.name);
    }

}