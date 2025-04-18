import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
	ArrayList<Task> tasks = new ArrayList<>();
	
	public void addTask(String task, String status) {
		Task newTask = new Task(task, status);
		tasks.add(newTask);
		System.out.println("Task successfully added!");
	}
	
	public void deleteTask(String task) {
		Iterator<Task> iterator = tasks.iterator();
		while (iterator.hasNext()) {
			Task currentTask = iterator.next();
			if (currentTask.getTask() == task) { //here
				iterator.remove();
			}
		}
	}

}
