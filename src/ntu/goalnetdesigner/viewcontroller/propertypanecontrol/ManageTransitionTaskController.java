package ntu.goalnetdesigner.viewcontroller.propertypanecontrol;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Tasklist;
import ntu.goalnetdesigner.data.persistence.TasklistTask;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.DatabaseActionLogger;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;

public class ManageTransitionTaskController {
    @FXML
    private TextField taskListNameField;
    
    @FXML
    private ListView<TasklistTask> taskView;

    private Tasklist selectedTasklist;
    private List<TasklistTask> tasklistTaskList;

    @FXML
    public void initialize(){
    	this.selectedTasklist = (Tasklist) ((Transition)UISession.getCurrentSelectionAsDrawable()).getTasklist();
    	this.tasklistTaskList = this.selectedTasklist.getTasklistTasks();
    	taskView.setItems(FXCollections.observableArrayList(this.tasklistTaskList));
    	taskListNameField.setText(this.selectedTasklist.getName());
    	
    	// on-close save name
    	UISession.secondaryStage.setOnHiding(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				selectedTasklist.setName(taskListNameField.getText());
				UISession.secondaryStage = null;
			}
		});
    }
    
	@FXML
    void addButtonOnClick(ActionEvent event) {
    	List<Task> choices = DataSession.Cache.tasks;

    	ChoiceDialog<Task> dialog = new ChoiceDialog<>(null, choices);
    	dialog.setTitle("Add");
    	dialog.setHeaderText("Select task");
    	dialog.setContentText("Task list:");
    	Optional<Task> result = dialog.showAndWait();
    	result.ifPresent(v -> {
    		TasklistTask tt = new TasklistTask();
    		// find max sequence, and append to end
    		tt.setSequence(this.tasklistTaskList.size());
    		this.selectedTasklist.addTasklistTask(tt);
    		v.addTasklistTask(tt);
    		refreshSequence();
    		DataService.tasklistTask.persist(tt);
    		taskView.setItems(FXCollections.observableArrayList(this.tasklistTaskList));
    		DatabaseActionLogger.log(Resource.Action.CREATE, Resource.ActionTargetType.TASKLIST_TASK, selectedTasklist.getTransitions().get(0).getId());
    	});
    }

    @FXML
    void moveUpOnClick(ActionEvent event) {
    	int index = this.taskView.getSelectionModel().getSelectedIndex();
    	if (index == -1)
    		return;
    	TasklistTask tt = this.tasklistTaskList.get(index);
    	this.tasklistTaskList.remove(index);
    	index = index - 1 < 0 ? 0 : index - 1;
    	this.tasklistTaskList.add(index, tt);
    	refreshSequence();
    	taskView.setItems(FXCollections.observableArrayList(this.tasklistTaskList));
    	this.taskView.getSelectionModel().select(index);
    	DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.TASKLIST_TASK, selectedTasklist.getTransitions().get(0).getId());
    }

    @FXML
    void moveDownOnClick(ActionEvent event) {
    	int index = this.taskView.getSelectionModel().getSelectedIndex();
    	if (index == -1)
    		return;
    	TasklistTask tt = this.tasklistTaskList.get(index);
    	this.tasklistTaskList.remove(index);
    	index = index + 1 > this.tasklistTaskList.size() ? this.tasklistTaskList.size() : index + 1;
    	this.tasklistTaskList.add(index, tt);
    	refreshSequence();
    	taskView.setItems(FXCollections.observableArrayList(this.tasklistTaskList));
    	this.taskView.getSelectionModel().select(index);
    	DatabaseActionLogger.log(Resource.Action.EDIT, Resource.ActionTargetType.TASKLIST_TASK, selectedTasklist.getTransitions().get(0).getId());
    }

    @FXML
    void deleteOnClick(ActionEvent event) {
    	int index = this.taskView.getSelectionModel().getSelectedIndex();
    	if (index == -1)
    		return;
    	TasklistTask sf = this.tasklistTaskList.get(index);
    	this.tasklistTaskList.remove(index);
    	refreshSequence();
    	DataService.tasklistTask.remove(sf);
    	taskView.setItems(FXCollections.observableArrayList(this.tasklistTaskList));
    	DatabaseActionLogger.log(Resource.Action.DELETE, Resource.ActionTargetType.TASKLIST_TASK, selectedTasklist.getTransitions().get(0).getId());
    }

    void refreshSequence() {
    	for (int i = 0; i < this.tasklistTaskList.size(); ++i){
    		this.tasklistTaskList.get(i).setSequence(i + 1);
    	}
    }
}