package assemAssist.workStation;
import assemAssist.AssemblyLine;
import assemAssist.AssemblyTask;
import assemAssist.carOrder.*;
import java.util.List;

public abstract class WorkStation {
    private CarOrder currentOrder;
    private AssemblyLine assemblyLine;
    private List<String> tasks;

    public WorkStation(CarOrder currentOrder, AssemblyLine assemblyLine){
        this.currentOrder = currentOrder;
        this.assemblyLine = assemblyLine;
    }

    public CarOrder getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(CarOrder newOrder) {
        this.currentOrder = newOrder;
    }

    public AssemblyLine getAssemblyLine() {
        return assemblyLine;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void addTask(AssemblyTask task){
        tasks.add(task.getTaskDefinition());
    }

    // TODO implement
    public void getTaskAndStatus(){

    }

    // TODO ipv lijst van strings beter lijst van assemblytasks(?)
    public void isFinished() {

    }

    public void getPendingTasks() {

    }

    public void getInformationFromTask(AssemblyTask task) {

    }

    public void performAssemblyTask() {

    }

}
