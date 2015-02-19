package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;


/**
 * The persistent class for the gnet database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Gnet.findAll",
                query="SELECT c FROM Gnet c"),
    @NamedQuery(name="Gnet.findById",
                query="SELECT c FROM Gnet c WHERE c.id = :id"),
}) 
public class Gnet implements Serializable, IDataServiceUnitSubscriber {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String description;

	private boolean goalSelectionType;

	private boolean isOpen;

	private String name;

	private BigInteger stateCount;

	private BigInteger transitionCount;

	//bi-directional many-to-one association to ActionLog
	@OneToMany(mappedBy="gnet")
	private List<ActionLog> actionLogs;

	//bi-directional many-to-one association to Arc
	@OneToMany(mappedBy="gnet")
	private List<Arc> arcs;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RootID")
	private State state1;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EndStateID")
	private State state2;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="StartStateID")
	private State state3;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="gnet")
	private List<State> states;

	//bi-directional many-to-one association to Transition
	@OneToMany(mappedBy="gnet")
	private List<Transition> transitions;

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

	public boolean getGoalSelectionType() {
		return this.goalSelectionType;
	}

	public void setGoalSelectionType(boolean goalSelectionType) {
		this.goalSelectionType = goalSelectionType;
	}

	public boolean getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<ActionLog> getActionLogs() {
		return this.actionLogs;
	}

	public void setActionLogs(List<ActionLog> actionLogs) {
		this.actionLogs = actionLogs;
	}

	public ActionLog addActionLog(ActionLog actionLog) {
		getActionLogs().add(actionLog);
		actionLog.setGnet(this);

		return actionLog;
	}

	public ActionLog removeActionLog(ActionLog actionLog) {
		getActionLogs().remove(actionLog);
		actionLog.setGnet(null);

		return actionLog;
	}

	public List<Arc> getArcs() {
		return this.arcs;
	}

	public void setArcs(List<Arc> arcs) {
		this.arcs = arcs;
	}

	public Arc addArc(Arc arc) {
		getArcs().add(arc);
		arc.setGnet(this);

		return arc;
	}

	public Arc removeArc(Arc arc) {
		getArcs().remove(arc);
		arc.setGnet(null);

		return arc;
	}

	public State getState1() {
		return this.state1;
	}

	public void setState1(State state1) {
		this.state1 = state1;
	}

	public State getState2() {
		return this.state2;
	}

	public void setState2(State state2) {
		this.state2 = state2;
	}

	public State getState3() {
		return this.state3;
	}

	public void setState3(State state3) {
		this.state3 = state3;
	}

	public List<State> getStates() {
		return this.states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public State addState(State state) {
		getStates().add(state);
		state.setGnet(this);

		return state;
	}

	public State removeState(State state) {
		getStates().remove(state);
		state.setGnet(null);

		return state;
	}

	public List<Transition> getTransitions() {
		return this.transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public Transition addTransition(Transition transition) {
		getTransitions().add(transition);
		transition.setGnet(this);

		return transition;
	}

	public Transition removeTransition(Transition transition) {
		getTransitions().remove(transition);
		transition.setGnet(null);

		return transition;
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