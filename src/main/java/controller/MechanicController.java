package controller;

import assemAssist.AssemblyLine;
import assemAssist.workStation.WorkStation;

import java.util.List;

public class MechanicController {

    private AssemblyLine assemblyLine;
    private WorkStation workStation;

    public MechanicController(AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
    }

    /**
     * Returns the names of all the work stations that are in the assembly line.
     * @return List of the names of the work stations.
     */
    public List<String> getAllWorkStations() {
        return assemblyLine.getWorkStationNames();
    }

    /**
     * Sets the work station at which the mechanic works to the given work station and
     * returns the pending tasks at this work post.
     * @param workStation The name of the work station the mechanic wants to work at.
     * @return List of pending tasks at the given work station
     */
    public List<String> selectWorkStation(String workStation) {
        this.workStation = assemblyLine.findWorkStation(workStation);
        return this.workStation.getPendingTasks();
    }

    /**
     * Selects a task and returns the description of this task.
     * @param task The name of the task to give the description of.
     * @return Description of the task.
     */
    public String selectTask (String task) {
        return workStation.getInformationFromTask(task);
    }

    /**
     * Finishes the given task and then gives an overview of the pending tasks at the mechanics work station.
     * @param task The name of the task to be finished.
     * @return List of pending tasks at the work station.
     */
    public List<String> finishTask(String task) {
        workStation.performAssemblyTask(task);
        return workStation.getPendingTasks();
    }
}
