package ntu.goalnetdesigner.session;

import java.util.ArrayList;
import java.util.List;

import ntu.goalnetdesigner.data.persistence.ActionLog;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.IDataServiceUnitSubscriber;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Tasklist;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.render.Drawable;
import ntu.goalnetdesigner.utility.CurrentDrawingMode;

public class DataSession {
	public static CurrentDrawingMode currentDrawingMode = 
			CurrentDrawingMode.STATE;
	
	public static class Cache {
		public static Gnet gnet = null;
		public static List<Task> tasks = null;
		public static List<Tasklist> tasklists = null;
		public static List<Arc> arcs = null;
		public static List<State> states = null;
		public static List<Method> functions = null;
		public static List<Transition> transitions = null;
		
		public static List<ActionLog> actionLogs = new ArrayList<ActionLog>();
		
		// this can be used to refresh cache as well
		public static void setGNetCache(Gnet gnet){
			if (gnet == null){
				Cache.gnet = null;
				Cache.arcs = null;
				Cache.states = null;
				Cache.transitions = null;
				loadTasksAndMethods(null);
			} else {
				Cache.gnet = gnet;
				Cache.arcs = gnet.getArcs();
				Cache.states = gnet.getStates();
				Cache.transitions = gnet.getTransitions();
				loadTasksAndMethods();
				DataService.begin();
			}
		}
		
		public static void loadTasksAndMethods(){
			Cache.functions = DataService.method.findAll();
			Cache.tasks = DataService.task.findAll();
			Cache.tasklists = DataService.tasklist.findAll();
		}
		
		public static void loadTasksAndMethods(Object Null){
			Cache.functions = null;
			Cache.tasks = null;
			Cache.tasklists = null;
		}
	}
	
}
