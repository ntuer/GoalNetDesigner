package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the task_function database table.
 * 
 */
@Embeddable
public class UserGnetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String userID;

	@Column(insertable=false, updatable=false)
	private String gnetID;

	public UserGnetPK() {
	}
	public String getUserID() {
		return this.userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getGnetID() {
		return this.gnetID;
	}
	public void setGnetID(String gnetID) {
		this.gnetID = gnetID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserGnetPK)) {
			return false;
		}
		UserGnetPK castOther = (UserGnetPK)other;
		return 
			this.userID.equals(castOther.userID)
			&& this.gnetID.equals(castOther.gnetID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userID.hashCode();
		hash = hash * prime + this.gnetID.hashCode();
		
		return hash;
	}
}