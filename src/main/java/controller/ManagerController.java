package controller;

import assemAssist.Company;
import assemAssist.schedulingAlgorithm.SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class ManagerController {

    private final Company company;

    public ManagerController(Company company) {
        this.company = company;
    }


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
        return company.getProductionScheduler().getSchedulingAlgorithms();
    }

    public List<String> selectSchedulingAlgorithm( String algorithmName) {
        company.getProductionScheduler().selectSchedulingAlgorithm(algorithmName);
        return company.getProductionScheduler().getSchedulingAlgorithm().posibleBatch();
    }

    public void selectBatchSet (String batchSet) {
        try{
            company.getProductionScheduler().getSchedulingAlgorithm().selectBatch(batchSet);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("This is not a vallid batch set.");
        }
    }
}
