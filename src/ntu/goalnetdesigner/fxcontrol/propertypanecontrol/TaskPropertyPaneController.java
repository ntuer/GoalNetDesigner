package ntu.goalnetdesigner.fxcontrol.propertypanecontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class TaskPropertyPaneController implements IPaneController{

    @FXML
    private TextField cost;

    @FXML
    private TextField achievement;

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private TextField id;

    private Task selectedObject = null;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		refresh();
	}
	
	public void refresh(){
		this.selectedObject = (Task) UISession.currentSelection;
		if (this.selectedObject == null)
			return;
		description.setText(this.selectedObject.getDescription());
		id.setText(this.selectedObject.getId());
		name.setText(this.selectedObject.getName());
		achievement.setText(selectedObject.getAchievement() + "");
		cost.setText(selectedObject.getCost() + "");
		setBidirectionalUpdate();
	}
	
	private void setBidirectionalUpdate(){
		// Name field update back
		name.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TaskPropertyPaneController.this.selectedObject.setName(name.getText());
					DatabaseActionLogger.log(Resource.Action.UPDATE, Resource.ActionTargetType.TASK, selectedObject.getId());
				}
			}
		});
		// description field update back
		description.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TaskPropertyPaneController.this.selectedObject.setDescription(description.getText());
					DatabaseActionLogger.log(Resource.Action.UPDATE, Resource.ActionTargetType.TASK, selectedObject.getId());
				}
			}
		});
		achievement.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TaskPropertyPaneController.this.selectedObject.setAchievement(Integer.parseInt(achievement.getText()));
					DatabaseActionLogger.log(Resource.Action.UPDATE, Resource.ActionTargetType.TASK, selectedObject.getId());
				}
			}
		});
		cost.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TaskPropertyPaneController.this.selectedObject.setCost(Integer.parseInt(cost.getText()));
					DatabaseActionLogger.log(Resource.Action.UPDATE, Resource.ActionTargetType.TASK, selectedObject.getId());
				} 
			}
		});
	}
    
	@FXML
    void manageFunctionsButtonOnClick(ActionEvent event) throws Exception {
    	UIUtility.Navigation.popUp(Resource.MANAGE_TASK_FUNCTION_PATH, UISession.primaryStage);
    }
}
