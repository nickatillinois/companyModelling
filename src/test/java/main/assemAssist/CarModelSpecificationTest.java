package main.assemAssist;

import assemAssist.CarModelSpecification;
import assemAssist.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class CarModelSpecificationTest {
    static Company company;


    @BeforeAll
    static void init() {
        company = new Company();
    }

    @Test
    void testConstructorCarModelSpecification(){
        TreeMap<String, HashSet<String>> D = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        D.put("Body", new HashSet<>() {{
            add("Sedan");
            add("Break");
        }});
        D.put("Color", new HashSet<>() {{
            add("red");
            add("blue");
            add("black");
            add("white");
        }});
        D.put("Engine", new HashSet<>() {{
            add("V4");
            add("V6");
        }});
        D.put("Gearbox", new HashSet<>() {{
            add("6 manual");
            add("5 manual");
            add("5 automatic");
        }});
        D.put("Seats", new HashSet<>() {{
            add("leather white");
            add("leather black");
            add("vinyl grey");
        }});
        D.put("Airco", new HashSet<>() {{
            add("Manual");
            add("Automatic");
        }});
        D.put("Wheels", new HashSet<>() {{
            add("winter");
            add("comfort");
            add("sports");
        }});
        boolean got_error = false;
        try{
            new CarModelSpecification(null, 80, D);
        }
        catch (IllegalArgumentException e){
            assertEquals("A modelName cannot be null.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            new CarModelSpecification("", 80, D);
        }
        catch (IllegalArgumentException e){
            assertEquals("A modelName cannot be the empty string.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            new CarModelSpecification("   ", 80, D);
        }
        catch (IllegalArgumentException e){
            assertEquals("A modelName cannot be whitespace.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            new CarModelSpecification("D", 0, D);
        }
        catch (IllegalArgumentException e){
            assertEquals("A standard task time must be greater than 0.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
        got_error = false;
        try{
            new CarModelSpecification("D", 80, null);
        }
        catch (IllegalArgumentException e){
            assertEquals("A car model specification cannot be null.", e.getMessage());
            got_error = true;
        }
        assertTrue(got_error);
    }

    @Test
    void testSetModelName() {
        String oldName;
        TreeMap<String, HashSet<String>> oldOptions;
        boolean got_error;
        for (CarModelSpecification c : company.getCatalog().getAvailableModels()) {
            oldName = c.getModelName();
            got_error = false;
            try{
                c.setModelName(null);
            }
            catch (IllegalArgumentException e){
                assertEquals("A modelName cannot be null.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.setModelName("");
            }
            catch (IllegalArgumentException e){
                assertEquals("A modelName cannot be the empty string.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.setModelName("     ");
            }
            catch (IllegalArgumentException e){
                assertEquals("A modelName cannot be whitespace.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            c.setModelName("D");
            assertEquals("D", c.getModelName());
            c.setModelName(oldName);
            oldOptions = c.getAvailableOptions();
            c.setAvailableOptions(new TreeMap<>());
            c.setAvailableOptions(oldOptions);
        }
    }

    @Test
    void testToStringCarModelSpecification() {
        for (CarModelSpecification c : company.getCatalog().getAvailableModels()) {
            // print a nice box
            System.out.println("+" + String.join("", Collections.nCopies(c.toString().length() + 2, "-")) + "+");
            System.out.println("| " + c + " |");
            System.out.println("+" + String.join("", Collections.nCopies(c.toString().length() + 2, "-")) + "+");
        }
    }

    @Test
    void testAddOption() {
        boolean got_error;
        for (CarModelSpecification c : company.getCatalog().getAvailableModels()) {
            got_error = false;
            try{
                c.addOption(null, "D");
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be null.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addOption("", "D");
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be the empty string.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addOption("     ", "D");
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be whitespace.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addOption("D", null);
            }
            catch (IllegalArgumentException e){
                assertEquals("An option cannot be null.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addOption("D", "");
            }
            catch (IllegalArgumentException e){
                assertEquals("An option cannot be the empty string.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addOption("D", "     ");
            }
            catch (IllegalArgumentException e){
                assertEquals("An option cannot be whitespace.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            c.addOption("Price", "28000");
            assertTrue(c.getAvailableOptions().get("price").contains("28000"));
            c.addOption("wheels", "4");
            assertTrue(c.getAvailableOptions().get("wheels").contains("4"));
        }
    }

    @Test
    void testAddComponent() {
        boolean got_error;
        HashSet<String> options = new HashSet<>();
        for (CarModelSpecification c : company.getCatalog().getAvailableModels()) {
            got_error = false;
            try{
                c.addComponent(null, options);
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be null.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addComponent("", options);
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be the empty string.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addComponent("    ", options);
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be whitespace.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.addComponent("D", null);
            }
            catch (IllegalArgumentException e){
                assertEquals("There must be at least one option for the given component.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            c.addComponent("beamGuns", options);
            assertTrue(c.getAvailableOptions().containsKey("beamGuns"));
        }
    }

    @Test
    void testRemoveComponent() {
        boolean got_error;
        for (CarModelSpecification c : company.getCatalog().getAvailableModels()) {
            got_error = false;
            try{
                c.removeComponent(null);
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be null.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.removeComponent("");
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be the empty string.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.removeComponent("    ");
            }
            catch (IllegalArgumentException e){
                assertEquals("A component cannot be whitespace.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
            got_error = false;
            try{
                c.removeComponent("not_a_component");
            }
            catch (IllegalArgumentException e){
                assertEquals("The given component does not exist.", e.getMessage());
                got_error = true;
            }
            assertTrue(got_error);
        }
    }

}
