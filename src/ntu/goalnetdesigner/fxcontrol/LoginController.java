package ntu.goalnetdesigner.fxcontrol;

import ntu.goalnetdesigner.data.*;
import ntu.goalnetdesigner.data.persistence.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {
	public Stage stage;
	
	@FXML
	private Text actiontarget;

	@FXML
    private ChoiceBox serverField;
	
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		TestManager tm = new TestManager();
		actiontarget.setText(tm.fetchFirstArc().getGNetID().toString());
		
//		try {
//			replaceSceneContent("/ntu/goalnetdesigner/fxui/MainPage.fxml");
//		} catch (Exception e) {
//			
//		}
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
}
