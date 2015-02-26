package ntu.goalnetdesigner.run;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.logic.ValidationManager;

public class GNetValidator implements IComponentValidator{
	private Gnet gnet;
	private ValidationManager parentValidationManager;

	public GNetValidator(Gnet gnet, ValidationManager parentValidationManager) {
		super();
		this.gnet = gnet;
		this.parentValidationManager = parentValidationManager;
	}

	public void validate() {
		State root = this.gnet.getRootState();
		if (root == null) {
			parentValidationManager
					.addWarning("This Goal Net has no root state.");
		} else if (!root.getComposite()) {
			parentValidationManager
					.addError("A Goal Net's root state must be a composite state. Current root state "
							+ root + " is not a composite state");
		}
		if (this.gnet.getStartState() == null) {
			parentValidationManager
					.addError("This Goal Net has no start state.");
		}
		if (this.gnet.getEndState() == null) {
			parentValidationManager.addError("This Goal Net has no end state.");
		}
	}
}
