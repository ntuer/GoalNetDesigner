package ntu.goalnetdesigner;

import ntu.goalnetdesigner.fxcontrol.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		 
		// load first page
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ntu/goalnetdesigner/fxui/Login.fxml"));
		Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Goal Net Designer Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Set login controller data for stage tracking
        LoginController ctrl  = loader.getController();
        ctrl.stage = primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
