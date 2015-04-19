package ntu.goalnetdesigner.session;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.Stage;
import ntu.goalnetdesigner.fxcontrol.propertypanecontrol.IPaneController;
import ntu.goalnetdesigner.render.Drawable;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.utility.CurrentDrawingMode;
import ntu.goalnetdesigner.utility.UIUtility;

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
	public static ObjectProperty<Object> cs = new SimpleObjectProperty<>();
	public static Object currentSelection = null;  // has to be object because it can be Renderable or Drawable
	public static IPaneController currentPaneController = null;
	
	// group selection status
	public static SelectionModel currentGroupSelection = new SelectionModel();

	// Direct click on drawing area
	public static void setCurrentSelection(Renderable obj){
		try{
			// restore shape of unselected object
			Renderable selectedObject = getRenderableFromCurrentSelection();
			if (selectedObject != null)
				UIUtility.Draw.restoreBorder(selectedObject);
		} catch (Exception e){
			
		} finally {
			UIUtility.Draw.setBoldBorder(obj);
			currentSelection = obj;
		}
	}
	
	// Click on tab pane
	public static void setCurrentSelection(Drawable obj){
		try{
			Renderable selectedObject = getRenderableFromCurrentSelection();
			if (selectedObject != null)
				UIUtility.Draw.restoreBorder(selectedObject);
		} catch (Exception e){
			
		} finally {
			UIUtility.Draw.setBoldBorder(obj.getRenderedObject());
			currentSelection = obj;
		}
	}
	
	// Fallback option for Functions and Tasks.
	public static void setCurrentSelection(Object obj){
		try{
			Renderable selectedObject = getRenderableFromCurrentSelection();
			if (selectedObject != null)
				UIUtility.Draw.restoreBorder(selectedObject);
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
	
	
	public static class SelectionModel {

		Set<Renderable> selection = new HashSet<>();

		public void add(Renderable node) {
			UIUtility.Draw.setBoldBorder(node);
			selection.add(node);
		}

		public void remove(Renderable node) {
			UIUtility.Draw.restoreBorder(node);
			selection.remove(node);
		}

		public void clear() {
			while (!selection.isEmpty()) {
				remove(selection.iterator().next());
			}
		}

		public boolean contains(Renderable node) {
			return selection.contains(node);
		}

		public void log() {
			System.out.println("Items in model: "
					+ Arrays.asList(selection.toArray()));
		}
		
		public int size(){
			return selection.size();
		}
		
		public Set<Renderable> getSelection(){
			return this.selection;
		}
	}
	public static CurrentDrawingMode currentDrawingMode = null;
}
