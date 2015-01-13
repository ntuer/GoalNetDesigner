package ntu.goalnetdesigner.render;

import ntu.goalnetdesigner.logger.UserConsoleLogger;
import ntu.goalnetdesigner.session.UISession;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class MouseEventHandler {
	private TableView propertyDisplayPane;
	private AnchorPane drawingPane;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	
	public MouseEventHandler(TableView propertyDisplayPane,
			AnchorPane drawingPane) {
		super();
		this.propertyDisplayPane = propertyDisplayPane;
		this.drawingPane = drawingPane;
	}

	public EventHandler<MouseEvent> mouseOnClickHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent e)
    	{
    		UserConsoleLogger.log("Object clicked!");
    		UISession.isInRenderedObject = true;
    		// select property
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
    		double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            
            ((StackPane)(e.getSource())).setTranslateX(newTranslateX);
            ((StackPane)(e.getSource())).setTranslateY(newTranslateY);
    	}
    };
	
}
