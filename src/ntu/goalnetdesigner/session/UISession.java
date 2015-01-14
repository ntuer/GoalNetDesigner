package ntu.goalnetdesigner.session;

import java.util.LinkedList;
import java.util.Queue;

import javafx.stage.Stage;
import ntu.goalnetdesigner.fxcontrol.IPaneController;
import ntu.goalnetdesigner.render.Renderable;

public class UISession {
	public static Stage primaryStage = null;
	
	public static boolean isInRenderedObject = false;
	public static boolean isDragging = false;
	public static Queue<Renderable> objectsForArc = new LinkedList<Renderable>();
	public static Object currentSelection = null;
	public static IPaneController currentPaneController = null;
	public static boolean isTreeVieewRefreshing = false;
}
