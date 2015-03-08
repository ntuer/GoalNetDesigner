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

public class RenderedArc extends RenderedEdge{
	
	public RenderedArc (RenderedState s, RenderedTransition t){
		// base object
		this.baseObject = new Arc();
		DataSession.Cache.arcs.add(this.getBaseObject());
		// Graphical representation
		getGraphicalRepresentation(s.getDisplay().getTranslateX(),  s.getDisplay().getTranslateY(),
				t.getDisplay().getTranslateX(),  t.getDisplay().getTranslateY());
		this.getBaseObject().setDirection(true);
		this.getBaseObject().setInputID(s.getBaseObject().getId());
		this.getBaseObject().setOutputID(t.getBaseObject().getId());
	}
	
	public RenderedArc (RenderedTransition t, RenderedState s){
		this.baseObject = new Arc();
		DataSession.Cache.arcs.add(this.getBaseObject());
		// Graphical representation
		getGraphicalRepresentation(t.getDisplay().getTranslateX(),  t.getDisplay().getTranslateY(),
				s.getDisplay().getTranslateX(),  s.getDisplay().getTranslateY());
		this.getBaseObject().setDirection(false);
		this.getBaseObject().setInputID(s.getBaseObject().getId());
		this.getBaseObject().setOutputID(s.getBaseObject().getId());
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
			Point2D p1 = UIUtility.Draw.findPointOnBorderForFirstRenderable(s, t);
			Point2D p2 = UIUtility.Draw.findPointOnBorderForFirstRenderable(t, s);
			
			getGraphicalRepresentation(p1.getX(), p1.getY(), p2.getX(), p2.getY());
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
			
			Point2D p1 = UIUtility.Draw.findPointOnBorderForFirstRenderable(s, t);
			Point2D p2 = UIUtility.Draw.findPointOnBorderForFirstRenderable(t, s);
			
			getGraphicalRepresentation(p2.getX(), p2.getY(), p1.getX(), p1.getY());
		}
		s.getAssociatedRenderedEdges().add(this);
		t.getAssociatedRenderedEdges().add(this);
		this.baseObject.setRenderedObject(this);
	}
	
	
	public void getGraphicalRepresentation(double sx, double sy, double ex, double ey){
		this.shape = new Arrow(sx, sy, ex, ey);
		this.shape.setStroke(Resource.ARC_COLOR);
		this.baseObject.setRenderedObject(this);
		this.getShape().setParentRenderable(this);
	}

	public Arc getBaseObject() {
		return (Arc) baseObject;
	}

	public void setBaseObject(Arc baseState) {
		this.baseObject = baseState;
	}
	
	@Override
	public void setMeh(RenderableMouseEventHandler meh){
		this.meh = meh;
		this.shape.setOnMouseClicked(meh.mouseOnClickHandler);
	}
	
}
