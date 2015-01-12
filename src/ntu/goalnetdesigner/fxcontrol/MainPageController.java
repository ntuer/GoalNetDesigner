package ntu.goalnetdesigner.fxcontrol;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.logger.UserConsoleLogger;
import ntu.goalnetdesigner.logic.MainPageManager;
import ntu.goalnetdesigner.render.RenderedIDrawableObjectFactory;
import ntu.goalnetdesigner.render.RenderedState;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.CurrentGNetObjectSelection;
import ntu.goalnetdesigner.utility.Navigation;
import ntu.goalnetdesigner.utility.Resource;

import com.sun.corba.se.spi.orbutil.fsm.State;

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
    private MenuItem helpMenuFeedback;

    @FXML
    private Tab stateTab;

    @FXML
    private TreeView<Arc> arcTreeView;

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
    private AnchorPane drawingPane;

    @FXML
    private TreeView<Method> functionTreeView;

    @FXML
    public void initialize() {
    	// Set Logger
    	UserConsoleLogger.setOutputArea(this.eventLogField);
    	
    	// Set drawing handler
    	drawingPane.setOnMouseClicked(mouseHandler);
    	
    	MainPageManager mpm = new MainPageManager();
        TreeItem<Method> rootNode = new TreeItem<Method>();
        rootNode.setExpanded(true);
        for(Method func: mpm.getMethods()){
        	TreeItem<Method> leaf = new TreeItem<Method>(func);
        	rootNode.getChildren().add(leaf);
        }
        
        functionTreeView.setRoot(rootNode);
        functionTreeView.showRootProperty().set(false);
    }
    
    private EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent me) 
    	{
    		try {
    		RenderedState state = (RenderedState) 
    				RenderedIDrawableObjectFactory.getRenderedObject(State.class, propertyTable, drawingPane);
    		state.getShape().setCenterX(me.getX());
    		state.getShape().setCenterY(me.getY());
    		state.getShape().setRadius(50.0f);
    		state.getShape().setFill(Color.RED);
	    	drawingPane.getChildren().addAll(state.getShape());
    		} catch (Exception e) {
    			
    		}
    	}
    };
    
    
    @FXML
    void fileMenuNewClicked(ActionEvent event) throws Exception{
    	Navigation.popUp(Resource.NEW_GNET_PATH, UISession.primaryStage);
    }

    @FXML
    void fileMenuOpenClicked(ActionEvent event) throws Exception{
    	Navigation.popUp(Resource.OPEN_GNET_PATH, UISession.primaryStage);
    }

    @FXML
    void fileMenuSaveClicked(ActionEvent event) {

    }

    @FXML
    void fileMenuNSaveAsClicked(ActionEvent event) {

    }

    @FXML
    void fileMenuCloseClicked(ActionEvent event) {
        
    }

    @FXML
    void fileMenuPrintClicked(ActionEvent event) {

    }

    @FXML
    void fileMenuExitClicked(ActionEvent event) {
    	UISession.primaryStage.close();
    }

    @FXML
    void editMenuUndoClicked(ActionEvent event) {

    }

    @FXML
    void editMenuDeleteClicked(ActionEvent event) {

    }

    @FXML
    void editMenuPropertyClicked(ActionEvent event) {

    }

    @FXML
    void runMenuVerifyClicked(ActionEvent event) {

    }

    @FXML
    void runMenuRunClicked(ActionEvent event) {

    }

    @FXML
    void sessionMenuCurrentUserClicked(ActionEvent event) {

    }

    @FXML
    void sessionMenuLogOutClicked(ActionEvent event) throws Exception{
    	Navigation.switchTo(Resource.LOGIN_PATH, UISession.primaryStage);
    	LoginSession.isLoggedIn = false;
    }

    @FXML
    void helpMenuAboutClicked(ActionEvent event) throws Exception{
    	Navigation.popUp(Resource.ABOUT_PATH, UISession.primaryStage);
    }

    @FXML
    void helpMenuFeedbackClicked(ActionEvent event) throws Exception{
    	Navigation.popUp(Resource.FEEDBACK_PATH, UISession.primaryStage);
    }

    @FXML
    void simpleStateButtonClicked(ActionEvent event) {
    	DataSession.currentGNetObjectSelection = CurrentGNetObjectSelection.STATE;
    }

    @FXML
    void compositeStateButtonClicked(ActionEvent event) {
    	DataSession.currentGNetObjectSelection = CurrentGNetObjectSelection.COMPOSITE_STATE;
    }

    @FXML
    void transitionButtonClicked(ActionEvent event) {
    	DataSession.currentGNetObjectSelection = CurrentGNetObjectSelection.TRANSITION;
    }

    @FXML
    void arcButtonClicked(ActionEvent event) {
    	DataSession.currentGNetObjectSelection = CurrentGNetObjectSelection.ARC;
    }

    @FXML
    void moveButtonClicked(ActionEvent event) {
    	DataSession.currentGNetObjectSelection = CurrentGNetObjectSelection.MOVE;
    }
    
}
