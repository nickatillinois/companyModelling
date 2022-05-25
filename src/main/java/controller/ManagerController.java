package controller;

import assemAssist.Company;

import java.util.List;

/**
 * This class is the controller for the manager.
 * It is responsible for the communication between the manager and the
 * Company class.
 *
 * @author  Team 10
 */
public class ManagerController {

    private final Company company;

    public ManagerController(Company company) {
        this.company = company;
    }


    public List<String> adaptSchedulingAlgorithm() {
        return company.getProductionScheduler().getSchedulingAlgorithms();
    }

    public List<String> selectSchedulingAlgorithm( String algorithmName) {
        company.getProductionScheduler().selectSchedulingAlgorithm(algorithmName);
        return company.getProductionScheduler().getSchedulingAlgorithm().possibleBatch();
    }

    public void selectBatchSet (String batchSet) {
        try{
            company.getProductionScheduler().getSchedulingAlgorithm().selectBatch(batchSet);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("This is not a vallid batch set.");
        }
    }

    public List<String> checkStatistics(int lastXDays) {
        return company.getStatistics(lastXDays);
    }

}
