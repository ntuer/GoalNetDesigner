package ntu.goalnetdesigner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Resource.LOGIN_PATH));
		Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("GoalNet Designer");
        primaryStage.setScene(scene);
        primaryStage.show();
        UISession.primaryStage = primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
