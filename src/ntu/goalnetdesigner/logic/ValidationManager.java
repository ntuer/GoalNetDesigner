package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.List;

import ntu.goalnetdesigner.run.ConnectionValidator;
import ntu.goalnetdesigner.run.GNetValidator;
import ntu.goalnetdesigner.run.IComponentValidator;
import ntu.goalnetdesigner.run.ObjectStringPair;
import ntu.goalnetdesigner.run.StateValidator;
import ntu.goalnetdesigner.run.TaskValidator;
import ntu.goalnetdesigner.run.TransitionValidator;
import ntu.goalnetdesigner.session.DataSession;

public class ValidationManager{
	private List<ObjectStringPair> errors;
	private List<ObjectStringPair> warnings;
	
	// individual validators
	private List<IComponentValidator> validators;
	
	public ValidationManager() {
		super();
		this.errors = new ArrayList<ObjectStringPair>();
		this.warnings = new ArrayList<ObjectStringPair>();
		this.validators = new ArrayList<IComponentValidator>();
		// TODO: change to config file editable using editor
		this.validators.add(new GNetValidator(DataSession.Cache.gnet, this));
		this.validators.add(new StateValidator(DataSession.Cache.gnet, this));
		this.validators.add(new TransitionValidator(DataSession.Cache.gnet, this));
		this.validators.add(new TaskValidator(this));
		this.validators.add(new ConnectionValidator(DataSession.Cache.gnet, this));
	}
	public List<ObjectStringPair> getErrors() {
		return errors;
	}
	public List<ObjectStringPair> getWarnings() {
		return warnings;
	}
	
	public void addError(Object object, String error){
		this.errors.add(new ObjectStringPair(object, error));
	}
	
	public void addWarning(Object object, String error){
		this.warnings.add(new ObjectStringPair(object, error));
	}
	
	public void validate(){
		for (IComponentValidator validator: this.validators){
			validator.validate();
		}
	}
	
	public String outputErrors(){
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (ObjectStringPair error: this.getErrors()){
			sb.append(i + ". ");
    		sb.append(error.getString() + "\n");
    		++i;
    	}
		if (i > 1)
			sb.insert(0, "Error List:\n");
		return sb.toString();
	}
	
	public String outputWarnings(){
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (ObjectStringPair warning: this.getWarnings()){
			sb.append(i + ". ");
    		sb.append(warning.getString() + "\n");
    		++i;
    	}
		if (i > 1)
			sb.insert(0, "Warnings List:\n");
		return sb.toString();
	}
	
	public boolean containsError(){
		return this.errors.size() > 0;
	}
	
}
