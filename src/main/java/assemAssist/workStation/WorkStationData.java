package assemAssist.workStation;

import assemAssist.CarOrderData;

import java.util.Map;

public class WorkStationData {

    private final String NAME;
    private final Map<String,Boolean> TASKSANDSTATUS;
    private final CarOrderData CURRENTORDER;

    public WorkStationData(String name, Map taskandstatus, CarOrderData currentorder) {
        NAME = name;
        TASKSANDSTATUS = taskandstatus;
        CURRENTORDER = currentorder;
    }

    public String getNAME(){
        return this.NAME;
    }

    public Map getTASKSANDSTATUS() {
        return this.TASKSANDSTATUS;
    }

    public CarOrderData getCURRENTORDER() {
        return this.CURRENTORDER;
    };
}
