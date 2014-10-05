package ntu.goalnetdesigner.fxcontrol;


import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class MainPageController {

    @FXML
    private MenuItem editMenuProperty;

    @FXML
    private MenuItem runMenuVerify;

    @FXML
    private MenuItem fileMenuOpen;

    @FXML
    private MenuItem fileMenuNSaveAs;

    @FXML
    private TextArea eventLogField;

    @FXML
    private TextArea outputField;

    @FXML
    private Tab eventLogTab;

    @FXML
    private Tab taskTab;

    @FXML
    private TreeView<?> stateTreeView;

    @FXML
    private MenuItem fileMenuPrint;

    @FXML
    private MenuItem fileMenuClose;

    @FXML
    private MenuItem helpMenuContactAuthor;

    @FXML
    private Tab stateTab;

    @FXML
    private TreeView<String> arcTreeView;

    @FXML
    private CheckMenuItem runMenuDisplayWarning;

    @FXML
    private Tab arcTab;

    @FXML
    private Tab transitionTab;

    @FXML
    private TableColumn<?, ?> propertyTableValueColumn;

    @FXML
    private MenuItem editMenuDelete;

    @FXML
    private MenuItem helpMenuAbout;

    @FXML
    private MenuItem fileMenuExit;

    @FXML
    private MenuItem sessionMenuCurrentUser;

    @FXML
    private TableView<?> propertyTable;

    @FXML
    private MenuItem editMenuUndo;

    @FXML
    private Tab functionTab;

    @FXML
    private TreeView<?> transitionTreeView;

    @FXML
    private Tab propertyTab;

    @FXML
    private TableColumn<?, ?> propertyTablePropertyColumn;

    @FXML
    private TreeView<?> taskTreeView;

    @FXML
    private MenuItem fileMenuNew;

    @FXML
    private MenuItem fileMenuSave;

    @FXML
    private Tab linkTab;

    @FXML
    private Tab outputTab;

    @FXML
    private MenuItem runMenuRun;

    @FXML
    private MenuItem sessionMenuLogOut;

    @FXML
    private TreeView<?> functionTreeView;

    @FXML
    void initialize() {
        TreeItem<String> rootItem = new TreeItem<String> ("Inbox");
        rootItem.setExpanded(true);
        for (int i = 1; i < 60; i++) {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
            rootItem.getChildren().add(item);
        }
        arcTreeView.setRoot(rootItem);
        outputField.setText("New Text");
    }
}
