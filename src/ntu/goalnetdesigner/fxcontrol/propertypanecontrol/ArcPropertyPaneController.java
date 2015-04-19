package ntu.goalnetdesigner.fxcontrol.propertypanecontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;

public class ArcPropertyPaneController implements IPaneController{
    @FXML
    private TextField inputid;

    @FXML
    private TextField outputid;

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private TextField id;

    @FXML
    private TextField direction;

    private Arc selectedObject = null;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		refresh();
	}
	
	public void refresh(){
		this.selectedObject = (Arc) UISession.getCurrentSelectionAsDrawable();
		if (this.selectedObject == null)
			return;
		description.setText(this.selectedObject.getDescription());
		id.setText(this.selectedObject.getId());
		name.setText(this.selectedObject.getName());
		direction.setText(this.selectedObject.getDirection() + "");
		inputid.setText(this.selectedObject.getInputID() + "");
		outputid.setText(this.selectedObject.getOutputID()+ "");
		setBidirectionalUpdate();
	}
	
	private void setBidirectionalUpdate(){
		// Name field update back
		name.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					ArcPropertyPaneController.this.selectedObject.setName(name.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.ARC, selectedObject.getId());
				}
			}
		});
		// description field update back
		description.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					ArcPropertyPaneController.this.selectedObject.setDescription(description.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.ARC, selectedObject.getId());
				} 
			}
		});
	}
	
}
