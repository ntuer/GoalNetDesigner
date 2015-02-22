package ntu.goalnetdesigner.fxcontrol.login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialogs;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.fxcontrol.propertypanecontrol.ArcPropertyPaneController;
import ntu.goalnetdesigner.logic.LoginManager;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.UIUtility;
import ntu.goalnetdesigner.utility.Resource;

public class ResetPasswordController{
	
    @FXML
    private Text actiontarget;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField questionField;

    @FXML
    private TextField answerField;

    @FXML
    private TextField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    private User user;
    
    @FXML
    public void initialize(){
    	questionField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					user = DataService.user.find(usernameField.getText());
					if (user != null)
						questionField.setText(user.getQuestion());
					else
						questionField.setText("");
				}
			}
		});
    }
    
    @FXML
    void resetClicked(ActionEvent event) throws Exception {
    	// check input validity
    	try{
    		if (usernameField.getText().isEmpty() 
    				|| passwordField.getText().isEmpty()
    				|| confirmPasswordField.getText().isEmpty()
        			|| answerField.getText().isEmpty()
        			|| questionField.getText().isEmpty()){
        		throw new Exception("You must fill in all fields.");
    		}
    		if (!passwordField.getText().equals(confirmPasswordField.getText())){
    			throw new Exception("Your two passwords are not the same.");
    		}
    		if (!answerField.getText().equals(user.getAnswer())){
    			throw new Exception("Your answer is wrong.");
    		}
    	} catch (Exception e) {
    		actiontarget.setText(e.getMessage());
    		return;
    	}
    	LoginManager lm = new LoginManager();
		if (lm.changePassword(user, passwordField.getText())) {
			Dialogs.showInformationDialog(UISession.primaryStage, "You can now login with your new password.", 
				    "User password reset", "Password Reset");
			UIUtility.Navigation.switchTo(Resource.LOGIN_PATH, UISession.primaryStage);
		}
		else
			actiontarget.setText("Registration Failed. User already exists.");
    }

    @FXML
    void cancelClicked(ActionEvent event) throws Exception {
    	UIUtility.Navigation.switchTo(Resource.LOGIN_PATH, UISession.primaryStage);
    }
}
