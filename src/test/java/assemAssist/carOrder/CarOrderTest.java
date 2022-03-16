package assemAssist.carOrder;

import assemAssist.exceptions.IllegalChoiceException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CarOrderTest {

    @Test
    void setChoiceExceptionsTest() throws IllegalChoiceException {
        Body body = new Body();
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
    }


}