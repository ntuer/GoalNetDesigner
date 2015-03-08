package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;


/**
 * The persistent class for the state database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="State.findAll",
                query="SELECT c FROM State c"),
    @NamedQuery(name="State.findById",
                query="SELECT c FROM State c WHERE c.id = :id"),
}) 
public class State extends ntu.goalnetdesigner.render.Drawable implements Serializable, IDataServiceUnitSubscriber {
	
	public String toString(){
		return this.getName();
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int achievement;

	private boolean composite;

	private int cost;

	private String description;

	private String name;

	private BigInteger token;

	private int x;

	private int y;

	//bi-directional many-to-one association to Gnet
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GNetID")
	private Gnet gnet;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SubGNetEndID")
	private State compositeEndState;

//	@OneToOne(mappedBy="subGNetEndState")
//	private List<State> states1;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ParentGNetID")
	private State parentState;

//	@OneToOne(mappedBy="parentGNet")
//	private List<State> states2;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SubGNetStartID")
	private State compositeStartState;

//	@OneToOne(mappedBy="subGNetStartState")
//	private List<State> states3;

	//bi-directional many-to-one association to StateFunction
	@OneToMany(mappedBy="state", orphanRemoval=true)
	@OrderBy("Sequence ASC")
	private List<StateFunction> stateFunctions;

	public State() {
	}
	
	public State(double x, double y, boolean isComposite) {
		this.id = UUID.randomUUID().toString();
		this.name = "State";
		this.x = (int)x;
		this.y = (int)y;
		this.composite = isComposite;
		this.stateFunctions = new ArrayList<StateFunction>();
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

	public boolean getComposite() {
		return this.composite;
	}

	public void setComposite(boolean composite) {
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

	public Gnet getGnet() {
		return this.gnet;
	}

	public void setGnet(Gnet gnet) {
		this.gnet = gnet;
	}

	public State getCompositeEndState() {
		return this.compositeEndState;
	}

	public void setCompositeEndState(State state1) {
		this.compositeEndState = state1;
	}

//	public List<State> getStates1() {
//		return this.states1;
//	}
//
//	public void setStates1(List<State> states1) {
//		this.states1 = states1;
//	}

//	public State addStates1(State states1) {
//		getStates1().add(states1);
//		states1.setState1(this);
//
//		return states1;
//	}
//
//	public State removeStates1(State states1) {
//		getStates1().remove(states1);
//		states1.setState1(null);
//
//		return states1;
//	}

	public State getParentState() {
		return this.parentState;
	}

	public void setParentState(State state2) {
		this.parentState = state2;
	}

//	public List<State> getStates2() {
//		return this.states2;
//	}
//
//	public void setStates2(List<State> states2) {
//		this.states2 = states2;
//	}
//
//	public State addStates2(State states2) {
//		getStates2().add(states2);
//		states2.setState2(this);
//
//		return states2;
//	}
//
//	public State removeStates2(State states2) {
//		getStates2().remove(states2);
//		states2.setState2(null);
//
//		return states2;
//	}

	public State getCompositeStartState() {
		return this.compositeStartState;
	}

	public void setCompositeStartState(State state3) {
		this.compositeStartState = state3;
	}

//	public List<State> getStates3() {
//		return this.states3;
//	}
//
//	public void setStates3(List<State> states3) {
//		this.states3 = states3;
//	}
//
//	public State addStates3(State states3) {
//		getStates3().add(states3);
//		states3.setState3(this);
//
//		return states3;
//	}
//
//	public State removeStates3(State states3) {
//		getStates3().remove(states3);
//		states3.setState3(null);
//
//		return states3;
//	}

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