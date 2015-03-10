package ntu.goalnetdesigner.fxcontrol.sharing;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.logic.TaskManager;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Dialogs;
import ntu.goalnetdesigner.utility.Dialogs.DialogOptions;
import ntu.goalnetdesigner.utility.Dialogs.DialogResponse;
import ntu.goalnetdesigner.utility.Resource;

public class ShareTaskController {

    @FXML
    private ListView<Task> taskListView;

    @FXML
    private ListView<Gnet> gnetListView;

    @FXML
    private Button cloneButton;

    @FXML
    public void initialize(){
    	AuthorizationManager am = new AuthorizationManager();
    	if (am.getGnetAccessLevelOfUser(LoginSession.user, DataSession.Cache.gnet).equals(Resource.UserGnetAccessLevel.READ)){
    		Dialogs.showErrorDialog(UISession.secondaryStage, "You do not have write access to this Goal net and cannot use this function!", "Unauthorized", "Error");
    		cloneButton.setDisable(true);
    	}
    	else {
	    	this.taskListView.setItems(FXCollections.observableArrayList(DataSession.Cache.gnet.getTasks()));
	    	this.gnetListView.setItems(FXCollections.observableArrayList(am.getWriteableGnetsOfUser(LoginSession.user)));
    	}
    }
    
    @FXML
    void cloneButtonClicked(ActionEvent event) {
    	Task task = this.taskListView.getSelectionModel().getSelectedItem();
    	Gnet gnet = this.gnetListView.getSelectionModel().getSelectedItem();
    	if (task == null || gnet == null){
    		Dialogs.showErrorDialog(UISession.secondaryStage, "Please select both Task and Goal Net", "Empty Selection", "Error");
    	}
    	else {
    		if (task.getTaskFunctions().size() != 0){
    			DialogResponse response = Dialogs.showConfirmDialog(UISession.secondaryStage, "This task contains functions and those functions will be cloned too. Proceed?", 
    			        "Functions in this task will be cloned too", "Clone with Functions", DialogOptions.OK_CANCEL);
    			if (response == DialogResponse.OK){
    		    	TaskManager.cloneInstanceToGnet(task, gnet);
    		    	Dialogs.showInformationDialog(UISession.secondaryStage, "Task " + task.getName() + " has been cloned to " + gnet.getName(), 
    		    		    "Clone successful", "Clone Task");
    			}
    		}
    	}
    }
}
