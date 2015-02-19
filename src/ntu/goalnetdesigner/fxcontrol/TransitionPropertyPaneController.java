package ntu.goalnetdesigner.fxcontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.render.RenderedTransition;
import ntu.goalnetdesigner.session.UISession;

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
	private ComboBox<?> tasklist;

	@FXML
	private TextField enabled;

	public void initialize(URL arg0, ResourceBundle arg1) {
		refresh();
	}
	private Transition selectedObject = null;
	@Override
	public void refresh(){
		try {
			selectedObject = ((RenderedTransition) UISession.currentSelection).getBaseObject();
		} catch (Exception e){
			selectedObject = (Transition) UISession.currentSelection;
		}
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
				} 
			}
		});
		enabled.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TransitionPropertyPaneController.this.selectedObject.setEnabled(Boolean.valueOf(enabled.getText()));
				} 
			}
		});
		achievement.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TransitionPropertyPaneController.this.selectedObject.setAchievement(Integer.parseInt(achievement.getText()));
				}
			}
		});
		cost.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					TransitionPropertyPaneController.this.selectedObject.setCost(Integer.parseInt(cost.getText()));
				} 
			}
		});
	}
}
