package ntu.goalnetdesigner.utility;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Resource {
	
	private Resource () {
		
	}
	
	public static Resource instance = null;
	
	public static Resource getInstance(){
		if (instance == null){
			instance = new Resource();
		}
		return instance;
	}
	
	public Scene getFxmlScene(String fxmlPath) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root = (Parent) loader.load();
        return new Scene(root);
	}
}
