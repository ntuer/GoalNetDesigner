package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the tasklist_task database table.
 * 
 */
@Entity
@Table(name="tasklist_task")
@NamedQuery(name="TasklistTask.findAll", query="SELECT t FROM TasklistTask t")
public class TasklistTask implements Serializable, IAssociationDataServiceUnitSubscriber {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TasklistTaskPK id;

	private int sequence;

	//bi-directional many-to-one association to Task
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TaskID")
	private Task task;

	//bi-directional many-to-one association to Tasklist
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TaskListID")
	private Tasklist tasklist;

	public String toString(){
		return this.tasklist.getName() + "->" + this.task.getName();
	}
	
	public TasklistTask() {
	}

	public TasklistTaskPK getId() {
		return this.id;
	}

	public void setId(TasklistTaskPK id) {
		this.id = id;
	}

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Tasklist getTasklist() {
		return this.tasklist;
	}

	public void setTasklist(Tasklist tasklist) {
		this.tasklist = tasklist;
	}

}