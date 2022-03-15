package useCases;

import assemAssist.carOrder.*;
import assemAssist.user.GarageHolder;
import org.junit.jupiter.api.Test;

public class OrderNewCar1 {

    private CarOrder carOrder;
    private GarageHolder garageHolder;

    @Test
    public void init() {
        Seats seats = new Seats();
        Wheels wheels = new Wheels();
        Gearbox gearbox = new Gearbox();
        Engine engine = new Engine();
        Color color = new Color();
        Body body = new Body();
        Airco airco = new Airco();
        CarModelSpecification cmf = new CarModelSpecification(body, color, engine, gearbox, seats, airco,
                wheels);
        String modelName = "jaguar";
        CarModel carModel = new CarModel(modelName, cmf);
        BodyEnum[] possibleValues = BodyEnum.values();


    }

}
