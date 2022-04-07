package assemAssist;

import assemAssist.carOrder.CarOrder;

import java.util.ArrayList;

public class Company {
    private final ProductionScheduler productionScheduler;
    public Company(ProductionScheduler productionScheduler){
        ArrayList<CarOrder> completedOrders = new ArrayList<CarOrder>();
        this.productionScheduler = productionScheduler;
    }

    public ProductionScheduler getProductionScheduler() {
        return productionScheduler;
    }
}
