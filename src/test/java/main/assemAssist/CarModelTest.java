package main.assemAssist;

import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
public class CarModelTest {
    static CarModel carModelA;
    static CarModel carModelB;
    static CarModel carModelC;


    @BeforeAll
    static void init() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        boolean wasError = false;
        try{
            CarModel carModelA = new CarModel("A", legalAOptions);
        }
        catch (IllegalConstraintException e){
            wasError = true;
        }
        assertFalse(wasError);
        carModelA = new CarModel("A", legalAOptions);
    }

    @Test
    void testConstructor() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        boolean threwError = false;
        try{
            new CarModel(null, legalAOptions);
        }
        catch (IllegalArgumentException e){
            threwError = true;
        }
        assertTrue(threwError);
        threwError = false;
        try{
            new CarModel("", legalAOptions);
        }
        catch (IllegalArgumentException e){
            threwError = true;
        }
        assertTrue(threwError);
        threwError = false;
        try{
            new CarModel("  ", legalAOptions);
        }
        catch (IllegalArgumentException e){
            threwError = true;
        }
        assertTrue(threwError);
        threwError = false;
        try{
            new CarModel("A", null);
        }
        catch (IllegalArgumentException e){
            threwError = true;
        }
        assertTrue(threwError);
        CarModel a = new CarModel("A", legalAOptions);
        assertEquals("A", a.getModelName());
        assertEquals(legalAOptions, a.getChosenOptions());
    }

    @Test
    void testGetChosenOptionsString(){
        System.out.println("Testing getChosenOptionsString");
        System.out.println(carModelA.getChosenOptionsString());
    }

}
