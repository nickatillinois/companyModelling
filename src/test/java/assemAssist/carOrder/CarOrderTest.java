package assemAssist.carOrder;

import assemAssist.AssemblyLine;
import assemAssist.ProductionScheduler;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalModelException;
import assemAssist.workStation.AccessoriesPost;
import assemAssist.workStation.CarBodyPost;
import assemAssist.workStation.DrivetrainPost;
import assemAssist.workStation.WorkStation;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CarOrderTest {

    static Body body = new Body();
    static Airco airco = new Airco();
    static Wheels wheels = new Wheels();
    static Seats seats = new Seats();
    static Gearbox gearbox = new Gearbox();
    static Engine engine = new Engine();
    static Color color = new Color();
    static CarModelSpecification carModelSpecification;
    static CarModel carModel;

    @BeforeAll
    static void init() throws IllegalModelException {
        CarModel.addModel("Jaguar");
        carModelSpecification = new CarModelSpecification(body, color, engine, gearbox, seats, airco, wheels);
        carModel = new CarModel("Jaguar", carModelSpecification);

    }

    @Test
    void exceptionsTest() throws IllegalChoiceException {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            body.setChosenChoice("");
        });
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            body.setChosenChoice(null);
        });
        Exception exception3 = assertThrows(IllegalChoiceException.class, () -> {
            body.setChosenChoice("stationwagon");
        });
        body.setChosenChoice("sedan");
        assert body.getChosenChoice().equalsIgnoreCase("sedan");
        body.setChosenChoice("BReak");
        assert body.getChosenChoice().equalsIgnoreCase("break");
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            carModelSpecification.setBody(null);
        });
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> {
            carModelSpecification.setAirco(null);
        });
        Exception exception6 = assertThrows(IllegalArgumentException.class, () -> {
            carModelSpecification.setColor(null);
        });
        Exception exception7 = assertThrows(IllegalArgumentException.class, () -> {
            carModelSpecification.setEngine(null);
        });
        Exception exception8 = assertThrows(IllegalArgumentException.class, () -> {
            carModelSpecification.setGearbox(null);
        });
        Exception exception9 = assertThrows(IllegalArgumentException.class, () -> {
            carModelSpecification.setWheels(null);
        });
        Exception exception10 = assertThrows(IllegalArgumentException.class, () -> {
            carModelSpecification.setSeats(null);
        });
        Exception exception11 = assertThrows(IllegalArgumentException.class, () -> {
            CarModelSpecification carModelSpecification = new CarModelSpecification(null, color, engine, gearbox, seats, airco, wheels);
        });
        Exception exception12 = assertThrows(IllegalArgumentException.class, () -> {
            CarModelSpecification carModelSpecification = new CarModelSpecification(body, null, engine, gearbox, seats, airco, wheels);
        });
        Exception exception13 = assertThrows(IllegalArgumentException.class, () -> {
            CarModelSpecification carModelSpecification = new CarModelSpecification(body, color, null, gearbox, seats, airco, wheels);
        });
        Exception exception14 = assertThrows(IllegalArgumentException.class, () -> {
            CarModelSpecification carModelSpecification = new CarModelSpecification(body, color, engine, null, seats, airco, wheels);
        });
        Exception exception15 = assertThrows(IllegalArgumentException.class, () -> {
            CarModelSpecification carModelSpecification = new CarModelSpecification(body, color, engine, gearbox, null, airco, wheels);
        });
        Exception exception16 = assertThrows(IllegalArgumentException.class, () -> {
            CarModelSpecification carModelSpecification = new CarModelSpecification(body, color, engine, gearbox, seats, null, wheels);
        });
        Exception exception17 = assertThrows(IllegalArgumentException.class, () -> {
            CarModelSpecification carModelSpecification = new CarModelSpecification(body, color, engine, gearbox, seats, airco, null);
        });
        Exception exception19 = assertThrows(IllegalArgumentException.class, () -> {
            CarModel carModel1 = new CarModel("", carModelSpecification);
        });
        Exception exception20 = assertThrows(IllegalArgumentException.class, () -> {
            CarModel carModel1 = new CarModel(null, carModelSpecification);
        });
        Exception exception18 = assertThrows(IllegalModelException.class, () -> {
            CarModel carModel1 = new CarModel("Jeep", carModelSpecification);
        });
        Exception exception21 = assertThrows(IllegalModelException.class, () -> {
            carModel.setModelName("Jeep");
        });
        Exception exception22 = assertThrows(IllegalArgumentException.class, () -> {
            carModel.setModelName("");
        });
        Exception exception23 = assertThrows(IllegalArgumentException.class, () -> {
            carModel.setModelName(null);
        });
    }
    @Test
    void testGettersModelSpecification() {
        assertEquals(body, carModelSpecification.getBody());
        assertEquals(airco, carModelSpecification.getAirco());
        assertEquals(color, carModelSpecification.getColor());
        assertEquals(engine, carModelSpecification.getEngine());
        assertEquals(gearbox, carModelSpecification.getGearbox());
        assertEquals(seats, carModelSpecification.getSeats());
        assertEquals(wheels, carModelSpecification.getWheels());
    }
    @Test
    void testMethodsCarModel() throws IllegalModelException {
        carModel.setModelName("Jaguar");
        assert carModel.getModelName().equalsIgnoreCase("jaguar");
        assert CarModel.getAvailableModels().size() == 1;
        CarModel.addModel("Astra");
        assert CarModel.getAvailableModels().size() == 2;
        assert carModel.getCarModelSpecification() == carModelSpecification;
        Exception exception23 = assertThrows(IllegalArgumentException.class, () -> {
            carModel.setCarModelSpecification(null);
        });
    }




}