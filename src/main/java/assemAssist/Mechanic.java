package assemAssist;

import assemAssist.workStation.WorkStation;

import java.util.List;

public class Mechanic {

    private final ProductionScheduler productionScheduler;
    private final AssemblyLine assemblyLine;
    private WorkStation workStation;

    public Mechanic(ProductionScheduler productionScheduler, AssemblyLine assemblyLine) {
        this.productionScheduler = productionScheduler;
        this.assemblyLine = assemblyLine;
    }

    public List<String> getAllWorkStations() {
        return assemblyLine.getWorkStationNames();
    }

    public List<String> selectWorkStation(String workStationName) {
        WorkStation workStation = assemblyLine.findWorkStation(workStationName);
        this.workStation = workStation;
        return workStation.getPendingTasks();
    }

    public String selectTask(String taskName) {
        return workStation.getInformationFromTask(taskName);
    }

    public List<String> finishTask(String taskName, int timeWorked) {
        workStation.performAssemblyTask(taskName,timeWorked);
        productionScheduler.advanceOrders(timeWorked);
        return workStation.getPendingTasks();
    }

    public List<String> getCurrentStateAssembly() {
        return assemblyLine.getCurrentStateString();
    }
}
