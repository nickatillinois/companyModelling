import assemAssist.AssemblyTask;
import assemAssist.carOrder.Color;
import assemAssist.carOrder.Seats;
import assemAssist.exceptions.IllegalChoiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssemblyTaskTest {

    static AssemblyTask task1;
    static AssemblyTask task2;

    @BeforeEach
    void init() throws IllegalChoiceException {
        task1 = new AssemblyTask("paint",new Color("blue"),"paint the car blue");
        task2 = new AssemblyTask("seats",new Seats("leather black"),"install leather black seats");
    }

    @Test
    void getAndSetIsCompleted() {
        assert(!task1.getIsCompleted());
        assert(!task2.getIsCompleted());
        task1.setIsCompleted(true);
        assert(task1.getIsCompleted());
        assert(!task2.getIsCompleted());
        task2.setIsCompleted(true);
        assert(task1.getIsCompleted());
        assert(task2.getIsCompleted());
    }

    @Test
    void getComponent() {
        assert(task1.getComponent().getChosenChoice().equals("blue"));
        assert(task2.getComponent().getChosenChoice().equals("leather black"));
    }

    @Test
    void getTaskDefinition() {
        assert(task1.getTaskDefinition().equals("paint the car blue"));
        assert(task2.getTaskDefinition().equals("install leather black seats"));
    }

    @Test
    void getName() {
        assert(task1.getName().equals("paint"));
        assert(task2.getName().equals("seats"));
    }
}