package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import java.math.BigInteger;
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
	@OneToMany(mappedBy="state1")
	private List<Gnet> gnets1;

	//bi-directional many-to-one association to Gnet
	@OneToMany(mappedBy="state2")
	private List<Gnet> gnets2;

	//bi-directional many-to-one association to Gnet
	@OneToMany(mappedBy="state3")
	private List<Gnet> gnets3;

	//bi-directional many-to-one association to Gnet
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GNetID")
	private Gnet gnet;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SubGNetEndID")
	private State state1;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="state1")
	private List<State> states1;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ParentGNetID")
	private State state2;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="state2")
	private List<State> states2;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SubGNetStartID")
	private State state3;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="state3")
	private List<State> states3;

	//bi-directional many-to-one association to StateFunction
	@OneToMany(mappedBy="state")
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

	public List<Gnet> getGnets1() {
		return this.gnets1;
	}

	public void setGnets1(List<Gnet> gnets1) {
		this.gnets1 = gnets1;
	}

	public Gnet addGnets1(Gnet gnets1) {
		getGnets1().add(gnets1);
		gnets1.setState1(this);

		return gnets1;
	}

	public Gnet removeGnets1(Gnet gnets1) {
		getGnets1().remove(gnets1);
		gnets1.setState1(null);

		return gnets1;
	}

	public List<Gnet> getGnets2() {
		return this.gnets2;
	}

	public void setGnets2(List<Gnet> gnets2) {
		this.gnets2 = gnets2;
	}

	public Gnet addGnets2(Gnet gnets2) {
		getGnets2().add(gnets2);
		gnets2.setState2(this);

		return gnets2;
	}

	public Gnet removeGnets2(Gnet gnets2) {
		getGnets2().remove(gnets2);
		gnets2.setState2(null);

		return gnets2;
	}

	public List<Gnet> getGnets3() {
		return this.gnets3;
	}

	public void setGnets3(List<Gnet> gnets3) {
		this.gnets3 = gnets3;
	}

	public Gnet addGnets3(Gnet gnets3) {
		getGnets3().add(gnets3);
		gnets3.setState3(this);

		return gnets3;
	}

	public Gnet removeGnets3(Gnet gnets3) {
		getGnets3().remove(gnets3);
		gnets3.setState3(null);

		return gnets3;
	}

	public Gnet getGnet() {
		return this.gnet;
	}

	public void setGnet(Gnet gnet) {
		this.gnet = gnet;
	}

	public State getState1() {
		return this.state1;
	}

	public void setState1(State state1) {
		this.state1 = state1;
	}

	public List<State> getStates1() {
		return this.states1;
	}

	public void setStates1(List<State> states1) {
		this.states1 = states1;
	}

	public State addStates1(State states1) {
		getStates1().add(states1);
		states1.setState1(this);

		return states1;
	}

	public State removeStates1(State states1) {
		getStates1().remove(states1);
		states1.setState1(null);

		return states1;
	}

	public State getState2() {
		return this.state2;
	}

	public void setState2(State state2) {
		this.state2 = state2;
	}

	public List<State> getStates2() {
		return this.states2;
	}

	public void setStates2(List<State> states2) {
		this.states2 = states2;
	}

	public State addStates2(State states2) {
		getStates2().add(states2);
		states2.setState2(this);

		return states2;
	}

	public State removeStates2(State states2) {
		getStates2().remove(states2);
		states2.setState2(null);

		return states2;
	}

	public State getState3() {
		return this.state3;
	}

	public void setState3(State state3) {
		this.state3 = state3;
	}

	public List<State> getStates3() {
		return this.states3;
	}

	public void setStates3(List<State> states3) {
		this.states3 = states3;
	}

	public State addStates3(State states3) {
		getStates3().add(states3);
		states3.setState3(this);

		return states3;
	}

	public State removeStates3(State states3) {
		getStates3().remove(states3);
		states3.setState3(null);

		return states3;
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