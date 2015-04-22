package ntu.goalnetdesigner.viewcontroller.propertypanecontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;

public class FunctionPropertyPaneController implements IPaneController{
    @FXML
    private TextField filename;

    @FXML
    private TextField parameterTypes;

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private TextField alias;

    @FXML
    private TextField id;

    @FXML
    private TextField returnType;

    @FXML
    private TextField parameterValues;


    private Method selectedObject = null;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		refresh();
	}
	
	public void refresh(){
		this.selectedObject = (Method) UISession.currentSelection.getValue();
		if (this.selectedObject == null)
			return;
		description.setText(this.selectedObject.getDescription());
		id.setText(this.selectedObject.getId());
		name.setText(this.selectedObject.getName());
		filename.setText(this.selectedObject.getFileName());
		alias.setText(this.selectedObject.getAlias());
		returnType.setText(this.selectedObject.getRTType());
		parameterTypes.setText(this.selectedObject.getParams());
		parameterValues.setText(this.selectedObject.getPValues());
		setBidirectionalUpdate();
	}
	
	private void setBidirectionalUpdate(){
		// Name field update back
		name.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					FunctionPropertyPaneController.this.selectedObject.setName(name.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.METHOD, selectedObject.getId());
				}
			}
		});
		// description field update back
		description.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					FunctionPropertyPaneController.this.selectedObject.setDescription(description.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.METHOD, selectedObject.getId());
				}
			}
		});
		alias.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					FunctionPropertyPaneController.this.selectedObject.setAlias(alias.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.METHOD, selectedObject.getId());
				}
			}
		});
		filename.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					FunctionPropertyPaneController.this.selectedObject.setFileName(filename.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.METHOD, selectedObject.getId());
				}
			}
		});
		returnType.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					FunctionPropertyPaneController.this.selectedObject.setRTType(returnType.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.METHOD, selectedObject.getId());
				}
			}
		});
		parameterValues.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					FunctionPropertyPaneController.this.selectedObject.setPValues(parameterValues.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.METHOD, selectedObject.getId());
				}
			}
		});
		parameterTypes.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					FunctionPropertyPaneController.this.selectedObject.setParams(parameterTypes.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.METHOD, selectedObject.getId());
				}
			}
		});
	}

    @FXML
    void manageFunctionsButtonOnClick(ActionEvent event) {
    	
    }

}
