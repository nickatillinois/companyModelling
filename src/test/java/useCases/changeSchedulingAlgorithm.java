package useCases;

import assemAssist.Company;
import assemAssist.Mechanic;
import assemAssist.exceptions.*;
import controller.GarageHolderController;
import controller.ManagerController;
import controller.MechanicController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.GarageHolderUI;
import ui.ManagerUI;
import ui.MechanicUI;
import ui.UI;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class changeSchedulingAlgorithm {


    @BeforeAll
    static void init(){
        ByteArrayInputStream in = new ByteArrayInputStream("3\na\n1\n4".getBytes());
        System.setIn(in);

    }

    @Test
    public void setBatch() throws IllegalCompletionDateException, IllegalConstraintException, IllegalModelException, OptionThenComponentException, OptionAThenOptionBException, RequiredComponentException {

        Company company = new Company();
        new UI(new GarageHolderUI(new GarageHolderController(company)),new ManagerUI(new ManagerController(company)),
                new MechanicUI(new MechanicController(new Mechanic(company.getProductionScheduler().getAssemblyLine()))));
        System.setIn(new ByteArrayInputStream("".getBytes()));
        assertEquals(company.getProductionScheduler().getSchedulers().get(0),company.getProductionScheduler().getSchedulingAlgorithm());

    }
}
