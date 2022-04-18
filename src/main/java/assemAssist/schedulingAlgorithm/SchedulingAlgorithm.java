package assemAssist.schedulingAlgorithm;

import assemAssist.CarOrder;
import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;

import java.util.ArrayList;
import java.util.List;

public abstract class SchedulingAlgorithm {
    List<CarOrder> carOrderList = new ArrayList<>();

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
