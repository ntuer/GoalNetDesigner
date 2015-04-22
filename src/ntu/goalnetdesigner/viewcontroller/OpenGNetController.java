package ntu.goalnetdesigner.viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class OpenGNetController {

    @FXML
    private TableView<Gnet> GNetTable;

    @FXML
    private TableColumn<Gnet, String> nameColumn;

    @FXML
    private Button OKButton;

    @FXML
    private TableColumn<Gnet, String> descriptionColumn;

    @FXML
    public void initialize(){
    	AuthorizationManager am = new AuthorizationManager();
    	ObservableList<Gnet> gNets = FXCollections.observableArrayList(am.getReadableGnetsOfUser(LoginSession.user));
    	GNetTable.setItems(gNets);
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Gnet,String>("name"));
    	descriptionColumn.setCellValueFactory(new PropertyValueFactory<Gnet,String>("description"));
    }
    
    @FXML
    void OKButtonClicked(ActionEvent event) {
    	AuthorizationManager am = new AuthorizationManager();
    	Gnet selectedGNet = GNetTable.getSelectionModel().getSelectedItem();
    	if (am.getGnetAccessLevelOfUser(LoginSession.user, selectedGNet).equals(Resource.UserGnetAccessLevel.READ)){
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Read Access Only");
    		alert.setHeaderText("You only have read access to this Goal Net");
    		alert.setContentText("Any changes on this Goal Net will not be saved to database. However, you can edit and export it.");
    		alert.showAndWait();
    	}
    	DataSession.setGNetCache(selectedGNet);
    	ConsoleLogger.log("Existing GNet Opened:" + selectedGNet.getName());
    	UIUtility.Navigation.closeContainingStage(OKButton);
    }

}
