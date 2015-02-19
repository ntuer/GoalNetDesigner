package ntu.goalnetdesigner.render;

import java.lang.reflect.Constructor;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.StrokeType;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.CurrentGNetObjectSelection;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedObjectFactory{
	
	// for state and transition
	public static Renderable getNewRenderedObject(double x, double y, ScrollPane tv, AnchorPane drawingPane)
			throws Exception {
		Class clazz = Class.forName("ntu.goalnetdesigner.render.Rendered" + getClassName());
		Constructor c = clazz.getConstructor(new Class[]{double.class, double.class});
		Renderable returnValue = (Renderable) c.newInstance(new Object[]{new Double(x), new Double(y)});
		// handle composite state
		if (DataSession.currentGNetObjectSelection == CurrentGNetObjectSelection.COMPOSITE_STATE){
			((RenderedState) returnValue).getBaseObject().setComposite(true);
			((RenderedState) returnValue).getShape().setFill(Resource.COMPOSITE_STATE_COLOR.deriveColor(1, 1, 1, 0.5));
			((RenderedState) returnValue).getShape().setStroke(Resource.COMPOSITE_STATE_COLOR);
			((RenderedState) returnValue).getShape().setStrokeWidth(2);
			((RenderedState) returnValue).getShape().setStrokeType(StrokeType.OUTSIDE);
		}
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
