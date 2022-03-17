package useCases;

import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalModelException;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class OrderNewCar1 {

    private CarOrder carOrder;
    private String garageHolder;

    @Test
    public void init() throws IllegalModelException, IllegalChoiceException {
        Body body = new Body("sedan");
        CarModelSpecification cmf = new CarModelSpecification(body,new Color("red"),
                new Engine("standard 2l 4 cilinders"),new Gearbox("6 speed manual"),
                new Seats("leather white"),new Airco("manual"),new Wheels("comfort"));
        String modelName = "jaguar";
        CarModel carModel = new CarModel(modelName, cmf);



    }



}
