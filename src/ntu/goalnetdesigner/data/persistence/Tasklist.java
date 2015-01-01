package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the tasklist database table.
 * 
 */
@Entity
@NamedQuery(name="Tasklist.findAll", query="SELECT t FROM Tasklist t")
public class Tasklist implements Serializable, IDataServiceUnitSubscriber  {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String description;

	private String name;

	//bi-directional many-to-one association to TasklistTask
	@OneToMany(mappedBy="tasklist")
	private List<TasklistTask> tasklistTasks;

	public Tasklist() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TasklistTask> getTasklistTasks() {
		return this.tasklistTasks;
	}

	public void setTasklistTasks(List<TasklistTask> tasklistTasks) {
		this.tasklistTasks = tasklistTasks;
	}

	public TasklistTask addTasklistTask(TasklistTask tasklistTask) {
		getTasklistTasks().add(tasklistTask);
		tasklistTask.setTasklist(this);

		return tasklistTask;
	}

	public TasklistTask removeTasklistTask(TasklistTask tasklistTask) {
		getTasklistTasks().remove(tasklistTask);
		tasklistTask.setTasklist(null);

		return tasklistTask;
	}

}