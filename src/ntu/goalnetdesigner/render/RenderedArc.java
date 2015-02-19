package ntu.goalnetdesigner.render;

import java.util.List;

import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.render.customcontrol.Arrow;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Resource;

public class RenderedArc extends Renderable{
	
	// This is used to generate a new Arc visually and modally
	public RenderedArc (double sx, double sy, double ex, double ey){
		super();
		
		// base object
		this.baseObject = new Arc();
		DataSession.Cache.arcs.add(this.getBaseObject());
		// Graphical representation
		getGraphicalRepresentation( sx,  sy,  ex,  ey);
	}
	
	// This is used to generate a new Arc visually using existing modal
	public RenderedArc(Arc a, List<State> states, List<Transition> transitions){
		this.baseObject = a;
		// Find associated rendered objects and create visual connection
		RenderedTransition t = null;
		RenderedState s = null;
		if (a.getDirection() == true){
			// state to transition
			for (State st : states){
				if (st.getId().equals(a.getInputID()))
					s = (RenderedState) st.getRenderedObject();
			}
			for (Transition tr : transitions){
				if (tr.getId().equals(a.getOutputID()))
					t = (RenderedTransition) tr.getRenderedObject();
			}
			getGraphicalRepresentation(s.getDisplay().getTranslateX(), s.getDisplay().getTranslateY(), 
									   t.getDisplay().getTranslateX(), t.getDisplay().getTranslateY());
		} else{
			// transition to state
			for (State st : states){
				if (st.getId().equals(a.getOutputID()))
					s = (RenderedState) st.getRenderedObject();
			}
			for (Transition tr : transitions){
				if (tr.getId().equals(a.getInputID()))
					t = (RenderedTransition) tr.getRenderedObject();
			}
			getGraphicalRepresentation(t.getDisplay().getTranslateX(), t.getDisplay().getTranslateY(), 
									   s.getDisplay().getTranslateX(), s.getDisplay().getTranslateY());
		}
		s.getAssociatedRenderedArcs().add(this);
		t.getAssociatedRenderedArcs().add(this);
		this.baseObject.setRenderedObject(this);
	}
	
	public void getGraphicalRepresentation(double sx, double sy, double ex, double ey){
		this.shape = new Arrow(sx, sy, ex, ey);
		this.shape.setFill(Resource.ARC_COLOR);
		this.baseObject.setRenderedObject(this);
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
	}
	
}
