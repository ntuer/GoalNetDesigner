package ntu.goalnetdesigner.viewcontroller.propertypanecontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.logic.TasklistManager;
import ntu.goalnetdesigner.render.RenderedTransition;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class TransitionPropertyPaneController implements IPaneController {
	@FXML
	private TextField cost;

	@FXML
	private TextField achievement;

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

	@FXML
	private TextField enabled;

	public void initialize(URL arg0, ResourceBundle arg1) {
		refresh();
	}
	private Transition selectedObject = null;
	@Override
	public void refresh(){
		this.selectedObject = (Transition) UISession.getCurrentSelectionAsDrawable();
		if (selectedObject == null)
			return;
		x.setText("" + selectedObject.getX());
		y.setText("" + selectedObject.getY());
		description.setText(selectedObject.getDescription());
		id.setText(selectedObject.getId());
		name.setText(selectedObject.getName());
		enabled.setText(selectedObject.getEnabled() + "");
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
					TransitionPropertyPaneController.this.selectedObject.setName(name.getText());
					((RenderedTransition) TransitionPropertyPaneController.this.selectedObject.getRenderedObject())
						.getText().setText(name.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.TASK, selectedObject.getId());
				}
			}
		});
		// description field update back
		description.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TransitionPropertyPaneController.this.selectedObject.setDescription(description.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.TASK, selectedObject.getId());
				} 
			}
		});
		enabled.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TransitionPropertyPaneController.this.selectedObject.setEnabled(Boolean.valueOf(enabled.getText()));
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.TASK, selectedObject.getId());
				} 
			}
		});
		achievement.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TransitionPropertyPaneController.this.selectedObject.setAchievement(Integer.parseInt(achievement.getText()));
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.TASK, selectedObject.getId());
				}
			}
		});
		cost.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TransitionPropertyPaneController.this.selectedObject.setCost(Integer.parseInt(cost.getText()));
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.TASK, selectedObject.getId());
				} 
			}
		});
	}

    @FXML
    void manageTasksButtonOnClick(ActionEvent event) throws Exception{
    	// create new Tasklist if does not exist
    	if (this.selectedObject.getTasklist() == null){
    		this.selectedObject.setTasklist(TasklistManager.newInstance());
    		this.selectedObject.getTasklist().getTransitions().add(this.selectedObject);
    	}
    	UIUtility.Navigation.popUp(Resource.MANAGE_TRANSITION_TASK_PATH, UISession.primaryStage);
    }
}
