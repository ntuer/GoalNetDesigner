package ntu.goalnetdesigner.fxcontrol.login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Dialogs;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class RegisterController{
	

    @FXML
    private Text actiontarget;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField questionField;

    @FXML
    private TextField answerField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField confirmPasswordField;
    
    @FXML
    private TextField ageField;
    
    @FXML
    private ComboBox<String> educationComboBox;

    @FXML
    public void initialize(){
    	ObservableList<String> value = FXCollections.observableArrayList (
    		    Resource.EducationLevel.HIGH_SCHOOL_AND_BELOW,
    		    Resource.EducationLevel.UNDERGRADUATE,
    		    Resource.EducationLevel.GRADUATE
    		);
    	educationComboBox.setItems(value);
    }
    
    @FXML
    void registerClicked(ActionEvent event) throws Exception {
    	// check input validity
    	try{
    		if (usernameField.getText().isEmpty() 
    				|| passwordField.getText().isEmpty()
    				|| confirmPasswordField.getText().isEmpty()
        			|| emailField.getText().isEmpty()
        			|| answerField.getText().isEmpty()
        			|| questionField.getText().isEmpty()
        			|| ageField.getText().isEmpty()
        			|| educationComboBox.getSelectionModel().getSelectedItem().isEmpty()){
        		throw new Exception("You must fill in all fields.");
    		}
    		if (!passwordField.getText().equals(confirmPasswordField.getText())){
    			throw new Exception("Your two passwords are not the same.");
    		}
    		Integer.parseInt(ageField.getText());
    	} catch (Exception e) {
    		actiontarget.setText(e.getMessage());
    		return;
    	}
    	
    	User u = new User();
    	u.setId(usernameField.getText());
    	u.setPassword(passwordField.getText());
    	u.setEmail(emailField.getText());
    	u.setAnswer(answerField.getText());
    	u.setQuestion(questionField.getText());
    	u.setAge(Integer.parseInt(ageField.getText()));
    	u.setEducationLevel(educationComboBox.getSelectionModel().getSelectedItem());
		AuthorizationManager lm = new AuthorizationManager();
		if (lm.register(u)) {
			Dialogs.showInformationDialog(UISession.primaryStage, "You can now login with " + u.getId(), 
				    "User registered", "Registration");
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
