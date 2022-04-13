package assemAssist.schedulingAlgorithm;

import assemAssist.CarOrder;

import java.time.LocalDate;

public abstract class SchedulingAlgorithm {

    protected abstract LocalDate addOrderToProductionSchedule(CarOrder order);
}
