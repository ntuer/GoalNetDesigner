package ntu.goalnetdesigner.logger;

import java.util.UUID;

import ntu.goalnetdesigner.data.persistence.ActionLog;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;

public class DatabaseActionLogger {
	public static void log(String action, String targetObjectType, String targetObjectId){
		ActionLog al = new ActionLog();
		al.setId(UUID.randomUUID().toString());
		al.setAction(action);
		al.setOperationTargetObject(targetObjectType);
		al.setTargetObjectID(targetObjectId);
		al.setGnet(DataSession.Cache.gnet);
		al.setUser(LoginSession.user);
		DataService.actionLog.persist(al);
	}
}
