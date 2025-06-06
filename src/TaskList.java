import java.util.ArrayList;
import java.util.Scanner;

import java.util.Iterator;
import java.util.ListIterator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileReader;
import java.io.FileWriter;

public class TaskList  {
	private ArrayList<Task> tasks = new ArrayList<>();
	Scanner input = new Scanner(System.in);
	
	public ArrayList<Task> getTaskList() {
		return this.tasks;
	}
	
	public void printTasklist() {
		for (int i = 0;  i < tasks.size(); i++) {
			Task currentTask = tasks.get(i);
			System.out.println(currentTask.task + " " + currentTask.status);
			}
		}
	
	public void loadTaskList(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				Task readTask = new Task(values[0], values[1], values[2]);
				tasks.add(readTask);
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveTaskList(String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (int i = 0;  i < tasks.size(); i++) {
				Task currentTask = tasks.get(i);
				writer.write(currentTask.getTask());
				writer.write(", ");
				writer.write(currentTask.getStatus().trim());
				writer.write(", ");
				writer.write(currentTask.getDateTimeAdded());
				System.out.println("|" + currentTask.getStatus() + "|");
				writer.newLine();
				}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addTask(String task, String status) {
		Task newTask = new Task(task, status, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
		tasks.add(newTask);
		System.out.println("Task successfully added!");
	}
	
	public void deleteTask(Task task) {
		Iterator<Task> iterator = tasks.iterator();
		boolean isInList = false;
		while (iterator.hasNext()) {
			Task currentTask = iterator.next();
			String currentTaskName = currentTask.getTask();
			//System.out.println(currentTaskName);
			if (currentTaskName.equals(task.getTask())) { //here
				iterator.remove();
				isInList = true;
			}
		}
		if (isInList == false) {
			System.out.println("Task cannot be deleted because it is not in the list");
		}
	}
	
	public void editTask(Task task, String newTaskName) { //3 parameters? task object, previous task name, new name (jtextfield)
		ListIterator<Task> iterator = tasks.listIterator();
		boolean isInList = false;
		while (iterator.hasNext()) {
			Task currentTask = iterator.next();
			String currentTaskName = currentTask.getTask();
			if (currentTaskName.equals(task.getTask())) {
				//System.out.println("What would you like to edit this task to?");
				//String newTaskName = input.nextLine();
				Task newTask = new Task(newTaskName, task.getStatus(), task.getDateTimeAdded());
				iterator.set(newTask);
				isInList = true;
			}
		}
		if (isInList == false) {
			System.out.println("Task cannot be edited because it is not in the list");
		}
	}
	
	public void markTaskComplete(Task task, int status) {
		ListIterator<Task> iterator = tasks.listIterator();
		boolean isInList = false;
		while (iterator.hasNext()) {
			Task currentTask = iterator.next();
			String currentTaskName = currentTask.getTask();
			if (currentTaskName.equals(task.getTask())) {
				currentTask.setStatus(status);
				isInList = true;
			}
	}
		if (isInList == false) {
			System.out.println("Task cannot be edited because it is not in the list");
		}
	
	
	}
}
