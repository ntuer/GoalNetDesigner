package ntu.goalnetdesigner.render;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.render.customcontrol.Arrow;
import ntu.goalnetdesigner.render.customcontrol.BidirectionalStackPane;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.CurrentDrawingMode;
import ntu.goalnetdesigner.utility.Resource;

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
    			UISession.setCurrentSelection(((Arrow)(e.getSource())).getParentRenderable());
    		else
    			UISession.setCurrentSelection(((BidirectionalStackPane)(e.getSource())).getParentRenderable());
    		UISession.isInRenderedObject = true; // flag to cancel drawing pane event
    		
    		// If user action is to add arc
    		if (UISession.currentDrawingMode == CurrentDrawingMode.ARC &&
    				UISession.isDragging != true){
    			UISession.objectsForArc.add(((BidirectionalStackPane)(e.getSource())).getParentRenderable());
    		} else {
    			// update display of property display pane.
    			UISession.isDragging = false;
	    		ConsoleLogger.log("Object clicked/Dragged and selected!");
	    		if (UISession.currentSelection instanceof RenderedState){
	    			try {
						propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.STATE_PROPERTY_PANE_PATH));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	    		} else if (UISession.currentSelection instanceof RenderedTransition){
	    			try {
						propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.TRANSITION_PROPERTY_PANE_PATH));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	    		} else if (UISession.currentSelection instanceof RenderedArc){
	    			try {
						propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.ARC_PROPERTY_PANE_PATH));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	    		}
	    		UISession.currentPaneController.refresh();
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
    	RenderableCoordinate rc = entry.getValue();
    	for (RenderedEdge ed : p.getAssociatedRenderedEdges()){
        	if (ed instanceof RenderedArc){
        		RenderedArc a = (RenderedArc) ed;
            	// state -> transition
            	if (a.getBaseObject().getDirection() == true){
            		if(p instanceof RenderedTransition)
            			a.update(rc.newTranslateX, rc.newTranslateY, false);
            		else
            			a.update(rc.newTranslateX, rc.newTranslateY, true);
            	} else {
            		if(p instanceof RenderedTransition)
            			a.update(rc.newTranslateX, rc.newTranslateY, true);
            		else
            			a.update(rc.newTranslateX, rc.newTranslateY, false);
            	}
        	} else if (ed instanceof RenderedCompositionEdge){
        		RenderedCompositionEdge c = (RenderedCompositionEdge) ed;
        		if (c.getBaseObjectStart() == p.getBaseObject())
        			c.update(rc.newTranslateX, rc.newTranslateY, true);
        		else if (c.getBaseObjectEnd() == p.getBaseObject())
        			c.update(rc.newTranslateX, rc.newTranslateY, false);
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
    			}
    			coordinates.clear();
    		}
    	}
    };
}
