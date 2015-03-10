package ntu.goalnetdesigner.fxcontrol.sharing;

import java.util.Iterator;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.persistence.UserGnet;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Dialogs;
import ntu.goalnetdesigner.utility.Dialogs.DialogOptions;
import ntu.goalnetdesigner.utility.Dialogs.DialogResponse;
import ntu.goalnetdesigner.utility.Resource;

public class ShareGNetController {

    @FXML
    private TableView<UserGnet> userTable;

    @FXML
    private TableColumn<UserGnet, String> nameColumn;

    @FXML
    private Button addButton;
    
    @FXML
    private Button removeButton;

    @FXML
    private TableColumn<UserGnet, String> accessLevelColumn;

    private ComboBox<User> userCmb = new ComboBox<User>();
    private ComboBox<String> accessCmb = new ComboBox<String>();
    private User selectedUser;
    private String selectedAccessLevel;
    
    private ObservableList<String> levelChoice = FXCollections.observableArrayList (
		    new String(Resource.UserGnetAccessLevel.READ),
		    new String(Resource.UserGnetAccessLevel.WRITE),
		    new String(Resource.UserGnetAccessLevel.ADMIN)
		);
    
    @FXML
    public void initialize(){
		userTable.setItems(FXCollections.observableArrayList(DataSession.Cache.gnet.getUserGnets()));
		nameColumn.setCellValueFactory(new Callback<CellDataFeatures<UserGnet, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<UserGnet, String> p) {
						return new ReadOnlyObjectWrapper<String>(p.getValue().getUser().getId());
					}
				});
    	accessLevelColumn.setCellValueFactory(new PropertyValueFactory<UserGnet,String>("accessLevel"));
    	AuthorizationManager am = new AuthorizationManager();
    	if (am.getGnetAccessLevelOfUser(LoginSession.user, DataSession.Cache.gnet).equals("Admin")){
    		accessLevelColumn.setCellFactory(ComboBoxTableCell.<UserGnet, String>forTableColumn(this.levelChoice));
    		accessLevelColumn.setOnEditCommit(new EventHandler<CellEditEvent<UserGnet, String>>() {
                @Override
                public void handle(CellEditEvent<UserGnet, String> t) {
                    ((UserGnet) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAccessLevel(t.getNewValue());
                }
            });
    		addButton.setDisable(false);
    		removeButton.setDisable(false);
    	}
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
    void addButtonClicked(ActionEvent event) {
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(0, 10, 0, 10));
    	
    	ObservableList<User> users = FXCollections.observableArrayList(DataService.user.findAll());
    	for (Iterator<User> iterator = users.iterator(); iterator.hasNext();){
    		User u = iterator.next();
    		for (UserGnet ug: DataSession.Cache.gnet.getUserGnets()){
    			if (ug.getUser().getId().equals(u.getId())){
    				iterator.remove();
    			}
    		}
    	}
		userCmb.setItems(users);
		
		accessCmb.setItems(FXCollections.observableArrayList(this.levelChoice));
		grid.add(userCmb, 0, 0);
		grid.add(accessCmb, 0, 1);
    	Callback callback = new Callback() {
			@Override
			public Object call(Object arg0) {
				ShareGNetController.this.selectedUser = userCmb.getSelectionModel().getSelectedItem();
				ShareGNetController.this.selectedAccessLevel = accessCmb.getSelectionModel().getSelectedItem();
				return null;
			}
    	};
    	DialogResponse resp = Dialogs.showCustomDialog(UISession.primaryStage, 
    			grid, "Please select user and choose access level", "Share Goal Net", DialogOptions.OK_CANCEL, callback);
    	
    	if (resp == DialogResponse.OK){
    		UserGnet ug = new UserGnet();
    		// find max sequence, and append to end
    		ug.setAccessLevel(this.selectedAccessLevel);
    		this.selectedUser.addUserGnet(ug);
    		DataSession.Cache.gnet.addUserGnet(ug);
    		DataService.userGnet.persist(ug);
    		userTable.setItems(FXCollections.observableArrayList(DataSession.Cache.gnet.getUserGnets()));
    	}
    }
    
    @FXML
    void removeButtonClicked(ActionEvent event) {
    	UserGnet ug = userTable.getSelectionModel().getSelectedItem();
    	User u = ug.getUser();
    	DataService.userGnet.remove(ug);
    	u.removeUserGnet(ug);
    	DataSession.Cache.gnet.removeUserGnet(ug);
    	userTable.setItems(FXCollections.observableArrayList(DataSession.Cache.gnet.getUserGnets()));
    }
}
