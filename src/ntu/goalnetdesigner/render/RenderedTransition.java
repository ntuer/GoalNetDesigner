package ntu.goalnetdesigner.render;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedTransition extends Renderable{
	
	public RenderedTransition (double x, double y){
		super();
		
		// base object
		this.baseObject = new Transition();
		
		// Graphical representation
		this.shape = new Rectangle();
		((Rectangle)this.shape).setWidth(Resource.TRANSITION_WIDTH);
		((Rectangle)this.shape).setHeight(Resource.TRANSITION_HEIGHT);
		((Rectangle)this.shape).setFill(Resource.TRANSITION_COLOR);
		this.text = new Text("Transition");
		this.display.setLayoutX(x - Resource.TRANSITION_WIDTH / 2);
		this.display.setLayoutY(y - Resource.TRANSITION_HEIGHT / 2);
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
		this.shape.setOnMouseClicked(meh.mouseOnClickHandler);
		this.shape.setOnMousePressed(meh.mousePressedHandler);
		this.shape.setOnMouseDragged(meh.mouseDraggedHandler);
	}
	
}
