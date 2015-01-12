package ntu.goalnetdesigner.logic;

import java.util.List;

import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.session.DataSession;

public class MainPageManager {
	public List<Method> getMethods(){
		return DataService.method.findAll();
	}
	
	public List<Arc> getArcs(){
		return DataSession.currentGNet.getArcs();
	}
	
	public List<State> getStates(){
		return DataSession.currentGNet.getStates();
	}
	
	public List<Task> getTasks(){
		return DataService.task.findAll();
	}
	
	public List<Transition> getTransitions(){
		return DataSession.currentGNet.getTransitions();
	}
}
