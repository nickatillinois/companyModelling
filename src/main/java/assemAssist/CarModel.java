package assemAssist;

import java.util.Map;

public class CarModel {
    private String modelName;
    private Map<String, String> chosenOptions;

    public String getChosenModelName() {
        return modelName;
    }

    public CarModel(String modelName, Map<String, String> chosenOptions) {
        this.modelName = modelName;
        this.chosenOptions = chosenOptions;
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
