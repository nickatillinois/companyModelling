import assemAssist.AssemblyTask;
import assemAssist.exceptions.IllegalChoiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssemblyTaskTest {

    static AssemblyTask task1;
    static AssemblyTask task2;

    @BeforeEach
    void init() throws IllegalChoiceException {
        task1 = new AssemblyTask("paint","paint the car blue");
        task2 = new AssemblyTask("seats","install leather black seats");
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