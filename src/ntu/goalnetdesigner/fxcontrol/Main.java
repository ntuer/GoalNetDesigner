package ntu.goalnetdesigner.fxcontrol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ntu/goalnetdesigner/fxui/Login.fxml"));
		Parent root = (Parent) loader.load();
        LoginController ctrl  = loader.getController();
        ctrl.stage = primaryStage;
        Scene scene = new Scene(root);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
