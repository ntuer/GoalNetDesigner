package ntu.goalnetdesigner.session;

import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.logic.AuthorizationManager;

public class LoginSession {
	public static User user = null;
	public static boolean isLoggedIn = false;
	public static String serverAddress = "localhost/gnetdb_new";
}
