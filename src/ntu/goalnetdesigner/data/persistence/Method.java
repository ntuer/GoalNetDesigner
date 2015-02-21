package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;

import javax.persistence.*;

import ntu.goalnetdesigner.session.DataSession;

import java.util.List;


/**
 * The persistent class for the method database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Method.findAll",
                query="SELECT c FROM Method c"),
    @NamedQuery(name="Method.findById",
                query="SELECT c FROM Method c WHERE c.id = :id"),
}) 
public class Method implements Serializable, IDataServiceUnitSubscriber {
	
	public String toString(){
		return this.getName();
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String alias;

	private String description;

	private String fileName;

	private String name;

	private String params;

	private String PValues;

	private String RTType;

	//bi-directional many-to-one association to StateFunction
	@OneToMany(mappedBy="method", orphanRemoval=true)
	private List<StateFunction> stateFunctions;

	//bi-directional many-to-one association to TaskFunction
	@OneToMany(mappedBy="method", orphanRemoval=true)
	private List<TaskFunction> taskFunctions;

	public Method() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getPValues() {
		return this.PValues;
	}

	public void setPValues(String PValues) {
		this.PValues = PValues;
	}

	public String getRTType() {
		return this.RTType;
	}

	public void setRTType(String RTType) {
		this.RTType = RTType;
	}

	public List<StateFunction> getStateFunctions() {
		return this.stateFunctions;
	}

	public void setStateFunctions(List<StateFunction> stateFunctions) {
		this.stateFunctions = stateFunctions;
	}

	public StateFunction addStateFunction(StateFunction stateFunction) {
		getStateFunctions().add(stateFunction);
		stateFunction.setMethod(this);

		return stateFunction;
	}

	public StateFunction removeStateFunction(StateFunction stateFunction) {
		getStateFunctions().remove(stateFunction);
		stateFunction.setMethod(null);

		return stateFunction;
	}

	public List<TaskFunction> getTaskFunctions() {
		return this.taskFunctions;
	}

	public void setTaskFunctions(List<TaskFunction> taskFunctions) {
		this.taskFunctions = taskFunctions;
	}

	public TaskFunction addTaskFunction(TaskFunction taskFunction) {
		getTaskFunctions().add(taskFunction);
		taskFunction.setMethod(this);

		return taskFunction;
	}

	public TaskFunction removeTaskFunction(TaskFunction taskFunction) {
		getTaskFunctions().remove(taskFunction);
		taskFunction.setMethod(null);

		return taskFunction;
	}

}