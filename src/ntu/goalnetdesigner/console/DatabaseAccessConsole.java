package ntu.goalnetdesigner.console;

import ntu.goalnetdesigner.data.persistence.ActionLog;
import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.persistence.UserUsergroup;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.data.service.DataServiceUnit;

public class DatabaseAccessConsole {

	public static void main(String[] args) {
//		DataServiceUnit<ActionLog> pm = DataService.actionLog;
//		ActionLog a = pm.find("f34c7687-9a6f-11e4-a7a6-002556c2fbd9");
//		User u = a.getUser();
//		System.out.print(u.getId());
//		System.out.print(u.getActionLogs().get(0).getId());
		
		DataServiceUnit<User> pm2 = DataService.user;
		User u = pm2.find("lisiyao");
		UserUsergroup uug = u.getUserUsergroups().get(0).getUser().getUserUsergroups().get(0);
		
		System.out.print(uug.getIsAdmin() + "sd");
		
	}
}
