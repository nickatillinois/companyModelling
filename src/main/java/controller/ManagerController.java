package controller;

import assemAssist.Company;
import assemAssist.schedulingAlgorithm.SchedulingAlgorithm;

import java.util.ArrayList;
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
        List<String> schedulers  = new ArrayList<>();
        for(SchedulingAlgorithm x : company.getSchedulingAlgorithms()){
            schedulers.add(x.getName());
        }
        return schedulers;
    }

    public List<String> selectSchedulingAlgorithm(String algorithmName) {
        List<SchedulingAlgorithm> schedulers = company.getSchedulingAlgorithms();
        for(SchedulingAlgorithm x :schedulers){
            if ((x.getName()).equals(algorithmName)){
                company.selectScheduler(x);
            }
        }
        return company.possibleBatch();
    }

    public void selectBatchSet(String batchSet) {
        try{
            company.selectBatch(batchSet);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("This is not a vallid batch set.");
        }
    }

    public List<String> checkStatistics(int lastXDays) {
        return company.getStatistics(lastXDays);
    }

}
