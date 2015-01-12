package ntu.goalnetdesigner.fxcontrol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.UserConsoleLogger;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Navigation;

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
    	t.setName(this.nameOfGoalNetField.getText());
    	t.setDescription(descriptionField.getText());
    	DataSession.currentGNet = t;
    	DataService.gnet.insert(t);
    	UserConsoleLogger.log("New Goal Net Created: " + t.getName());
    	Navigation.closeParentStage(okButton);
    }

    @FXML
    void cancelButtonClicked(ActionEvent event) {
    	Navigation.closeParentStage(cancelButton);
    }
}
