import assemAssist.ProductionScheduler;
import assemAssist.carOrder.*;
import assemAssist.exceptions.IllegalChoiceException;
import assemAssist.exceptions.IllegalCompletionDateException;
import assemAssist.exceptions.IllegalModelException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static assemAssist.ProductionScheduler.getAvailableModels;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarOrderTest {

    static Body body;

    static {
        try {
            body = new Body("sedan");
        } catch (IllegalChoiceException e) {
            e.printStackTrace();
        }
    }

    static Airco airco;

    static {
        try {
            airco = new Airco("manual");
        } catch (IllegalChoiceException e) {
            e.printStackTrace();
        }
    }

    static Wheels wheels;

    static {
        try {
            wheels = new Wheels("comfort");
        } catch (IllegalChoiceException e) {
            e.printStackTrace();
        }
    }

    static Seats seats;

    static {
        try {
            seats = new Seats("leather white");
        } catch (IllegalChoiceException e) {
            e.printStackTrace();
        }
    }

    static Gearbox gearbox;

    static {
        try {
            gearbox = new Gearbox("6 speed manual");
        } catch (IllegalChoiceException e) {
            e.printStackTrace();
        }
    }

    static Engine engine;

    static {
        try {
            engine = new Engine("standard 2l 4 cilinders");
        } catch (IllegalChoiceException e) {
            e.printStackTrace();
        }
    }

    static Color color;

    static {
        try {
            color = new Color("red");
        } catch (IllegalChoiceException e) {
            e.printStackTrace();
        }
    }

    static CarModelSpecification carModelSpecification;
    static CarModel carModel;
    static CarOrder carOrder;

    @BeforeAll
    static void init() throws IllegalModelException {
        ProductionScheduler.addModel("Jaguar");
        carModelSpecification = new CarModelSpecification(body, color, engine, gearbox, seats, airco, wheels);
        carModel = new CarModel("Jaguar", carModelSpecification);
        carOrder = new CarOrder("Danny Smeets", carModel);
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
        assert getAvailableModels().size() == 1;
        ProductionScheduler.addModel("Astra");
        assert getAvailableModels().size() == 2;
        assert carModel.getCarModelSpecification() == carModelSpecification;
        Exception exception23 = assertThrows(IllegalArgumentException.class, () -> {
            carModel.setCarModelSpecification(null);
        });
    }
    @Test
    void testMethodsCarOrder() throws IllegalModelException, IllegalCompletionDateException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            carOrder = new CarOrder(null, carModel);
        });
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            carOrder = new CarOrder("", carModel);
        });
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            carOrder = new CarOrder("jan decro", null);
        });
        assertEquals(carOrder.getGarageholder(), "Danny Smeets");
        carOrder.setGarageholder("Kirlin Debie");
        assertEquals(carOrder.getGarageholder(), "Kirlin Debie");
        assertEquals(carOrder.getCarModel(), carModel);
        assert !carOrder.isCompleted();
        carOrder.setCompleted(true);
        assert carOrder.isCompleted();
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            carOrder.setCarModel(null);
        });
        Exception exception6 = assertThrows(IllegalArgumentException.class, () -> {
            carOrder.setCompletionTime(null);
        });
        LocalDateTime date = LocalDateTime.now();
        carOrder.setCompletionTime(date);
        assert carOrder.getCompletionTime().equals(date);
    }
    @Test
    void testAirco() throws IllegalModelException {
        int size1 = Airco.getAvailableChoices().length;
        assert size1 == 2;
        assert airco.getChosenChoice().equalsIgnoreCase("ManuAl");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            airco = new Airco(null);
        });
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            airco = new Airco("");
        });
        Exception exception3 = assertThrows(IllegalChoiceException.class, () -> {
            airco = new Airco("red");
        });
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            airco.setChosenChoice(null);
        });
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> {
            airco.setChosenChoice("");
        });
        Exception exception6 = assertThrows(IllegalChoiceException.class, () -> {
            airco.setChosenChoice("leather white");
        });
    }
    @Test
    void testBody() throws IllegalModelException {
        int size1 = Body.getAvailableChoices().length;
        assert size1 == 2;
        assert body.getChosenChoice().equalsIgnoreCase("sedan");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            body = new Body(null);
        });
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            body = new Body("");
        });
        Exception exception3 = assertThrows(IllegalChoiceException.class, () -> {
            body = new Body("red");
        });
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            body.setChosenChoice(null);
        });
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> {
            body.setChosenChoice("");
        });
        Exception exception6 = assertThrows(IllegalChoiceException.class, () -> {
            body.setChosenChoice("leather white");
        });
    }
    @Test
    void testColor() throws IllegalModelException {
        int size1 = Color.getAvailableChoices().length;
        assert size1 == 4;
        assert color.getChosenChoice().equalsIgnoreCase("reD");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            color = new Color(null);
        });
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            color = new Color("");
        });
        Exception exception3 = assertThrows(IllegalChoiceException.class, () -> {
            color = new Color("manual");
        });
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            color.setChosenChoice(null);
        });
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> {
            color.setChosenChoice("");
        });
        Exception exception6 = assertThrows(IllegalChoiceException.class, () -> {
            color.setChosenChoice("leather white");
        });
    }
    @Test
    void testEngine() throws IllegalModelException, IllegalChoiceException {
        int size1 = Engine.getAvailableChoices().length;
        assert size1 == 2;
        engine.setChosenChoice("standard 2l 4 cilinders");
        assert engine.getChosenChoice().equalsIgnoreCase("Standard 2l 4 cilinders");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            engine = new Engine(null);
        });
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            engine = new Engine("");
        });
        Exception exception3 = assertThrows(IllegalChoiceException.class, () -> {
            engine = new Engine("red");
        });
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            engine.setChosenChoice(null);
        });
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> {
            engine.setChosenChoice("");
        });
        Exception exception6 = assertThrows(IllegalChoiceException.class, () -> {
            engine.setChosenChoice("leather white");
        });
    }
    @Test
    void testGearbox() throws IllegalModelException {
        int size1 = Gearbox.getAvailableChoices().length;
        assert size1 == 2;
        assert gearbox.getChosenChoice().equalsIgnoreCase("6 speed manual");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gearbox = new Gearbox(null);
        });
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            gearbox = new Gearbox("");
        });
        Exception exception3 = assertThrows(IllegalChoiceException.class, () -> {
            gearbox = new Gearbox("red");
        });
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            gearbox.setChosenChoice(null);
        });
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> {
            gearbox.setChosenChoice("");
        });
        Exception exception6 = assertThrows(IllegalChoiceException.class, () -> {
            gearbox.setChosenChoice("leather white");
        });
    }
    @Test
    void testSeats() throws IllegalModelException {
        int size1 = Seats.getAvailableChoices().length;
        assert size1 == 3;
        assert seats.getChosenChoice().equalsIgnoreCase("leather white");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            seats = new Seats(null);
        });
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            seats = new Seats("");
        });
        Exception exception3 = assertThrows(IllegalChoiceException.class, () -> {
            seats = new Seats("red");
        });
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            seats.setChosenChoice(null);
        });
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> {
            seats.setChosenChoice("");
        });
        Exception exception6 = assertThrows(IllegalChoiceException.class, () -> {
            seats.setChosenChoice("manual");
        });
    }
    @Test
    void testWheels() throws IllegalModelException {
        int size1 = Wheels.getAvailableChoices().length;
        assert size1 == 2;
        assert wheels.getChosenChoice().equalsIgnoreCase("comfort");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            wheels = new Wheels(null);
        });
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            wheels = new Wheels("");
        });
        Exception exception3 = assertThrows(IllegalChoiceException.class, () -> {
            wheels = new Wheels("red");
        });
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            wheels.setChosenChoice(null);
        });
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> {
            wheels.setChosenChoice("");
        });
        Exception exception6 = assertThrows(IllegalChoiceException.class, () -> {
            wheels.setChosenChoice("leather white");
        });
    }



}