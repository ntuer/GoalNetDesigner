package ntu.goalnetdesigner.fxcontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.render.RenderedState;
import ntu.goalnetdesigner.session.UISession;

public class StatePropertyPaneController implements IPaneController{
	@FXML
    private TextField composite;

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
    
	public void initialize(URL arg0, ResourceBundle arg1) {
		refresh();
	}
	
	public void refresh(){
		State s = null;
		try {
			s = ((RenderedState) UISession.currentSelection).getBaseObject();
		} catch (Exception e){
			s = (State) UISession.currentSelection;
		}
		if (s == null)
			return;
		x.setText("" + s.getX());
		y.setText("" + s.getY());
		description.setText(s.getDescription());
		id.setText(s.getId());
		name.setText(s.getName());
		composite.setText(s.getComposite() + "");
	}
}
