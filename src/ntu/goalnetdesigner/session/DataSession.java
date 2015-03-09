package ntu.goalnetdesigner.session;

import java.util.ArrayList;
import java.util.List;

import ntu.goalnetdesigner.data.persistence.ActionLog;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Tasklist;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.utility.Resource;

public class DataSession {
	public static class Cache {
		public static Gnet gnet = null;
		public static List<Task> tasks = null;
		public static List<Tasklist> tasklists = null;
		public static List<Arc> arcs = null;
		public static List<State> states = null;
		public static List<Method> functions = null;
		public static List<Transition> transitions = null;
		public static List<ActionLog> actionLogs = null;
	}
	
	public static String pathToExe = "";

	// this can be used to refresh cache as well
	public static void setGNetCache(Gnet gnet){
		if (gnet == null){
			Cache.gnet = null;
			Cache.arcs = null;
			Cache.states = null;
			Cache.transitions = null;
			Cache.functions = null;
			Cache.tasks = null;
			Cache.tasklists = null;
			Cache.actionLogs = null;
			DataService.reestablish();
			String userId = LoginSession.user.getId();
			LoginSession.user = null;
			LoginSession.user = DataService.user.find(userId);
		} else {
			Cache.gnet = gnet;
			Cache.arcs = gnet.getArcs();
			Cache.states = gnet.getStates();
			Cache.transitions = gnet.getTransitions();
			Cache.functions = gnet.getMethods();
			Cache.tasks = gnet.getTasks();
			Cache.tasklists = DataService.tasklist.findAll();
			Cache.actionLogs = new ArrayList<ActionLog>();
			DataService.begin();
			DatabaseActionLogger.log(Resource.Action.OPEN, Resource.ActionTargetType.GNET, DataSession.Cache.gnet.getId());
		}
		
	}	
}
