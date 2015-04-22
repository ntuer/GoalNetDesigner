package ntu.goalnetdesigner.fxcontrol.viewcontroller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import ntu.goalnetdesigner.data.persistence.Gnet;

public class GoalNetDrawingTabControl extends Tab{
	// handle multiple gnets
	private Gnet gnet;
	public GoalNetDrawingTabControl(Gnet gnet){
		this.gnet = gnet;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"/ntu/goalnetdesigner/fxui/customcontrol/GoalNetDrawingTab.fxml"));
				        fxmlLoader.setRoot(this);
				        fxmlLoader.setController(this);

				        try {
				            fxmlLoader.load();
				        } catch (IOException exception) {
				            throw new RuntimeException(exception);
				        }
	}
	public Gnet getGnet() {
		return gnet;
	}
	public void setGnet(Gnet gnet) {
		this.gnet = gnet;
	}
}
