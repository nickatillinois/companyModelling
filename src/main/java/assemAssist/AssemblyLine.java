package assemAssist;

import assemAssist.carOrder.CarOrder;
import assemAssist.workStation.WorkStation;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an assembly line with workstations.
 *
 * @author SWOP Team 10
 */
public class AssemblyLine {
    private List<WorkStation> workStations;
    private int maxWorkingHoursToday = 22-6;
    private int hoursWorkedToday = 0;
    private final int STARTHOUR = 6;
    private final int ENDHOUR = 22;

    public AssemblyLine(List<WorkStation> workStations){
        setWorkStations(workStations);
    }

    public List<WorkStation> getWorkStations() {
        return workStations;
    }

    private void setWorkStations(List<WorkStation> workStations) {
        this.workStations = workStations;
    }

    private int getMaxWorkingHoursToday() {
        return maxWorkingHoursToday;
    }

    private void setMaxWorkingHoursToday(int maxWorkingHoursToday) {
        this.maxWorkingHoursToday = maxWorkingHoursToday;
    }

    public int getHoursWorkedToday() {
        return hoursWorkedToday;
    }

    private void setHoursWorkedToday(int hoursWorkedToday) {
        this.hoursWorkedToday = hoursWorkedToday;
    }

    public void nextDay() throws IllegalCallerException{
        for(WorkStation workStation : workStations)
            if (workStation.getCurrentOrder() != null)
                throw new IllegalCallerException("There are workstation(s) that are stile working a current order. ");
        if(getHoursWorkedToday() <= ENDHOUR - STARTHOUR )
            setMaxWorkingHoursToday(ENDHOUR - STARTHOUR);
        else
            setMaxWorkingHoursToday(ENDHOUR - STARTHOUR-(getHoursWorkedToday()-(ENDHOUR - STARTHOUR)));
        setHoursWorkedToday(0);
    }

    public List<CarOrder> getCurrentState(){
        List<CarOrder> currentState  = new ArrayList<>(workStations.size());
        for (int i = 0 ; i < workStations.size(); i++){
            currentState.add( workStations.get(i).getCurrentOrder());
        }
        return currentState;

    }

    public int remainWorkingTime(){
        return getMaxWorkingHoursToday()-getHoursWorkedToday();
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

    public List<CarOrder> move(CarOrder carOrder, int timeBetweenToStates) throws IllegalCallerException{
        for(WorkStation workStation : getWorkStations())
            if(!workStation.isFinished())
                throw new IllegalCallerException("The assmebly line is stil working at a workpost!");
        setHoursWorkedToday(getHoursWorkedToday() + timeBetweenToStates);
        CarOrder finischedCar = workStations.get(2).getCurrentOrder();
        if (finischedCar !=  null) {
            finischedCar.setCompleted(true);
        }
        workStations.get(2).setCurrentOrder(workStations.get(1).getCurrentOrder());
        workStations.get(1).setCurrentOrder(workStations.get(0).getCurrentOrder());
        workStations.get(0).setCurrentOrder(carOrder);
        List<CarOrder> newStateAndFiniched = new ArrayList<>(4);
        newStateAndFiniched.add(carOrder);
        newStateAndFiniched.add(workStations.get(1).getCurrentOrder());
        newStateAndFiniched.add(workStations.get(2).getCurrentOrder());
        newStateAndFiniched.add(finischedCar);
        return newStateAndFiniched;
    }



}
