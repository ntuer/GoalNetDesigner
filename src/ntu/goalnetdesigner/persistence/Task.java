package ntu.goalnetdesigner.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private int achievement;

	private int childrenTaskNumber;

	private BigInteger childTaskID;

	private String className;

	private byte composite;

	private int cost;

	private String description;

	private String name;

	public Task() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAchievement() {
		return this.achievement;
	}

	public void setAchievement(int achievement) {
		this.achievement = achievement;
	}

	public int getChildrenTaskNumber() {
		return this.childrenTaskNumber;
	}

	public void setChildrenTaskNumber(int childrenTaskNumber) {
		this.childrenTaskNumber = childrenTaskNumber;
	}

	public BigInteger getChildTaskID() {
		return this.childTaskID;
	}

	public void setChildTaskID(BigInteger childTaskID) {
		this.childTaskID = childTaskID;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public byte getComposite() {
		return this.composite;
	}

	public void setComposite(byte composite) {
		this.composite = composite;
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}