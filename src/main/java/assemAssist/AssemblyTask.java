package assemAssist;

import java.util.Objects;

/**
 * Class representing an assembly task.
 *
 * @author SWOP Team 10
 */
public class AssemblyTask {

    /**
     *  A string representing the name of the component this assembly task works on.
     */
    private final String name;
    /**
     * A boolean representing whether this assembly task is completed.
     */
    private boolean isCompleted;
    /**
     * A string containing this task's description.
     */
    private final String taskDefinition;

    /**
     * Creates an assembly task at the given work station for a given component, with the given task definition.
     *
     * @param name the name for this assembly task
     * @param taskDefinition the description for this assembly task
     * @throws IllegalArgumentException | name == null
     *                                  | name.isEmpty()
     *                                  | taskDefinition == null
     *                                  | taskDefinition.isEmpty()
     */
    public AssemblyTask(String name, String taskDefinition) {
        if (name == null) { throw new IllegalArgumentException("The name for this task cannot be null."); }
        if (name.isEmpty()) throw new IllegalArgumentException("The given name cannot be empty.");
        if (taskDefinition == null) throw new IllegalArgumentException("The given definition cannot be null.");
        if (taskDefinition.isEmpty()) { throw new IllegalArgumentException("The given definition cannot be empty."); }

        this.name = name;
        this.isCompleted = false;
        this.taskDefinition = taskDefinition;
    }

    /**
     * Returns whether this task is completed or not.
     *
     * @return if this task is complete
     */
    public boolean getIsCompleted() {
        return isCompleted;
    }

    /**
     * Sets this task's status to complete or not complete.
     */
    public void setCompleted() {
        isCompleted = true;
    }

    /**
     * Returns the task definition belonging to this task.
     */
    public String getTaskDefinition(){
        return taskDefinition;
    }

    /**
     * Returns the name of this assembly task, representing the component it works on.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssemblyTask that = (AssemblyTask) o;
        return Objects.equals(name, that.getName()) && Objects.equals(taskDefinition, that.getTaskDefinition());
    }
}
