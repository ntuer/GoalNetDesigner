package ntu.goalnetdesigner.validation.syntax;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.logic.ValidationManager;
import ntu.goalnetdesigner.validation.IComponentValidator;

public class TransitionValidator implements IComponentValidator{
	private Gnet gnet;
	private ValidationManager parentValidationManager;

	public TransitionValidator(Gnet gnet, ValidationManager parentValidationManager) {
		super();
		this.gnet = gnet;
		this.parentValidationManager = parentValidationManager;
	}

	public void validate() {
		for (Transition t: gnet.getTransitions()){
			if (t.getTasklist() == null ||
					t.getTasklist().getTasklistTasks() == null || 
					t.getTasklist().getTasklistTasks().size() == 0){
				parentValidationManager
				.addWarning(t, "Transition " + t.getName() + " has no tasks associated.");
			}
		}
	}
}
