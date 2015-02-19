package ntu.goalnetdesigner.render;

import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedState extends Renderable{
	
	public RenderedState (double x, double y){
		super();
		// base object
		this.baseObject = new State(x, y, false);
		this.getBaseObject().setGnet(DataSession.Cache.gnet);
		DataSession.Cache.states.add(this.getBaseObject());
		// Graphical representation
		getGraphicalRepresentation(x, y);
	}
	
	public RenderedState(State s){
		this.baseObject = s;
		getGraphicalRepresentation(s.getX(), s.getY());
	}
	
	public void getGraphicalRepresentation(double x, double y){
		this.shape = new Circle();
		((Circle)this.shape).setRadius(Resource.STATE_RADIUS);
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
	public void setMeh(MouseEventHandler meh){
		this.meh = meh;
		this.display.setOnMouseClicked(meh.mouseOnClickHandler);
		this.display.setOnMousePressed(meh.mousePressedHandler);
		this.display.setOnMouseDragged(meh.mouseDraggedHandler);
		this.display.setOnMouseReleased(meh.mouseReleasedHandler);
	}
	
	public void showAsComposite(){
		((Circle)this.shape).setFill(Resource.COMPOSITE_STATE_COLOR.deriveColor(1, 1, 1, 0.5));
		((Circle)this.shape).setStroke(Resource.COMPOSITE_STATE_COLOR);
		((Circle)this.shape).setStrokeWidth(2);
		((Circle)this.shape).setStrokeType(StrokeType.OUTSIDE);
	}
	
	public void showAsSimple(){
		((Circle)this.shape).setFill(Resource.STATE_COLOR.deriveColor(1, 1, 1, 0.5));
		((Circle)this.shape).setStroke(Resource.STATE_COLOR);
		((Circle)this.shape).setStrokeWidth(2);
		((Circle)this.shape).setStrokeType(StrokeType.OUTSIDE);
	}
}
