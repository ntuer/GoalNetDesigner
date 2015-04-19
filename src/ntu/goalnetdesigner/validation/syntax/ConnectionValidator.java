package ntu.goalnetdesigner.validation.syntax;

import java.util.HashMap;
import java.util.Map;

import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.logic.ValidationManager;
import ntu.goalnetdesigner.validation.IComponentValidator;

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
		HashMap<State, OffsetStatus> stateArcOffset = new HashMap<State, OffsetStatus>();
		HashMap<Transition, OffsetStatus> transitionArcOffset = new HashMap<Transition, OffsetStatus>();
		// init values in hashmap
		for (State s: gnet.getStates()){
			stateArcOffset.put(s, OffsetStatus.NOT_VISITED);
		}
		for (Transition s: gnet.getTransitions()){
			transitionArcOffset.put(s, OffsetStatus.NOT_VISITED);
		}
		// iterate arcs, and mark states and transitions
		// process start first
		for (Arc a: gnet.getArcs()){
			if (a.getDirection()){ // State to transition
				for (State st: gnet.getStates()){
					if (st.getId().equals(a.getInputID()))
						stateArcOffset.put(st, OffsetStatus.HALF);
				}
			} else { // Transition to state
				for (Transition tr: gnet.getTransitions()){
					if (tr.getId().equals(a.getInputID()))
						transitionArcOffset.put(tr, OffsetStatus.HALF);
				}
			}
		}
		// process end later
		for (Arc a: gnet.getArcs()){
			if (a.getDirection()){ // State to transition
				for (Transition tr: gnet.getTransitions()){
					if (tr.getId().equals(a.getOutputID()))
						transitionArcOffset.put(tr, OffsetStatus.getNext(transitionArcOffset.get(tr)));
				}
			} else { // Transition to state
				for (State st: gnet.getStates()){
					if (st.getId().equals(a.getOutputID()))
						stateArcOffset.put(st, OffsetStatus.getNext(stateArcOffset.get(st)));
				}
			}
		}
		for (Map.Entry<State, OffsetStatus> entry : stateArcOffset.entrySet()) {
		    // Stand alone state not root
			if(entry.getValue() == OffsetStatus.NOT_VISITED){
				// Root state exception case
				if (gnet.getRootState() != null && 
						entry.getKey().getId().equals(gnet.getRootState().getId()))
					continue;
				
		    	parentValidationManager
				.addError(entry.getKey(), "State " + entry.getKey() + " is not connected to any transition and it's not the root state.");
		    }
			
			if(entry.getValue() == OffsetStatus.HALF){
				// start/end state exception case
				if (gnet.getStartState() != null && entry.getKey().getId().equals(gnet.getStartState().getId()) ||
					gnet.getEndState() != null && entry.getKey().getId().equals(gnet.getEndState().getId()))
					continue;
				
		    	parentValidationManager
				.addWarning(entry.getKey(), "State " + entry.getKey() + " is a deadend. Make sure this is the behaviour you want.");
		    }
		}
		
		for (Map.Entry<Transition, OffsetStatus> entry : transitionArcOffset.entrySet()) {
		    // Stand alone state not root
			if(entry.getValue() == OffsetStatus.NOT_VISITED){
				parentValidationManager
				.addError(entry.getKey(), "Transition " + entry.getKey() + " is not connected to any state.");
			} else if(entry.getValue() == OffsetStatus.HALF){
				parentValidationManager
				.addError(entry.getKey(), "Transition " + entry.getKey() + " is a deadend.");
			}
		}
	}
}
