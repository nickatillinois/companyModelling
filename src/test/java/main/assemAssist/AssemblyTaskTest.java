package main.assemAssist;

import assemAssist.AssemblyTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssemblyTaskTest {

    static AssemblyTask task1;
    static AssemblyTask task2;

    @BeforeEach
    void init() {
        task1 = new AssemblyTask("paint","paint the car blue");
        task2 = new AssemblyTask("seats","install leather black seats");
    }

    @Test
    void getAndSetIsCompleted() {
        assert(!task1.getIsCompleted());
        assert(!task2.getIsCompleted());
        task1.setCompleted();
        assert(task1.getIsCompleted());
        assert(!task2.getIsCompleted());
        task2.setCompleted();
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

    @Test
    void testEquals() {
        task1 = new AssemblyTask("seats","install leather black seats");
        task2 = new AssemblyTask("seats","install leather black seats");
        assert(task1.equals(task2));
        task1 = new AssemblyTask("seats","install leather white seats");
        task2 = new AssemblyTask("seats","install leather black seats");
        assert(!task1.equals(task2));
    }
}