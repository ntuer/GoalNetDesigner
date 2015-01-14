package ntu.goalnetdesigner.fxcontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.render.RenderedTransition;
import ntu.goalnetdesigner.session.UISession;

public class ArcPropertyPaneController implements IPaneController{
    @FXML
    private TextField inputid;

    @FXML
    private TextField outputid;

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private TextField id;

    @FXML
    private TextField direction;

    
    public void initialize(URL arg0, ResourceBundle arg1) {
		refresh();
	}
	
	public void refresh(){
		Arc a = null;
		try {
			a = ((RenderedArc) UISession.currentSelection).getBaseObject();
		} catch (Exception e){
			a = (Arc) UISession.currentSelection;
		}
		if (a == null)
			return;
		description.setText(a.getDescription());
		id.setText(a.getId());
		name.setText(a.getName());
		direction.setText(a.getDirection() + "");
		inputid.setText(a.getInputID() + "");
		outputid.setText(a.getOutputID()+ "");
	}
}
