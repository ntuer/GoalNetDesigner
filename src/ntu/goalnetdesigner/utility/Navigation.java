package ntu.goalnetdesigner.utility;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Navigation {
	public static void switchScene(String fxmlPath, Stage targetStage) throws Exception{
		Scene scene = Resource.getInstance().getFxmlScene(fxmlPath);
//        if (scene == null) {
//            scene = new Scene(page);
//            stage.setScene(scene);
//        } else {
//            stage.getScene().setRoot(page);
//        }
		targetStage.setScene(scene);
		targetStage.sizeToScene();
        //return page;
	}
	
	public static Stage popUpStage(String fxmlPath, Stage ownerStage) throws Exception{
		Stage st = new Stage();
		st.initModality(Modality.APPLICATION_MODAL);
		st.initOwner(ownerStage.getScene().getWindow());
        Scene scene = Resource.getInstance().getFxmlScene(fxmlPath);
        st.setTitle("Goal Net Designer Login");
        st.setScene(scene);
        st.show();
        return st;
	}
}
