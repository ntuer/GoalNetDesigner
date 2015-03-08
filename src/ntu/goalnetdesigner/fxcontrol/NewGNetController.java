package ntu.goalnetdesigner.fxcontrol;

import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.UserGnet;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class NewGNetController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;
    @FXML
    private TextArea descriptionField;
    
    @FXML
    private TextField nameOfGoalNetField;

    @FXML
    void okButtonClicked(ActionEvent event) {
    	Gnet t = new Gnet();
    	// It must be placed here to save trouble
    	// otherwise Gnet constructor will initialize lists to null 
    	t.setId(UUID.randomUUID().toString());
    	t.setName(this.nameOfGoalNetField.getText());
    	t.setDescription(descriptionField.getText());
    	DataService.gnet.atomicInsert(t);
    	UserGnet ug = new UserGnet();
    	LoginSession.user.addUserGnet(ug);
    	t.addUserGnet(ug);
    	ug.setAccessLevel(Resource.UserGnetAccessLevel.ADMIN);
    	DataService.userGnet.atomicInsert(ug);
    	DataSession.setGNetCache(t);
    	ConsoleLogger.log("New Goal Net Created: " + t.getName());
    	UIUtility.Navigation.closeContainingStage(okButton);
    }

    @FXML
    void cancelButtonClicked(ActionEvent event) {
    	UIUtility.Navigation.closeContainingStage(cancelButton);
    }
}
