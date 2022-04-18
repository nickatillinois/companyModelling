package assemAssist.schedulingAlgorithm;

import assemAssist.CarModel;
import assemAssist.CarOrder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Batch extends SchedulingAlgorithm{
    String name = "Specification Batch";
    private String batch;

    @Override
    public CarOrder getNextCarOrder() {
        for (CarOrder order : carOrderList)
            if (Objects.equals(order.getCarModel().getChosenOptionsString(), getBatch())) {
                carOrderList.remove(order);
                return order;
            }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<CarOrder> getProductionSchedule() {
        //TODO
        return null;
    }

    public void selectBatch(String batch){
        this.batch = batch;
    }

    public String getBatch(){
        return batch;
    }
}
