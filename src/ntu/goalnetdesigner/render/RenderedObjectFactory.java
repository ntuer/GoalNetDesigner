package ntu.goalnetdesigner.render;

import java.lang.reflect.Constructor;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.CurrentDrawingMode;

public class RenderedObjectFactory{
	
	// for state and transition
	public static Renderable getNewRenderedObject(double x, double y, ScrollPane tv, AnchorPane drawingPane)
			throws Exception {
		Class clazz = Class.forName("ntu.goalnetdesigner.render.Rendered" + getClassName());
		Constructor c = clazz.getConstructor(new Class[]{double.class, double.class});
		Renderable returnValue = (Renderable) c.newInstance(new Object[]{new Double(x), new Double(y)});
		// handle composite state
		if (UISession.currentDrawingMode == CurrentDrawingMode.COMPOSITE_STATE){
			((RenderedState) returnValue).getBaseObject().setComposite(true);
			((RenderedState) returnValue).showAsComposite();
		}
		return returnValue;
	}
	
	public static String getClassName(){
		switch(UISession.currentDrawingMode){
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
