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

	//bi-directional many-to-one association to Function
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FunctionID")
	private Function function;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="StateID")
	private State state;

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

	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

}