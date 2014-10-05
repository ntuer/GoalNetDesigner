package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the state database table.
 * 
 */
@Entity
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private int achievement;

	private byte composite;

	private int cost;

	private String description;

	private BigInteger GNetID;

	private String name;

	private BigInteger parentGNetID;

	private BigInteger subGNetEndID;

	private BigInteger subGNetStartID;

	private BigInteger token;

	private int x;

	private int y;

	public State() {
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

	public BigInteger getGNetID() {
		return this.GNetID;
	}

	public void setGNetID(BigInteger GNetID) {
		this.GNetID = GNetID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getParentGNetID() {
		return this.parentGNetID;
	}

	public void setParentGNetID(BigInteger parentGNetID) {
		this.parentGNetID = parentGNetID;
	}

	public BigInteger getSubGNetEndID() {
		return this.subGNetEndID;
	}

	public void setSubGNetEndID(BigInteger subGNetEndID) {
		this.subGNetEndID = subGNetEndID;
	}

	public BigInteger getSubGNetStartID() {
		return this.subGNetStartID;
	}

	public void setSubGNetStartID(BigInteger subGNetStartID) {
		this.subGNetStartID = subGNetStartID;
	}

	public BigInteger getToken() {
		return this.token;
	}

	public void setToken(BigInteger token) {
		this.token = token;
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