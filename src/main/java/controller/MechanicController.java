package controller;

import assemAssist.Mechanic;

import java.util.List;

/**
 * This class is the controller for the mechanic.
 * It is responsible for the communication between the mechanic actor and the
 * Mechanic class.
 *
 * @author  Team 10
 */
public class MechanicController {

    private Mechanic mechanic;

    public MechanicController(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    /**
     * Returns the names of all the work stations that are in the assembly line.
     * @return List of the names of the work stations.
     */
    public List<String> getAllWorkStations() {
        return mechanic.getAllWorkStations();
    }

    /**
     * Sets the work station at which the mechanic works to the given work station and
     * returns the pending tasks at this work post.
     * @param workStation The name of the work station the mechanic wants to work at.
     * @return List of pending tasks at the given work station
     */
    public List<String> selectWorkStation(String workStation) {
        return mechanic.selectWorkStation(workStation);
    }

    /**
     * Selects a task and returns the description of this task.
     * @param task The name of the task to give the description of.
     * @return Description of the task.
     * @throws IllegalArgumentException | The taskName is null or not a valid taskName.
     */
    public String selectTask (String task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("Name of the task cannot be null.");
        }
        try {
            return mechanic.selectTask(task);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Finishes the given task and then gives an overview of the pending tasks at the mechanics work station.
     * @param task The name of the task to be finished.
     * @return List of pending tasks at the work station.
     * @throws IllegalArgumentException | The taskName is not a valid task or null or timeWorked is negative.
     */
    public List<String> finishTask(String task, int timeWorked) throws IllegalArgumentException {
        try {
            return mechanic.finishTask(task, timeWorked);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Gives an overview of the AssemblyLine and the tasks that are still pending
     * @return List of WorkStations and their pending tasks.
     */
    public List<String> getCurrentStateAssembly() {
        return mechanic.getCurrentStateAssembly();
    }
}
