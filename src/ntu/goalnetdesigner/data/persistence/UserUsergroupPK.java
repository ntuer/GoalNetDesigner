package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_usergroup database table.
 * 
 */
@Embeddable
public class UserUsergroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String userID;

	@Column(insertable=false, updatable=false)
	private String userGroupID;

	public UserUsergroupPK() {
	}
	public String getUserID() {
		return this.userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserGroupID() {
		return this.userGroupID;
	}
	public void setUserGroupID(String userGroupID) {
		this.userGroupID = userGroupID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserUsergroupPK)) {
			return false;
		}
		UserUsergroupPK castOther = (UserUsergroupPK)other;
		return 
			this.userID.equals(castOther.userID)
			&& this.userGroupID.equals(castOther.userGroupID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userID.hashCode();
		hash = hash * prime + this.userGroupID.hashCode();
		
		return hash;
	}
}