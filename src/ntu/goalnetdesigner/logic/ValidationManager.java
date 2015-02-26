package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.List;

import ntu.goalnetdesigner.run.ConnectionValidator;
import ntu.goalnetdesigner.run.GNetValidator;
import ntu.goalnetdesigner.run.IComponentValidator;
import ntu.goalnetdesigner.run.StateValidator;
import ntu.goalnetdesigner.run.TaskValidator;
import ntu.goalnetdesigner.run.TransitionValidator;
import ntu.goalnetdesigner.session.DataSession;

public class ValidationManager implements IComponentValidator{
	private List<String> error;
	private List<String> warning;
	
	// individual validators
	private List<IComponentValidator> validators;
	
	public ValidationManager() {
		super();
		this.error = new ArrayList<String>();
		this.warning = new ArrayList<String>();
		this.validators = new ArrayList<IComponentValidator>();
		// TODO: change to config file editable using editor
		this.validators.add(new GNetValidator(DataSession.Cache.gnet, this));
		this.validators.add(new StateValidator(DataSession.Cache.gnet, this));
		this.validators.add(new TransitionValidator(DataSession.Cache.gnet, this));
		this.validators.add(new TaskValidator(this));
		this.validators.add(new ConnectionValidator(DataSession.Cache.gnet, this));
	}
	public List<String> getErrors() {
		return error;
	}
	public List<String> getWarnings() {
		return warning;
	}
	
	public void addError(String error){
		this.error.add(error);
	}
	
	public void addWarning(String warning){
		this.warning.add(warning);
	}
	
	public void validate(){
		for (IComponentValidator validator: this.validators){
			validator.validate();
		}
	}
	
	public String outputErrors(){
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (String error: this.getErrors()){
			sb.append(i + ". ");
    		sb.append(error + "\n");
    		++i;
    	}
		if (i > 1)
			sb.insert(0, "Error List:\n");
		return sb.toString();
	}
	
	public String outputWarnings(){
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (String warning: this.getWarnings()){
			sb.append(i + ". ");
    		sb.append(warning + "\n");
    		++i;
    	}
		if (i > 1)
			sb.insert(0, "Warnings List:\n");
		return sb.toString();
	}
}
