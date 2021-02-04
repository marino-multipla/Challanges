package test_pkg;

/*
 * DATE:04/02/21;
 * AUTHOR: Maccagni Giacomo
 * DESCRIPTION: 
 */
public class Task {
	
	private int id;
	private int duration;
	private int dependentTaskId;
	private int cumulative_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDependentTaskId() {
		return dependentTaskId;
	}
	public void setDependentTaskId(int dependentTaskId) {
		this.dependentTaskId = dependentTaskId;
	}
	
	public int getCumulative_time() {
		return cumulative_time;
	}
	
	public void setCumulative_time(int cumulative_time) {
		this.cumulative_time = cumulative_time;
	}
	
	public Task(int id, int duration, int dependentTaskId) {
		super();
		this.id = id;
		this.duration = duration;
		this.dependentTaskId = dependentTaskId;
		this.cumulative_time = 0;
	}

}//end of class Task