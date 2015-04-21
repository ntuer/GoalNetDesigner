package ntu.goalnetdesigner.validation.semantic;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.logic.ValidationManager;
import ntu.goalnetdesigner.validation.IComponentValidator;

public class DeadlockValidator implements IComponentValidator{
	private Gnet gnet;
	private ValidationManager parentValidationManager;

	public DeadlockValidator(Gnet gnet, ValidationManager parentValidationManager) {
		super();
		this.gnet = gnet;
		this.parentValidationManager = parentValidationManager;
	}
}
