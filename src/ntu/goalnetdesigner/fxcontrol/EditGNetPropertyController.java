package ntu.goalnetdesigner.fxcontrol;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Resource;

public class EditGNetPropertyController{

    @FXML
    private ComboBox<State> startStateComboBox;

    @FXML
    private ComboBox<State> rootStateComboBox;

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private ComboBox<State> endStateComboBox;

    @FXML
    private TextField id;

    @FXML
    public void initialize() {
		refresh();
	}
	
	public void refresh(){
		description.setText(DataSession.Cache.gnet.getDescription());
		id.setText(DataSession.Cache.gnet.getId());
		name.setText(DataSession.Cache.gnet.getName());
		
		// init state lists and selection value
		int i = 0;
		startStateComboBox.setItems(FXCollections.observableArrayList(DataSession.Cache.states));
		if (DataSession.Cache.gnet.getStartState() != null){
			// set selection
			for (i = 0; i < startStateComboBox.getItems().size(); ++i) {
				if (startStateComboBox.getItems().get(i).getId().equals(DataSession.Cache.gnet.getStartState().getId()))
					break;
			}
			startStateComboBox.getSelectionModel().select(i);
		}
		endStateComboBox.setItems(FXCollections.observableArrayList(DataSession.Cache.states));
		if (DataSession.Cache.gnet.getStartState() != null){
			// set selection
			for (i = 0; i < endStateComboBox.getItems().size(); ++i) {
				if (endStateComboBox.getItems().get(i).getId().equals(DataSession.Cache.gnet.getEndState().getId()))
					break;
			}
			endStateComboBox.getSelectionModel().select(i);
		}
		rootStateComboBox.setItems(FXCollections.observableArrayList(DataSession.Cache.states));
		if (DataSession.Cache.gnet.getStartState() != null){
			// set selection
			for (i = 0; i < rootStateComboBox.getItems().size(); ++i) {
				if (rootStateComboBox.getItems().get(i).getId().equals(DataSession.Cache.gnet.getRootState().getId()))
					break;
			}
			rootStateComboBox.getSelectionModel().select(i);
		}
		setBidirectionalUpdate();
	}
	
	private void setBidirectionalUpdate(){
		// Name field update back
		name.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					DataSession.Cache.gnet.setName(name.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.GNET, DataSession.Cache.gnet.getId());
				}
			}
		});
		// description field update back
		description.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					DataSession.Cache.gnet.setDescription(description.getText());
					DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.GNET, DataSession.Cache.gnet.getId());
				} 
			}
		});
		
		startStateComboBox.getSelectionModel().selectedItemProperty()
		.addListener(new ChangeListener<State>() {
			@Override
			public void changed(
					ObservableValue<? extends State> selected,
					State oldValue, State newValue) {
				DataSession.Cache.gnet.setStartState(newValue);
				DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.GNET, DataSession.Cache.gnet.getId());
			}
		});
		endStateComboBox.getSelectionModel().selectedItemProperty()
		.addListener(new ChangeListener<State>() {
			@Override
			public void changed(
					ObservableValue<? extends State> selected,
					State oldValue, State newValue) {
				DataSession.Cache.gnet.setEndState(newValue);
				DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.GNET, DataSession.Cache.gnet.getId());
			}
		});
		rootStateComboBox.getSelectionModel().selectedItemProperty()
		.addListener(new ChangeListener<State>() {
			@Override
			public void changed(
					ObservableValue<? extends State> selected,
					State oldValue, State newValue) {
				DataSession.Cache.gnet.setRootState(newValue);
				DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.GNET, DataSession.Cache.gnet.getId());
			}
		});
	}
}
