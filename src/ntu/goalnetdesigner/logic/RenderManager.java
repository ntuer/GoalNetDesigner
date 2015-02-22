package ntu.goalnetdesigner.logic;

import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.render.RenderableMouseEventHandler;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.render.RenderedObjectFactory;
import ntu.goalnetdesigner.render.RenderedState;
import ntu.goalnetdesigner.render.RenderedTransition;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;

public class RenderManager {
	
	private AnchorPane drawingPane;
	private ScrollPane propertyPane;

	public RenderManager(AnchorPane drawingPane, ScrollPane propertyPane) {
		super();
		this.drawingPane = drawingPane;
		this.propertyPane = propertyPane;
	}

	public AnchorPane getDrawingPane() {
		return drawingPane;
	}

	public void setDrawingPane(AnchorPane drawingPane) {
		this.drawingPane = drawingPane;
	}
	
	
	public void renderGNet(Gnet gnet){
		if (gnet == null){
			drawingPane.getChildren().clear();
			return;
		}
		List<Arc> arcs = gnet.getArcs();
		List<State> states = gnet.getStates();
		List<Transition> transitions = gnet.getTransitions();
		for(State s: states){
			drawExistingState(s);
		}
		for (Transition t : transitions){
			drawExistingTransition(t);
		}
		for (Arc a: arcs){
			drawExistingArc(a, states, transitions);
		}
	}
	
	public void drawExistingState(State s){
		RenderedState rs = new RenderedState(s);
		drawingPane.getChildren().addAll(rs.getDisplay());
		setMouseEventHandler(rs);
	}
	
	public void drawExistingTransition(Transition t){
		RenderedTransition rt = new RenderedTransition(t);
		drawingPane.getChildren().addAll(rt.getDisplay());
		setMouseEventHandler(rt);
	}
	
	public void drawExistingArc(Arc a, List<State> states, List<Transition> transitions){
		RenderedArc ra = new RenderedArc(a, states, transitions);
		drawingPane.getChildren().addAll(ra.getShape());
		drawingPane.getChildren().addAll(ra.getShape().getArrow());
	}
	
	public RenderedArc drawNewArcByStartAndEnd(Renderable s, Renderable e){
    	RenderedArc a = null;
    	if (s instanceof RenderedState){
    		if (!(e instanceof RenderedTransition)){
    			UISession.objectsForArc.clear();
    			return a;
    		}
			a = new RenderedArc(
					((RenderedState) s).getDisplay().getTranslateX(), ((RenderedState) s).getDisplay().getTranslateY(),
					((RenderedTransition) e).getDisplay().getTranslateX(), ((RenderedTransition) e).getDisplay().getTranslateY());
    		a.getBaseObject().setDirection(true);
    		a.getBaseObject().setInputID(((RenderedState) s).getBaseObject().getId());
    		a.getBaseObject().setOutputID(((RenderedTransition) e).getBaseObject().getId());
    		
    	} else if (s instanceof RenderedTransition){
    		if (!(e instanceof RenderedState)){
    			UISession.objectsForArc.clear();
    			return a;
    		}
			a = new RenderedArc(
					((RenderedTransition) s).getDisplay().getTranslateX(), ((RenderedTransition) s).getDisplay().getTranslateY(),
					((RenderedState) e).getDisplay().getTranslateX(), ((RenderedState) e).getDisplay().getTranslateY());
    		a.getBaseObject().setDirection(false);
    		a.getBaseObject().setInputID(((RenderedTransition) s).getBaseObject().getId());
    		a.getBaseObject().setOutputID(((RenderedState) e).getBaseObject().getId());
    	} 
    	
    	s.getAssociatedRenderedArcs().add(a);
    	e.getAssociatedRenderedArcs().add(a);
    	a.getBaseObject().setGnet(DataSession.Cache.gnet);
    	a.getBaseObject().setIsDirect(true);
    	
    	UISession.objectsForArc.clear();
    	setMouseEventHandler(a);
    	DataService.arc.persist(a.getBaseObject());
    	return a;
	}
	
	public Renderable drawNewStateOrTransition(double x, double y){
		try {
			Renderable r =  RenderedObjectFactory.getNewRenderedObject(x, y, propertyPane, drawingPane);
			setMouseEventHandler(r);
			if (r instanceof RenderedTransition)
				DataService.transition.persist((Transition) r.getBaseObject());
			else
				DataService.state.persist((State) r.getBaseObject());
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void setMouseEventHandler(Renderable r){
		RenderableMouseEventHandler meh = new RenderableMouseEventHandler(propertyPane, drawingPane);
		r.setMeh(meh);
	}
}

