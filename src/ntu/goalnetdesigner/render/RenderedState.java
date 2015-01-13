package ntu.goalnetdesigner.render;

import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.logger.UserConsoleLogger;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedState extends Renderable{
	
	public RenderedState (double x, double y){
		super();
		
		// base object
		this.baseObject = new State(x, y, false);
		
		// Graphical representation
		this.shape = new Circle();
		((Circle)this.shape).setRadius(Resource.STATE_RADIUS);
		((Circle)this.shape).setFill(Resource.STATE_COLOR);
		this.text = new Text(((State)this.baseObject).getName());
		this.display.setTranslateX(x - Resource.STATE_RADIUS);
		this.display.setTranslateY(y - Resource.STATE_RADIUS);
		this.display.getChildren().addAll(shape, text);
	}

	public Circle getShape() {
		return (Circle)shape;
	}

	public void setShape(Circle shape) {
		this.shape = shape;
	}

	public State getBaseObject() {
		return (State) baseObject;
	}

	public void setBaseObject(State baseState) {
		this.baseObject = baseState;
	}
	
	@Override
	public void setMeh(MouseEventHandler meh){
		this.meh = meh;
		this.display.setOnMouseClicked(meh.mouseOnClickHandler);
		this.display.setOnMousePressed(meh.mousePressedHandler);
		this.display.setOnMouseDragged(meh.mouseDraggedHandler);
	}
	
}
