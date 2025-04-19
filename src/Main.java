
public class Main {

	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		//tasklist.addTask("Do the dishes", "Incomplete");
		ui.userAddTask();
		ui.userAddTask();
		ui.userAddTask();
		ui.printTasklist();
		ui.userEditTask();
		ui.userDeleteTask();
		ui.userMarkTaskComplete();
		ui.printTasklist();
	}

}
