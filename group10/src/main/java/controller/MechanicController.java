package controller;

import assemAssist.Mechanic;

import java.util.List;

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
     */
    public String selectTask (String task) {
        return mechanic.selectTask(task);
    }

    /**
     * Finishes the given task and then gives an overview of the pending tasks at the mechanics work station.
     * @param task The name of the task to be finished.
     * @return List of pending tasks at the work station.
     */
    public List<String> finishTask(String task, int timeWorked) {
        return mechanic.finishTask(task, timeWorked);
    }

    public List<String> getCurrentStateAssembly() {
        return mechanic.getCurrentStateAssembly();
    }
}
