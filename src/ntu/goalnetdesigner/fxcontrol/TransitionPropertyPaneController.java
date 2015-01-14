package ntu.goalnetdesigner.fxcontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.render.RenderedTransition;
import ntu.goalnetdesigner.session.UISession;

public class TransitionPropertyPaneController implements IPaneController {
	@FXML
	private TextField cost;

	@FXML
	private TextField achievement;

	@FXML
	private TextField name;

	@FXML
	private TextField x;

	@FXML
	private TextArea description;

	@FXML
	private TextField y;

	@FXML
	private TextField id;

	@FXML
	private ComboBox<?> tasklist;

	@FXML
	private TextField enabled;

	public void initialize(URL arg0, ResourceBundle arg1) {
		refresh();
	}

	@Override
	public void refresh(){
		Transition t = null;
		try {
			t = ((RenderedTransition) UISession.currentSelection).getBaseObject();
		} catch (Exception e){
			t = (Transition) UISession.currentSelection;
		}
		if (t == null)
			return;
		x.setText("" + t.getX());
		y.setText("" + t.getY());
		description.setText(t.getDescription());
		id.setText(t.getId());
		name.setText(t.getName());
		enabled.setText(t.getEnabled() + "");
		achievement.setText(t.getAchievement() + "");
		cost.setText(t.getCost() + "");
	}
	
}
