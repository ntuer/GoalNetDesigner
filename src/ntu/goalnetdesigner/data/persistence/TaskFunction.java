package ntu.goalnetdesigner.data.persistence;

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

	//bi-directional many-to-one association to Method
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FunctionID")
	private Method method;

	//bi-directional many-to-one association to Task
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TaskID")
	private Task task;

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

	public Method getMethod() {
		return this.method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}