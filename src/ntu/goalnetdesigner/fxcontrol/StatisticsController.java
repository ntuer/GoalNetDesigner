package ntu.goalnetdesigner.fxcontrol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ntu.goalnetdesigner.data.persistence.ActionLog;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.Resource;

public class StatisticsController {
	
    @FXML
    private Label startLabel;
    
    @FXML
    private VBox vbox;
    
    @FXML
    private PieChart chart;
    
    @FXML
    public void initialize(){
    	Integer creation = 0;
    	Integer deletion = 0;
    	Integer edit = 0;
    	Integer move = 0;
    	for (ActionLog al: DataSession.Cache.actionLogs){
    		String action = al.getAction();
    		if (action.equals(Resource.Action.OPEN)){
    			startLabel.setText(al.getTimestamp().toString());
    		} else if (action.equals(Resource.Action.CREATE)){
    			++creation;
    		} else if (action.equals(Resource.Action.EDIT)){
    			++edit;
    		} else if (action.equals(Resource.Action.DELETE)){
    			++deletion;
    		} else if (action.equals(Resource.Action.MOVE)){
    			++move;
    		}
    	}
    	
        ObservableList<PieChart.Data> pieChartData = 
                FXCollections.observableArrayList(
                    new PieChart.Data("Creation", creation),
                    new PieChart.Data("Edit", edit),
                    new PieChart.Data("Deletion", deletion),
                    new PieChart.Data("Move", move));
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        chart.setData(pieChartData);
        chart.setTitle("User Operations");
        
        // set label as numbers
		for (Node node : chart.lookupAll("Text.chart-pie-label")) {
			if (node instanceof Text) {
				for (PieChart.Data data : pieChartData) {
					if (data.getName().equals(((Text) node).getText()))
						((Text) node).setText(String.format("%,.0f",
								data.getPieValue()));
				}
			}
		}
    }
	
}
