package assemAssist;

import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.workStation.WorkStation;
import purecollections.PList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class representing a production schedule.
 *
 * @author SWOP Team 10
 */
public class ProductionScheduler {
private final AssemblyLine assemblyLine;
private final List<CarOrder>  productionSchedule;
private String manager;

    /**
     * Create a new production schedule af a single assembly line that can be managed by the manager
     * @param assemblyLine | the assemblyline belonging to the production scheduler
     */
public ProductionScheduler( AssemblyLine assemblyLine){
    productionSchedule = new ArrayList<>();
    this.assemblyLine = assemblyLine;

}
    /**
     * An immutable PList of available car models
     *
     */
    private static PList<String> availableModels = PList.empty();

    /**
     * Returns an immutable PList of available car models
     *
     * @return an immutable PList of available car models
     */
    public static PList<String> getAvailableModels(){
        return availableModels;
    }

    /**
     * Returns an array of available airco choices.
     *
     * @return an array of available airco choices.
     */
    public static String[] getAvailableAircoChoices(){
        return new String[]{"manual", "automatic climate control"};
    }

    /**
     * Returns an array of available body choices.
     *
     * @return an array of available body choices.
     */
    public static String[] getAvailableBodyChoices(){
        return new String[]{"sedan", "break"};
    }

    /**
     * Returns an array of available color choices.
     *
     * @return an array of available color choices.
     */
    public static String[] getAvailableColorChoices(){
        return new String[]{"red", "blue", "black", "white"};
    }

    /**
     * Returns an array of available engine choices.
     *
     * @return an array of available engine choices.
     */
    public static String[] getAvailableEngineChoices(){
        return new String[]{"standard 2l 4 cilinders", "performance 2.5l 6 cilinders"};
    }

    /**
     * Returns an array of available gearbox choices.
     *
     * @return an array of available gearbox choices.
     */
    public static String[] getAvailableGearboxChoices(){
        return new String[]{"6 speed manual", "5 speed automatic"};
    }

    /**
     * Returns an array of available seats choices.
     *
     * @return an array of available seats choices.
     */
    public static String[] getAvailableSeatsChoices(){
        return new String[]{"leather black", "leather white", "vinyl grey"};
    }

    /**
     * Returns an array of available wheels choices.
     *
     * @return an array of available wheels choices.
     */
    public static String[] getAvailableWheelsChoices(){
        return new String[]{"comfort", "sports (low profile)"};
    }

    /**
     * Adds the given string of a car model name to the end of the PList of car model names.
     * If the given string is already in the PList, the string is not added.
     *
     * @param model  the string that is added to the PList of existing car models.
     * @throws IllegalArgumentException | given model name is null
     *                                  | given model name is the empty string
     */
    public static void addModel(String model){
        if(model == null){throw new IllegalArgumentException("A model name cannot be null.");}
        if(model.length() == 0){throw new IllegalArgumentException("A model name cannot be the empty string.");}
        if(ProductionScheduler.getAvailableModels().contains(model)) return;
        availableModels = availableModels.plus(model);
    }
    /**
     * This function return the manager that can manage the assembly line.
     * @return manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * Set the manager of the product schedule to the parameter.
     * @param manager | the manager of the product schedule
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * This function returns the assembly line on whits this production schedule runs.
     * @return assemblyLine
     */
    public AssemblyLine getAssemblyLine(){
        return this.assemblyLine;
    }

    /**
     * This function gifs a list of the orders that wait to be produced in FCFS order.
     * @return productionSchedule
     */
    public List<CarOrder> getProductionSchedule() {
        return productionSchedule;
    }

    /**
     * This function calculate a simulation of an advanced assembly line without knowing of this is possible.
     * @return simulation
     */
    public List<CarOrder> simulateAdvanceAssemblyLine() {
        List<CarOrder> currentState = getCurrentState();
        ArrayList<CarOrder> simulatedState = new ArrayList<>(3);
        if (getProductionSchedule().size() == 0)
            simulatedState.add(null);
        else if(assemblyLine.remainWorkingTime() >=3 )
            simulatedState.add( getProductionSchedule().get(0));
        else
            simulatedState.add(null);
        simulatedState.add( currentState.get(0));
        simulatedState.add( currentState.get(1));
        return simulatedState;
    }

