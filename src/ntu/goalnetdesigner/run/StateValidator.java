package ntu.goalnetdesigner.run;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.logic.ValidationManager;

public class StateValidator implements IComponentValidator {
	private Gnet gnet;
	private ValidationManager parentValidationManager;

	public StateValidator(Gnet gnet, ValidationManager parentValidationManager) {
		super();
		this.gnet = gnet;
		this.parentValidationManager = parentValidationManager;
	}

	public void validate() {
		for (State s: gnet.getStates()){
			if (s.getComposite()){
				if (s.getCompositeStartState() == null){
					parentValidationManager
					.addError("Composite state " + s.getName() + " has no start state.");
				}
				if (s.getCompositeEndState() == null){
					parentValidationManager
					.addError("Composite state " + s.getName() + " has no end state.");
				}
			} else {
				if(s.getStateFunctions() == null ||
						s.getStateFunctions().size() == 0){
					parentValidationManager
					.addWarning("State " + s.getName() + " has no functions associated and it is not a composite state.");
				}
			}
		}
	}
}
