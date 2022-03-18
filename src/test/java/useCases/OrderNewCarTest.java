package useCases;

import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalModelException;
import org.junit.jupiter.api.Test;

public class OrderNewCarTest {

    private CarOrder carOrder;
    private String garageHolder;

    @Test
    public void init() throws IllegalModelException, IllegalChoiceException {
        Body body = new Body("sedan");
        CarModelSpecification cmf = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        CarModel.addModel("Jaguar");
        String modelName = "jaguar";
        CarModel carModel = new CarModel(modelName, cmf);
        String garageHolder = "john doe";
        CarOrder carOrder = new CarOrder(garageHolder, carModel);



    }



}
