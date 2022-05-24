package main.assemAssist;

import assemAssist.CarModel;
import assemAssist.CarOrder;
import assemAssist.Company;
import assemAssist.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
public class CarModelTest {
    static CarModel carModelA;
    static CarModel carModelB;
    static CarModel carModelC;
    static Company company;

    static {
        try {
            company = new Company();
        } catch (IllegalConstraintException e) {
            e.printStackTrace();
        }
    }


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
            CarModel carModelA = new CarModel("A", legalAOptions,company.getWorkingTimeWorkingStation("A"));
        }
        catch (IllegalConstraintException e){
            wasError = true;
        }
        assertFalse(wasError);
        carModelA = new CarModel("A", legalAOptions,company.getWorkingTimeWorkingStation("A"));
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
            new CarModel(null, legalAOptions,0);
        }
        catch (IllegalArgumentException e){
            threwError = true;
        }
        assertTrue(threwError);
        threwError = false;
        try{
            new CarModel("", legalAOptions,0);
        }
        catch (IllegalArgumentException e){
            threwError = true;
        }
        assertTrue(threwError);
        threwError = false;
        try{
            new CarModel("  ", legalAOptions, 0);
        }
        catch (IllegalArgumentException e){
            threwError = true;
        }
        assertTrue(threwError);
        threwError = false;
        try{
            new CarModel("A", null,0);
        }
        catch (IllegalArgumentException e){
            threwError = true;
        }
        assertTrue(threwError);
        CarModel a = new CarModel("A", legalAOptions,company.getWorkingTimeWorkingStation("A"));
        assertEquals("A", a.getModelName());
        assertEquals(legalAOptions, a.getChosenOptions());
    }

    @Test
    void testGetChosenOptionsString(){
        System.out.println("Testing getChosenOptionsString");
        System.out.println(carModelA.getChosenOptionsString());
    }

}
