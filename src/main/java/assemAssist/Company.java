package assemAssist;

import java.util.ArrayList;

public class Company {
    private final ProductionScheduler productionScheduler;
    private Catalog catalog;

    public Company(ProductionScheduler productionScheduler){
        ArrayList<CarOrder> completedOrders = new ArrayList<CarOrder>();
        this.productionScheduler = productionScheduler;
        this.catalog = new Catalog();
    }

    public ProductionScheduler getProductionScheduler() {
        return productionScheduler;
    }
}
