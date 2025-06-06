import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Comparable<Task> {
	String task;
	String status = "Incomplete";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	LocalDateTime dateTimeAdded;
	
	public Task(String task, String status, String dateTimeAdded) {
		this.task = task;
		this.status = status;
		this.dateTimeAdded = LocalDateTime.parse(dateTimeAdded.trim(), formatter);
	}
	
	public String getTask() {
		return this.task;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getDateTimeAdded() {
		return this.dateTimeAdded.toString();
	}
	
	public void setStatus(int status) {
		if (status == 1) {
			this.status = "Complete";
		}
		else if (status == 2) {
			this.status = "Incomplete";
		}
		
	}

	@Override
	public int compareTo(Task otherTask) {
		return this.task.compareTo(otherTask.task);
	}

}
