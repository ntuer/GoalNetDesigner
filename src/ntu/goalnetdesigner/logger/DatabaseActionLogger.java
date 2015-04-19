package ntu.goalnetdesigner.logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import ntu.goalnetdesigner.data.persistence.ActionLog;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;

public class DatabaseActionLogger {
	public static void log(String action, String targetObjectType, String targetObjectId){
		ActionLog al = new ActionLog();
		al.setAction(action);
		al.setOperationTargetObject(targetObjectType);
		al.setTargetObjectID(targetObjectId);
		al.setGnet(DataSession.Cache.gnet);
		al.setUser(LoginSession.user);
		al.setTimestamp(new Timestamp((new Date()).getTime()));;
		DataService.actionLog.persist(al);
		DataSession.Cache.actionLogs.add(al);
	}
}
