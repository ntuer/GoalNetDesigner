package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the transition database table.
 * 
 */
@Entity
@NamedQuery(name="Transition.findAll", query="SELECT t FROM Transition t")
public class Transition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private int achievement;

	private int cost;

	private String description;

	private byte enabled;

	private int exceptionStateID;

	private BigInteger GNetID;

	private int level;

	private String name;

	private BigInteger taskListID;

	private BigInteger taskNumber;

	private String type;

	private int x;

	private int y;

	public Transition() {
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

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public int getExceptionStateID() {
		return this.exceptionStateID;
	}

	public void setExceptionStateID(int exceptionStateID) {
		this.exceptionStateID = exceptionStateID;
	}

	public BigInteger getGNetID() {
		return this.GNetID;
	}

	public void setGNetID(BigInteger GNetID) {
		this.GNetID = GNetID;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getTaskListID() {
		return this.taskListID;
	}

	public void setTaskListID(BigInteger taskListID) {
		this.taskListID = taskListID;
	}

	public BigInteger getTaskNumber() {
		return this.taskNumber;
	}

	public void setTaskNumber(BigInteger taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

}