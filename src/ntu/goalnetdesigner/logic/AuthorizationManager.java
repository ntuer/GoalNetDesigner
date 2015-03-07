package ntu.goalnetdesigner.logic;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.service.DataService;
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
		return true;
	}
}
