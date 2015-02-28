package ntu.goalnetdesigner.fxcontrol.user;

import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import ntu.goalnetdesigner.data.persistence.UsergroupGnet;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.utility.UIUtility;

public class GNetUsergroupController {

    @FXML
    private TableView<UsergroupGnet> usergroupTable;

    @FXML
    private TableColumn<UsergroupGnet, String> nameColumn;

    @FXML
    private TableColumn<UsergroupGnet, String> descriptionColumn;

    @FXML
    private TableColumn<UsergroupGnet, String> canReadColumn;

    @FXML
    private TableColumn<UsergroupGnet, String> canWriteColumn;
    
    @FXML
    private Button OKButton;
    
    private List<UsergroupGnet> ugList;
    
    @FXML
    public void initialize(){
		List<UsergroupGnet> ugList = DataSession.Cache.gnet.getUsergroupGnets();
		ObservableList<String> levelChoice = FXCollections.observableArrayList (
			    new String("true"),
			    new String("false")
			);
		
		usergroupTable.setItems(FXCollections.observableArrayList(ugList));
		nameColumn.setCellValueFactory(new Callback<CellDataFeatures<UsergroupGnet, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<UsergroupGnet, String> p) {
						return new ReadOnlyObjectWrapper<String>(p.getValue().getUsergroup().getName());
					}
				});
		//nameColumn.setCellFactory(TextFieldTableCell.<UsergroupGnet>forTableColumn());
		descriptionColumn.setCellValueFactory(new Callback<CellDataFeatures<UsergroupGnet, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<UsergroupGnet, String> p) {
						return new ReadOnlyObjectWrapper<String>(p.getValue().getUsergroup().getDescription());
					}
				});
		//descriptionColumn.setCellFactory(TextFieldTableCell.<UsergroupGnet>forTableColumn());
    	canReadColumn.setCellValueFactory(new PropertyValueFactory<UsergroupGnet,String>("read"));
    	canWriteColumn.setCellValueFactory(new PropertyValueFactory<UsergroupGnet,String>("write"));
    	AuthorizationManager am = new AuthorizationManager();
    	if (am.canUserWriteGnet(LoginSession.user, DataSession.Cache.gnet)){
	    	canReadColumn.setCellFactory(ComboBoxTableCell.<UsergroupGnet, String>forTableColumn(levelChoice));
	    	canWriteColumn.setCellFactory(ComboBoxTableCell.<UsergroupGnet, String>forTableColumn(levelChoice));
    	}
    }
    
    @FXML
    void OKButtonClicked(ActionEvent event) {
    	UsergroupGnet selectedUsergroup = usergroupTable.getSelectionModel().getSelectedItem();
    	UIUtility.Navigation.closeContainingStage(OKButton);
    }
}
