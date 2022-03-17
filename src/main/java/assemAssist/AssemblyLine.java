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

    /**
     * This will create a new assembly line en initialise it.
     * @param workStations list of the workstatioin of the new assembly line!
     */
    public AssemblyLine(List<WorkStation> workStations){
        setWorkStations(workStations);
    }

    /**
     * This function give you a list of the workstations of the assembly Line.
     * @return work stations
     */
    public List<WorkStation> getWorkStations() {
        return workStations;
    }

    /**
     * This function set the Workstations to the given list.
     * @param workStations new workstations
     */
    private void setWorkStations(List<WorkStation> workStations) {
        this.workStations = workStations;
    }

    /**
     * Returns the names of all the workstations in the assembly line.
     */
    public List<String> getWorkStationNames() {
        List<String> workStationNames = new ArrayList();
        for (int i = 0; i < workStations.size(); i++) {
            workStationNames.add(workStations.get(i).getName());
        }
        return workStationNames;
    }

    /**
     * This function returns the max working hours of today.
     * @return max working hours
     */
    private int getMaxWorkingHoursToday() {
        return maxWorkingHoursToday;
    }

    /**
     * This function set the max working hours to the new value
     * @param maxWorkingHoursToday update max working hours
     */
    private void setMaxWorkingHoursToday(int maxWorkingHoursToday) {
        this.maxWorkingHoursToday = maxWorkingHoursToday;
    }

    /**
     * This function returns the hours that are already worked to day
     * @return hours worked today
     */
    public int getHoursWorkedToday() {
        return hoursWorkedToday;
    }

    /**
     * This function will update the working hours of this day.
     * @param hoursWorkedToday working hours today
     */
    private void setHoursWorkedToday(int hoursWorkedToday) {
        this.hoursWorkedToday = hoursWorkedToday;
    }

    /**
     * This function wil set the working hours today to zero en update the hours that can be worked next day
     * @throws IllegalCallerException if workstations are not done with their tasks.
     */
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

    /**
     * This function returns a list where on place i is the car order that is in workstation i.
     * @return List of car orders
     */
    public List<CarOrder> getCurrentState(){
        List<CarOrder> currentState  = new ArrayList<>(workStations.size());
        for (int i = 0 ; i < workStations.size(); i++){
            currentState.add( workStations.get(i).getCurrentOrder());
        }
        return currentState;

    }

    /**
     * This function calculated and returns the remaining working hours
     * @return remain working hours
     */
    public int remainWorkingTime(){
        return getMaxWorkingHoursToday()-getHoursWorkedToday();
    }

    /**
     * This function return of the assembly line can advance.
     * @return boolean
     */
    public boolean canMove(){
        boolean canMove = true;
        for (WorkStation workStation : workStations){
            if(!workStation.isFinished()){
                canMove = false;
            }
        }
        return canMove;
    }

    /**
     * This function returns a list of car orders that not is finished at there current workstation.
     * @return list of car orders
     */
    public List<String> canNotMove(){
        List<String> canNotMove = new ArrayList<>();
        canNotMove.add("Blocked");
        for(WorkStation workStation : workStations){
            if (!workStation.isFinished())
                canNotMove.add(workStation.toString().split("\\.")[2].split("@")[0]);
        }
        return canNotMove;
    }

    /**
     * This function will push an order to the next workstation and return a list of 4 orders with on the i place
     * the order in workstation i and on the 4 place the order that is finished complete.
     * @param carOrder new car order for workstation 1
     * @param timeBetweenToStates time necessary to complete this phase.
     * @return list of car orders
     * @throws IllegalCallerException if the assembly line can't move
     */
    public List<String> move(CarOrder carOrder, int timeBetweenToStates) throws IllegalCallerException{
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
        List<String> newStateAndFinished = new ArrayList<>();
        List<CarOrder> curuntStatus  = getCurrentState();
        for(int i = 0 ; i < curuntStatus.size() ; i++){
            CarOrder carOrder2 = curuntStatus.get(i);
            String s = getWorkStations().get(i).toString() + " ; ";
            if(carOrder2 != null) {
                s += "Model: " + carOrder.getCarModel();
                getWorkStations().get(i).getTasksAndStatus();
            }
            else
                s += "No Order in this workstation";
            newStateAndFinished.add(s);
        }
//        if(carOrder != null)
//            newStateAndFinished.add(carOrder.toString().split("\\.")[3].split("@")[0]);
//        else
//            newStateAndFinished.add("No order");
//        if (workStations.get(1).getCurrentOrder() != null)
//            newStateAndFinished.add(workStations.get(1).getCurrentOrder().toString().split("\\.")[3].split("@")[0]);
//        else
//            newStateAndFinished.add("No order");
//        if (workStations.get(2).getCurrentOrder() != null)
//            newStateAndFinished.add(workStations.get(2).getCurrentOrder().toString().split("\\.")[3].split("@")[0]);
//        else
//            newStateAndFinished.add("No order");
        return newStateAndFinished;
    }

    /**
     * Finds a specific work station and returns this.
     *
     */
    public WorkStation findWorkStation (String workStation) throws IllegalArgumentException{
        for (int i = 0; i < workStations.size(); i++) {
            if (workStations.get(i).getName().equals(workStation)){
                return workStations.get(i);
            }
        }
        throw new IllegalArgumentException("This is not a work station at this assembly line!");
    }


}
