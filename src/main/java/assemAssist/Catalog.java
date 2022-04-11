package assemAssist;

import assemAssist.exceptions.IllegalModelException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a catalog of the products offered by the car manufacturing company.
 *
 * @author SWOP team 10
 */
public class Catalog {

    private ArrayList<CarModelSpecification> availableModels = new ArrayList<>();
    public Catalog() {
        createStandardModels();
    }

    /**
     * Returns the available car models.
     *
     * @return The available car models in the catalog.
     */
    public ArrayList<CarModelSpecification> getAvailableModels() {
        return availableModels;
    }

    public ArrayList<String> getAvailableModelNames() {
        ArrayList<String> names = new ArrayList<>();
        for (CarModelSpecification model : availableModels) {
            names.add(model.getModelName());
        }
        return names;
    }

    /**
     * Returns the car model with the given name.
     *
     * @param modelName The name of the car model.
     * @return The car model with the given name.
     */
    private CarModelSpecification getModel(String modelName) throws IllegalModelException {
        for (CarModelSpecification model : availableModels) {
            if (model.getModelName().equals(modelName)) {
                return model;
            }
        }
        throw new IllegalModelException("Model " + modelName + " is currently not offered.");
    }
    /** A method that returns a string representation of a given car model */
    public String getModelSpecification(String modelName) throws IllegalModelException {
        return getModel(modelName).getCarModelSpecificationString();
    }

    /**
     * Adds the given car model to the list of car models in the catalog.
     *
     * @param model The name of new car model.
     */
    public void addModel(CarModelSpecification model){
        availableModels.add(model);
    }

    public Map<String, List<String>> getModelSpecifications(String modelName) throws IllegalModelException {
        CarModelSpecification model = getModel(modelName);
        return model.getSpecifications();
    }
    private void createStandardModels(){
        HashMap<String, List<String>> A = new HashMap<>();
        A.put("Body", new ArrayList<>() {{
            add("Sedan");
            add("Break");
        }});
        A.put("Color", new ArrayList<>() {{
            add("red");
            add("blue");
            add("black");
            add("white");
        }});
        A.put("Engine", new ArrayList<>() {{
            add("V4");
            add("V6");
        }});
        A.put("Gearbox", new ArrayList<>() {{
            add("6 manual");
            add("5 manual");
            add("5 automatic");
        }});
        A.put("Seats", new ArrayList<>() {{
            add("leather white");
            add("leather black");
            add("vinyl grey");
        }});
        A.put("Airco", new ArrayList<>() {{
            add("Manual");
            add("Automatic");
        }});
        A.put("Wheels", new ArrayList<>() {{
            add("winter");
            add("comfort");
            add("sports");
        }});
        this.addModel(new CarModelSpecification("A", A));

        HashMap<String, List<String>> B = new HashMap<>();
        A.put("Body", new ArrayList<>() {{
            add("Sedan");
            add("Break");
            add("Sport");
        }});
        A.put("Color", new ArrayList<>() {{
            add("red");
            add("blue");
            add("green");
            add("yellow");
        }});
        A.put("Engine", new ArrayList<>() {{
            add("V4");
            add("V6");
            add("V8");
        }});
        A.put("Gearbox", new ArrayList<>() {{
            add("6 manual");
            add("5 manual");
        }});
        A.put("Seats", new ArrayList<>() {{
            add("leather white");
            add("leather black");
            add("vinyl grey");
        }});
        A.put("Airco", new ArrayList<>() {{
            add("Manual");
            add("Automatic");
        }});
        A.put("Wheels", new ArrayList<>() {{
            add("winter");
            add("comfort");
            add("sports");
        }});
        A.put("Spoiler", new ArrayList<>() {{
            add("low");
        }});
        this.addModel(new CarModelSpecification("B", B));

        HashMap<String, List<String>> C = new HashMap<>();
        A.put("Body", new ArrayList<>() {{
            add("Sport");
        }});
        A.put("Color", new ArrayList<>() {{
            add("black");
            add("white");
        }});
        A.put("Engine", new ArrayList<>() {{
            add("V6");
            add("V8");
        }});
        A.put("Gearbox", new ArrayList<>() {{
            add("6 manual");
        }});
        A.put("Seats", new ArrayList<>() {{
            add("leather white");
            add("leather black");
        }});
        A.put("Airco", new ArrayList<>() {{
            add("Manual");
            add("Automatic");
        }});
        A.put("Wheels", new ArrayList<>() {{
            add("winter");
            add("sports");
        }});
        A.put("Spoiler", new ArrayList<>() {{
            add("low");
            add("high");
        }});
        this.addModel(new CarModelSpecification("C", C));
    }
}