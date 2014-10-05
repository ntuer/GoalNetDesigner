package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the state_function database table.
 * 
 */
@Entity
@Table(name="state_function")
@NamedQuery(name="StateFunction.findAll", query="SELECT s FROM StateFunction s")
public class StateFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StateFunctionPK id;

	private String arguments;

	private int sequence;

	public StateFunction() {
	}

	public StateFunctionPK getId() {
		return this.id;
	}

	public void setId(StateFunctionPK id) {
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