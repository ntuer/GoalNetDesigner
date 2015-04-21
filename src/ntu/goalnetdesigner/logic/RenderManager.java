package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.StateFunction;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.TaskFunction;
import ntu.goalnetdesigner.data.persistence.Tasklist;
import ntu.goalnetdesigner.data.persistence.TasklistTask;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.render.Drawable;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderableMouseEventHandler;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.render.RenderedCompositionEdge;
import ntu.goalnetdesigner.render.RenderedEdge;
import ntu.goalnetdesigner.render.RenderedState;
import ntu.goalnetdesigner.render.RenderedTransition;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.CurrentDrawingMode;
import ntu.goalnetdesigner.utility.Resource;

public class RenderManager {
	
	private AnchorPane drawingPane;

	public RenderManager(AnchorPane drawingPane) {
		super();
		this.drawingPane = drawingPane;
	}

	public AnchorPane getDrawingPane() {
		return drawingPane;
	}

	public void setDrawingPane(AnchorPane drawingPane) {
		this.drawingPane = drawingPane;
	}
	
	public void renderExistingGNet(Gnet gnet){
		drawingPane.getChildren().clear();
		if (gnet != null) {
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
			// draw compositions
			for (State s: states){
				if (s.getComposite()){
					if (s.getCompositeStartState() != null)
						drawComposition(s, s.getCompositeStartState());
					if (s.getCompositeEndState() != null)
						drawComposition(s.getCompositeEndState(), s);
				}
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
		Renderable r = null;
		if (UISession.currentDrawingMode == CurrentDrawingMode.STATE ||
				UISession.currentDrawingMode == CurrentDrawingMode.COMPOSITE_STATE){
			if (UISession.currentDrawingMode == CurrentDrawingMode.STATE){
				r = new RenderedState(x, y, false);
			} else {
				r = new RenderedState(x, y, true);
			}
			((State)r.getBaseObject()).setGnet(DataSession.Cache.gnet);
			DataSession.Cache.states.add((State) r.getBaseObject());
			DataService.state.persist((State) r.getBaseObject());
			DatabaseActionLogger.log(Resource.Action.CREATE, Resource.ActionTargetType.STATE, ((State) r.getBaseObject()).getId());
		} else if (UISession.currentDrawingMode == CurrentDrawingMode.TRANSITION ||
				UISession.currentDrawingMode == CurrentDrawingMode.REASONING_TRANSITION) {
			if (UISession.currentDrawingMode == CurrentDrawingMode.TRANSITION){
				r = new RenderedTransition(x, y, "simple");
			} else {
				r = new RenderedTransition(x, y, "reasoning");
			}
			((Transition)r.getBaseObject()).setGnet(DataSession.Cache.gnet);
			DataSession.Cache.transitions.add((Transition) r.getBaseObject());
			DataService.transition.persist((Transition) r.getBaseObject());
			DatabaseActionLogger.log(Resource.Action.CREATE, Resource.ActionTargetType.TRANSITION, ((Transition) r.getBaseObject()).getId());
		}
		if (r != null)
			setMouseEventHandler(r);
		return r;
	}
	
	public RenderedArc drawNewArcByStartAndEnd(Renderable s, Renderable e){
    	RenderedArc a = null;
    	if (s instanceof RenderedState){
    		if (!(e instanceof RenderedTransition)){
    			UISession.objectsForArc.clear();
    			return null;
    		}
			a = new RenderedArc((RenderedState)s, (RenderedTransition) e);
    	} else if (s instanceof RenderedTransition){
    		if (!(e instanceof RenderedState)){
    			UISession.objectsForArc.clear();
    			return null;
    		}
    		a = new RenderedArc((RenderedTransition)s, (RenderedState) e);
    	} 
    	
    	s.getAssociatedRenderedEdges().add(a);
    	e.getAssociatedRenderedEdges().add(a);
    	a.getBaseObject().setGnet(DataSession.Cache.gnet);
    	DataSession.Cache.arcs.add(a.getBaseObject());
    	a.getBaseObject().setIsDirect(true);
    	
    	UISession.objectsForArc.clear();
    	setMouseEventHandler(a);
    	DataService.arc.persist(a.getBaseObject());
    	DatabaseActionLogger.log(Resource.Action.CREATE, Resource.ActionTargetType.ARC, a.getBaseObject().getId());
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
		RenderableMouseEventHandler meh = new RenderableMouseEventHandler();
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
			DatabaseActionLogger.log(Resource.Action.DELETE, Resource.ActionTargetType.METHOD, ((Method) baseObject).getId());
			DataService.method.remove((Method) baseObject);
			// update cache
			DataSession.Cache.functions.remove((Method) baseObject);
			// remove from related states
			for (State state: DataSession.Cache.states){
				for (StateFunction sf: state.getStateFunctions()){
					if (sf.getMethod().getId().equals(((Method)baseObject).getId())){
						state.getStateFunctions().remove(sf);
						break;
					}
				}
			}
			// remove from related functions
			for (Task task: DataSession.Cache.tasks){
				for (TaskFunction tf: task.getTaskFunctions()){
					if (tf.getMethod().getId().equals(((Method)baseObject).getId())){
						task.getTaskFunctions().remove(tf);
						break;
					}
				}
			}
		} else if (baseObject instanceof Task){
			DatabaseActionLogger.log(Resource.Action.DELETE, Resource.ActionTargetType.TASK, ((Task) baseObject).getId());
			DataService.task.remove((Task) baseObject);
			// update cache
			DataSession.Cache.tasks.remove((Task) baseObject);
			// remove from related functions 
			for (Method method: DataSession.Cache.functions){
				for (TaskFunction tf: method.getTaskFunctions()){
					if (tf.getTask().getId().equals(((Task)baseObject).getId())){
						method.getTaskFunctions().remove(tf);
						break;
					}
				}
			}
			// remove from related tasklists
			for (Tasklist tl: DataSession.Cache.tasklists){
				for (TasklistTask tlt: tl.getTasklistTasks()){
					if (tlt.getTask().getId().equals(((Task)baseObject).getId())){
						tl.getTasklistTasks().remove(tlt);
						break;
					}
				}
			}
			
		} else if (baseObject instanceof Arc){
			DatabaseActionLogger.log(Resource.Action.DELETE, Resource.ActionTargetType.ARC, ((Arc) baseObject).getId());
			DataService.arc.remove((Arc) baseObject);
			// update cache
			DataSession.Cache.arcs.remove((Arc) baseObject);
		} else if (baseObject instanceof State){
			// remove arcs first
			ArrayList<Arc> arcsToRemove = new ArrayList<>();
			State s = (State) baseObject;
			DatabaseActionLogger.log(Resource.Action.DELETE, Resource.ActionTargetType.STATE, s.getId());
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
			DatabaseActionLogger.log(Resource.Action.DELETE, Resource.ActionTargetType.TRANSITION, t.getId());
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
		} else {
			drawingPane.getChildren().remove(((Renderable)currentSelection).getDisplay());
		}
	}
}

