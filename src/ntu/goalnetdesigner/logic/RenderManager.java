package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.render.Drawable;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderableMouseEventHandler;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.render.RenderedCompositionEdge;
import ntu.goalnetdesigner.render.RenderedEdge;
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
		for (State s: states){
			if (s.getComposite()){
				drawComposition(s, s.getCompositeStartState());
				drawComposition(s.getCompositeEndState(), s);
			}
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
		setMouseEventHandler(ra);
		drawingPane.getChildren().addAll(ra.getShape());
		drawingPane.getChildren().addAll(ra.getShape().getArrow());
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
	
	public RenderedArc drawNewArcByStartAndEnd(Renderable s, Renderable e){
    	RenderedArc a = null;
    	if (s instanceof RenderedState){
    		if (!(e instanceof RenderedTransition)){
    			UISession.objectsForArc.clear();
    			return a;
    		}
			a = new RenderedArc((RenderedState)s, (RenderedTransition) e);
    	} else if (s instanceof RenderedTransition){
    		if (!(e instanceof RenderedState)){
    			UISession.objectsForArc.clear();
    			return a;
    		}
    		a = new RenderedArc((RenderedTransition)s, (RenderedState) e);
    	} 
    	
    	s.getAssociatedRenderedEdges().add(a);
    	e.getAssociatedRenderedEdges().add(a);
    	a.getBaseObject().setGnet(DataSession.Cache.gnet);
    	a.getBaseObject().setIsDirect(true);
    	
    	UISession.objectsForArc.clear();
    	setMouseEventHandler(a);
    	DataService.arc.persist(a.getBaseObject());
    	return a;
	}
	
	public RenderedCompositionEdge drawComposition(State start, State end){
		RenderedCompositionEdge rc = new RenderedCompositionEdge(start, end);
		start.getRenderedObject().getAssociatedRenderedEdges().add(rc);
		end.getRenderedObject().getAssociatedRenderedEdges().add(rc);
		drawingPane.getChildren().addAll(rc.getShape());
		drawingPane.getChildren().addAll(rc.getShape().getArrow());
		return rc;
	}
	
	public void removeComposition(State start, State end){
		List<RenderedEdge> reList = start.getRenderedObject().getAssociatedRenderedEdges();
		for (RenderedEdge re: reList){
			if (re instanceof RenderedCompositionEdge){
				RenderedCompositionEdge rc = (RenderedCompositionEdge) re;
				if (rc.getBaseObjectEnd() == end){
					reList.remove(rc);
					start.getRenderedObject().getAssociatedRenderedEdges().remove(rc);
					end.getRenderedObject().getAssociatedRenderedEdges().remove(rc);
					drawingPane.getChildren().remove(rc.getShape());
					drawingPane.getChildren().remove(rc.getShape().getArrow());
					break;
				}
			}
		}
	}
	
	private void setMouseEventHandler(Renderable r){
		RenderableMouseEventHandler meh = new RenderableMouseEventHandler(propertyPane, drawingPane);
		r.setMeh(meh);
	}

	public void delete(Object currentSelection) {
		if (currentSelection instanceof Renderable){
			if (deleteBaseObject(((Renderable) currentSelection).getBaseObject()))
				deleteRenderable((Renderable) currentSelection);
		} else if (currentSelection instanceof Drawable){
			if (deleteBaseObject(currentSelection))
				deleteRenderable(((Drawable) currentSelection).getRenderedObject());
		} else {
			// fallback option for Task and Function
			deleteBaseObject(currentSelection);
		}
	}
	
	// Delete from database
	// Delete from cache
	private boolean deleteBaseObject(Object baseObject){
		boolean isToDeleteState = true;
		
		if (baseObject instanceof Method){
			DataService.method.remove((Method) baseObject);
			// update cache
			DataSession.Cache.functions.remove((Method) baseObject);
		} else if (baseObject instanceof Task){
			DataService.task.remove((Task) baseObject);
			// update cache
			DataSession.Cache.tasks.remove((Task) baseObject);
		} else if (baseObject instanceof Arc){
			DataService.arc.remove((Arc) baseObject);
			// update cache
			DataSession.Cache.arcs.remove((Arc) baseObject);
		} else if (baseObject instanceof State){
			// remove arcs first
			ArrayList<Arc> arcsToRemove = new ArrayList<>();
			State s = (State) baseObject;
			
			// Don't delete composite state, start and end.
			if (s.getComposite())
				isToDeleteState = false;
			if (DataSession.Cache.gnet.getStartState() != null && 
					s.getId().equals(DataSession.Cache.gnet.getStartState().getId()))
				isToDeleteState = false;
			if (DataSession.Cache.gnet.getEndState() != null && 
					s.getId().equals(DataSession.Cache.gnet.getEndState().getId()))
				isToDeleteState = false;
			if (DataSession.Cache.gnet.getRootState() != null && 
					s.getId().equals(DataSession.Cache.gnet.getRootState().getId()))
				isToDeleteState = false;
			for (State st: DataSession.Cache.states){
				if (st.getComposite()){
					if (st.getCompositeStartState() != null &&
							st.getCompositeStartState().getId().equals(s.getId()))
						isToDeleteState = false;
					if (st.getCompositeEndState() != null &&
							st.getCompositeEndState().getId().equals(s.getId()))
						isToDeleteState = false;
				}
			}
			
			if (!isToDeleteState){
				ConsoleLogger.log("State " + s.getId() + " should not be deleted because used in Goal Net or Composite State.");
				return false;
			}
			for (Arc a: DataSession.Cache.arcs){
				if (a.getInputID().equals(s.getId()) || a.getOutputID().equals(s.getId()))
					arcsToRemove.add(a);
			}
			for (Arc a: arcsToRemove){
				delete(a);
			}
			arcsToRemove.clear();
			DataService.state.remove((State) baseObject);
			// update cache
			DataSession.Cache.states.remove((State) baseObject);
		} else if (baseObject instanceof Transition){
			// remove arcs first
			ArrayList<Arc> arcsToRemove = new ArrayList<>();
			Transition t = (Transition) baseObject;
			if (t.getTasklist() != null){
				DataService.tasklist.remove(t.getTasklist());
				DataSession.Cache.tasklists.remove(t.getTasklist());
			}
			for (Arc a: DataSession.Cache.arcs){
				if (a.getInputID().equals(t.getId()) || a.getOutputID().equals(t.getId()))
					arcsToRemove.add(a);
			}
			for (Arc a: arcsToRemove){
				delete(a);
			}
			arcsToRemove.clear();
			DataService.transition.remove((Transition) baseObject);
			// update cache
			DataSession.Cache.transitions.remove((Transition) baseObject);
		}
		DataService.flush();
		return true;
	}
	
	private void deleteRenderable(Renderable currentSelection){
		if (currentSelection instanceof RenderedArc){
			drawingPane.getChildren().remove(((RenderedArc)currentSelection).getShape());
			drawingPane.getChildren().remove(((RenderedArc)currentSelection).getShape().getArrow());
		} else if (currentSelection instanceof Renderable){
			drawingPane.getChildren().remove(((Renderable)currentSelection).getDisplay());
		}
	}
}

