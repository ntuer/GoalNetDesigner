package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the state database table.
 * 
 */
@Entity
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable, IDrawable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int achievement;

	private byte composite;

	private int cost;

	private String description;

	private BigInteger GNetID;

	private String name;

	private String parentGNetID;

	private String subGNetEndID;

	private String subGNetStartID;

	private BigInteger token;

	private int x;

	private int y;

	//bi-directional many-to-one association to StateFunction
	@OneToMany(mappedBy="state")
	private List<StateFunction> stateFunctions;

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

	public String getParentGNetID() {
		return this.parentGNetID;
	}

	public void setParentGNetID(String parentGNetID) {
		this.parentGNetID = parentGNetID;
	}

	public String getSubGNetEndID() {
		return this.subGNetEndID;
	}

	public void setSubGNetEndID(String subGNetEndID) {
		this.subGNetEndID = subGNetEndID;
	}

	public String getSubGNetStartID() {
		return this.subGNetStartID;
	}

	public void setSubGNetStartID(String subGNetStartID) {
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

	public List<StateFunction> getStateFunctions() {
		return this.stateFunctions;
	}

	public void setStateFunctions(List<StateFunction> stateFunctions) {
		this.stateFunctions = stateFunctions;
	}

	public StateFunction addStateFunction(StateFunction stateFunction) {
		getStateFunctions().add(stateFunction);
		stateFunction.setState(this);

		return stateFunction;
	}

	public StateFunction removeStateFunction(StateFunction stateFunction) {
		getStateFunctions().remove(stateFunction);
		stateFunction.setState(null);

		return stateFunction;
	}

}