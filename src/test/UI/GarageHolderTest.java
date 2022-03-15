package UI;

import ui.GarageHolderUI;
import controller.GarageHolderController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GarageHolderTest {

    @Test
    void testLogin() {
        GarageHolderController garageHolderController = new GarageHolderController();
        List<String> orderingForm = new ArrayList<>();
        orderingForm.add("Color: red, blue");
        orderingForm.add("Seats: 2, 4");
        List<String> orderingFormAsked = garageHolderController.selectModel("Jaguar");

        assertEquals(orderingForm,orderingFormAsked);
    }

}