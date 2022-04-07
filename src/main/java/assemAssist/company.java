package assemAssist;

import assemAssist.carOrder.CarOrder;

import java.util.ArrayList;

public class company {
    public company(){
        ArrayList<CarOrder> completedOrders = new ArrayList<CarOrder>();
        ProductionScheduler productionScheduler = new ProductionScheduler(new AssemblyLine());
    }
}
