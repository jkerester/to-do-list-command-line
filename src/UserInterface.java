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
		System.out.println("Enter the task here: ");
		String task = input.nextLine();
		tasklist.deleteTask(task);
	}
	
	public void printTasklist() {
		for (int i = 0;  i < tasklist.tasks.size(); i++) {
			Task currentTask = tasklist.tasks.get(i);
			System.out.println(currentTask.task + " " + currentTask.status);
		}
	}

}
