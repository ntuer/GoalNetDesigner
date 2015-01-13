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
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.logger.UserConsoleLogger;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderedIDrawableObjectFactory;
import ntu.goalnetdesigner.render.RenderedState;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.CurrentGNetObjectSelection;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;
import ntu.goalnetdesigner.utility.UIUtility.Navigation;



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
    private TreeView<State> stateTreeView;

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
    private TreeView<Transition> transitionTreeView;

    @FXML
    private Tab propertyTab;

    @FXML
    private TableColumn<?, ?> propertyTablePropertyColumn;

    @FXML
    private TreeView<Task> taskTreeView;

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
    }
    
    private void refreshTreeViewsAndDrawingPane(){
    	// Set drawing handler
    	if(DataSession.Cache.gnet == null)
    		drawingPane.setOnMouseClicked(null);
    	else
    		drawingPane.setOnMouseClicked(drawingHandler);
    	// set gnet related properties
    	arcTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.arcs));
    	arcTreeView.showRootProperty().set(false);
        stateTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.states));
        stateTreeView.showRootProperty().set(false);
        transitionTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.transitions));
        transitionTreeView.showRootProperty().set(false);
    	
        // set global properties visible
    	functionTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.functions));
        functionTreeView.showRootProperty().set(false);
        taskTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.tasks));
        taskTreeView.showRootProperty().set(false);
    	
        UserConsoleLogger.log("TreeViews and Drawing Pane refreshed");
    }

    
    private EventHandler<MouseEvent> drawingHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent me) 
    	{
    		try {
    			UserConsoleLogger.log("User Click on " + me.getX() + "," + me.getY());
    			// generate an object in factory according to current selection
    			Renderable object = RenderedIDrawableObjectFactory.getRenderedObject(
    					me.getX(), me.getY(), propertyTable, drawingPane);
    			// display it on screen
    			drawingPane.getChildren().addAll(object.getDisplay());
    		} catch (Exception e) {
    			UserConsoleLogger.log("Error when creating RenderableObject " 
    					+ DataSession.currentGNetObjectSelection.toString());
    		}
    	}
    };
    
    
    @FXML
    void fileMenuNewClicked(ActionEvent event) throws Exception{
    	UIUtility.Navigation.popUp(Resource.NEW_GNET_PATH, UISession.primaryStage);
    	if (DataSession.Cache.gnet != null)
    		this.refreshTreeViewsAndDrawingPane();
    }

    @FXML
    void fileMenuOpenClicked(ActionEvent event) throws Exception{
    	UIUtility.Navigation.popUp(Resource.OPEN_GNET_PATH, UISession.primaryStage);
    	if (DataSession.Cache.gnet != null)
    		this.refreshTreeViewsAndDrawingPane();
    	}

    @FXML
    void fileMenuSaveClicked(ActionEvent event) {
    	// save to database
    }

    @FXML
    void fileMenuNSaveAsClicked(ActionEvent event) {
    	// save as to database
    }

    @FXML
    void fileMenuCloseClicked(ActionEvent event) {
        DataSession.Cache.setGNet(null);
        this.refreshTreeViewsAndDrawingPane();
    }

    @FXML
    void fileMenuPrintClicked(ActionEvent event) {
    	// print
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