package assemAssist.schedulingAlgorithm;

import assemAssist.CarOrder;

import java.time.LocalDate;

public abstract class SchedulingAlgorithm {

    public abstract LocalDate addOrderToProductionSchedule(CarOrder order);
}
