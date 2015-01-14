package ntu.goalnetdesigner.render;

import java.lang.reflect.Constructor;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.StrokeType;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.CurrentGNetObjectSelection;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedIDrawableObjectFactory{
	
	public static Renderable getRenderedObject(double x, double y, ScrollPane tv, AnchorPane drawingPane)
			throws Exception {
		
		// create the rendered object
		Class clazz = Class.forName("ntu.goalnetdesigner.render.Rendered" + getClassName());
		Constructor c = clazz.getConstructor(new Class[]{double.class, double.class});
		Renderable returnValue = (Renderable) c.newInstance(new Object[]{new Double(x), new Double(y)});
		if (DataSession.currentGNetObjectSelection == CurrentGNetObjectSelection.COMPOSITE_STATE){
			((RenderedState) returnValue).getBaseObject().setComposite(true);
			((RenderedState) returnValue).getShape().setFill(Resource.COMPOSITE_STATE_COLOR.deriveColor(1, 1, 1, 0.5));
			((RenderedState) returnValue).getShape().setStroke(Resource.COMPOSITE_STATE_COLOR);
			((RenderedState) returnValue).getShape().setStrokeWidth(2);
			((RenderedState) returnValue).getShape().setStrokeType(StrokeType.OUTSIDE);
		}
		MouseEventHandler meh = new MouseEventHandler(tv, drawingPane);
		returnValue.setMeh(meh);
		return returnValue;
	}
	
	// for arc
	public static RenderedArc getRenderedObject(double sx, double sy, double ex, double ey, ScrollPane tv, AnchorPane drawingPane) throws Exception {
		
		// create the rendered object
		Class clazz = Class.forName("ntu.goalnetdesigner.render.RenderedArc");
		Constructor c = clazz.getConstructor(new Class[]{double.class, double.class, double.class, double.class});
		RenderedArc returnValue = (RenderedArc) c.newInstance(
				new Object[]{new Double(sx), new Double(sy), new Double(ex), new Double(ey)});
		
		MouseEventHandler meh = new MouseEventHandler(tv, drawingPane);
		returnValue.setMeh(meh);
		return returnValue;
	}
	
	public static String getClassName(){
		switch(DataSession.currentGNetObjectSelection){
			case STATE:
				return "State";
			case COMPOSITE_STATE:
				return "State";
			case TRANSITION:
				return "Transition";
			case ARC:
				return "Arc";
			default:
				return "State";
		}
	}
}
