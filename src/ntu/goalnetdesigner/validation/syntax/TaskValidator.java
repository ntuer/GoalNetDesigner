package ntu.goalnetdesigner.validation.syntax;

import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.logic.ValidationManager;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.validation.IComponentValidator;

public class TaskValidator implements IComponentValidator{
	private ValidationManager parentValidationManager;

	public TaskValidator(ValidationManager parentValidationManager) {
		super();
		this.parentValidationManager = parentValidationManager;
	}

	public void validate() {
		for (Task t: DataSession.Cache.tasks){
			if (t.getTaskFunctions() == null || t.getTaskFunctions().size() == 0){
				parentValidationManager
				.addWarning(t, "Task " + t.getName() + " has no functions associated.");
			}
		}
	}
}
