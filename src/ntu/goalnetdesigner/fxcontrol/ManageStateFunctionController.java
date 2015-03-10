package ntu.goalnetdesigner.fxcontrol;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.StateFunction;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Dialogs;
import ntu.goalnetdesigner.utility.Dialogs.DialogOptions;
import ntu.goalnetdesigner.utility.Dialogs.DialogResponse;
import ntu.goalnetdesigner.utility.Resource;

public class ManageStateFunctionController {

    @FXML
    private ListView<StateFunction> functionListView;

    private State selectedState;
    private List<StateFunction> stateFunctionList;
    
    // Helper fields for adding new function
	private Method selectedFunction;
	private ComboBox<Method> cmb = new ComboBox<Method>();
    
    @FXML
    public void initialize(){
    	this.selectedState = (State) UISession.getDrawableFromCurrentSelection();
    	this.stateFunctionList = this.selectedState.getStateFunctions();
    	functionListView.setItems(FXCollections.observableArrayList(this.stateFunctionList));
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
    void addButtonOnClick(ActionEvent event) {
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(0, 10, 0, 10));
		cmb.setItems(FXCollections.observableArrayList(DataSession.Cache.functions));
		grid.add(cmb, 0, 0);
    	Callback callback = new Callback() {
			@Override
			public Object call(Object arg0) {
				ManageStateFunctionController.this.selectedFunction = cmb.getSelectionModel().getSelectedItem();
				return null;
			}
    	};
    	DialogResponse resp = Dialogs.showCustomDialog(UISession.primaryStage, 
    			grid, "Please select function", "Select function", DialogOptions.OK_CANCEL, callback);
    	
    	if (resp == DialogResponse.OK){
    		StateFunction sf = new StateFunction();
    		// find max sequence, and append to end
    		sf.setSequence(this.stateFunctionList.size());
    		this.selectedState.addStateFunction(sf);
    		this.selectedFunction.addStateFunction(sf);
    		refreshSequence();
    		DataService.stateFunction.persist(sf);
    		functionListView.setItems(FXCollections.observableArrayList(this.stateFunctionList));
    		DatabaseActionLogger.log(Resource.Action.CREATE, Resource.ActionTargetType.STATE_FUNCTION, selectedState.getId());
    	}
    }

    @FXML
    void moveUpOnClick(ActionEvent event) {
    	int index = this.functionListView.getSelectionModel().getSelectedIndex();
    	if (index == -1)
    		return;
    	StateFunction sf = this.stateFunctionList.get(index);
    	this.stateFunctionList.remove(index);
    	index = index - 1 < 0 ? 0 : index - 1;
    	this.stateFunctionList.add(index, sf);
    	refreshSequence();
    	functionListView.setItems(FXCollections.observableArrayList(this.stateFunctionList));
    	this.functionListView.getSelectionModel().select(index);
    	DatabaseActionLogger.log(Resource.Action.UPDATE, Resource.ActionTargetType.STATE_FUNCTION, selectedState.getId());
    }

    @FXML
    void moveDownOnClick(ActionEvent event) {
    	int index = this.functionListView.getSelectionModel().getSelectedIndex();
    	if (index == -1)
    		return;
    	StateFunction sf = this.stateFunctionList.get(index);
    	this.stateFunctionList.remove(index);
    	index = index + 1 > this.stateFunctionList.size() ? this.stateFunctionList.size() : index + 1;
    	this.stateFunctionList.add(index, sf);
    	refreshSequence();
    	functionListView.setItems(FXCollections.observableArrayList(this.stateFunctionList));
    	this.functionListView.getSelectionModel().select(index);
    	DatabaseActionLogger.log(Resource.Action.UPDATE, Resource.ActionTargetType.STATE_FUNCTION, selectedState.getId());
    }

    @FXML
    void deleteOnClick(ActionEvent event) {
    	int index = this.functionListView.getSelectionModel().getSelectedIndex();
    	if (index == -1)
    		return;
    	StateFunction sf = this.stateFunctionList.get(index);
    	this.stateFunctionList.remove(index);
    	refreshSequence();
    	DataService.stateFunction.remove(sf);
    	functionListView.setItems(FXCollections.observableArrayList(this.stateFunctionList));
    	DatabaseActionLogger.log(Resource.Action.DELETE, Resource.ActionTargetType.STATE_FUNCTION, selectedState.getId());
    }

    void refreshSequence() {
    	for (int i = 0; i < this.stateFunctionList.size(); ++i){
    		this.stateFunctionList.get(i).setSequence(i + 1);
    	}
    }
}