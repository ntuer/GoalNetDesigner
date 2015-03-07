package ntu.goalnetdesigner.fxcontrol.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.utility.UIUtility;

public class UserInUsergroupController {

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
    	ObservableList<Gnet> gNets = FXCollections.observableArrayList(DataService.gnet.findAll());
    	GNetTable.setItems(gNets);
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Gnet,String>("name"));
    	descriptionColumn.setCellValueFactory(new PropertyValueFactory<Gnet,String>("description"));
    }
    
    @FXML
    void OKButtonClicked(ActionEvent event) {
    	Gnet selectedGNet = GNetTable.getSelectionModel().getSelectedItem();
    	DataSession.setGNetCache(selectedGNet);
    	ConsoleLogger.log("Existing GNet Opened:" + selectedGNet.getName());
    	UIUtility.Navigation.closeContainingStage(OKButton);
    }

}
