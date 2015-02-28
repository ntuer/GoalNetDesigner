package ntu.goalnetdesigner.render;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.render.customcontrol.Arrow;
import ntu.goalnetdesigner.render.customcontrol.BidirectionalStackPane;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.CurrentDrawingMode;
import ntu.goalnetdesigner.utility.Resource;

public class RenderableMouseEventHandler {
	private ScrollPane propertyPane;
	private AnchorPane drawingPane;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	private double newTranslateX, newTranslateY;
	
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
    		if (DataSession.currentDrawingMode == CurrentDrawingMode.ARC &&
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
    		orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = ((StackPane)(e.getSource())).getTranslateX();
            orgTranslateY = ((StackPane)(e.getSource())).getTranslateY();
    	}
    };
    
    public EventHandler<MouseEvent> mouseDraggedHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent e)
    	{
    		UISession.isInRenderedObject = true;
    		UISession.isDragging = true; // flag to cancel arc drawing event
    		double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            newTranslateX = orgTranslateX + offsetX;
            newTranslateY = orgTranslateY + offsetY;
            
            // Set display
            ((StackPane)(e.getSource())).setTranslateX(newTranslateX);
            ((StackPane)(e.getSource())).setTranslateY(newTranslateY);
            
            // set underlying object
            // Set Arcs
            Renderable p = ((BidirectionalStackPane)(e.getSource())).getParentRenderable();
            for (RenderedEdge ed : p.getAssociatedRenderedEdges()){
            	if (ed instanceof RenderedArc){
            		RenderedArc a = (RenderedArc) ed;
	            	// state -> transition
	            	if (a.getBaseObject().getDirection() == true){
	            		if(p instanceof RenderedTransition)
	            			a.update(newTranslateX, newTranslateY, false);
	            		else
	            			a.update(newTranslateX, newTranslateY, true);
	            	} else {
	            		if(p instanceof RenderedTransition)
	            			a.update(newTranslateX, newTranslateY, true);
	            		else
	            			a.update(newTranslateX, newTranslateY, false);
	            	}
            	} else if (ed instanceof RenderedCompositionEdge){
            		RenderedCompositionEdge c = (RenderedCompositionEdge) ed;
            		if (c.getBaseObjectStart() == p.getBaseObject())
            			c.update(newTranslateX, newTranslateY, true);
            		else if (c.getBaseObjectEnd() == p.getBaseObject())
            			c.update(newTranslateX, newTranslateY, false);
            	}
            }
    	}
    };
    
    public EventHandler<MouseEvent> mouseReleasedHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent e)
    	{
    		if (UISession.isDragging){
    			// after dragging, set underlying object value.
	    		Renderable r = ((BidirectionalStackPane)(e.getSource())).getParentRenderable();
	            if (r instanceof RenderedState){
	            	((RenderedState) r).getBaseObject().setX((int) (newTranslateX + Resource.STATE_RADIUS));
	            	((RenderedState) r).getBaseObject().setY((int) (newTranslateY + Resource.STATE_RADIUS));
	            } else {
	            	((RenderedTransition) r).getBaseObject().setX((int) newTranslateX);
	            	((RenderedTransition) r).getBaseObject().setY((int) newTranslateY);
	            }
    		}
    	}
    };
}
