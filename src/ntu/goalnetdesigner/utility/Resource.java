package ntu.goalnetdesigner.utility;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ntu.goalnetdesigner.fxcontrol.propertypanecontrol.IPaneController;
import ntu.goalnetdesigner.session.UISession;

public class Resource {

	public static class System{
		public static final String SYSTEM_VERSION = "1.0";
	}
	
	// Lazy singleton as required by FXMLLoader getClass.
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
		root.getStylesheets().add(this.getClass().getResource(Resource.CSS_PATH).toExternalForm());
        return new Scene(root);
	}
	
	public Pane getPaneByFxml(String fxmlPath) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Pane root = (Pane) loader.load();
		root.getStylesheets().add(this.getClass().getResource(Resource.CSS_PATH).toExternalForm());
		UISession.currentPaneController = (IPaneController) loader.getController();
        return root;
	}
	
	public static final String CSS_PATH = "/ntu/goalnetdesigner/css/Modena.css";
	
	public static final String ABOUT_PATH = "/ntu/goalnetdesigner/fxui/About.fxml";
	public static final String FEEDBACK_PATH = "/ntu/goalnetdesigner/fxui/Feedback.fxml";
	public static final String MAINPAGE_PATH = "/ntu/goalnetdesigner/fxui/MainPage.fxml";
	public static final String NEW_GNET_PATH = "/ntu/goalnetdesigner/fxui/NewGNet.fxml";
	public static final String OPEN_GNET_PATH = "/ntu/goalnetdesigner/fxui/OpenGNet.fxml";
	public static final String EDIT_GNET_PROPERTY_PATH = "/ntu/goalnetdesigner/fxui/EditGNetProperty.fxml";

	public static final String MANAGE_STATE_FUNCTION_PATH = "/ntu/goalnetdesigner/fxui/ManageStateFunction.fxml";
	public static final String MANAGE_TASK_FUNCTION_PATH = "/ntu/goalnetdesigner/fxui/ManageTaskFunction.fxml";
	public static final String MANAGE_TRANSITION_TASK_PATH = "/ntu/goalnetdesigner/fxui/ManageTransitionTask.fxml";
	
	public static final String LOGIN_PATH = "/ntu/goalnetdesigner/fxui/login/Login.fxml";	
	public static final String REGISTER_PATH = "/ntu/goalnetdesigner/fxui/login/Register.fxml";
	public static final String RESET_PASSWORD_PATH = "/ntu/goalnetdesigner/fxui/login/ResetPassword.fxml";
	
	public static final String ARC_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/propertypane/ArcPropertyPane.fxml";
	public static final String STATE_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/propertypane/StatePropertyPane.fxml";
	public static final String TRANSITION_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/propertypane/TransitionPropertyPane.fxml";
	public static final String FUNCTION_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/propertypane/FunctionPropertyPane.fxml";
	public static final String TASK_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/propertypane/TaskPropertyPane.fxml";
	
	public static final Color STATE_COLOR = Color.PALEGREEN;
	public static final Color COMPOSITE_STATE_COLOR = Color.BLUEVIOLET;
	public static final Color TRANSITION_COLOR = Color.PALEGREEN;
	public static final Color SELECTION_COLOR = Color.GREEN;
	public static final Color ARC_COLOR = Color.BLACK;
	public static final Color COMPOSITION_ARC_COLOR = Color.BLUEVIOLET;
	public static final Color SELECTED_OBJECT_COLOR = Color.RED;
	
	public static final double STATE_RADIUS = 25.0;
	public static final double TRANSITION_HEIGHT = 30.0;
	public static final double TRANSITION_WIDTH = 60.0;
	
	public static final int NORMAL_STROKE_WIDTH = 2;
	public static final int SELECTED_STROKE_WIDTH = 4;

	public static final int GRAPH_BORDER_SIZE = 50;
}
