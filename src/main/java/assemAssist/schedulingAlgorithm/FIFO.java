package assemAssist.schedulingAlgorithm;

import assemAssist.CarOrder;

import java.time.LocalDate;
import java.util.List;

public class FIFO extends SchedulingAlgorithm {
    String name = "FIFO";

    @Override
    public List<String> posibleBatch() {
        return null;
    }

    @Override
    public void selectBatch(String selectBatch) {
        throw new  IllegalCallerException("This algoritme contains no batches.");
    }

    @Override
    public CarOrder getNextCarOrder() {
        if (carOrderList.isEmpty())
                return null;
        return carOrderList.remove(0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<CarOrder> getProductionSchedule() {
        return carOrderList;
    }


}
