package assemAssist;

import assemAssist.workStation.WorkStation;

import java.util.ArrayList;
import java.util.List;

public class AssemblyLine {
    private List<WorkStation> workStations;
    private CarManufacturingCompany carManufacturingCompany;

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
}
