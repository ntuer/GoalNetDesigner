package ntu.goalnetdesigner.fxcontrol;

import java.net.URL;
import java.util.*;

import ntu.goalnetdesigner.data.manager.*;
import ntu.goalnetdesigner.data.persistence.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	public Stage stage;
	
	@FXML
	private Text actiontarget;

	@FXML
    private ComboBox serverSelectionComboBox;
	
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		TestManager tm = new TestManager();
		Arc a = tm.fetchFirstArc();
		actiontarget.setText(tm.fetchFirstArc().getGNetID().toString());
		
		try {
			replaceSceneContent("/ntu/goalnetdesigner/fxui/MainPage.fxml");
		} catch (Exception e) {
			
		}
	}
	
	private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(getClass().getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }

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
