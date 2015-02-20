package ntu.goalnetdesigner.fxcontrol.propertypanecontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.logic.TasklistManager;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.render.RenderedState;
import ntu.goalnetdesigner.render.RenderedTransition;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class StatePropertyPaneController implements IPaneController{
	@FXML
    private TextField composite;

    @FXML
    private TextField name;

    @FXML
    private TextField x;

    @FXML
    private TextArea description;

    @FXML
    private TextField y;

    @FXML
    private TextField id;
    
	public void initialize(URL arg0, ResourceBundle arg1) {
		refresh();
	}
	State selectedObject = null;
	public void refresh(){
		this.selectedObject = (State) UISession.getDrawableFromCurrentSelection();
		if (selectedObject == null)
			return;
		x.setText("" + selectedObject.getX());
		y.setText("" + selectedObject.getY());
		description.setText(selectedObject.getDescription());
		id.setText(selectedObject.getId());
		name.setText(selectedObject.getName());
		composite.setText(selectedObject.getComposite() + "");
		setBidirectionalUpdate();
	}
	private void setBidirectionalUpdate(){
		// Name field update back
		name.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					StatePropertyPaneController.this.selectedObject.setName(name.getText());
					((RenderedState) StatePropertyPaneController.this.selectedObject.getRenderedObject())
						.getText().setText(name.getText());
				}
			}
		});
		// description field update back
		description.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					StatePropertyPaneController.this.selectedObject.setDescription(description.getText());
				}
			}
		});
		composite.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					boolean newValue = Boolean.valueOf(composite.getText());
					StatePropertyPaneController.this.selectedObject.setComposite(newValue);
					if (newValue){
						((RenderedState) StatePropertyPaneController.this.selectedObject.getRenderedObject()).showAsComposite();
					} else {
						((RenderedState) StatePropertyPaneController.this.selectedObject.getRenderedObject()).showAsSimple();
					}
				} 
			}
		});
	}
	
	@FXML
    void manageFunctionsButtonOnClick(ActionEvent event) throws Exception {
    	UIUtility.Navigation.popUp(Resource.MANAGE_STATE_FUNCTION_PATH, UISession.primaryStage);
    }
}
