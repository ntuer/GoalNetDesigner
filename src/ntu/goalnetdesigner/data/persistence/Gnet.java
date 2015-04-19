package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


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
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RootID")
	private State rootState;

	//bi-directional many-to-one association to State
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EndStateID")
	private State endState;

	//bi-directional many-to-one association to State
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="StartStateID")
	private State startState;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="gnet")
	private List<State> states;

	//bi-directional many-to-one association to Transition
	@OneToMany(mappedBy="gnet")
	private List<Transition> transitions;
	
	//bi-directional many-to-one association to Arc
	@OneToMany(mappedBy="gnet")
	private List<Method> methods;
		
	//bi-directional many-to-one association to Arc
	@OneToMany(mappedBy="gnet")
	private List<Task> tasks;

	//bi-directional many-to-one association to UsergroupGnet
	@OneToMany(mappedBy="gnet")
	private List<UserGnet> userGnets;

	public String toString(){
		return this.name;
	}
	
	public Gnet() {
		this.id = UUID.randomUUID().toString();
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

	public State getRootState() {
		return this.rootState;
	}

	public void setRootState(State state1) {
		this.rootState = state1;
	}

	public State getEndState() {
		return this.endState;
	}

	public void setEndState(State state2) {
		this.endState = state2;
	}

	public State getStartState() {
		return this.startState;
	}

	public void setStartState(State state3) {
		this.startState = state3;
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
	
	public List<Method> getMethods() {
		return this.methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public Method addMethod(Method method) {
		getMethods().add(method);
		method.setGnet(this);
		return method;
	}

	public Method removeMethod(Method method) {
		getMethods().remove(method);
		method.setGnet(null);

		return method;
	}
	
	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setGnet(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setGnet(null);

		return task;
	}
	
	public List<UserGnet> getUserGnets() {
		return this.userGnets;
	}

	public void setUserGnets(List<UserGnet> userGnets) {
		this.userGnets = userGnets;
	}

	public UserGnet addUserGnet(UserGnet userGnet) {
		getUserGnets().add(userGnet);
		userGnet.setGnet(this);

		return userGnet;
	}

	public UserGnet removeUserGnet(UserGnet userGnet) {
		getUserGnets().remove(userGnet);
		userGnet.setGnet(null);

		return userGnet;
	}

}