package ntu.goalnetdesigner.utility;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ntu.goalnetdesigner.fxcontrol.IPaneController;
import ntu.goalnetdesigner.session.UISession;

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
	
	public Scene getSceneByFxml(String fxmlPath) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root = (Parent) loader.load();
        return new Scene(root);
	}
	
	public Pane getPaneByFxml(String fxmlPath) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Pane root = (Pane) loader.load();
		UISession.currentPaneController = (IPaneController) loader.getController();
        return root;
	}
	
	public static final String CSS_PATH = "/ntu/goalnetdesigner/css/JMetroLightTheme.css";
	
	public static final String ABOUT_PATH = "/ntu/goalnetdesigner/fxui/About.fxml";
	public static final String FEEDBACK_PATH = "/ntu/goalnetdesigner/fxui/Feedback.fxml";
	public static final String LOGIN_PATH = "/ntu/goalnetdesigner/fxui/Login.fxml";
	public static final String MAINPAGE_PATH = "/ntu/goalnetdesigner/fxui/MainPage.fxml";
	public static final String NEW_GNET_PATH = "/ntu/goalnetdesigner/fxui/NewGNet.fxml";
	public static final String OPEN_GNET_PATH = "/ntu/goalnetdesigner/fxui/OpenGNet.fxml";
	public static final String REGISTER_PATH = "/ntu/goalnetdesigner/fxui/Register.fxml";
	public static final String RESET_PASSWORD_PATH = "/ntu/goalnetdesigner/fxui/ResetPassword.fxml";
	
	
	public static final String ARC_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/ArcPropertyPane.fxml";
	public static final String STATE_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/StatePropertyPane.fxml";
	public static final String TRANSITION_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/TransitionPropertyPane.fxml";
	
	public static final Color STATE_COLOR = Color.PALEGREEN;
	public static final Color COMPOSITE_STATE_COLOR = Color.TOMATO;
	public static final Color TRANSITION_COLOR = Color.PALEGREEN;
	public static final Color SELECTION_COLOR = Color.GREEN;
	public static final Color ARC_COLOR = Color.BLACK;
	
	public static final double STATE_RADIUS = 25.0;
	public static final double TRANSITION_HEIGHT = 30.0;
	public static final double TRANSITION_WIDTH = 60.0;
}
