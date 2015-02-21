package ntu.goalnetdesigner.fxcontrol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.StateFunction;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.UIUtility;

public class ManageStateFunctionController {

    @FXML
    private ListView<StateFunction> functionList;

    private State selectedState;
    private ObservableList<StateFunction> stateFunctionList;
    
    // Helper fields for adding new function
	private Method selectedFunction;
	private ComboBox<Method> cmb = new ComboBox<Method>();
    
    @FXML
    public void initialize(){
    	this.selectedState = (State) UISession.getDrawableFromCurrentSelection();
    	this.stateFunctionList = FXCollections.observableArrayList(this.selectedState.getStateFunctions());
    	functionList.setItems(this.stateFunctionList);
    }
    
    @FXML
    void addButtonOnClick(ActionEvent event) {
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(0, 10, 0, 10));
		cmb.setItems(FXCollections.observableArrayList(DataSession.Cache.functions));
		grid.add(cmb, 0, 0);
    	Callback callback = new Callback() {
			@Override
			public Object call(Object arg0) {
				ManageStateFunctionController.this.selectedFunction = cmb.getSelectionModel().getSelectedItem();
				return null;
			}
    	};
    	DialogResponse resp = Dialogs.showCustomDialog(UISession.primaryStage, 
    			grid, "Please log in", "Login", DialogOptions.OK_CANCEL, callback);
    	
    	if (resp == DialogResponse.OK){
    		StateFunction sf = new StateFunction();
    		// find max sequence, and append to end
    		sf.setSequence(this.stateFunctionList.size());
    		this.selectedState.addStateFunction(sf);
    		this.selectedFunction.addStateFunction(sf);
    		this.stateFunctionList.add(sf);
    	}
    }

    @FXML
    void moveUpOnClick(ActionEvent event) {
    	StateFunction sf = this.functionList.getSelectionModel().getSelectedItem();
    	int index = this.functionList.getSelectionModel().getSelectedIndex();
    	this.stateFunctionList.remove(index);
    	index = index - 1 < 0 ? 0 : index - 1;
    	this.stateFunctionList.add(index, sf);
    	this.functionList.getSelectionModel().select(index);
    }

    @FXML
    void moveDownOnClick(ActionEvent event) {
    	StateFunction sf = this.functionList.getSelectionModel().getSelectedItem();
    	int index = this.functionList.getSelectionModel().getSelectedIndex();
    	this.stateFunctionList.remove(index);
    	index = index + 1 > this.stateFunctionList.size() ? this.stateFunctionList.size() : index + 1;
    	this.stateFunctionList.add(index, sf);
    	this.functionList.getSelectionModel().select(index);
    }

    @FXML
    void deleteOnClick(ActionEvent event) {
    	int index = this.functionList.getSelectionModel().getSelectedIndex();
    	this.stateFunctionList.remove(index);
    }

    @FXML
    void saveButtonOnClick(ActionEvent event) {
    	for (int i = 0; i < this.stateFunctionList.size(); ++i){
    		this.stateFunctionList.get(i).setSequence(i + 1);
    	}
    	StateFunction[] a = new StateFunction[stateFunctionList.size()];
    	this.selectedState.setStateFunctions(new ArrayList<StateFunction>(Arrays.asList((stateFunctionList.toArray(a)))));
    	UIUtility.Navigation.closeContainingStage((Button)event.getSource());
    }
}