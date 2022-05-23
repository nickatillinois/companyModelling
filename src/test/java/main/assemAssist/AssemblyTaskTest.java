package main.assemAssist;

import assemAssist.AssemblyTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssemblyTaskTest {

    static AssemblyTask task1;
    static AssemblyTask task2;

    @BeforeEach
    void init() {
        task1 = new AssemblyTask("paint","paint the car blue");
        task2 = new AssemblyTask("seats","install leather black seats");
    }

    @Test
    void constructorTest() {
        task1 = new AssemblyTask("name","definition");
        assertFalse(task1.getIsCompleted());
        assertEquals("name", task1.getName());
        assertEquals("definition",task1.getTaskDefinition());
    }

    @Test
    void constructorTestNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> task1 = new AssemblyTask("","definition"));
    }

    @Test
    void constructorTestDefNull() {
        assertThrows(IllegalArgumentException.class, () -> task1 = new AssemblyTask("spoiler",null));
    }

    @Test
    void constructorTestDefEmpty() {
        assertThrows(IllegalArgumentException.class, () -> task1 = new AssemblyTask("spoiler",""));
    }

    @Test
    void getAndSetIsCompleted() {
        assertFalse(task1.getIsCompleted());
        assertFalse(task2.getIsCompleted());
        task1.setCompleted();
        assertTrue(task1.getIsCompleted());
        assertFalse(task2.getIsCompleted());
        task2.setCompleted();
        assertTrue(task1.getIsCompleted());
        assertTrue(task2.getIsCompleted());
    }

    @Test
    void getTaskDefinition() {
        assertEquals(task1.getTaskDefinition(),"paint the car blue");
        assertEquals(task2.getTaskDefinition(),"install leather black seats");
    }

    @Test
    void getName() {
        assertEquals(task1.getName(),"paint");
        assertEquals(task2.getName(),"seats");
    }

    @Test
    void testEquals() {
        task1 = new AssemblyTask("seats","install leather black seats");
        task2 = new AssemblyTask("seats","install leather black seats");
        assertEquals(task1,task2);
        task1 = new AssemblyTask("seats","install leather white seats");
        task2 = new AssemblyTask("seats","install leather black seats");
        assertNotEquals(task1,task2);
    }
}