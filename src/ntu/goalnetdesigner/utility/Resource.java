package ntu.goalnetdesigner.utility;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Resource {
	
	private Resource () {
		
	}
	
	private static Resource instance = null;
	
	public static Resource getInstance(){
		if (instance == null){
			instance = new Resource();
		}
		return instance;
	}
	
	public Scene getFxmlScene(String fxmlPath) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root = (Parent) loader.load();
        return new Scene(root);
	}
	
	public static final String ABOUT_PATH = "/ntu/goalnetdesigner/fxui/About.fxml";
	public static final String FEEDBACK_PATH = "/ntu/goalnetdesigner/fxui/Feedback.fxml";
	public static final String LOGIN_PATH = "/ntu/goalnetdesigner/fxui/Login.fxml";
	public static final String MAINPAGE_PATH = "/ntu/goalnetdesigner/fxui/MainPage.fxml";
	public static final String NEW_GNET_PATH = "/ntu/goalnetdesigner/fxui/NewGNet.fxml";
	public static final String OPEN_GNET_PATH = "/ntu/goalnetdesigner/fxui/OpenGNet.fxml";
	public static final String REGISTER_PATH = "/ntu/goalnetdesigner/fxui/Register.fxml";
	public static final String RESET_PASSWORD_PATH = "/ntu/goalnetdesigner/fxui/ResetPassword.fxml";
	
}
