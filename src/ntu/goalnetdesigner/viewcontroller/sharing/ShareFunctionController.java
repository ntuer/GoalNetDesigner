package ntu.goalnetdesigner.viewcontroller.sharing;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.logic.FunctionManager;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.utility.Resource;

public class ShareFunctionController {

    @FXML
    private ListView<Method> functionListView;

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
	    	this.functionListView.setItems(FXCollections.observableArrayList(DataSession.Cache.gnet.getMethods()));
	    	this.gnetListView.setItems(FXCollections.observableArrayList(am.getWriteableGnetsOfUser(LoginSession.user)));
    	}
    }
    
    @FXML
    void cloneButtonClicked(ActionEvent event) {
    	Method method = this.functionListView.getSelectionModel().getSelectedItem();
    	Gnet gnet = this.gnetListView.getSelectionModel().getSelectedItem();
    	if (method == null || gnet == null){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Empty Selection");
    		alert.setContentText("Please select both Function and Goal Net!");
    		alert.showAndWait();
    	}
    	else {
	    	FunctionManager.cloneInstanceToGnet(method, gnet);
	    	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Clone Function");
    		alert.setHeaderText("Clone successful");
    		alert.setContentText("Function " + method.getName() + " has been cloned to " + gnet.getName());
    		alert.showAndWait();
    	}
    }
}
