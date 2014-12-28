package ntu.goalnetdesigner.logic;

import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.service.*;

public class LoginManager {
	public boolean isValidUser(String id, String password){
		User user = DataService.user.find(id);
		return user != null;
	}
}
