package ntu.goalnetdesigner.fxcontrol.login;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

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
    	usernameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
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
    	AuthorizationManager lm = new AuthorizationManager();
		if (lm.changePassword(user, passwordField.getText())) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Password Reset");
			alert.setHeaderText("User password reset successfully");
			alert.setContentText("You can login with your new password now.");
			alert.showAndWait();
			UIUtility.Navigation.switchTo(Resource.LOGIN_PATH, UISession.primaryStage);
		}
		else
			actiontarget.setText("Reset failed.");
    }

    @FXML
    void cancelClicked(ActionEvent event) throws Exception {
    	UIUtility.Navigation.switchTo(Resource.LOGIN_PATH, UISession.primaryStage);
    }
}
