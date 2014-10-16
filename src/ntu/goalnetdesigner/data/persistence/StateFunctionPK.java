package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the state_function database table.
 * 
 */
@Embeddable
public class StateFunctionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String stateID;

	@Column(insertable=false, updatable=false)
	private String functionID;

	public StateFunctionPK() {
	}
	public String getStateID() {
		return this.stateID;
	}
	public void setStateID(String stateID) {
		this.stateID = stateID;
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
		if (!(other instanceof StateFunctionPK)) {
			return false;
		}
		StateFunctionPK castOther = (StateFunctionPK)other;
		return 
			this.stateID.equals(castOther.stateID)
			&& this.functionID.equals(castOther.functionID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.stateID.hashCode();
		hash = hash * prime + this.functionID.hashCode();
		
		return hash;
	}
}