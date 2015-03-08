package ntu.goalnetdesigner.fxcontrol.sharing;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.ListView;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.logic.FunctionManager;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
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
    	if (am.getGnetAccessLevelOfUser(LoginSession.user, DataSession.Cache.gnet).equals(Resource.UserGnetAccessLevel.Read)){
    		Dialogs.showErrorDialog(UISession.secondaryStage, "You do not have write access to this Goal net and cannot use this function!", "Unauthorized", "Error");
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
    		Dialogs.showErrorDialog(UISession.secondaryStage, "Please select both Function and Goal Net", "Empty Selection", "Error");
    	}
    	else {
	    	FunctionManager.cloneInstanceToGnet(method, gnet);
	    	Dialogs.showInformationDialog(UISession.secondaryStage, "Function " + method.getName() + " has been cloned to " + gnet.getName(), 
	    		    "Clone successful", "Clone Function");
    	}
    }
}
