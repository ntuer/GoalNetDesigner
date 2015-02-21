package ntu.goalnetdesigner.fxcontrol;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.StateFunction;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.TaskFunction;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.UIUtility;

public class ManageTaskFunctionController {

    @FXML
    private ListView<TaskFunction> functionList;

    private Task selectedTask;
    private ObservableList<TaskFunction> taskFunctionList;
    
    // Helper fields for adding new function
	private Method selectedFunction;
	private ComboBox<Method> cmb = new ComboBox<Method>();
    
    @FXML
    public void initialize(){
    	this.selectedTask = (Task) UISession.currentSelection;
    	this.taskFunctionList = FXCollections.observableArrayList(this.selectedTask.getTaskFunctions());
    	functionList.setItems(this.taskFunctionList);
    }
    
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
    			grid, "Please select function to associate with this task", "Select function", DialogOptions.OK_CANCEL, callback);
    	
    	if (resp == DialogResponse.OK){
    		TaskFunction sf = new TaskFunction();
    		// find max sequence, and append to end
    		sf.setSequence(this.taskFunctionList.size());
    		this.selectedTask.addTaskFunction(sf);
    		this.selectedFunction.addTaskFunction(sf);
    		this.taskFunctionList.add(sf);
    	}
    }

    @FXML
    void moveUpOnClick(ActionEvent event) {
    	TaskFunction sf = this.functionList.getSelectionModel().getSelectedItem();
    	int index = this.functionList.getSelectionModel().getSelectedIndex();
    	this.taskFunctionList.remove(index);
    	index = index - 1 < 0 ? 0 : index - 1;
    	this.taskFunctionList.add(index, sf);
    	this.functionList.getSelectionModel().select(index);
    }

    @FXML
    void moveDownOnClick(ActionEvent event) {
    	TaskFunction sf = this.functionList.getSelectionModel().getSelectedItem();
    	int index = this.functionList.getSelectionModel().getSelectedIndex();
    	this.taskFunctionList.remove(index);
    	index = index + 1 > this.taskFunctionList.size() ? this.taskFunctionList.size() : index + 1;
    	this.taskFunctionList.add(index, sf);
    	this.functionList.getSelectionModel().select(index);
    }

    @FXML
    void deleteOnClick(ActionEvent event) {
    	TaskFunction tf = this.functionList.getSelectionModel().getSelectedItem();
    	int index = this.functionList.getSelectionModel().getSelectedIndex();
    	this.taskFunctionList.remove(index);
    }

    @FXML
    void saveButtonOnClick(ActionEvent event) {
    	for (int i = 0; i < this.taskFunctionList.size(); ++i){
    		this.taskFunctionList.get(i).setSequence(i + 1);
    	}
    	TaskFunction[] a = new TaskFunction[taskFunctionList.size()];
    	this.selectedTask.setTaskFunctions(new ArrayList<TaskFunction>(Arrays.asList((taskFunctionList.toArray(a)))));
    	UIUtility.Navigation.closeContainingStage((Button)event.getSource());
    }
}