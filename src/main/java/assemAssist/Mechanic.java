package assemAssist;

import assemAssist.workStation.WorkStation;

import java.util.List;

public class Mechanic {

    /**
     * The assembly line the mechanic is working at.
     */
    private final AssemblyLine assemblyLine;

    /**
     * Once the mechanic selects a work station, it is kept here.
     */
    private WorkStation workStation;

    public Mechanic(AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
    }

    /**
     * @return The assembly line the mechanic works at.
     */
    public AssemblyLine getAssemblyLine() {
        return assemblyLine;
    }

    /**
     * @return The work station the mechanic is currently working at.
     */
    public WorkStation getWorkStation() {
        return workStation;
    }

    /**
     * Returns the names of all the work stations at the assemblyline the mechanic works at.
     * @return List of names of work stations.
     */
    public List<String> getAllWorkStations() {
        return assemblyLine.getWorkStationNames();
    }

    /**
     * The mechanic selects a workstation to work at. It returns the tasks that are still pending at this workpost
     * @param workStationName The name of the workstation the mechanic wants to work at.
     * @return List of the names of the pending tasks at the selected workstation.
     */
    public List<String> selectWorkStation(String workStationName) throws IllegalArgumentException {
        if (workStationName == null)
            throw new IllegalArgumentException("The name of the workstation cannot be null.");
        WorkStation workStation = assemblyLine.findWorkStation(workStationName);
        this.workStation = workStation;
        return workStation.getPendingTasks();
    }

    /**
     * The mechanic selects a task they want to perform. The method returns the description of the selected task.
     * @param taskName The name of the selected task.
     * @return The description of the selected task.
     */
    public String selectTask(String taskName) {
        return workStation.getInformationFromTask(taskName);
    }

    /**
     * The mechanic indicates they are finished performing a certain task in a given amount of time. An updated overview of the pending tasks is returned.
     * @param taskName The name of the finished task
     * @param timeWorked Time spent performing the finished task
     * @return List containing the remaining pending tasks at the workstation the mechanic is working at.
     */
    public List<String> finishTask(String taskName, int timeWorked) {
        workStation.performAssemblyTask(taskName,timeWorked);
        return workStation.getPendingTasks();
    }

    /**
     * Gives an overview of the different workstations at the assembly line the mechanic works at as well as the tasks
     * at these stations and their status.
     * @return List containing string of the form "WorkStationName ; TaskName1: TaskStatus1, ..., TaskNameN, TaskStatusN".
     */
    public List<String> getCurrentStateAssembly() {
        return assemblyLine.getCurrentStateString();
    }
}
