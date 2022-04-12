package assemAssist;

import assemAssist.exceptions.IllegalConstraintException;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.constraint.Inspector;

import java.util.Map;

public class CarModel {
    private String modelName;
    private Map<String, String> chosenOptions;

    public String getModelName() {
        return modelName;
    }

    public CarModel(String modelName, Map<String, String> chosenOptions) {
        this.modelName = modelName;
        this.chosenOptions = chosenOptions;
    }

    public boolean inspect() throws IllegalConstraintException, IllegalModelException {
        return new Inspector(this).inspect();
    }
    public Map<String, String> getChosenOptions() {
        return chosenOptions;
    }

    public String getChosenOptionsString() {
        StringBuilder sb = new StringBuilder();
        for (String key : chosenOptions.keySet()) {
            sb.append(key).append(": ").append(chosenOptions.get(key)).append("\n");
        }
        return sb.toString();
    }


}
