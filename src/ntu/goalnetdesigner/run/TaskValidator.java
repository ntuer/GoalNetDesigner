package ntu.goalnetdesigner.run;

import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.logic.ValidationManager;
import ntu.goalnetdesigner.session.DataSession;

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
				.addWarning("Task " + t.getName() + " has no functions associated.");
			}
		}
	}
}
