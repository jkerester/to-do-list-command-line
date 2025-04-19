import java.util.ArrayList;
import java.util.Scanner;

import java.util.Iterator;
import java.util.ListIterator;

public class TaskList {
	ArrayList<Task> tasks = new ArrayList<>();
	Scanner input = new Scanner(System.in);
	
	public void addTask(String task, String status) {
		Task newTask = new Task(task, status);
		tasks.add(newTask);
		System.out.println("Task successfully added!");
	}
	
	public void deleteTask(String task) {
		Iterator<Task> iterator = tasks.iterator();
		boolean isInList = false;
		while (iterator.hasNext()) {
			Task currentTask = iterator.next();
			String currentTaskName = currentTask.getTask();
			//System.out.println(currentTaskName);
			if (currentTaskName.equals(task)) { //here
				iterator.remove();
				isInList = true;
			}
		}
		if (isInList == false) {
			System.out.println("Task cannot be deleted because it is not in the list");
		}
	}
	
	public void editTask(String task) {
		ListIterator<Task> iterator = tasks.listIterator();
		boolean isInList = false;
		while (iterator.hasNext()) {
			Task currentTask = iterator.next();
			String currentTaskName = currentTask.getTask();
			if (currentTaskName.equals(task)) {
				System.out.println("What would you like to edit this task to?");
				String newTaskName = input.nextLine();
				Task newTask = new Task(newTaskName, "Incomplete");
				iterator.set(newTask);
				isInList = true;
			}
		}
		if (isInList == false) {
			System.out.println("Task cannot be edited because it is not in the list");
		}
	}
	
	public void markTaskComplete(String task) {
		ListIterator<Task> iterator = tasks.listIterator();
		boolean isInList = false;
		while (iterator.hasNext()) {
			Task currentTask = iterator.next();
			String currentTaskName = currentTask.getTask();
			if (currentTaskName.equals(task)) {
				currentTask.setStatus(1);
			}
	}
	
	
	}
}
