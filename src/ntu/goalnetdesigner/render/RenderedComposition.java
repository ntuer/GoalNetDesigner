package ntu.goalnetdesigner.render;

import java.util.List;

import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.render.customcontrol.Arrow;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedComposition extends RenderedEdge{
	
	private State baseObjectStart;
	private State baseObjectEnd;
	
	// This is used to generate a new Arc visually using existing modal
	public RenderedComposition(State s, State e){
		super();
		this.baseObject = null;
		this.baseObjectStart = s;
		this.baseObjectEnd = e;

		getGraphicalRepresentation(s.getRenderedObject().getDisplay().getTranslateX(), s.getRenderedObject().getDisplay().getTranslateY(), 
								   e.getRenderedObject().getDisplay().getTranslateX(), e.getRenderedObject().getDisplay().getTranslateY());
		s.getRenderedObject().getAssociatedRenderedEdges().add(this);
		e.getRenderedObject().getAssociatedRenderedEdges().add(this);
	}
	
	public void getGraphicalRepresentation(double sx, double sy, double ex, double ey){
		this.shape = new Arrow(sx, sy, ex, ey);
		this.shape.setStroke(Resource.COMPOSITION_ARC_COLOR);
		this.getShape().getArrow().setStroke(Resource.COMPOSITE_STATE_COLOR);
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
	
	@Override
	public void setMeh(RenderableMouseEventHandler meh){
		this.meh = meh;
	}

	public State getBaseObjectStart() {
		return baseObjectStart;
	}

	public void setBaseObjectStart(State baseObjectStart) {
		this.baseObjectStart = baseObjectStart;
	}

	public State getBaseObjectEnd() {
		return baseObjectEnd;
	}

	public void setBaseObjectEnd(State baseObjectEnd) {
		this.baseObjectEnd = baseObjectEnd;
	}
	
}
