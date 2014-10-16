package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the function database table.
 * 
 */
@Entity
@NamedQuery(name="Function.findAll", query="SELECT f FROM Function f")
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String alias;

	private String description;

	private String fileName;

	private String name;

	private String params;

	private String RTType;

	private String values;

	//bi-directional many-to-one association to StateFunction
	@OneToMany(mappedBy="function")
	private List<StateFunction> stateFunctions;

	//bi-directional many-to-one association to TaskFunction
	@OneToMany(mappedBy="function")
	private List<TaskFunction> taskFunctions;

	public Function() {
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

	public String getRTType() {
		return this.RTType;
	}

	public void setRTType(String RTType) {
		this.RTType = RTType;
	}

	public String getValues() {
		return this.values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public List<StateFunction> getStateFunctions() {
		return this.stateFunctions;
	}

	public void setStateFunctions(List<StateFunction> stateFunctions) {
		this.stateFunctions = stateFunctions;
	}

	public StateFunction addStateFunction(StateFunction stateFunction) {
		getStateFunctions().add(stateFunction);
		stateFunction.setFunction(this);

		return stateFunction;
	}

	public StateFunction removeStateFunction(StateFunction stateFunction) {
		getStateFunctions().remove(stateFunction);
		stateFunction.setFunction(null);

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
		taskFunction.setFunction(this);

		return taskFunction;
	}

	public TaskFunction removeTaskFunction(TaskFunction taskFunction) {
		getTaskFunctions().remove(taskFunction);
		taskFunction.setFunction(null);

		return taskFunction;
	}

}