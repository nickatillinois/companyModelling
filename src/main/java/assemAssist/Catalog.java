package assemAssist;

import assemAssist.exceptions.IllegalModelException;

import java.util.*;

/**
 * Class representing a catalog of the products offered by the car manufacturing company.
 *
 * @author SWOP team 10
 */
public class Catalog {

    /**
     * The set of the CarModels offered by the car manufacturing company.
     */
    private static final HashSet<CarModelSpecification> availableModels = new HashSet<>();

    /**
     * A constructor that initializes the catalog with the car models specified in the assignment.
     */
    public Catalog() {
        createStandardModels();
    }

    /**
     * Returns the available car models.
     *
     * @return The available car models in the catalog.
     */
    public HashSet<CarModelSpecification> getAvailableModels() {
        return availableModels;
    }

    /**
     * Returns a set of car model names currently offered by the car manufacturing company.
     *
     * @return A set of car model names currently offered by the car manufacturing company.
     */
    public HashSet<String> getAvailableModelNames() {
        HashSet<String> names = new HashSet<>();    // Set of all available model names
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
    public CarModelSpecification getModel(String modelName) throws IllegalModelException {

        for (CarModelSpecification model : availableModels) {
            if (model.getModelName().equals(modelName)) {
                return model;
            }
        }
        throw new IllegalModelException("Model " + modelName + " is currently not offered.");
    }
    /** A method that returns a string representation of a given car model */
    public String getModelSpecification(String modelName) throws IllegalModelException {
        return getModel(modelName).toString();
    }



    public TreeMap<String, HashSet<String>> getModelSpecifications(String modelName) throws IllegalModelException {
        CarModelSpecification model = getModel(modelName);
        return model.getAvailableOptions();
    }

    /**
     * Adds the given car model to the set of car models in the catalog.
     *
     * @param model The name of new car model.
     */
    public void addModel(CarModelSpecification model){
        availableModels.add(model);
    }

    private void createStandardModels(){
        TreeMap<String, HashSet<String>> A = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        A.put("Body", new HashSet<>() {{
            add("Sedan");
            add("Break");
        }});
        A.put("Color", new HashSet<>() {{
            add("red");
            add("blue");
            add("black");
            add("white");
        }});
        A.put("Engine", new HashSet<>() {{
            add("V4");
            add("V6");
        }});
        A.put("Gearbox", new HashSet<>() {{
            add("6 manual");
            add("5 manual");
            add("5 automatic");
        }});
        A.put("Seats", new HashSet<>() {{
            add("leather white");
            add("leather black");
            add("vinyl grey");
        }});
        A.put("Airco", new HashSet<>() {{
            add("Manual");
            add("Automatic");
        }});
        A.put("Wheels", new HashSet<>() {{
            add("winter");
            add("comfort");
            add("sports");
        }});
        this.addModel(new CarModelSpecification("A", 50, A));

        TreeMap<String, HashSet<String>> B = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        B.put("Body", new HashSet<>() {{
            add("Sedan");
            add("Break");
            add("Sport");
        }});
        B.put("Color", new HashSet<>() {{
            add("red");
            add("blue");
            add("green");
            add("yellow");
        }});
        B.put("Engine", new HashSet<>() {{
            add("V4");
            add("V6");
            add("V8");
        }});
        B.put("Gearbox", new HashSet<>() {{
            add("6 manual");
            add("5 manual");
        }});
        B.put("Seats", new HashSet<>() {{
            add("leather white");
            add("leather black");
            add("vinyl grey");
        }});
        B.put("Airco", new HashSet<>() {{
            add("Manual");
            add("Automatic");
        }});
        B.put("Wheels", new HashSet<>() {{
            add("winter");
            add("comfort");
            add("sports");
        }});
        B.put("Spoiler", new HashSet<>() {{
            add("low");
        }});
        this.addModel(new CarModelSpecification("B", 70, B));

        TreeMap<String, HashSet<String>> C = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        C.put("Body", new HashSet<>() {{
            add("Sport");
        }});
        C.put("Color", new HashSet<>() {{
            add("black");
            add("white");
        }});
        C.put("Engine", new HashSet<>() {{
            add("V6");
            add("V8");
        }});
        C.put("Gearbox", new HashSet<>() {{
            add("6 manual");
        }});
        C.put("Seats", new HashSet<>() {{
            add("leather white");
            add("leather black");
        }});
        C.put("Airco", new HashSet<>() {{
            add("Manual");
            add("Automatic");
        }});
        C.put("Wheels", new HashSet<>() {{
            add("winter");
            add("sports");
        }});
        C.put("Spoiler", new HashSet<>() {{
            add("low");
            add("high");
        }});
        this.addModel(new CarModelSpecification("C", 60, C));
    }
}