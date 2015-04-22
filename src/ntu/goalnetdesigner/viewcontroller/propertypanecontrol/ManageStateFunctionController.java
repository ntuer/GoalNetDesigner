package ntu.goalnetdesigner.viewcontroller.propertypanecontrol;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.StateFunction;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;

public class ManageStateFunctionController {

    @FXML
    private ListView<StateFunction> functionListView;

    private State selectedState;
    private List<StateFunction> stateFunctionList;
    
    @FXML
    public void initialize(){
    	this.selectedState = (State) UISession.getCurrentSelectionAsDrawable();
    	this.stateFunctionList = this.selectedState.getStateFunctions();
    	functionListView.setItems(FXCollections.observableArrayList(this.stateFunctionList));
    }
    
	@FXML
    void addButtonOnClick(ActionEvent event) {
    	List<Method> choices = DataSession.Cache.functions;
    	ChoiceDialog<Method> dialog = new ChoiceDialog<Method>(null, choices);
    	dialog.setTitle("Select function");
    	dialog.setHeaderText("Please select function");
    	dialog.setContentText("Function list:");
    	Optional<Method> result = dialog.showAndWait();

    	result.ifPresent(v -> {
    		StateFunction sf = new StateFunction();
    		// find max sequence, and append to end
    		sf.setSequence(this.stateFunctionList.size());
    		this.selectedState.addStateFunction(sf);
    		v.addStateFunction(sf);
    		refreshSequence();
    		DataService.stateFunction.persist(sf);
    		functionListView.setItems(FXCollections.observableArrayList(this.stateFunctionList));
    		DatabaseActionLogger.log(Resource.Action.CREATE, Resource.ActionTargetType.STATE_FUNCTION, selectedState.getId());
    	});
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
    	DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.STATE_FUNCTION, selectedState.getId());
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
    	DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.STATE_FUNCTION, selectedState.getId());
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