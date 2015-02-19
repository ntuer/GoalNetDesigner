package ntu.goalnetdesigner.session;

import java.util.LinkedList;
import java.util.Queue;

import javafx.stage.Stage;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.fxcontrol.IPaneController;
import ntu.goalnetdesigner.render.Drawable;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderedTransition;

public class UISession {
	// Main window reference
	public static Stage primaryStage = null;
	
	// Object dragging and onclick lock
	public static boolean isInRenderedObject = false;
	public static boolean isDragging = false;
	
	// Arc drawing temp storage
	public static Queue<Renderable> objectsForArc = new LinkedList<Renderable>();
		
	// treeview refresh lock
	public static boolean isTreeVieewRefreshing = false;
	
	// current selection status
	public static Object currentSelection = null;  // has to be object because it can be Renderable or Drawable
	public static IPaneController currentPaneController = null;

	public static void setCurrentSelection(Renderable obj){
		Renderable selectedObject = getRenderableFromCurrentSelection();
		if (selectedObject != null)
			selectedObject.getShape().setStrokeWidth(2);
		obj.getShape().setStrokeWidth(5);
		currentSelection = obj;
	}
	
	private static Renderable getRenderableFromCurrentSelection(){
		Renderable selectedObject = null;
		try {
			selectedObject = ((Drawable) UISession.currentSelection).getRenderedObject();
		} catch (Exception e) {
			selectedObject = ((Renderable) UISession.currentSelection);
		}
		return selectedObject;
	}
	
	public static void setCurrentSelection(Drawable obj){
		Renderable selectedObject = getRenderableFromCurrentSelection();
		if (selectedObject != null)
			selectedObject.getShape().setStrokeWidth(2);
		obj.getRenderedObject().getShape().setStrokeWidth(5);
		currentSelection = obj;
	}
	
}
