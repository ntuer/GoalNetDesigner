package ntu.goalnetdesigner.fxcontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.logic.LoginManager;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.UIUtility;
import ntu.goalnetdesigner.utility.Resource;

public class LoginController implements Initializable{
	
	@FXML
	private Text actiontarget;

    @FXML
    private TextField serverAddressField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private TextField usernameField;
    
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception{
		LoginSession.serverAddress = serverAddressField.getText();
		String id = usernameField.getText();
		String password = passwordField.getText();
		LoginManager lm = new LoginManager();
		if (lm.login(id, password)) {
			UIUtility.Navigation.switchTo(Resource.MAINPAGE_PATH, UISession.primaryStage);
		}
		else
			actiontarget.setText("Invalid Login");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		serverAddressField.setText("localhost/gnetdb_new");
	}
}
