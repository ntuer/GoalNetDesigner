package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usergroup database table.
 * 
 */
@Entity
@NamedQuery(name="Usergroup.findAll", query="SELECT u FROM Usergroup u")
public class Usergroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String desc;

	private String name;

	//bi-directional many-to-one association to UserUsergroup
	@OneToMany(mappedBy="usergroup")
	private List<UserUsergroup> userUsergroups;

	//bi-directional many-to-one association to UsergroupGnet
	@OneToMany(mappedBy="usergroup")
	private List<UsergroupGnet> usergroupGnets;

	public Usergroup() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserUsergroup> getUserUsergroups() {
		return this.userUsergroups;
	}

	public void setUserUsergroups(List<UserUsergroup> userUsergroups) {
		this.userUsergroups = userUsergroups;
	}

	public UserUsergroup addUserUsergroup(UserUsergroup userUsergroup) {
		getUserUsergroups().add(userUsergroup);
		userUsergroup.setUsergroup(this);

		return userUsergroup;
	}

	public UserUsergroup removeUserUsergroup(UserUsergroup userUsergroup) {
		getUserUsergroups().remove(userUsergroup);
		userUsergroup.setUsergroup(null);

		return userUsergroup;
	}

	public List<UsergroupGnet> getUsergroupGnets() {
		return this.usergroupGnets;
	}

	public void setUsergroupGnets(List<UsergroupGnet> usergroupGnets) {
		this.usergroupGnets = usergroupGnets;
	}

	public UsergroupGnet addUsergroupGnet(UsergroupGnet usergroupGnet) {
		getUsergroupGnets().add(usergroupGnet);
		usergroupGnet.setUsergroup(this);

		return usergroupGnet;
	}

	public UsergroupGnet removeUsergroupGnet(UsergroupGnet usergroupGnet) {
		getUsergroupGnets().remove(usergroupGnet);
		usergroupGnet.setUsergroup(null);

		return usergroupGnet;
	}

}