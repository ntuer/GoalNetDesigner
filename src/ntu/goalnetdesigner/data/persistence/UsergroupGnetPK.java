package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the usergroup_gnet database table.
 * 
 */
@Embeddable
public class UsergroupGnetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String userGroupID;

	@Column(insertable=false, updatable=false)
	private String GNetID;

	public UsergroupGnetPK() {
	}
	public String getUserGroupID() {
		return this.userGroupID;
	}
	public void setUserGroupID(String userGroupID) {
		this.userGroupID = userGroupID;
	}
	public String getGNetID() {
		return this.GNetID;
	}
	public void setGNetID(String GNetID) {
		this.GNetID = GNetID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsergroupGnetPK)) {
			return false;
		}
		UsergroupGnetPK castOther = (UsergroupGnetPK)other;
		return 
			this.userGroupID.equals(castOther.userGroupID)
			&& this.GNetID.equals(castOther.GNetID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userGroupID.hashCode();
		hash = hash * prime + this.GNetID.hashCode();
		
		return hash;
	}
}