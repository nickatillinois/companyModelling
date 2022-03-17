package controller;

import assemAssist.ProductionScheduler;

import java.util.List;

public class ManagerController {
    private ProductionScheduler productionScheduler;
    public ManagerController(ProductionScheduler productionScheduler) {
        this.productionScheduler = productionScheduler;
    }

    public List newLogin() {
        return productionScheduler.getCurrentAndFutureStatusAndTaskStatus();
    }

    public List<String> confirmAdvance(int currentPhaseTime) {
        List<String> MoveOrNot = productionScheduler.advanceOrders(currentPhaseTime);

        return MoveOrNot;
    }


}
