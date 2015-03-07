package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.UUID;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.StateFunction;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.TaskFunction;
import ntu.goalnetdesigner.data.persistence.TasklistTask;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.session.DataSession;

public class TaskManager {
	public static Task newInstance(){
		Task t = new Task();
		t.setId(UUID.randomUUID().toString());
		t.setName("New Task");
		t.setTaskFunctions(new ArrayList<TaskFunction>());
		t.setTasklistTasks(new ArrayList<TasklistTask>());
		DataSession.Cache.tasks.add(t);
		DataService.task.persist(t);
		return t;
	}
	
	public static Task cloneInstanceToGnet(Task old, Gnet gnet){
		Task t = new Task();
		t.setId(UUID.randomUUID().toString());
		t.setName(old.getName());
		t.setTaskFunctions(new ArrayList<TaskFunction>());
		t.setTasklistTasks(new ArrayList<TasklistTask>());
		gnet.addTask(t);
		DataService.task.persist(t);
		// copy over associated functions too
		if (old.getTaskFunctions().size() != 0){
			for (TaskFunction oldtf: old.getTaskFunctions()){
				Method newm = FunctionManager.cloneInstanceToGnet(oldtf.getMethod(), gnet);
				TaskFunction newtf = new TaskFunction();
				newm.addTaskFunction(newtf);
				t.addTaskFunction(newtf);
				DataService.taskFunction.persist(newtf);
			}
		}
		return t;
	}
}
