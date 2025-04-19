
public class Task {
	String task;
	String status;
	
	public Task(String task, String status) {
		this.task = task;
		this.status = "Incomplete";
	}
	
	public String getTask() {
		return this.task;
	}
	
	public void setStatus(int status) {
		if (status == 1) {
			this.status = "Complete";
		}
		else if (status == 2) {
			this.status = "Incomplete";
		}
		
	}

}
