package ntu.goalnetdesigner.run;

import java.util.HashMap;
import java.util.Map;

import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.logic.ValidationManager;

public class ConnectionValidator implements IComponentValidator{
	private Gnet gnet;
	private ValidationManager parentValidationManager;

	public ConnectionValidator(Gnet gnet, ValidationManager parentValidationManager) {
		super();
		this.gnet = gnet;
		this.parentValidationManager = parentValidationManager;
	}
	
	private enum OffsetStatus{
		NOT_VISITED, HALF, COMPLETED;
		public static OffsetStatus getNext(OffsetStatus v) {
		     return v.ordinal() < OffsetStatus.values().length - 1
		         ? OffsetStatus.values()[v.ordinal() + 1]
		         : v;
		   }
	}
	
	public void validate() {
		HashMap<String, OffsetStatus> stateArcOffset = new HashMap<String, OffsetStatus>();
		HashMap<String, OffsetStatus> transitionArcOffset = new HashMap<String, OffsetStatus>();
		// init values in hashmap
		for (State s: gnet.getStates()){
			stateArcOffset.put(s.getId(), OffsetStatus.NOT_VISITED);
		}
		for (Transition s: gnet.getTransitions()){
			transitionArcOffset.put(s.getId(), OffsetStatus.NOT_VISITED);
		}
		// iterate arcs, and mark states and transitions
		// process start first
		for (Arc a: gnet.getArcs()){
			if (a.getDirection()){ // State to transition
				stateArcOffset.put(a.getInputID(), OffsetStatus.HALF);
			} else { // Transition to state
				transitionArcOffset.put(a.getInputID(), OffsetStatus.HALF);
			}
		}
		// process end later
		for (Arc a: gnet.getArcs()){
			if (a.getDirection()){ // State to transition
				transitionArcOffset.put(a.getOutputID(), OffsetStatus.getNext(transitionArcOffset.get(a.getOutputID())));
			} else { // Transition to state
				stateArcOffset.put(a.getOutputID(), OffsetStatus.getNext(stateArcOffset.get(a.getOutputID())));
			}
		}
		for (Map.Entry<String, OffsetStatus> entry : stateArcOffset.entrySet()) {
		    // Stand alone state not root
			if(entry.getValue() == OffsetStatus.NOT_VISITED){
				// Root state exception case
				if (gnet.getRootState() != null && 
						entry.getValue().equals(gnet.getRootState().getId()))
					continue;
				
		    	parentValidationManager
				.addError("State ID " + entry.getKey() + " is not connected to any transition and it's not the root state.");
		    }
			
			if(entry.getValue() == OffsetStatus.HALF){
				// start/end state exception case
				if (gnet.getStartState() != null && entry.getKey().equals(gnet.getStartState().getId()) ||
					gnet.getEndState() != null && entry.getKey().equals(gnet.getEndState().getId()))
					continue;
				
		    	parentValidationManager
				.addWarning("State ID " + entry.getKey() + " is a deadend. Make sure this is the behaviour you want.");
		    }
		}
		
		for (Map.Entry<String, OffsetStatus> entry : transitionArcOffset.entrySet()) {
		    // Stand alone state not root
			if(entry.getValue() == OffsetStatus.NOT_VISITED){
				parentValidationManager
				.addError("Transition ID " + entry.getKey() + " is not connected to any state.");
			} else if(entry.getValue() == OffsetStatus.HALF){
				parentValidationManager
				.addError("Transition ID " + entry.getKey() + " is a deadend.");
			}
		}
	}
}
