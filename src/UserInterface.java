import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserInterface implements ActionListener {
	Scanner input = new Scanner(System.in);
	TaskList tasklist = new TaskList();
	TaskList sortedTaskList = new TaskList();
	int count = 0;
	private JFrame frame;
	private JPanel panel;
	private JPanel topPanel;
	private JPanel centerPanel;
	private String filePath;
	private JTextField userText;
	private JButton addButton;
	private String sortOption;
	private JComboBox<String> sortButton;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	public UserInterface() {
		filePath = "C:\\Users\\jkere\\Desktop\\todolistexample.csv";
		JFrame frame = new JFrame();
		frame.setSize(1000,1000);
		
		userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		
		addButton = new JButton("Add task");
		addButton.setBounds(150,20,165,25);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				userAddTask();
			}	
		});
		
		String[] sortingOptions = {"Order added (oldest first)", "Order added (most recent first)", "Alphabetical", "Incomplete first", "Complete first"};
		sortButton = new JComboBox(sortingOptions);
		sortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortOption = sortButton.getSelectedItem().toString();
				populateTaskListGui();
			}
		});
		
		
		
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(0,3));
		topPanel.add(userText);
		topPanel.add(addButton);
		topPanel.add(sortButton);
		
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0,4)); //should not use grid layout
		populateTaskListGui();
		
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //add way so that when program ends, the list is sorted and saved in oldest - most recent added
		frame.setTitle("To Do List");
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() { //sorts and saves the task list to default option on window exit
			public void windowClosing(WindowEvent we) {
				sortTaskList("Order added (oldest first)");
				tasklist.saveTaskList(filePath);
				try { Runtime.getRuntime().exec("taskkill /f /im java.exe");
				} catch (IOException e4) {
					e4.printStackTrace();
				}
			}
		}
				
				);
	}
	
	public void sortTaskList(String sortOption) {
		if (sortOption == "Order added (most recent first)") {
			Collections.sort(sortedTaskList.getTaskList(), new Comparator<Task>() {
				@Override
				public int compare(Task task1, Task task2) {
					LocalDateTime dateTimeAdded1 = LocalDateTime.parse(task1.getDateTimeAdded().trim(), formatter);
					LocalDateTime dateTimeAdded2 = LocalDateTime.parse(task2.getDateTimeAdded().trim(), formatter);
					return dateTimeAdded1.compareTo(dateTimeAdded2);
				}
			});
			Collections.reverse(sortedTaskList.getTaskList());
		}
		else if (sortOption == "Order added (oldest first)") {
			Collections.sort(sortedTaskList.getTaskList(), new Comparator<Task>() {
				@Override
				public int compare(Task task1, Task task2) {
					LocalDateTime dateTimeAdded1 = LocalDateTime.parse(task1.getDateTimeAdded().trim(), formatter);
					LocalDateTime dateTimeAdded2 = LocalDateTime.parse(task2.getDateTimeAdded().trim(), formatter);
					return dateTimeAdded1.compareTo(dateTimeAdded2);
				}
				
			});
			//return sortedTaskList;
		}
		else if (sortOption == "Alphabetical") {
			Collections.sort(sortedTaskList.getTaskList());
			tasklist.printTasklist();
		}
		else if (sortOption == "Incomplete first") {
			Collections.sort(sortedTaskList.getTaskList(), new Comparator<Task>() {
				@Override
				public int compare(Task task1, Task task2) {
					return task1.getStatus().compareTo(task2.getStatus());
				}
			});
			Collections.reverse(sortedTaskList.getTaskList());
		}
		else if (sortOption == "Complete first") {
			Collections.sort(sortedTaskList.getTaskList(), new Comparator<Task>() {
				@Override
				public int compare(Task task1, Task task2) {
					return task1.getStatus().compareTo(task2.getStatus());
				}
			});
		}
		
		//return sortedTaskList;
	}
	
	public void populateTaskListGui() {
		tasklist.getTaskList().clear();
		tasklist.loadTaskList(filePath);
		sortedTaskList = tasklist; //updates the task list to be sorted while preserving the original order added of the task list
		sortTaskList(sortOption);
		panel.removeAll();
		//ArrayList<Task> list = tasklist.getTaskList();
		List<JTextField> taskTextList = new ArrayList<>(); 
		List<JButton> deleteButtonList = new ArrayList<>(); //list for all of the delete buttons
		List<JButton> editButtonList = new ArrayList<>(); //not sure if these lists are needed but keeping them here for now
		List<JCheckBox> statusMarkList = new ArrayList<>();
		
		for (int i = 0;  i < sortedTaskList.getTaskList().size(); i++) {
			Task currentTask = sortedTaskList.getTaskList().get(i); //trying to use getter for accessing arraylist of tasklist
			
			JTextField taskText = new JTextField(currentTask.getTask());
			JButton deleteButton = new JButton("Delete Task");
			deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					tasklist.deleteTask(currentTask);
					tasklist.saveTaskList("C:\\Users\\jkere\\Desktop\\todolistexample.csv"); //trying to save list and reload it so it shows up on screen
					//tasklist.loadTaskList("C:\\Users\\jkere\\Desktop\\todolistexample.csv"); //this is causing wonky stuff to happen
					populateTaskListGui();
					//panel.revalidate();
					//panel.repaint();
				}
				
			});
			JButton editButton = new JButton("Edit Task");
			editButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					userEditTask(currentTask, taskText.getText());
					tasklist.saveTaskList(filePath);
					populateTaskListGui();
				}
				
			});
			//System.out.print(currentTask.getStatus());
			JCheckBox checkbox = new JCheckBox();
			if (currentTask.getStatus().trim().equals("Incomplete")) { //for some reason spaces are being added to the status incrementing by 1 each time so it's not equaling it
				System.out.print(currentTask.getStatus());
				checkbox.setSelected(false);
				System.out.print("Hello");
			}
			else if (currentTask.getStatus().trim().equals("Complete")) {
				checkbox.setSelected(true);
				System.out.print("Hello");
			}
			checkbox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (checkbox.isSelected()) {
						tasklist.markTaskComplete(currentTask, 1);
						tasklist.saveTaskList(filePath);
					}
					if (!checkbox.isSelected()) {
						tasklist.markTaskComplete(currentTask, 2);
						tasklist.saveTaskList(filePath);
					}
				}
				
			});
			
			panel.add(checkbox);
			panel.add(taskText);
			panel.add(editButton);
			panel.add(deleteButton);
			//System.out.println(currentTask.task + " " + currentTask.status);
			}
		panel.revalidate();
		panel.repaint();
	
	}
	
	public void userLoadTaskList() {
		//String filePath = "C:\\Users\\jkere\\Desktop\\todolistexample.csv";
		tasklist.loadTaskList(filePath);
	}
	
	public void userSaveTaskList() {
		//String filePath = "C:\\Users\\jkere\\Desktop\\todolistexample.csv";
		tasklist.saveTaskList(filePath);
	}
	
	public void userAddTask() { //for some reason pressing any of the buttons adds an extra space every time before the status of all other entries
		//System.out.println("Enter your task here: ");
		//String task = input.nextLine();
		String task = userText.getText();
		tasklist.addTask(task, "Incomplete");
		userSaveTaskList();
		populateTaskListGui();
		userText.setText("");
		panel.revalidate();
		panel.repaint();
	}
	
	public void userDeleteTask(Task task) {
		//System.out.println("Enter the task to delete here: ");
		//String task = input.nextLine();
		tasklist.deleteTask(task);
	}
	
	public void userEditTask(Task taskToEdit, String newTask) {
		//System.out.println("Enter the task to edit here: ");
		//String task = input.nextLine();
		tasklist.editTask(taskToEdit, newTask);
	}
	
	public void userMarkTaskComplete() {
		System.out.println("Enter the task to mark complete: ");
		String task = input.nextLine();
		//tasklist.markTaskComplete(task);
	}
	
	public void printTasklist() {
		for (int i = 0;  i < tasklist.getTaskList().size(); i++) {
			Task currentTask = tasklist.getTaskList().get(i);
			System.out.println(currentTask.task + " " + currentTask.status);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
