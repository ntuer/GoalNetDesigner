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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Tasklist;
import ntu.goalnetdesigner.data.persistence.TasklistTask;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.UIUtility;

public class ManageTransitionTaskController {
    @FXML
    private TextField taskListNameField;
    @FXML
    private ListView<TasklistTask> taskView;

    private Tasklist selectedTasklist;
    private ObservableList<TasklistTask> tasklistTask;
    
    // Helper fields for adding new function
	private Task selectedTask;
	private ComboBox<Task> cmb = new ComboBox<Task>();
    
    @FXML
    public void initialize(){
    	this.selectedTasklist = ((Transition)UISession.getDrawableFromCurrentSelection()).getTasklist();
    	this.tasklistTask = FXCollections.observableArrayList(this.selectedTasklist.getTasklistTasks());
    	taskView.setItems(this.tasklistTask);
    	this.taskListNameField.setText(this.selectedTasklist.getName());
    }
    
    @FXML
    void addButtonOnClick(ActionEvent event) {
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(0, 10, 0, 10));
		cmb.setItems(FXCollections.observableArrayList(DataSession.Cache.tasks));
		grid.add(cmb, 0, 0);
    	Callback callback = new Callback() {
			@Override
			public Object call(Object arg0) {
				ManageTransitionTaskController.this.selectedTask = cmb.getSelectionModel().getSelectedItem();
				return null;
			}
    	};
    	DialogResponse resp = Dialogs.showCustomDialog(UISession.primaryStage, 
    			grid, "Please select function to associate with this task", "Select function", DialogOptions.OK_CANCEL, callback);
    	
    	if (resp == DialogResponse.OK){
    		TasklistTask sf = new TasklistTask();
    		// find max sequence, and append to end
    		sf.setSequence(this.tasklistTask.size());
    		this.selectedTasklist.addTasklistTask(sf);
    		this.selectedTask.addTasklistTask(sf);
    		this.tasklistTask.add(sf);
    	}
    }

    @FXML
    void moveUpOnClick(ActionEvent event) {
    	TasklistTask sf = this.taskView.getSelectionModel().getSelectedItem();
    	int index = this.taskView.getSelectionModel().getSelectedIndex();
    	this.tasklistTask.remove(index);
    	index = index - 1 < 0 ? 0 : index - 1;
    	this.tasklistTask.add(index, sf);
    	this.taskView.getSelectionModel().select(index);
    }

    @FXML
    void moveDownOnClick(ActionEvent event) {
    	TasklistTask sf = this.taskView.getSelectionModel().getSelectedItem();
    	int index = this.taskView.getSelectionModel().getSelectedIndex();
    	this.tasklistTask.remove(index);
    	index = index + 1 > this.tasklistTask.size() ? this.tasklistTask.size() : index + 1;
    	this.tasklistTask.add(index, sf);
    	this.taskView.getSelectionModel().select(index);
    }

    @FXML
    void deleteOnClick(ActionEvent event) {
    	TasklistTask tf = this.taskView.getSelectionModel().getSelectedItem();
    	int index = this.taskView.getSelectionModel().getSelectedIndex();
    	this.tasklistTask.remove(index);
    }

    @FXML
    void saveButtonOnClick(ActionEvent event) {
    	for (int i = 0; i < this.tasklistTask.size(); ++i){
    		this.tasklistTask.get(i).setSequence(i + 1);
    	}
    	TasklistTask[] a = new TasklistTask[tasklistTask.size()];
    	this.selectedTasklist.setTasklistTasks(new ArrayList<TasklistTask>(Arrays.asList((tasklistTask.toArray(a)))));
    	this.selectedTasklist.setName(taskListNameField.getText());
    	UIUtility.Navigation.closeContainingStage((Button)event.getSource());
    }
}