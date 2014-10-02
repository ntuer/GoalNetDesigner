package ntu.goalnetdesigner.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the task_function database table.
 * 
 */
@Entity
@Table(name="task_function")
@NamedQuery(name="TaskFunction.findAll", query="SELECT t FROM TaskFunction t")
public class TaskFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TaskFunctionPK id;

	private String arguments;

	private int sequence;

	public TaskFunction() {
	}

	public TaskFunctionPK getId() {
		return this.id;
	}

	public void setId(TaskFunctionPK id) {
		this.id = id;
	}

	public String getArguments() {
		return this.arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}