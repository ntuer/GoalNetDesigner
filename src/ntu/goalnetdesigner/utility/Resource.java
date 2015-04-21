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
		//root.getStylesheets().add(this.getClass().getResource(Resource.CSS_PATH).toExternalForm());
        return new Scene(root);
	}
	
	public Pane getPaneByFxml(String fxmlPath) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Pane root = (Pane) loader.load();
//		root.getStylesheets().add(this.getClass().getResource(Resource.CSS_PATH).toExternalForm());
		UISession.currentPaneController = (IPaneController) loader.getController();
        return root;
	}
	
	public static final String CSS_PATH = "/ntu/goalnetdesigner/css/Modena.css";
	
	public static final String STATISTICS_PATH = "/ntu/goalnetdesigner/fxui/Statistics.fxml";
	public static final String FEEDBACK_PATH = "/ntu/goalnetdesigner/fxui/Feedback.fxml";
	public static final String MAINPAGE_PATH = "/ntu/goalnetdesigner/fxui/MainPage.fxml";
	public static final String NEW_GNET_PATH = "/ntu/goalnetdesigner/fxui/NewGNet.fxml";
	public static final String OPEN_GNET_PATH = "/ntu/goalnetdesigner/fxui/OpenGNet.fxml";
	public static final String EDIT_GNET_PROPERTY_PATH = "/ntu/goalnetdesigner/fxui/EditGNetProperty.fxml";

	public static final String MANAGE_STATE_FUNCTION_PATH = "/ntu/goalnetdesigner/fxui/propertypane/ManageStateFunction.fxml";
	public static final String MANAGE_TASK_FUNCTION_PATH = "/ntu/goalnetdesigner/fxui/propertypane/ManageTaskFunction.fxml";
	public static final String MANAGE_TRANSITION_TASK_PATH = "/ntu/goalnetdesigner/fxui/propertypane/ManageTransitionTask.fxml";
	
	public static final String LOGIN_PATH = "/ntu/goalnetdesigner/fxui/login/Login.fxml";	
	public static final String REGISTER_PATH = "/ntu/goalnetdesigner/fxui/login/Register.fxml";
	public static final String RESET_PASSWORD_PATH = "/ntu/goalnetdesigner/fxui/login/ResetPassword.fxml";
	
	public static final String SHARE_GNET_PATH = "/ntu/goalnetdesigner/fxui/sharing/ShareGNet.fxml";
	public static final String SHARE_TASK_PATH = "/ntu/goalnetdesigner/fxui/sharing/ShareTask.fxml";
	public static final String SHARE_FUNCTION_PATH = "/ntu/goalnetdesigner/fxui/sharing/ShareFunction.fxml";
	
	public static final String ARC_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/propertypane/ArcPropertyPane.fxml";
	public static final String STATE_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/propertypane/StatePropertyPane.fxml";
	public static final String TRANSITION_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/propertypane/TransitionPropertyPane.fxml";
	public static final String FUNCTION_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/propertypane/FunctionPropertyPane.fxml";
	public static final String TASK_PROPERTY_PANE_PATH ="/ntu/goalnetdesigner/fxui/propertypane/TaskPropertyPane.fxml";
	
	public static final Color STATE_COLOR = Color.PALEGREEN;
	public static final Color COMPOSITE_STATE_COLOR = Color.RED;
	public static final Color TRANSITION_COLOR = Color.PALEGREEN;
	public static final Color SELECTION_COLOR = Color.GREEN;
	public static final Color ARC_COLOR = Color.BLACK;
	public static final Color COMPOSITION_ARC_COLOR = Color.RED;
	
	public static final double STATE_RADIUS = 25.0;
	public static final double TRANSITION_HEIGHT = 30.0;
	public static final double TRANSITION_WIDTH = 60.0;
	
	public static final int NORMAL_STROKE_WIDTH = 2;
	public static final int SELECTED_STROKE_WIDTH = 4;

	public static final int EXPORT_PICTURE_BORDER_SIZE = 50;
	
	public static class UserGnetAccessLevel{
		public static final String READ = "Read";
		public static final String WRITE = "Write";
		public static final String ADMIN = "Admin";
	}
	
	public static class Action{
		public static final String CREATE = "Create";
		public static final String EDIT = "Edit";
		public static final String DELETE = "Delete";
		public static final String MOVE = "Move";
		public static final String OPEN = "Open";
		public static final String CLOSE = "Close";
	}
	
	public static class ActionTargetType{
		public static final String GNET = "GNet";
		public static final String METHOD = "Method";
		public static final String TASK = "Task";
		public static final String STATE = "State";
		public static final String TRANSITION = "Transition";
		public static final String ARC = "Arc";
		public static final String STATE_FUNCTION = "State_Function";
		public static final String TASK_FUNCTION = "Task_Function";
		public static final String TASKLIST_TASK = "Tasklist_Task";
	}
	
	public static class EducationLevel{
		public static final String HIGH_SCHOOL_AND_BELOW = "High School and Below";
		public static final String UNDERGRADUATE = "Undergraduate";
		public static final String GRADUATE = "Graduate";
	}
}
