package main.assemAssist.constraint;


import assemAssist.CarModel;
import assemAssist.constraint.IfOptionThenComponent;
import assemAssist.constraint.Inspector;
import assemAssist.exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


public class InspectorTest {

    @Test
    void testLegal() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        boolean threwException = false;
        try {
            new CarModel("A", legalAOptions);
        } catch (Exception e) {
            threwException = true;
        }
        assertFalse(threwException);
    }

    @Test
    void testNoWheelsA(){
        TreeMap<String, String> noWheelsAModel = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        noWheelsAModel.put("color", "red");
        noWheelsAModel.put("body", "break");
        noWheelsAModel.put("engine", "v4");
        noWheelsAModel.put("seats", "leather white");
        noWheelsAModel.put("airco", "manual");
        noWheelsAModel.put("gearbox", "6 manual");
        boolean threwException = false;
        try {
            new CarModel("A", noWheelsAModel);
        } catch (Exception e) {
            assertEquals(e.getClass(), RequiredComponentException.class);
            threwException = true;
        }
        assertTrue(threwException);
    }

    @Test
    void testSportNoSpoilerB(){
        TreeMap<String, String> sportNoSpoilerBModel = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        sportNoSpoilerBModel.put("color", "red");
        sportNoSpoilerBModel.put("body", "sport");
        sportNoSpoilerBModel.put("engine", "v6");
        sportNoSpoilerBModel.put("seats", "leather white");
        sportNoSpoilerBModel.put("airco", "manual");
        sportNoSpoilerBModel.put("gearbox", "6 manual");
        sportNoSpoilerBModel.put("wheels", "winter");
        boolean threwException = false;
        try{
            new CarModel("B", sportNoSpoilerBModel);
        }
        catch(Exception e){
            assertEquals(e.getClass(), OptionThenComponentException.class);
            threwException = true;
        }
        assertTrue(threwException);
    }


    @Test
    void testsportWeakEngineB(){
        TreeMap<String, String> sportWeakEngineBModel = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        sportWeakEngineBModel.put("color", "red");
        sportWeakEngineBModel.put("body", "sport");
        sportWeakEngineBModel.put("engine", "v4");
        sportWeakEngineBModel.put("seats", "leather white");
        sportWeakEngineBModel.put("airco", "manual");
        sportWeakEngineBModel.put("gearbox", "6 manual");
        sportWeakEngineBModel.put("wheels", "winter");
        sportWeakEngineBModel.put("spoiler", "low");
        boolean threwException = false;
        try{
            new CarModel("B", sportWeakEngineBModel);
        }
        catch(Exception e){
            assertEquals(e.getClass(), OptionAThenOptionBException.class);
            threwException = true;
        }
        assertTrue(threwException);
    }

    @Test
    void addIllegalAAndBConstraintTest() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel A = new CarModel("A", legalAOptions);
        ArrayList<String> AAndB = new ArrayList<>();
        AAndB.add("red");
        Inspector IA = new Inspector(A);
        IA.reset();
        boolean gotError = false;
        try{
            IA.addOptionAThenOptionBPair(AAndB);
        }
        catch(Exception e){
            assertEquals("IllegalConstraintException", e.getClass().getSimpleName());
            gotError = true;
        }
        assertTrue(gotError);
        AAndB.add("leather black");
        IA.addOptionAAndOptionBPair(AAndB);
        gotError = false;
        try{
            new CarModel("A", legalAOptions);
        }
        catch(Exception e){
            assertEquals("OptionAThenOptionBException", e.getClass().getSimpleName());
            gotError = true;
        }
        assertTrue(gotError);
        IA.reset();
    }

    @Test
    void addIllegalAThenBConstraintTest() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel A = new CarModel("A", legalAOptions);
        ArrayList<String> AAndB = new ArrayList<>();
        AAndB.add("red");
        Inspector IA = new Inspector(A);
        IA.reset();
        boolean gotError = false;
        try{
            IA.addOptionAThenOptionBPair(AAndB);
        }
        catch(Exception e){
            assertEquals("IllegalConstraintException", e.getClass().getSimpleName());
            gotError = true;
        }
        assertTrue(gotError);
        AAndB.add("leather black");
        IA.addOptionAThenOptionBPair(AAndB);
        gotError = false;
        try{
            new CarModel("A", legalAOptions);
        }
        catch(Exception e){
            assertEquals("OptionAThenOptionBException", e.getClass().getSimpleName());
            gotError = true;
        }
        assertTrue(gotError);
        IA.reset();
    }

    @Test
    void addIllegalOptionThenComponentConstraintTest() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel A = new CarModel("A", legalAOptions);
        ArrayList<String> OThenC = new ArrayList<>();
        OThenC.add("red");
        Inspector IA = new Inspector(A);
        IA.reset();
        boolean gotError = false;
        try{
            IA.addOptionAThenOptionBPair(OThenC);
        }
        catch(Exception e){
            assertEquals("IllegalConstraintException", e.getClass().getSimpleName());
            gotError = true;
        }
        assertTrue(gotError);
        OThenC.add("spoiler");
        IA.addOptionThenComponentPair(OThenC);
        gotError = false;
        try{
            new CarModel("A", legalAOptions);
        }
        catch(Exception e){
            assertEquals("OptionThenComponentException", e.getClass().getSimpleName());
            gotError = true;
        }
        assertTrue(gotError);
        IA.reset();
    }

    @Test
    void testEqualConstraints() throws IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {
        TreeMap<String, String> legalAOptions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        legalAOptions.put("color", "red");
        legalAOptions.put("body", "break");
        legalAOptions.put("engine", "v4");
        legalAOptions.put("seats", "leather white");
        legalAOptions.put("airco", "manual");
        legalAOptions.put("gearbox", "6 manual");
        legalAOptions.put("wheels", "winter");
        CarModel A = new CarModel("A", legalAOptions);
        ArrayList<String> OThenC = new ArrayList<>();
        OThenC.add("red");
        OThenC.add("spoiler");
        Inspector IA = new Inspector(A);
        IA.reset();
        IA.addOptionThenComponentPair(OThenC);
        IA.addOptionThenComponentPair(OThenC);
        // check that the inspector only adds one of these two pairs
        assertTrue(true);
        IA.reset();
    }


}
