package ntu.goalnetdesigner.render;

import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedState extends Renderable{
	
	public RenderedState (double x, double y, boolean isComposite){
		super();
		this.baseObject = new State(x, y, isComposite);
		setGraphicalRepresentation(x, y);
	}
	
	public RenderedState(State s){
		this.baseObject = s;
		setGraphicalRepresentation(s.getX(), s.getY());
	}
	
	public void setGraphicalRepresentation(double x, double y){
		this.shape = new Circle();
		((Circle)this.shape).setRadius(Resource.STATE_RADIUS);
		((Circle)this.shape).setStrokeWidth(Resource.NORMAL_STROKE_WIDTH);
		((Circle)this.shape).setStrokeType(StrokeType.OUTSIDE);
		if (((State)this.getBaseObject()).getComposite())
			this.showAsComposite();
		else
			this.showAsSimple();
		this.text = new Text(((State)this.baseObject).getName());
		this.display.setTranslateX(x - Resource.STATE_RADIUS);
		this.display.setTranslateY(y - Resource.STATE_RADIUS);
		this.display.getChildren().addAll(shape, text);
		this.baseObject.setRenderedObject(this);
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
	public void setMeh(RenderableMouseEventHandler meh){
		this.meh = meh;
		this.display.setOnMouseClicked(meh.mouseOnClickHandler);
		this.display.setOnMousePressed(meh.mousePressedHandler);
		this.display.setOnMouseDragged(meh.mouseDraggedHandler);
		this.display.setOnMouseReleased(meh.mouseReleasedHandler);
	}
	
	public void showAsComposite(){
		((Circle)this.shape).setFill(Resource.COMPOSITE_STATE_COLOR.deriveColor(1, 1, 1, 0.5));
		((Circle)this.shape).setStroke(Resource.COMPOSITE_STATE_COLOR);
	}
	
	public void showAsSimple(){
		((Circle)this.shape).setFill(Resource.STATE_COLOR.deriveColor(1, 1, 1, 0.5));
		((Circle)this.shape).setStroke(Resource.STATE_COLOR);
	}
}
