package ntu.goalnetdesigner.fxcontrol;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ntu.goalnetdesigner.data.persistence.ActionLog;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Resource;

public class StatisticsController {

    @FXML
    private Label startLabel;

    @FXML
    private Label updateLabel;

    @FXML
    private Label deletionLabel;

    @FXML
    private Label creationLabel;

    @FXML
    public void initialize(){
    	Integer creation = 0;
    	Integer deletion = 0;
    	Integer update = 0;
    	for (ActionLog al: DataSession.Cache.actionLogs){
    		String action = al.getAction();
    		if (action.equals(Resource.Action.OPEN)){
    			startLabel.setText(al.getTimestamp().toString());
    		} else if (action.equals(Resource.Action.CREATE)){
    			++creation;
    		} else if (action.equals(Resource.Action.UPDATE)){
    			++update;
    		} else if (action.equals(Resource.Action.DELETE)){
    			++deletion;
    		}
    	}
    	creationLabel.setText(creation.toString());
    	updateLabel.setText(update.toString());
    	deletionLabel.setText(deletion.toString());
    }
	
}
