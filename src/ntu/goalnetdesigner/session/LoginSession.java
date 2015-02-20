package ntu.goalnetdesigner.session;

import ntu.goalnetdesigner.data.persistence.User;

public class LoginSession {
	public static User user = null;
	public static boolean isLoggedIn = false;
	public static String serverAddress = "localhost/gnetdb_new";
}
