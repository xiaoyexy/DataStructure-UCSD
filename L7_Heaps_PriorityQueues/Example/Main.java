// Main.java
package L7_Heaps_PriorityQueues.Example;

import L7_Heaps_PriorityQueues.PriorityQueue;

import java.util.Comparator;

/**
 * Simple task scheduler using a priority queue.
 *
 * Program output:
 *      Priority = 1, Name = Play audio
 *      Priority = 2, Name = Refresh screen
 *      Priority = 3, Name = Handle mouse move
 *      Priority = 4, Name = Swap process memory
 *      Priority = 5, Name = Write data to disk
 *      Priority = 6, Name = Close file
 *      Priority = 7, Name = Write log entry
 */
public class Main {
    public static void main(String args[]) {
        // Populate prioritized tasks
        PriorityQueue<Task> tasks = new PriorityQueue<Task>(
                new Comparator<Task>() {
                    public int compare(Task t1, Task t2) {
                        return t2.getPriority() - t1.getPriority();
                    }
                }
        );
        tasks.insert(new Task(3, "Handle mouse move"));
        tasks.insert(new Task(6, "Close file"));
        tasks.insert(new Task(5, "Write data to disk"));
        tasks.insert(new Task(2, "Refresh screen"));
        tasks.insert(new Task(4, "Swap process memory"));
        tasks.insert(new Task(1, "Play audio"));
        tasks.insert(new Task(7, "Write log entry"));

        // Output tasks in priority order
        while (!tasks.isEmpty()) {
            Task task = tasks.extract();

            System.out.println("Priority = " + task.getPriority()
                    + ", Name = " + task.getName());
        }
    }
}
