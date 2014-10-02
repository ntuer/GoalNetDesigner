package ntu.goalnetdesigner.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tasklist_task database table.
 * 
 */
@Entity
@Table(name="tasklist_task")
@NamedQuery(name="TasklistTask.findAll", query="SELECT t FROM TasklistTask t")
public class TasklistTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TasklistTaskPK id;

	private int sequence;

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

}