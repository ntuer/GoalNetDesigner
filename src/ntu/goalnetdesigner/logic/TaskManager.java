package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.UUID;

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
}
