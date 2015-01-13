package ntu.goalnetdesigner.render;

import java.lang.reflect.Constructor;

import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.CurrentGNetObjectSelection;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class RenderedIDrawableObjectFactory{
	
	public static Renderable getRenderedObject(double x, double y, TableView tv, AnchorPane drawingPane)
			throws Exception {
		
		// create the rendered object
		Class clazz = Class.forName("ntu.goalnetdesigner.render.Rendered" + getClassName());
		Constructor c = clazz.getConstructor(new Class[]{double.class, double.class});
		Renderable returnValue = (Renderable) c.newInstance(new Object[]{new Double(x), new Double(y)});
		
		MouseEventHandler meh = new MouseEventHandler(tv, drawingPane);
		returnValue.setMeh(meh);
		return returnValue;
	}
	
	public static String getClassName(){
		switch(DataSession.currentGNetObjectSelection){
			case STATE:
				return "State";
			case COMPOSITE_STATE:
				return "CompositeState";
			case TRANSITION:
				return "Transition";
			case ARC:
				return "Arc";
			default:
				return "State";
		}
	}
}
