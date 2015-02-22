package ntu.goalnetdesigner.session;

import java.util.LinkedList;
import java.util.Queue;

import javafx.stage.Stage;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.fxcontrol.propertypanecontrol.IPaneController;
import ntu.goalnetdesigner.render.Drawable;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderedArc;

public class UISession {
	// Main window reference
	public static Stage primaryStage = null;
	public static Stage secondaryStage = null;
	
	// Object dragging and onclick lock
	public static boolean isInRenderedObject = false;
	public static boolean isDragging = false;
	
	// Arc drawing temp storage
	public static Queue<Renderable> objectsForArc = new LinkedList<Renderable>();
	
	// treeview refresh lock
	public static boolean isTreeViewRefreshing = false;
	
	// current selection status
	public static Object currentSelection = null;  // has to be object because it can be Renderable or Drawable
	public static IPaneController currentPaneController = null;

	public static void setCurrentSelection(Renderable obj){
		try{
			// restore shape of unselected object
			Renderable selectedObject = getRenderableFromCurrentSelection();
			if (selectedObject != null)
				selectedObject.getShape().setStrokeWidth(1);
		} catch (Exception e){
			
		} finally {
			obj.getShape().setStrokeWidth(5);
			currentSelection = obj;
		}
	}
	
	public static void setCurrentSelection(Drawable obj){
		try{
			Renderable selectedObject = getRenderableFromCurrentSelection();
			if (selectedObject != null)
				selectedObject.getShape().setStrokeWidth(1);
		} catch (Exception e){
			
		} finally {
			obj.getRenderedObject().getShape().setStrokeWidth(5);
			currentSelection = obj;
		}
	}
	
	// Fallback option for Functions and Tasks.
	public static void setCurrentSelection(Object obj){
		try{
			Renderable selectedObject = getRenderableFromCurrentSelection();
			if (selectedObject != null)
				selectedObject.getShape().setStrokeWidth(1);
		} catch (Exception e){
			
		} finally {
			currentSelection = obj;
		}
	}

	public static Renderable getRenderableFromCurrentSelection(){
		Renderable selectedObject = null;
		// try-catch to find renderedObject
		try {
			selectedObject = ((Drawable) UISession.currentSelection).getRenderedObject();
		} catch (Exception e) {
			selectedObject = ((Renderable) UISession.currentSelection);
		}
		return selectedObject;
	}
	
	public static Drawable getDrawableFromCurrentSelection(){
		Drawable d = null;
		try {
			d = ((Renderable) UISession.currentSelection).getBaseObject();
		} catch (Exception e){
			d = (Drawable) UISession.currentSelection;
		}
		return d;
	}
}
