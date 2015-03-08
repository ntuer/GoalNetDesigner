package ntu.goalnetdesigner.render;

import java.util.List;

import javafx.geometry.Point2D;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.render.customcontrol.Arrow;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class RenderedCompositionEdge extends RenderedEdge{
	
	private State baseObjectStart;
	private State baseObjectEnd;
	
	// This is used to generate a new Arc visually using existing modal
	public RenderedCompositionEdge(State s, State e){
		super();
		this.baseObject = null;
		this.baseObjectStart = s;
		this.baseObjectEnd = e;

		Point2D p1 = UIUtility.Draw.findPointOnBorderForFirstRenderable(s.getRenderedObject(), e.getRenderedObject());
		Point2D p2 = UIUtility.Draw.findPointOnBorderForFirstRenderable(e.getRenderedObject(), s.getRenderedObject());
		getGraphicalRepresentation(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		s.getRenderedObject().getAssociatedRenderedEdges().add(this);
		e.getRenderedObject().getAssociatedRenderedEdges().add(this);
	}
	
	public void getGraphicalRepresentation(double sx, double sy, double ex, double ey){
		this.shape = new Arrow(sx, sy, ex, ey);
		this.shape.setStroke(Resource.COMPOSITION_ARC_COLOR);
		this.getShape().getArrow().setStroke(Resource.COMPOSITE_STATE_COLOR);
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
