package assemAssist;

import assemAssist.carOrder.CarOrder;
import assemAssist.workStation.WorkStation;

import java.util.ArrayList;
import java.util.List;

public class AssemblyLine {
    private List<WorkStation> workStations;
    private CarManufacturingCompany carManufacturingCompany;
    private int maxWorkingHoursToday = 22-6;
    private int hoursWorkedToday = 0;
    private final int STARTHOUR = 6;
    private final int ENDHOUR = 22;

    public AssemblyLine(ArrayList<WorkStation> workStations, CarManufacturingCompany carManufacturingCompany){
        this.carManufacturingCompany = carManufacturingCompany;
        this.workStations = workStations;
    }

    public CarManufacturingCompany getCarManufacturingCompany() {
        return carManufacturingCompany;
    }

    public void setCarManufacturingCompany(CarManufacturingCompany carManufacturingCompany) {
        this.carManufacturingCompany = carManufacturingCompany;
    }

    public List<WorkStation> getWorkStations() {
        return workStations;
    }

    public void setWorkStations(List<WorkStation> workStations) {
        this.workStations = workStations;
    }

    public int getMaxWorkingHoursToday() {
        return maxWorkingHoursToday;
    }

    public void setMaxWorkingHoursToday(int maxWorkingHoursToday) {
        this.maxWorkingHoursToday = maxWorkingHoursToday;
    }

    public int getHoursWorkedToday() {
        return hoursWorkedToday;
    }

    public void setHoursWorkedToday(int hoursWorkedToday) {
        this.hoursWorkedToday = hoursWorkedToday;
    }

    public void nextDay(){
        if(getHoursWorkedToday() <= ENDHOUR - STARTHOUR )
            setMaxWorkingHoursToday(ENDHOUR - STARTHOUR);
        else
            setMaxWorkingHoursToday(ENDHOUR - STARTHOUR-((ENDHOUR - STARTHOUR) - getHoursWorkedToday()));
        setHoursWorkedToday(0);
    }

    public List<CarOrder> getCurrentState(){
        List<CarOrder> currentState  = new ArrayList<>(workStations.size());
        for (int i = 0 ; i < workStations.size(); i++){
            currentState.set(i, workStations.get(i).getCurrentOrder());
        }
        return currentState;

    }

    public int remainWorkingTime(){
        return ENDHOUR-STARTHOUR-getHoursWorkedToday();
    }

    public boolean canMove(){
        boolean canMove = true;
        for (WorkStation workStation : workStations){
            if(!workStation.isFinished()){
                canMove = false;
            }
        }
        return canMove;
    }

    public List<CarOrder> canNotMove(){
        List<CarOrder> canNotMove = new ArrayList<>();
        for(WorkStation workStation : workStations){
            if (!workStation.isFinished())
                canNotMove.add(workStation.getCurrentOrder());
        }
        return canNotMove;
    }

    public List<CarOrder> move(CarOrder carOrder, int timeBetweenToStates){
        setHoursWorkedToday(getHoursWorkedToday() + timeBetweenToStates);
        CarOrder finischedCar = workStations.get(2).getCurrentOrder();
        finischedCar.setCompleted(true);
        workStations.get(2).setCurrentOrder(workStations.get(1).getCurrentOrder());
        workStations.get(1).setCurrentOrder(workStations.get(0).getCurrentOrder());
        workStations.get(0).setCurrentOrder(carOrder);
        List<CarOrder> newStateAndFiniched = new ArrayList<>(4);
        newStateAndFiniched.set(0,carOrder);
        newStateAndFiniched.set(1,workStations.get(1).getCurrentOrder());
        newStateAndFiniched.set(2,workStations.get(2).getCurrentOrder());
        newStateAndFiniched.set(3,finischedCar);
        return newStateAndFiniched;
    }


}
