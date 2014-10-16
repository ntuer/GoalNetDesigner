package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tasklist_task database table.
 * 
 */
@Embeddable
public class TasklistTaskPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String taskListID;

	@Column(insertable=false, updatable=false)
	private String taskID;

	public TasklistTaskPK() {
	}
	public String getTaskListID() {
		return this.taskListID;
	}
	public void setTaskListID(String taskListID) {
		this.taskListID = taskListID;
	}
	public String getTaskID() {
		return this.taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TasklistTaskPK)) {
			return false;
		}
		TasklistTaskPK castOther = (TasklistTaskPK)other;
		return 
			this.taskListID.equals(castOther.taskListID)
			&& this.taskID.equals(castOther.taskID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.taskListID.hashCode();
		hash = hash * prime + this.taskID.hashCode();
		
		return hash;
	}
}