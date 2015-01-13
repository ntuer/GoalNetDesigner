package ntu.goalnetdesigner.render;

import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedSelection extends Renderable{
	
	public RenderedSelection (double x, double y){
		super();
		
		// base object
		this.baseObject = new Transition();
		
		// Graphical representation
		this.shape = new Circle();
		((Circle)this.shape).setRadius(Resource.STATE_RADIUS);
		((Circle)this.shape).setFill(Resource.STATE_COLOR);
		this.text = new Text("State");
		this.display.setLayoutX(x - Resource.STATE_RADIUS);
		this.display.setLayoutY(y - Resource.STATE_RADIUS);
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
