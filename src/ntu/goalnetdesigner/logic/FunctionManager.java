package ntu.goalnetdesigner.logic;

import java.util.UUID;

import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.session.DataSession;

public class FunctionManager {
	public static Method newInstance(){
		Method m = new Method();
		m.setId(UUID.randomUUID().toString());
		m.setName("New function");
		DataSession.Cache.functions.add(m);
		return m;
	}
}
