package ntu.goalnetdesigner.render;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.render.customcontrol.Arrow;
import ntu.goalnetdesigner.render.customcontrol.BidirectionalStackPane;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.CurrentDrawingMode;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class RenderableMouseEventHandler {
	private ScrollPane propertyPane;
	private AnchorPane drawingPane;
	public double orgSceneX, orgSceneY;
	
	private class RenderableCoordinate {
		public double orgTranslateX, orgTranslateY;
		public double newTranslateX, newTranslateY;
	}
	
	private HashMap<Renderable, RenderableCoordinate> coordinates = new HashMap<>();
	
	public RenderableMouseEventHandler(ScrollPane propertyDisplayPane,
			AnchorPane drawingPane) {
		super();
		this.propertyPane = propertyDisplayPane;
		this.drawingPane = drawingPane;
	}

	public EventHandler<MouseEvent> mouseOnClickHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent e)
    	{
    		if (e.getSource() instanceof Arrow)
    			UISession.currentSelection.setValue(((Arrow)(e.getSource())).getParentRenderable());
    		else
    			UISession.currentSelection.setValue(((BidirectionalStackPane)(e.getSource())).getParentRenderable());
    		UISession.isInRenderedObject = true; // flag to cancel drawing pane event
    		
    		// If user action is to add arc
    		if (UISession.currentDrawingMode == CurrentDrawingMode.ARC &&
    				UISession.isDragging != true){
    			UISession.objectsForArc.add(((BidirectionalStackPane)(e.getSource())).getParentRenderable());
    		} else {
    			// set flag to cancel dragging event
    			UISession.isDragging = false;
	    		ConsoleLogger.log("Object clicked/Dragged and selected!");
    		}
    	}
    };
	
    // handle dragging event
    public EventHandler<MouseEvent> mousePressedHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent e)
    	{
    		UISession.isInRenderedObject = true;
    		orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
    		if (UISession.currentGroupSelection.size() > 0){
    			for (Renderable r: UISession.currentGroupSelection.getSelection()){
    				RenderableCoordinate rc = new RenderableCoordinate();
    	            rc.orgTranslateX = (r.getDisplay()).getTranslateX();
    	            rc.orgTranslateY = (r.getDisplay()).getTranslateY();
    	            coordinates.put(r, rc);
    			}
    		} else {
    			RenderableCoordinate rc = new RenderableCoordinate();
	            rc.orgTranslateX = ((StackPane) e.getSource()).getTranslateX();
	            rc.orgTranslateY = ((StackPane) e.getSource()).getTranslateY();
    			coordinates.put(((BidirectionalStackPane)e.getSource()).getParentRenderable(), rc);
    		}
    	}
    };
    
    private void updateDisplay(Map.Entry<Renderable, RenderableCoordinate> entry, double offsetX, double offsetY){
    	StackPane p = entry.getKey().getDisplay();
    	RenderableCoordinate rc = entry.getValue();
    	
    	rc.newTranslateX = rc.orgTranslateX + offsetX;
    	rc.newTranslateY = rc.orgTranslateY + offsetY;
        
        // Set display
        p.setTranslateX(rc.newTranslateX);
        p.setTranslateY(rc.newTranslateY);
        
    }
    
    private void updateArcs(Map.Entry<Renderable, RenderableCoordinate> entry){
    	Renderable p = entry.getKey();
    	Renderable s = null, e = null;
    	for (RenderedEdge ed : p.getAssociatedRenderedEdges()){
        	if (ed instanceof RenderedArc){
        		RenderedArc a = (RenderedArc) ed;
            	// state -> transition
            	if (a.getBaseObject().getDirection() == true){
            		if(p instanceof RenderedTransition){
            			e = p;
            			String stateId = a.getBaseObject().getInputID();
            			for (State state: DataSession.Cache.states){
            				if (state.getId().equals(stateId))
            					s = state.getRenderedObject();
            			}
            		} else {
            			s = p;
            			String transitionId = a.getBaseObject().getOutputID();
            			for (Transition transition: DataSession.Cache.transitions){
            				if (transition.getId().equals(transitionId))
            					e = transition.getRenderedObject();
            			}
            		}
            	} else {
            		if(p instanceof RenderedTransition){
            			s = p;
            			String stateId = a.getBaseObject().getOutputID();
            			for (State state: DataSession.Cache.states){
            				if (state.getId().equals(stateId))
            					e = state.getRenderedObject();
            			}
            		} else {
            			e = p;
            			String transitionId = a.getBaseObject().getInputID();
            			for (Transition transition: DataSession.Cache.transitions){
            				if (transition.getId().equals(transitionId))
            					s = transition.getRenderedObject();
            			}
            		}
            	}
            	Point2D p1 = UIUtility.Draw.findPointOnBorderForFirstRenderable(s, e);
            	Point2D p2 = UIUtility.Draw.findPointOnBorderForFirstRenderable(e, s);
            	a.update(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        	} else if (ed instanceof RenderedCompositionEdge){
        		RenderedCompositionEdge c = (RenderedCompositionEdge) ed;
    			Point2D p1 = UIUtility.Draw.findPointOnBorderForFirstRenderable(c.getBaseObjectStart().getRenderedObject(), c.getBaseObjectEnd().getRenderedObject());
            	Point2D p2 = UIUtility.Draw.findPointOnBorderForFirstRenderable(c.getBaseObjectEnd().getRenderedObject(), c.getBaseObjectStart().getRenderedObject());
            	c.update(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        	}
        }
    }
    
    private void updateBaseObject(Map.Entry<Renderable, RenderableCoordinate> entry){
    	Renderable p = entry.getKey();
    	RenderableCoordinate rc = entry.getValue();
    	
    	if (p instanceof RenderedState){
        	((RenderedState) p).getBaseObject().setX((int) (rc.newTranslateX + Resource.STATE_RADIUS));
        	((RenderedState) p).getBaseObject().setY((int) (rc.newTranslateY + Resource.STATE_RADIUS));
        } else {
        	((RenderedTransition) p).getBaseObject().setX((int) rc.newTranslateX);
        	((RenderedTransition) p).getBaseObject().setY((int) rc.newTranslateY);
        }
    }
    
    public EventHandler<MouseEvent> mouseDraggedHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent e)
    	{
    		UISession.isInRenderedObject = true;
    		UISession.isDragging = true; // flag to cancel arc drawing event
    		double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            for (Map.Entry<Renderable, RenderableCoordinate> entry: coordinates.entrySet()){
            	updateBaseObject(entry);
            	updateDisplay(entry, offsetX, offsetY);
                updateArcs(entry);
            }
    	}
    };
    
    public EventHandler<MouseEvent> mouseReleasedHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent e)
    	{
    		if (UISession.isDragging){
    			// after dragging, set underlying object value.
    			for (Map.Entry<Renderable, RenderableCoordinate> entry: coordinates.entrySet()){
    				updateBaseObject(entry);
    				// update action log for dragging
        			if (entry.getKey() instanceof RenderedTransition)
        				DatabaseActionLogger.log(Resource.Action.MOVE, Resource.ActionTargetType.TRANSITION, 
        						((RenderedTransition)entry.getKey()).getBaseObject().getId());
        			else if (entry.getKey() instanceof RenderedState)
        				DatabaseActionLogger.log(Resource.Action.MOVE, Resource.ActionTargetType.STATE, 
        						((RenderedState)entry.getKey()).getBaseObject().getId());
    			}
    			coordinates.clear();
    		}
    	}
    };
}
