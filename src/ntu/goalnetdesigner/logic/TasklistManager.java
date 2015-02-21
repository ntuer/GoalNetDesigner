package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.UUID;

import ntu.goalnetdesigner.data.persistence.Tasklist;
import ntu.goalnetdesigner.data.persistence.TasklistTask;
import ntu.goalnetdesigner.session.DataSession;

public class TasklistManager {
	public static Tasklist newInstance(){
		Tasklist t = new Tasklist();
		t.setId(UUID.randomUUID().toString());
		t.setName("New tasklist");
		t.setTasklistTasks(new ArrayList<TasklistTask>());
		DataSession.Cache.tasklists.add(t);
		DataSession.Diff.newObjects.add(t);
		return t;
	}
}