import java.util.Scanner;
public class UserInterface {
	Scanner input = new Scanner(System.in);
	TaskList tasklist = new TaskList();
	
	public void userAddTask() {
		System.out.println("Enter your task here: ");
		String task = input.nextLine();
		tasklist.addTask(task, "Incomplete");
	}
	
	public void userDeleteTask() {
		System.out.println("Enter the task to delete here: ");
		String task = input.nextLine();
		tasklist.deleteTask(task);
	}
	
	public void userEditTask() {
		System.out.println("Enter the task to edit here: ");
		String task = input.nextLine();
		tasklist.editTask(task);
	}
	
	public void userMarkTaskComplete() {
		System.out.println("Enter the task to mark complete: ");
		String task = input.nextLine();
		tasklist.markTaskComplete(task);
	}
	
	public void printTasklist() {
		for (int i = 0;  i < tasklist.tasks.size(); i++) {
			Task currentTask = tasklist.tasks.get(i);
			System.out.println(currentTask.task + " " + currentTask.status);
		}
	}

}
