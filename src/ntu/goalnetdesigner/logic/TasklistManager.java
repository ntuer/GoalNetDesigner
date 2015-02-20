package ntu.goalnetdesigner.logic;

import java.util.UUID;

import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.Tasklist;
import ntu.goalnetdesigner.session.DataSession;

public class TasklistManager {
	public static Tasklist newInstance(){
		Tasklist t = new Tasklist();
		t.setId(UUID.randomUUID().toString());
		t.setName("New tasklist");
		DataSession.Cache.tasklists.add(t);
		return t;
	}
}