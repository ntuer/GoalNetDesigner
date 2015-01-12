package ntu.goalnetdesigner.utility;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Navigation {
	public static void switchTo(String fxmlPath, Stage targetStage) throws Exception{
		Scene scene = Resource.getInstance().getFxmlScene(fxmlPath);
//        if (scene == null) {
//            scene = new Scene(page);
//            stage.setScene(scene);
//        } else {
//            stage.getScene().setRoot(page);
//        }
		targetStage.setScene(scene);
		targetStage.sizeToScene();
	}
	
	public static Stage popUp(String fxmlPath, Stage ownerStage) throws Exception{
		Stage st = new Stage();
		st.initModality(Modality.APPLICATION_MODAL);
		st.initOwner(ownerStage.getScene().getWindow());
        Scene scene = Resource.getInstance().getFxmlScene(fxmlPath);
        st.setScene(scene);
        st.show();
        return st;
	}
	
	public static void closeParentStage(Button bt){
		Stage stage = (Stage) bt.getScene().getWindow();
        stage.close();
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
