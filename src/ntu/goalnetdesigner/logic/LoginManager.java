package ntu.goalnetdesigner.logic;

import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.service.*;
import ntu.goalnetdesigner.session.LoginSession;

public class LoginManager {
	
	public boolean login(String id, String password){
		User user = DataService.user.find(id);
		LoginSession.user = user;
		LoginSession.isLoggedIn = user != null? true : false;
		return user != null;
	}
}
