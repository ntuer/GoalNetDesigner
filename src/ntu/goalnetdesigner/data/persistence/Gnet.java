package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the gnet database table.
 * 
 */
@Entity
@NamedQuery(name="Gnet.findAll", query="SELECT g FROM Gnet g")
public class Gnet implements Serializable, IDataServiceUnitSubscriber {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String description;

	private String endStateID;

	private byte goalSelectionType;

	private byte isOpen;

	private String name;

	private String rootID;

	private String startStateID;

	private BigInteger stateCount;

	private BigInteger transitionCount;

	//bi-directional many-to-one association to UsergroupGnet
	@OneToMany(mappedBy="gnet")
	private List<UsergroupGnet> usergroupGnets;

	public Gnet() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndStateID() {
		return this.endStateID;
	}

	public void setEndStateID(String endStateID) {
		this.endStateID = endStateID;
	}

	public byte getGoalSelectionType() {
		return this.goalSelectionType;
	}

	public void setGoalSelectionType(byte goalSelectionType) {
		this.goalSelectionType = goalSelectionType;
	}

	public byte getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(byte isOpen) {
		this.isOpen = isOpen;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRootID() {
		return this.rootID;
	}

	public void setRootID(String rootID) {
		this.rootID = rootID;
	}

	public String getStartStateID() {
		return this.startStateID;
	}

	public void setStartStateID(String startStateID) {
		this.startStateID = startStateID;
	}

	public BigInteger getStateCount() {
		return this.stateCount;
	}

	public void setStateCount(BigInteger stateCount) {
		this.stateCount = stateCount;
	}

	public BigInteger getTransitionCount() {
		return this.transitionCount;
	}

	public void setTransitionCount(BigInteger transitionCount) {
		this.transitionCount = transitionCount;
	}

	public List<UsergroupGnet> getUsergroupGnets() {
		return this.usergroupGnets;
	}

	public void setUsergroupGnets(List<UsergroupGnet> usergroupGnets) {
		this.usergroupGnets = usergroupGnets;
	}

	public UsergroupGnet addUsergroupGnet(UsergroupGnet usergroupGnet) {
		getUsergroupGnets().add(usergroupGnet);
		usergroupGnet.setGnet(this);

		return usergroupGnet;
	}

	public UsergroupGnet removeUsergroupGnet(UsergroupGnet usergroupGnet) {
		getUsergroupGnets().remove(usergroupGnet);
		usergroupGnet.setGnet(null);

		return usergroupGnet;
	}

}