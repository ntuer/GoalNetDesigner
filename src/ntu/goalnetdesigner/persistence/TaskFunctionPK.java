package ntu.goalnetdesigner.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the task_function database table.
 * 
 */
@Embeddable
public class TaskFunctionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String taskID;

	private String functionID;

	public TaskFunctionPK() {
	}
	public String getTaskID() {
		return this.taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getFunctionID() {
		return this.functionID;
	}
	public void setFunctionID(String functionID) {
		this.functionID = functionID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TaskFunctionPK)) {
			return false;
		}
		TaskFunctionPK castOther = (TaskFunctionPK)other;
		return 
			this.taskID.equals(castOther.taskID)
			&& this.functionID.equals(castOther.functionID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.taskID.hashCode();
		hash = hash * prime + this.functionID.hashCode();
		
		return hash;
	}
}