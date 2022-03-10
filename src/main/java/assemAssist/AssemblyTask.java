package assemAssist;

import assemAssist.carOrder.Option;
import assemAssist.workStation.WorkStation;

public class AssemblyTask {

    private boolean isCompleted;
    private final WorkStation workStation;
    private final Option component;
    private final String taskDefinition;

    public AssemblyTask(WorkStation workStation, Option component, String taskDefinition) {
        this.isCompleted = false;
        this.workStation = workStation;
        this.component = component;
        this.taskDefinition = taskDefinition;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Option getComponent() {
        return component;
    }

    public WorkStation getWorkStation() {
        return workStation;
    }

    public String getTaskDefinition(){
        return taskDefinition;
    }

}