    /**
     * This function will advance the assembly line if that is possible and then returns the nuw state
     * else it return null.
     * @param timeBetweenTwoStates | time entered by the manager that was consumed
     * @return new state or null
     */
    public void advanceOrders(int timeBetweenTwoStates){
        if (!assemblyLine.canMove())
            return;
        if (productionSchedule.isEmpty())
            assemblyLine.move(null,timeBetweenTwoStates);
        assemblyLine.move(productionSchedule.remove(0),timeBetweenTwoStates);
    }

    /**
     * This function returns a list of carOrders that have pending task in his workstation.
     * @return list of car orders
     */
    public List<String> canNotMove(){
        if (assemblyLine.canMove())
            throw new IllegalCallerException("The caller can move!");
        return assemblyLine.canNotMove();
    }

    /**
     * This function returns a list whit car orders, the car order at index i is in progress in the i'th workstation.
     * @return list of car orders
     */
    public List<CarOrder> getCurrentState(){
        return  assemblyLine.getCurrentState();
    }

    /**
     * This function will add a real car order to the production schedule.
     * @param carOrder | the car order added to the proction schedule
     * @throws NullPointerException | car order is null
     */
    public LocalDate addOrderToProductionSchedule(CarOrder carOrder) throws NullPointerException, IllegalCompletionDateException {
        if (carOrder != null) {
            productionSchedule.add(carOrder);
            int carsToProduceToday = Math.min(assemblyLine.remainWorkingTime() - 2,0);
            float carsToProduceLater = productionSchedule.size() -carsToProduceToday;
            int daysNeeded = (int)Math.ceil(carsToProduceLater/14);
            return LocalDate.now().plusDays(daysNeeded);
        }
        else
            throw new NullPointerException("You can not add null to the production schedule.");

    }

    /**
     * This function return a list of carorders of the given garageHolder.
     * @param garageHolder | the garageholder of whom the carorders are to be found
     * @return list of car order
     */
    public List<CarOrder> getOrdersFromGarageHolder(String garageHolder){
        List<CarOrder> orderFromGarageHolder = productionSchedule.stream().
                filter(p-> Objects.equals(p.getGarageholder(), garageHolder)).collect(Collectors.toList());
        List<CarOrder> orderInWorkstations = getCurrentState();
        for (CarOrder order : orderInWorkstations){
            if (order != null)
                orderFromGarageHolder.add(order);
        }
        return orderFromGarageHolder;
    }

    /**
     * This function returns a list of pending car orders of the given garage holder.
     * @param garageHolder | the garage holder of whom the car orders are to be found
     * @return list of car orders
     */
    public List<CarOrder> getPendingOrdersFromGarageHolder(String garageHolder){
        List<CarOrder> pending = productionSchedule.stream().filter(p-> Objects.equals(p.getGarageholder(), garageHolder)).collect(Collectors.toList());
        for(WorkStation workStation : getAssemblyLine().getWorkStations()){
            if (workStation.getCurrentOrder() != null)
                if (Objects.equals(workStation.getCurrentOrder().getGarageholder(), garageHolder))
                    pending.add( workStation.getCurrentOrder());
        }
        return pending;
    }
    
    public List<String> selectModel(String carModel) {
        List<String> availableChoices = new ArrayList<>();
        availableChoices.add("body: " + String.join(", ", getAvailableBodyChoices()));
        availableChoices.add("color: " + String.join(", ", getAvailableColorChoices()));
        availableChoices.add("engine: " + String.join(", ", getAvailableEngineChoices()));
        availableChoices.add("gearbox: " + String.join(", ", getAvailableGearboxChoices()));
        availableChoices.add("seats: " + String.join(", ", getAvailableSeatsChoices()));
        availableChoices.add("airco: " + String.join(", ", getAvailableAircoChoices()));
        availableChoices.add("wheels: " + String.join(", ", getAvailableWheelsChoices()));
        return availableChoices;
    }

    public LocalDate completeOrderingForm(List<String> carOptions, String garageHolder, String chosenModel) throws IllegalChoiceException, IllegalModelException,
            IllegalCompletionDateException {
        CarModelSpecification cmf = new CarModelSpecification(new Body(carOptions.get(0)),new Color(carOptions.get(1)), new Engine(carOptions.get(2)),new Gearbox(carOptions.get(3)), new Seats(carOptions.get(4)),new Airco(carOptions.get(5)),new Wheels(carOptions.get(6)));
        CarModel carModel = new CarModel(chosenModel, cmf);
        CarOrder carOrder = new CarOrder(garageHolder, carModel);
        return addOrderToProductionSchedule(carOrder);
    }
}
