package ntu.goalnetdesigner.viewcontroller.propertypanecontrol;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.TaskFunction;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;

public class ManageTaskFunctionController {

	@FXML
    private ListView<TaskFunction> functionListView;

    private Task selectedTask;
    private List<TaskFunction> taskFunctionList;
    
    @FXML
    public void initialize(){
    	this.selectedTask = (Task) UISession.currentSelection.getValue();
    	this.taskFunctionList = this.selectedTask.getTaskFunctions();
    	functionListView.setItems(FXCollections.observableArrayList(this.taskFunctionList));
    }
    
	@FXML
    void addButtonOnClick(ActionEvent event) {
    	List<Method> choices = DataSession.Cache.functions;
    	ChoiceDialog<Method> dialog = new ChoiceDialog<>(null, choices);
    	dialog.setTitle("Add");
    	dialog.setHeaderText("Select function");
    	dialog.setContentText("Function list:");

    	Optional<Method> result = dialog.showAndWait();
    	result.ifPresent(v -> {
    		TaskFunction tf = new TaskFunction();
    		// find max sequence, and append to end
    		tf.setSequence(this.taskFunctionList.size());
    		this.selectedTask.addTaskFunction(tf);
    		v.addTaskFunction(tf);
    		refreshSequence();
    		DataService.taskFunction.persist(tf);
    		functionListView.setItems(FXCollections.observableArrayList(this.taskFunctionList));
    		DatabaseActionLogger.log(Resource.Action.CREATE, Resource.ActionTargetType.TASK_FUNCTION, selectedTask.getId());
    	});
    }

    @FXML
    void moveUpOnClick(ActionEvent event) {
    	int index = this.functionListView.getSelectionModel().getSelectedIndex();
    	if (index == -1)
    		return;
    	TaskFunction tf = this.taskFunctionList.get(index);
    	this.taskFunctionList.remove(index);
    	index = index - 1 < 0 ? 0 : index - 1;
    	this.taskFunctionList.add(index, tf);
    	refreshSequence();
    	functionListView.setItems(FXCollections.observableArrayList(this.taskFunctionList));
    	this.functionListView.getSelectionModel().select(index);
    	DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.TASK_FUNCTION, selectedTask.getId());
    }

    @FXML
    void moveDownOnClick(ActionEvent event) {
    	int index = this.functionListView.getSelectionModel().getSelectedIndex();
    	if (index == -1)
    		return;
    	TaskFunction tf = this.taskFunctionList.get(index);
    	this.taskFunctionList.remove(index);
    	index = index + 1 > this.taskFunctionList.size() ? this.taskFunctionList.size() : index + 1;
    	this.taskFunctionList.add(index, tf);
    	refreshSequence();
    	functionListView.setItems(FXCollections.observableArrayList(this.taskFunctionList));
    	this.functionListView.getSelectionModel().select(index);
    	DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.TASK_FUNCTION, selectedTask.getId());
    }

    @FXML
    void deleteOnClick(ActionEvent event) {
    	int index = this.functionListView.getSelectionModel().getSelectedIndex();
    	if (index == -1)
    		return;
    	TaskFunction sf = this.taskFunctionList.get(index);
    	this.taskFunctionList.remove(index);
    	refreshSequence();
    	DataService.taskFunction.remove(sf);
    	functionListView.setItems(FXCollections.observableArrayList(this.taskFunctionList));
    	DatabaseActionLogger.log(Resource.Action.DELETE, Resource.ActionTargetType.TASK_FUNCTION, selectedTask.getId());
    }

    void refreshSequence() {
    	for (int i = 0; i < this.taskFunctionList.size(); ++i){
    		this.taskFunctionList.get(i).setSequence(i + 1);
    	}
    }
}