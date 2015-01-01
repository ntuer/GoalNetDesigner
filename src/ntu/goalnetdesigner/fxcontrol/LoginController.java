package ntu.goalnetdesigner.fxcontrol;

import java.net.URL;
import java.util.*;

import ntu.goalnetdesigner.logic.LoginManager;
import ntu.goalnetdesigner.utility.Navigation;
import ntu.goalnetdesigner.session.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	public Stage stage;
	
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
		if (lm.isValidUser(id, password)) {
			LoginSession.isLoggedIn = true;
			LoginSession.id = id;
			Navigation.switchScene("/ntu/goalnetdesigner/fxui/MainPage.fxml", this.stage);
		}
		else
			actiontarget.setText("Invalid Login");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		serverAddressField.setText("localhost/gnetdb_new");
	}
	
//	private Parent replaceSceneContent(String fxml) throws Exception {
//        Parent page = (Parent) FXMLLoader.load(getClass().getResource(fxml), null, new JavaFXBuilderFactory());
//        Scene scene = stage.getScene();
//        if (scene == null) {
//            scene = new Scene(page);
//            stage.setScene(scene);
//        } else {
//            stage.getScene().setRoot(page);
//        }
//        stage.sizeToScene();
//        return page;
//    }
}
