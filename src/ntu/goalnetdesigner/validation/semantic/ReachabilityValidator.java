package ntu.goalnetdesigner.validation.semantic;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.logic.ValidationManager;
import ntu.goalnetdesigner.validation.IComponentValidator;

public class ReachabilityValidator implements IComponentValidator{
	private Gnet gnet;
	private ValidationManager parentValidationManager;

	public ReachabilityValidator(Gnet gnet, ValidationManager parentValidationManager) {
		super();
		this.gnet = gnet;
		this.parentValidationManager = parentValidationManager;
	}
}
