package controller;

import assemAssist.ProductionScheduler;
import assemAssist.carOrder.CarOrder;

import java.util.ArrayList;
import java.util.List;

public class ManagerController {
    private ProductionScheduler productionScheduler;
    public ManagerController(ProductionScheduler productionScheduler) {
        this.productionScheduler = productionScheduler;
    }

    public List<List<String>> newLogin() {
        System.out.println(productionScheduler.getCurrentAndFutureStatusAndTaskStatus());
        return productionScheduler.getCurrentAndFutureStatusAndTaskStatus();
    }

    public List<String> confirmAdvance(int currentPhaseTime) {
        List<String> MoveOrNot = productionScheduler.advanceOrders(currentPhaseTime);

        return MoveOrNot;
    }


}
