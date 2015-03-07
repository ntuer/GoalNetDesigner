package ntu.goalnetdesigner.render;

import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.render.customcontrol.TransitionPolygon;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedTransition extends Renderable{
	public RenderedTransition (double x, double y){
		super();
		// base object
		this.baseObject = new Transition(x, y);
		this.getBaseObject().setGnet(DataSession.Cache.gnet);
		DataSession.Cache.transitions.add(this.getBaseObject());
		// Graphical representation
		getGraphicalRepresentation(x, y);
	}
	
	public RenderedTransition(Transition t){
		this.baseObject = t;
		getGraphicalRepresentation(t.getX(), t.getY());
	}
	
	public void getGraphicalRepresentation(double x, double y){
		this.shape = new TransitionPolygon("rectangle");
		showAsSimple();
		this.text = new Text(((Transition)this.baseObject).getName());
		this.display.setTranslateX(x - Resource.TRANSITION_WIDTH / 2);
		this.display.setTranslateY(y - Resource.TRANSITION_HEIGHT / 2);
		this.display.getChildren().addAll(shape, text);
		this.baseObject.setRenderedObject(this);
	}

	public TransitionPolygon getShape() {
		return (TransitionPolygon)shape;
	}

	public void setShape(TransitionPolygon shape) {
		this.shape = shape;
	}

	public Transition getBaseObject() {
		return (Transition) baseObject;
	}

	public void setBaseObject(Transition baseState) {
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
	
	public void showAsSimple(){
		((TransitionPolygon)this.shape).transformTo("rectangle");
		((TransitionPolygon)this.shape).setFill(Resource.TRANSITION_COLOR.deriveColor(1, 1, 1, 0.5));
		((TransitionPolygon)this.shape).setStroke(Resource.TRANSITION_COLOR);
		((TransitionPolygon)this.shape).setStrokeWidth(Resource.NORMAL_STROKE_WIDTH);
		((TransitionPolygon)this.shape).setStrokeType(StrokeType.OUTSIDE);
	}
	
	public void showAsReasoning(){
		((TransitionPolygon)this.shape).transformTo("diamond");
		((TransitionPolygon)this.shape).setFill(Resource.TRANSITION_COLOR.deriveColor(1, 1, 1, 0.5));
		((TransitionPolygon)this.shape).setStroke(Resource.TRANSITION_COLOR);
		((TransitionPolygon)this.shape).setStrokeWidth(Resource.NORMAL_STROKE_WIDTH);
		((TransitionPolygon)this.shape).setStrokeType(StrokeType.OUTSIDE);
	}
}
