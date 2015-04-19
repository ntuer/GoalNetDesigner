package ntu.goalnetdesigner.logic;

import java.util.ArrayList;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.StateFunction;
import ntu.goalnetdesigner.data.persistence.TaskFunction;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Resource;

public class FunctionManager {
	public static Method newInstance(){
		Method m = new Method();
		m.setName("New function");
		m.setFileName("filename");
		m.setRTType("void");
		m.setStateFunctions(new ArrayList<StateFunction>());
		m.setTaskFunctions(new ArrayList<TaskFunction>());
		DataSession.Cache.gnet.addMethod(m);
		DataService.method.persist(m);
		DatabaseActionLogger.log(Resource.Action.CREATE, Resource.ActionTargetType.METHOD, m.getId());
		return m;
	}
	
	public static Method cloneInstanceToGnet(Method old, Gnet gnet){
		Method m = new Method();
		m.setName(old.getName());
		m.setFileName(old.getFileName());
		m.setRTType(old.getRTType());
		m.setStateFunctions(new ArrayList<StateFunction>());
		m.setTaskFunctions(new ArrayList<TaskFunction>());
		gnet.addMethod(m);
		DataService.method.persist(m);
		return m;
	}
}
