package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.UUID;

import ntu.goalnetdesigner.data.persistence.Tasklist;
import ntu.goalnetdesigner.data.persistence.TasklistTask;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.session.DataSession;

public class TasklistManager {
	public static Tasklist newInstance(){
		Tasklist t = new Tasklist();
		t.setName("New tasklist");
		t.setTasklistTasks(new ArrayList<TasklistTask>());
		DataSession.Cache.tasklists.add(t);
		DataService.tasklist.persist(t);
		return t;
	}
}