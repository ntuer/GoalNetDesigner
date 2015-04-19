package ntu.goalnetdesigner.utility;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.render.Drawable;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.render.RenderedState;
import ntu.goalnetdesigner.render.RenderedTransition;
import ntu.goalnetdesigner.session.UISession;


public class CurrentSelectionProperty extends SimpleObjectProperty<Object>{
	ScrollPane propertyPane;
	public CurrentSelectionProperty() {
		super();
		
		this.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<? extends Object> arg0,
					Object oldPropertyValue, Object newPropertyValue) {
				// cancel old selection
				if (oldPropertyValue != null){
					Renderable or = null;
					if (oldPropertyValue instanceof Renderable){
						or = (Renderable) oldPropertyValue;
					} else if (oldPropertyValue instanceof Drawable){
						or = ((Drawable) oldPropertyValue).getRenderedObject();
					}
					if (or != null){
						UIUtility.Draw.restoreBorder(or);
					}
				}
				// select new object
				if (newPropertyValue != null){
					
					Renderable nr = null;
					if (newPropertyValue instanceof Renderable){
						nr = (Renderable) newPropertyValue;
					} else if (newPropertyValue instanceof Drawable){
						nr = ((Drawable) newPropertyValue).getRenderedObject();
					}
					if (nr != null){
						UIUtility.Draw.setBoldBorder(nr);
						try{
							if (nr instanceof RenderedState){
								CurrentSelectionProperty.this.propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.STATE_PROPERTY_PANE_PATH));
							} else if (nr instanceof RenderedTransition){
								CurrentSelectionProperty.this.propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.TRANSITION_PROPERTY_PANE_PATH));
							} else if (nr instanceof RenderedArc){
								CurrentSelectionProperty.this.propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.ARC_PROPERTY_PANE_PATH));
							}
						} catch (Exception e){
							e.printStackTrace();
						}
					} else {
						try{
							if (newPropertyValue instanceof Method){
								CurrentSelectionProperty.this.propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.FUNCTION_PROPERTY_PANE_PATH));
							} else if (newPropertyValue instanceof Task){
								CurrentSelectionProperty.this.propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.TASK_PROPERTY_PANE_PATH));
							}
						} catch (Exception e){
							e.printStackTrace();
						}
					}
					UISession.currentPaneController.refresh();
				} else {
					UISession.currentPaneController = null;
					CurrentSelectionProperty.this.propertyPane.setContent(null);
				}
			}
		});
	}
	public ScrollPane getPropertyPane() {
		return propertyPane;
	}
	public void setPropertyPane(ScrollPane propertyPane) {
		this.propertyPane = propertyPane;
	}
}