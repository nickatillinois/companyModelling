package assemAssist;

import java.util.ArrayList;

/**
 * Class representing a catalog of the products offered by the car manufacturing company.
 *
 * @author SWOP team 10
 */
public class Catalog {

    private ArrayList<CarModel> availableModels = new ArrayList<VehicleModel>();
    public Catalog() {
        this.availableModels.add(new ModelA());
        this.availableModels.add(new ModelB());
        this.availableModels.add(new ModelC());
    }

    /**
     * Returns the available car models.
     *
     * @return The available car models in the catalog.
     */
    public ArrayList<CarModel> getAvailableModels() {
        return availableModels;
    }

    public ArrayList<String> getAvailableModelsNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (CarModel model : availableModels) {
            names.add(model.getName());
        }
        return names;
    }

    /**
     * Returns the car model with the given name.
     *
     * @param modelName The name of the car model.
     * @return The car model with the given name.
     */
    private CarModel getModel(String modelName) {
        for (CarModel model : availableModels) {
            if (model.getName().equals(modelName)) {
                return model;
            }
        }
        return null;
    }

    /**
     * Adds the given Carmodel to
     *
     * @param modelName The name of new car model.
     */
    public addModel(CarModel model){
        availableModels.add(model);
    }
    public Map<String, List<String>> getModelSpecifications(String modelName){
        CarModel model = getModel(modelName);
        return model.getSpecifications();
    }
    private void createStandardModels(){
        availableModels.add(new CarModelSpecification())
    }
}