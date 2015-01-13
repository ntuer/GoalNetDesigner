package ntu.goalnetdesigner.render;

import javafx.scene.shape.Line;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.render.customcontrol.Arrow;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedArc extends Renderable{
	
	public RenderedArc (double sx, double sy, double ex, double ey){
		super();
		
		// base object
		this.baseObject = new Arc();
		DataSession.Cache.arcs.add(this.getBaseObject());
		// Graphical representation
		this.shape = new Arrow(sx, sy, ex, ey);
		this.shape.setFill(Resource.ARC_COLOR);
	}
	
	public void update(double x, double y, boolean isStart){
		if (isStart){
			this.getShape().setStartX(x);
			this.getShape().setStartY(y);
		} else {
			this.getShape().setEndX(x);
			this.getShape().setEndY(y);
		}
		this.getShape().handleChange();
	}
	
	public Arrow getShape() {
		return (Arrow)shape;
	}

	public void setShape(Arrow shape) {
		this.shape = shape;
	}

	public Arc getBaseObject() {
		return (Arc) baseObject;
	}

	public void setBaseObject(Arc baseState) {
		this.baseObject = baseState;
	}
	
	@Override
	public void setMeh(MouseEventHandler meh){
		this.meh = meh;
		this.display.setOnMouseClicked(meh.mouseOnClickHandler);
	}
	
}
