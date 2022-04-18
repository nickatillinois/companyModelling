package assemAssist.schedulingAlgorithm;

import assemAssist.CarOrder;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class SchedulingAlgorithm {
    List<CarOrder> carOrderList = new ArrayList<>();

    public abstract List<String> posibleBatch();

    public abstract void selectBatch(String selectBatch);

    public abstract CarOrder getNextCarOrder();

    public abstract String getName();

    public abstract List<CarOrder> getProductionSchedule();

    public  void addOrderToProductionSchedule(CarOrder order) throws IllegalConstraintException, IllegalModelException {
        if (order.isValidCarModel())
            carOrderList.add(order);
        else
            throw new IllegalConstraintException("This car order has not a valid car model!");
    }
}
