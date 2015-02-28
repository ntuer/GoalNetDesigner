package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.List;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.persistence.UserUsergroup;
import ntu.goalnetdesigner.data.persistence.Usergroup;
import ntu.goalnetdesigner.data.persistence.UsergroupGnet;

public class AuthorizationManager {
	public boolean canUserWriteGnet(User user, Gnet gnet){
		ArrayList<Usergroup> ugList = new ArrayList<>();
		for (UsergroupGnet ugnet: gnet.getUsergroupGnets()){
			if (ugnet.getWrite())
				ugList.add(ugnet.getUsergroup());
		}
		boolean canWrite = false;
		
		for (Usergroup ug: ugList){
			List<UserUsergroup> uugList = ug.getUserUsergroups();
			for (UserUsergroup uug: uugList){
				if (user.getId().equals(uug.getUser().getId())){
					canWrite = true;
					break;
				}
			}
			if (canWrite)
				break;
		}
		return canWrite;
	}
}
