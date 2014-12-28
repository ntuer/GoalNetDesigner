package ntu.goalnetdesigner.fxcontrol;

import java.net.URL;
import java.util.*;

import ntu.goalnetdesigner.data.persistence.*;
import ntu.goalnetdesigner.data.service.*;
import ntu.goalnetdesigner.logic.LoginManager;
import ntu.goalnetdesigner.utility.Navigation;
import ntu.goalnetdesigner.session.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	public Stage stage;
	
	@FXML
	private Text actiontarget;

	@FXML
    private ComboBox serverSelectionComboBox;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private TextField usernameField;
    
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception{
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setServerSelectionComboBoxData();
	}
	
	private void setServerSelectionComboBoxData(){
		List<String> list = new ArrayList<String>();
		list.add("localhost");
		ObservableList obList = FXCollections.observableList(list);
		serverSelectionComboBox.setItems(obList);
	}
}
