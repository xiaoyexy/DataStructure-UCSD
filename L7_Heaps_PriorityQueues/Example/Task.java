// Task.java
package L7_Heaps_PriorityQueues.Example;

public class Task {
    private int priority;   // Low value means high priority
    String Name;

    public Task(int priority, String name) {
        this.priority = priority;
        Name = name;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return Name;
    }
}
