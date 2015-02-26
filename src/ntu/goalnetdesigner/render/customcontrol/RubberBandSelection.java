package ntu.goalnetdesigner.render.customcontrol;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.UIUtility;

public class RubberBandSelection {

    final DragContext dragContext = new DragContext();
    private Rectangle rect;
    private Pane group;

    public RubberBandSelection( Pane group) {
        this.group = group;
        rect = new Rectangle(0,0,0,0);
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(1);
        rect.setStrokeLineCap(StrokeLineCap.ROUND);
        rect.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.6));
    }

    public void enableGroupSelectionEventHandler(boolean obj){
    	if (obj){
    		group.setOnMousePressed(onMousePressedEventHandler);
            group.setOnMouseDragged(onMouseDraggedEventHandler);
            group.setOnMouseReleased(onMouseReleasedEventHandler);
    	} else {
    		group.setOnMousePressed(null);
            group.setOnMouseDragged(null);
            group.setOnMouseReleased(null);
    	}
    }
    
    
    public EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            dragContext.mouseAnchorX = event.getX();
            dragContext.mouseAnchorY = event.getY();

            rect.setTranslateX(dragContext.mouseAnchorX);
            rect.setTranslateY(dragContext.mouseAnchorY);
            rect.setWidth(0);
            rect.setHeight(0);

            group.getChildren().add( rect);

            event.consume();
        }
    };

    // on drag update rectangle display.
    // No modification to selectionmodel is done.
    public EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            double offsetX = event.getX() - dragContext.mouseAnchorX;
            double offsetY = event.getY() - dragContext.mouseAnchorY;

            if( offsetX > 0)
                rect.setWidth( offsetX);
            else {
                rect.setTranslateX(event.getX());
                rect.setWidth(dragContext.mouseAnchorX - rect.getTranslateX());
            }

            if( offsetY > 0) {
                rect.setHeight( offsetY);
            } else {
                rect.setTranslateY(event.getY());
                rect.setHeight(dragContext.mouseAnchorY - rect.getTranslateY());
            }
            event.consume();
        }
    };

    public EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
			if (!event.isShiftDown() && !event.isControlDown()) {
				UISession.currentGroupSelection.clear();
			}

			// Only select state and transitions. 
			// Have to iterate twice due to lack of combined list and method to find bound for canvas
			for (Transition t : DataSession.Cache.transitions) {
				if (UIUtility.GroupSelection.isRenderableInSelectionRectangle(t.getRenderedObject(), rect)) {
					if (event.isShiftDown()) {
						UISession.currentGroupSelection.add(t.getRenderedObject());
					} else if (event.isControlDown()) {
						if (UISession.currentGroupSelection.contains(t
								.getRenderedObject())) {
							UISession.currentGroupSelection.remove(t
									.getRenderedObject());
						} else {
							UISession.currentGroupSelection.add(t.getRenderedObject());
						}
					} else {
						UISession.currentGroupSelection.add(t.getRenderedObject());
					}
				}
			}
			
			for (State t : DataSession.Cache.states) {
				if (UIUtility.GroupSelection.isRenderableInSelectionRectangle(t.getRenderedObject(), rect)) {
					if (event.isShiftDown()) {
						UISession.currentGroupSelection.add(t.getRenderedObject());
					} else if (event.isControlDown()) {
						if (UISession.currentGroupSelection.contains(t
								.getRenderedObject())) {
							UISession.currentGroupSelection.remove(t
									.getRenderedObject());
						} else {
							UISession.currentGroupSelection.add(t.getRenderedObject());
						}
					} else {
						UISession.currentGroupSelection.add(t.getRenderedObject());
					}
				}
			}
            UISession.currentGroupSelection.log();

            // cancel rectangle display
            rect.setX(0);
            rect.setY(0);
            rect.setWidth(0);
            rect.setHeight(0);
            group.getChildren().remove(rect);
            event.consume();
        }
    };

    private final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
    }
}