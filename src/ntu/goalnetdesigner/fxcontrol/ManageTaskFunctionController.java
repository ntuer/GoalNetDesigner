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
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.TaskFunction;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Dialogs;
import ntu.goalnetdesigner.utility.Dialogs.DialogOptions;
import ntu.goalnetdesigner.utility.Dialogs.DialogResponse;
import ntu.goalnetdesigner.utility.Resource;

public class ManageTaskFunctionController {

	@FXML
    private ListView<TaskFunction> functionListView;

    private Task selectedTask;
    private List<TaskFunction> taskFunctionList;
    
    // Helper fields for adding new function
	private Method selectedFunction;
	private ComboBox<Method> cmb = new ComboBox<Method>();
    
    @FXML
    public void initialize(){
    	this.selectedTask = (Task) UISession.currentSelection.getValue();
    	this.taskFunctionList = this.selectedTask.getTaskFunctions();
    	functionListView.setItems(FXCollections.observableArrayList(this.taskFunctionList));
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
				ManageTaskFunctionController.this.selectedFunction = cmb.getSelectionModel().getSelectedItem();
				return null;
			}
    	};
    	DialogResponse resp = Dialogs.showCustomDialog(UISession.primaryStage, 
    			grid, "Please select function", "Select function", DialogOptions.OK_CANCEL, callback);
    	
    	if (resp == DialogResponse.OK){
    		TaskFunction tf = new TaskFunction();
    		// find max sequence, and append to end
    		tf.setSequence(this.taskFunctionList.size());
    		this.selectedTask.addTaskFunction(tf);
    		this.selectedFunction.addTaskFunction(tf);
    		refreshSequence();
    		DataService.taskFunction.persist(tf);
    		functionListView.setItems(FXCollections.observableArrayList(this.taskFunctionList));
    		DatabaseActionLogger.log(Resource.Action.CREATE, Resource.ActionTargetType.TASK_FUNCTION, selectedTask.getId());
    	}
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