package assemAssist;

import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
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
    private int minutesWorkedToday = 0;
    private final int STARTHOUR = 6;
    private final int ENDHOUR = 22;
    private int maxWorkingMinutesToday = (ENDHOUR-STARTHOUR)*60;

    /**
     * This will create a new assembly line en initialise it.
     */
    public AssemblyLine(Company company){

        List<WorkStation> workStations = new ArrayList<>();
        CarBodyPost carBodyPost = new CarBodyPost(company); workStations.add(carBodyPost);
        DrivetrainPost drivetrainPost = new DrivetrainPost(company); workStations.add(drivetrainPost);
        AccessoriesPost accessoriesPost = new AccessoriesPost(company); workStations.add(accessoriesPost);
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
        List<String> workStationNames = new ArrayList<>();
        for (WorkStation workStation : workStations) {
            workStationNames.add(workStation.getName());
        }
        return workStationNames;
    }

    /**
     * This function returns the max working minutes of today.
     * @return max working minutes
     */
    private int getMaxWorkingMinutesToday() {
        return maxWorkingMinutesToday;
    }

    /**
     * This function set the max working minutes to the new value
     * @param maxWorkingMinutesToday update max working minutes
     */
    private void setMaxWorkingMinutesToday(int maxWorkingMinutesToday) {
        this.maxWorkingMinutesToday = maxWorkingMinutesToday;
    }

    /**
     * This function returns the minutes that are already worked to day
     * @return minutes worked today
     */
    public int getMinutesWorkedToday() {
        return minutesWorkedToday;
    }

    /**
     * This function will update the working minutes of this day.
     * @param minutesWorkedToday working minutes today
     */
    private void setMinutesWorkedToday(int minutesWorkedToday) {
        this.minutesWorkedToday = minutesWorkedToday;
    }

    /**
     * This function wil set the working minutes today to zero en update the minutes that can be worked next day
     * @throws IllegalCallerException if workstations are not done with their tasks.
     * @throws IllegalStateException if the delay is more than 8 hours
     */
    public void nextDay() throws IllegalCallerException, IllegalStateException{
        for(WorkStation workStation : workStations)
            if (workStation.getCurrentOrder() != null)
                throw new IllegalCallerException("There are workstation(s) that are still working a current order. ");
        if(getMinutesWorkedToday() <= getMaxWorkingMinutesToday())
            setMaxWorkingMinutesToday((ENDHOUR - STARTHOUR) * 60);
        else if ((getMinutesWorkedToday() - getMaxWorkingMinutesToday() > (8 * 60)))
            throw new IllegalStateException("The delay of today is over 8 hours that is not possible!");
        else
            setMaxWorkingMinutesToday((ENDHOUR - STARTHOUR)*60-(getMinutesWorkedToday()-getMaxWorkingMinutesToday()));
        setMinutesWorkedToday(0);
    }

    /**
     * This function returns a list where on place i is the car order that is in workstation i.
     * @return List of car orders
     */
    public List<CarOrder> getCurrentState(){
        List<CarOrder> currentState  = new ArrayList<>(workStations.size());
        for (WorkStation workStation : workStations) {
            currentState.add(workStation.getCurrentOrder());
        }
        return currentState;

    }

    /**
     * Returns the name of all the workstation in the assembly line along with the tasks at the workstation and
     * their current status.
     * @return List of strings in the format "NameOfWorkstation ; TaskName1, TaskStatus1; ...; TaskNameN, TaskStatusN"
     */
    public List<String> getCurrentStateString() {
        List<String> currentState  = new ArrayList<>(workStations.size());
        for (WorkStation workStation : workStations) {
            currentState.add(workStation.getName() + " ; " + workStation.getTasksAndStatus());
        }
        return currentState;
    }

    /**
     * This function calculated and returns the remaining working hours
     * @return remain working minutes
     */
    public int remainingWorkingTime(){
        return getMaxWorkingMinutesToday()-getMinutesWorkedToday();
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
                break;
            }
        }
        return canMove;
    }

    /**
     * This function will push an order to the next workstation and return a list of 4 orders with on the i place
     * the order in workstation i and on the 4 place the order that is finished complete.
     * @param carOrder new car order for workstation 1
     * @param timeBetweenTwoStates time necessary to complete this phase.
     * @return list of car orders
     * @throws IllegalCallerException if the assembly line can't move
     * @throws RuntimeException | setting of the new orders goes wrong.
     */
    public List<String> move(CarOrder carOrder, int timeBetweenTwoStates) throws IllegalCallerException, RuntimeException{
        for(WorkStation workStation : getWorkStations())
            if(!workStation.isFinished())
                throw new IllegalCallerException("The assembly line is stil working at a work post!");
        if (carOrder != null && carOrder.getWorkingMinutesWorkStation()*3 < remainingWorkingTime()) {
            setMinutesWorkedToday(getMinutesWorkedToday() + timeBetweenTwoStates);
            CarOrder finishedCar = workStations.get(2).getCurrentOrder();
            if (finishedCar != null) {
                finishedCar.setCompleted();
            }
            try {
                workStations.get(2).setCurrentOrder(workStations.get(1).getCurrentOrder());
                workStations.get(1).setCurrentOrder(workStations.get(0).getCurrentOrder());
                workStations.get(0).setCurrentOrder(carOrder);
            } catch (RuntimeException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        else{
            if (emptyAssemblyLine())
                nextDay();
            else{
                setMinutesWorkedToday(getMinutesWorkedToday() + timeBetweenTwoStates);
                CarOrder finishedCar = workStations.get(2).getCurrentOrder();
                if (finishedCar != null) {
                    finishedCar.setCompleted();
                }
                try {
                    workStations.get(2).setCurrentOrder(workStations.get(1).getCurrentOrder());
                    workStations.get(1).setCurrentOrder(workStations.get(0).getCurrentOrder());
                    workStations.get(0).setCurrentOrder(null);
                } catch (RuntimeException e) {
                    throw new RuntimeException(e.getMessage());
                }

            }
        }

        return getNewState();
    }

    private boolean emptyAssemblyLine() {
        for( WorkStation workStation : workStations ){
            if (workStation.getCurrentOrder() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds a specific work station and returns this.
     * @return The WorkStation that was requested.
     * @throws IllegalArgumentException | The requested work station does not exist or is null.
     */
    public WorkStation findWorkStation (String workStation) throws IllegalArgumentException{
        for (WorkStation station : workStations) {
            if (station.getName().equals(workStation)) {
                return station;
            }
        }
        throw new IllegalArgumentException("This is not a work station at this assembly line!");
    }

    private List<String> getNewState() {
        List<String> newStateAndFinished = new ArrayList<>();
        List<CarOrder> currentStatus  = getCurrentState();
        for(int i = 0 ; i < currentStatus.size() ; i++){
            CarOrder carOrder2 = currentStatus.get(i);
            String s = getWorkStations().get(i).getName() + " ; ";
            if(carOrder2 != null) {
                s += carOrder2.getCarModelAndOptions();
            }
            else
                s += "No Order in this workstation";
            newStateAndFinished.add(s);
        }
        return newStateAndFinished;
    }

}