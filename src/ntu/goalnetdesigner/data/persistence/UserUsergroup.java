package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the user_usergroup database table.
 * 
 */
@Entity
@Table(name="user_usergroup")
@NamedQuery(name="UserUsergroup.findAll", query="SELECT u FROM UserUsergroup u")
public class UserUsergroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserUsergroupPK id;

	private BigInteger isAdmin;

	//bi-directional many-to-one association to Usergroup
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UserGroupID")
	private Usergroup usergroup;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UserID")
	private User user;

	public UserUsergroup() {
	}

	public UserUsergroupPK getId() {
		return this.id;
	}

	public void setId(UserUsergroupPK id) {
		this.id = id;
	}

	public BigInteger getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(BigInteger isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Usergroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}