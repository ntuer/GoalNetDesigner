package ntu.goalnetdesigner.session;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javafx.stage.Stage;
import ntu.goalnetdesigner.fxcontrol.propertypanecontrol.IPaneController;
import ntu.goalnetdesigner.render.Drawable;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.utility.CurrentDrawingMode;
import ntu.goalnetdesigner.utility.CurrentSelectionProperty;
import ntu.goalnetdesigner.utility.UIUtility;

public class UISession {
	// Main window reference
	public static Stage primaryStage = null;
	public static Stage secondaryStage = null;
	
	// Object dragging and onclick lock
	public static boolean isInRenderedObject = false;
	public static boolean isDragging = false;
	// treeview refresh lock
	public static boolean isTreeViewRefreshing = false;
	
	// Arc drawing temp storage
	public static Queue<Renderable> objectsForArc = new LinkedList<Renderable>();
	
	public static IPaneController currentPaneController = null;
	public static CurrentSelectionProperty currentSelection = new CurrentSelectionProperty();
	
	// group selection status
	public static GroupSelectionModel currentGroupSelection = new GroupSelectionModel();
	
	public static Drawable getCurrentSelectionAsDrawable(){
		Object obj = UISession.currentSelection.getValue();
		if (obj instanceof Renderable)
			return ((Renderable) obj).getBaseObject();
		else if (obj instanceof Drawable)
			return (Drawable) obj;
		else 
			return null;
	}
	
	public static class GroupSelectionModel {
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
