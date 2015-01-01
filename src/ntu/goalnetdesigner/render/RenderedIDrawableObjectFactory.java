package ntu.goalnetdesigner.render;

import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class RenderedIDrawableObjectFactory{
	
	public static Renderable getRenderedObject(Class baseClass, TableView tv, AnchorPane drawingPane) throws Exception{
		MouseEventHandler meh = new MouseEventHandler(tv, drawingPane);
		Renderable returnValue = (Renderable) Class.forName(
				"ntu.goalnetdesigner.render.Rendered" + baseClass.getSimpleName()).newInstance();
		returnValue.setMeh(meh);
		return returnValue;
	}
	
}
