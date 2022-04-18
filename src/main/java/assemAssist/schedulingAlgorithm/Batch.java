package assemAssist.schedulingAlgorithm;

import assemAssist.CarModel;
import assemAssist.CarOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Batch extends SchedulingAlgorithm{
    String name = "Specification Batch";
    private String batch;
    private List<CarOrder> productionSchedule;
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
        return productionSchedule;
    }

    public void selectBatch(String batch){
        this.batch = batch;
        productionSchedule  =new ArrayList<>();
        List<CarOrder> fifo = new ArrayList<>();
        for(CarOrder order: carOrderList) {
            if (Objects.equals(order.getCarModel().getChosenOptionsString(), getBatch()))
                productionSchedule.add(order);
            else
                fifo.add(order);
        }
        productionSchedule.addAll(fifo);

    }

    public String getBatch(){
        return batch;
    }

    public  List<String> posibleBatch(){
        List<String> batchs = new ArrayList<>();
        for (CarOrder order: carOrderList) {
            String specification = order.getCarModel().getChosenOptionsString();
            int counter = 1;
            for (CarOrder order1: carOrderList){
                if (order != order1)
                    if(!batchs.contains(specification))
                        if(Objects.equals(specification, order1.getCarModel().getChosenOptionsString()))
                            counter++;
                if(counter >= 3 ){
                    batchs.add(specification);
                    break;
                }

            }
        }

        return null;
    }
}
