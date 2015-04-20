package ntu.goalnetdesigner.fxcontrol.sharing;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.logic.TaskManager;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
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
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Unauthorized");
    		alert.setContentText("You do not have write access to this Goal net and cannot use this function!");
    		alert.showAndWait();
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
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Empty Selection");
    		alert.setContentText("Please select both Task and Goal Net!");
    		alert.showAndWait();
    	}
    	else {
    		if (task.getTaskFunctions().size() != 0){
    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confirmation");
    			alert.setHeaderText("Clone with Functions");
    			alert.setContentText("This task contains functions and those functions will be cloned too. Proceed?");
    			Optional<ButtonType> result = alert.showAndWait();
    			if (result.get() == ButtonType.OK){
    				TaskManager.cloneInstanceToGnet(task, gnet);
    				Alert alert2 = new Alert(AlertType.INFORMATION);
    				alert2.setTitle("Clone Task");
    				alert2.setHeaderText("Clone successful");
    				alert2.setContentText("Task " + task.getName() + " has been cloned to " + gnet.getName());
    				alert2.showAndWait();
    			}
    		}
    	}
    }
}
