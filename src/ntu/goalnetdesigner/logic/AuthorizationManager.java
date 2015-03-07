package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.List;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.persistence.UserUsergroup;
import ntu.goalnetdesigner.data.persistence.Usergroup;
import ntu.goalnetdesigner.data.persistence.UsergroupGnet;
import ntu.goalnetdesigner.data.service.*;
import ntu.goalnetdesigner.session.LoginSession;

public class AuthorizationManager {
	
	public boolean login(String id, String password){
		User user = DataService.user.find(id);
		LoginSession.user = user;
		LoginSession.isLoggedIn = user != null? true : false;
		return user != null;
	}

	public boolean register(User u) {
		try{
			DataService.user.atomicInsert(u);
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean changePassword(User user, String text) {
		try{
			DataService.begin();
			user.setPassword(text);
			DataService.commit();
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
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
