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

    /**
     * Creates a work station with a given current order and a given assembly line.
     *
     * @param currentOrder the current order for this work station
     * @param assemblyLine the assembly line this work station is part of
     * @throws IllegalArgumentException | currentOrder is null
     *                                  | assemblyLine is null
     */
    public WorkStation(CarOrder currentOrder, AssemblyLine assemblyLine){
        if (currentOrder == null) { throw new IllegalArgumentException("The given order can't be null."); }
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

    // TODO implement
    public void getTaskAndStatus(){

    }

    // TODO implement
    public void isFinished() {

    }

    // TODO implement
    public void getPendingTasks() {

    }

    // TODO implement
    public void getInformationFromTask(AssemblyTask task) {

    }

    // TODO implement
    public void performAssemblyTask() {

    }

}
