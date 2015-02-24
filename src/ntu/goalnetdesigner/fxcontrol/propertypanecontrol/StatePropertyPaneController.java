package ntu.goalnetdesigner.fxcontrol.propertypanecontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.logic.RenderManager;
import ntu.goalnetdesigner.render.RenderedState;
import ntu.goalnetdesigner.session.DataSession;
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
    
    @FXML
    private ComboBox<State> startStateComboBox;
    
    @FXML
    private ComboBox<State> endStateComboBox;
    
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
		
		startStateComboBox.setItems(FXCollections.observableArrayList(DataSession.Cache.states));
		if (this.selectedObject.getCompositeStartState() != null){
			int i = 0;
			// remove itself
			for (State s: startStateComboBox.getItems()){
				if (s.getId() == this.selectedObject.getId()){
					startStateComboBox.getItems().remove(s);
					break;
				}
			}
			// set selection
			for (i = 0; i < startStateComboBox.getItems().size(); ++i) {
				if (startStateComboBox.getItems().get(i).getId() == this.selectedObject.getCompositeStartState().getId())
					break;
			}
			startStateComboBox.getSelectionModel().select(i);
		}
		
		endStateComboBox.setItems(FXCollections.observableArrayList(DataSession.Cache.states));
		if (this.selectedObject.getCompositeEndState() != null){
			int i = 0;
			// remove itself
			for (State s: endStateComboBox.getItems()){
				if (s.getId() == this.selectedObject.getId()){
					endStateComboBox.getItems().remove(s);
					break;
				}
			}
			for (i = 0; i < endStateComboBox.getItems().size(); ++i) {
				if (endStateComboBox.getItems().get(i).getId() == this.selectedObject.getCompositeEndState().getId())
					break;
			}
			endStateComboBox.getSelectionModel().select(i);
		}
		setCompositeStateComboBoxUsable(selectedObject.getComposite());
		setBidirectionalUpdate();
	}
	
	private void setCompositeStateComboBoxUsable(boolean usable){
		this.startStateComboBox.setDisable(!usable);
		this.endStateComboBox.setDisable(!usable);
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
					setCompositeStateComboBoxUsable(newValue);
				} 
			}
		});
		startStateComboBox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<State>() {
					@Override
					public void changed(
							ObservableValue<? extends State> selected,
							State oldValue, State newValue) {
						if (oldValue != null){
							UIUtility.Draw.renderManager.removeComposition(selectedObject, oldValue);
						}
						StatePropertyPaneController.this.selectedObject.setCompositeStartState(newValue);
						if (newValue != null){
							UIUtility.Draw.renderManager.drawComposition(selectedObject, newValue);
						}
					}
				});

		endStateComboBox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<State>() {
					@Override
					public void changed(
							ObservableValue<? extends State> selected,
							State oldValue, State newValue) {
						if (oldValue != null){
							UIUtility.Draw.renderManager.removeComposition(oldValue, selectedObject);
						}
						StatePropertyPaneController.this.selectedObject.setCompositeEndState(newValue);
						if (newValue != null){
							UIUtility.Draw.renderManager.drawComposition(newValue, selectedObject);
						}
					}
				});
	}
	
	@FXML
    void manageFunctionsButtonOnClick(ActionEvent event) throws Exception {
    	UIUtility.Navigation.popUp(Resource.MANAGE_STATE_FUNCTION_PATH, UISession.primaryStage);
    }
}
