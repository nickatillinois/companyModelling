package controller;

import assemAssist.ProductionScheduler;

import java.util.ArrayList;
import java.util.List;

public class ManagerController {

    private final ProductionScheduler productionScheduler;

    public ManagerController(ProductionScheduler productionScheduler) {
        this.productionScheduler = productionScheduler;
    }

    /* public List<List<String>> newLogin() {
        return productionScheduler.getCurrentAndFutureStatusAndTaskStatus();
    }

    public List<String> confirmAdvance(int currentPhaseTime) {
        List<String> MoveOrNot = productionScheduler.advanceOrders(currentPhaseTime);

        return MoveOrNot;
    }*/

    public List<String> checkStatistics() {
        List<String> statistics = new ArrayList<>();
        statistics.add("Average number of cars produced in a day: 10");
        statistics.add("Median number of cars produced in a day: 11");
        statistics.add("Cars produced yesterday: 13");
        statistics.add("Cars produced two days ago: 9");

        statistics.add("Average delay (in minutes): 7");
        statistics.add("Median delay (in minutes): 5");
        statistics.add("Last delay: 13 minutes, 3 days ago");
        statistics.add("Second to last delay: 2 minutes, 5 days ago");

        return statistics;
    }

    public List<String> adaptSchedulingAlgorithm() {
        List<String> availableSchedulingAlgorithms = new ArrayList<>();
        availableSchedulingAlgorithms.add("FIFO");
        availableSchedulingAlgorithms.add("Specification Batch");

        return availableSchedulingAlgorithms;
    }

    public List<String> selectSchedulingAlgorithm(String algorithmName) {
        if (algorithmName.equals("Specification Batch")) {
            List<String> batches = new ArrayList<>();
            batches.add("Body, color, wheels");
            batches.add("seats, Airco");
            return batches;
        } else {
            return null;
        }
    }

    public void selectBatchSet (String batchSet) {
        return;
    }
}
