package ntu.goalnetdesigner.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the gnet database table.
 * 
 */
@Entity
@NamedQuery(name="Gnet.findAll", query="SELECT g FROM Gnet g")
public class Gnet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String description;

	private BigInteger endStateID;

	private byte goalSelectionType;

	private byte isOpen;

	private String name;

	private BigInteger rootID;

	private BigInteger startStateID;

	private BigInteger stateNumber;

	private BigInteger transitionNumber;

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

	public BigInteger getEndStateID() {
		return this.endStateID;
	}

	public void setEndStateID(BigInteger endStateID) {
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

	public BigInteger getRootID() {
		return this.rootID;
	}

	public void setRootID(BigInteger rootID) {
		this.rootID = rootID;
	}

	public BigInteger getStartStateID() {
		return this.startStateID;
	}

	public void setStartStateID(BigInteger startStateID) {
		this.startStateID = startStateID;
	}

	public BigInteger getStateNumber() {
		return this.stateNumber;
	}

	public void setStateNumber(BigInteger stateNumber) {
		this.stateNumber = stateNumber;
	}

	public BigInteger getTransitionNumber() {
		return this.transitionNumber;
	}

	public void setTransitionNumber(BigInteger transitionNumber) {
		this.transitionNumber = transitionNumber;
	}

}