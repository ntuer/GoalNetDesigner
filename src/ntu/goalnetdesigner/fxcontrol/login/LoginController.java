package ntu.goalnetdesigner.fxcontrol.login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class LoginController implements Initializable{
	
	@FXML
	private Text actiontarget;

    @FXML
    private TextField serverAddressField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private TextField usernameField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		serverAddressField.setText("localhost/gnetdb_new");
	}
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception{
		LoginSession.serverAddress = serverAddressField.getText();
		String id = usernameField.getText();
		String password = passwordField.getText();
		AuthorizationManager lm = new AuthorizationManager();
		if (lm.login(id, password)) {
			UIUtility.Navigation.switchTo(Resource.MAINPAGE_PATH, UISession.primaryStage);
		}
		else
			actiontarget.setText("Invalid Login");
	}

    @FXML
    void registerClicked(ActionEvent event) throws Exception {
    	UIUtility.Navigation.switchTo(Resource.REGISTER_PATH, UISession.primaryStage);
    }

    @FXML
    void resetPasswordClicked(ActionEvent event) throws Exception {
    	UIUtility.Navigation.switchTo(Resource.RESET_PASSWORD_PATH, UISession.primaryStage);
    }
}
