package ntu.goalnetdesigner.logic;

import java.util.UUID;

import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.session.DataSession;

public class TaskManager {
	public static Task newInstance(){
		Task t = new Task();
		t.setId(UUID.randomUUID().toString());
		t.setName("New Task");
		DataSession.Cache.tasks.add(t);
		return t;
	}
}
