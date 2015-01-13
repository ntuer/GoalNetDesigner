package ntu.goalnetdesigner.render;

import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.render.customcontrol.BidirectionalStackPane;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.CurrentGNetObjectSelection;
import ntu.goalnetdesigner.utility.Resource;

public class MouseEventHandler {
	private TableView propertyDisplayPane;
	private AnchorPane drawingPane;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	private double newTranslateX, newTranslateY;
	
	public MouseEventHandler(TableView propertyDisplayPane,
			AnchorPane drawingPane) {
		super();
		this.propertyDisplayPane = propertyDisplayPane;
		this.drawingPane = drawingPane;
	}

	public EventHandler<MouseEvent> mouseOnClickHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent e)
    	{
    		UISession.isInRenderedObject = true;
    		if (DataSession.currentGNetObjectSelection == CurrentGNetObjectSelection.ARC &&
    				UISession.isDragging != true){
    			UISession.objectsForArc.add(((BidirectionalStackPane)(e.getSource())).getParentRenderable());
    		} else {
    			UISession.isDragging = false;
	    		ConsoleLogger.log("Object clicked/Dragged and selected!");
	    		// select property
	    		// TODO
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
    		UISession.isDragging = true;
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
            for (RenderedArc a : p.getAssociatedRenderedArcs()){
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
            }
    	}
    };
    
    public EventHandler<MouseEvent> mouseReleasedHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent e)
    	{
    		Renderable r = ((BidirectionalStackPane)(e.getSource())).getParentRenderable();
            if (r instanceof RenderedState){
            	((RenderedState) r).getBaseObject().setX((int) (newTranslateX + Resource.STATE_RADIUS));
            	((RenderedState) r).getBaseObject().setX((int) (newTranslateY + Resource.STATE_RADIUS));
            } else {
            	((RenderedTransition) r).getBaseObject().setX((int) newTranslateX);
            	((RenderedTransition) r).getBaseObject().setX((int) newTranslateY);
            }
            // set underlying object
    	}
    };
}
