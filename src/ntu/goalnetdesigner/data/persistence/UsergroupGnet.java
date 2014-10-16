package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usergroup_gnet database table.
 * 
 */
@Entity
@Table(name="usergroup_gnet")
@NamedQuery(name="UsergroupGnet.findAll", query="SELECT u FROM UsergroupGnet u")
public class UsergroupGnet implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsergroupGnetPK id;

	private byte read;

	private byte write;

	//bi-directional many-to-one association to Gnet
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GNetID")
	private Gnet gnet;

	//bi-directional many-to-one association to Usergroup
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UserGroupID")
	private Usergroup usergroup;

	public UsergroupGnet() {
	}

	public UsergroupGnetPK getId() {
		return this.id;
	}

	public void setId(UsergroupGnetPK id) {
		this.id = id;
	}

	public byte getRead() {
		return this.read;
	}

	public void setRead(byte read) {
		this.read = read;
	}

	public byte getWrite() {
		return this.write;
	}

	public void setWrite(byte write) {
		this.write = write;
	}

	public Gnet getGnet() {
		return this.gnet;
	}

	public void setGnet(Gnet gnet) {
		this.gnet = gnet;
	}

	public Usergroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

}