package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.UUID;

import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.StateFunction;
import ntu.goalnetdesigner.data.persistence.TaskFunction;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.session.DataSession;

public class FunctionManager {
	public static Method newInstance(){
		Method m = new Method();
		m.setId(UUID.randomUUID().toString());
		m.setName("New function");
		m.setFileName("filename");
		m.setRTType("void");
		m.setStateFunctions(new ArrayList<StateFunction>());
		m.setTaskFunctions(new ArrayList<TaskFunction>());
		DataSession.Cache.functions.add(m);
		DataService.method.persist(m);
		return m;
	}
}
