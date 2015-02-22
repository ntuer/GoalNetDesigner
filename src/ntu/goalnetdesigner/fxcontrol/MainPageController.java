package ntu.goalnetdesigner.fxcontrol;

import java.io.File;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.logic.FunctionManager;
import ntu.goalnetdesigner.logic.RenderManager;
import ntu.goalnetdesigner.logic.SaveManager;
import ntu.goalnetdesigner.logic.TaskManager;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.CurrentDrawingMode;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;
import ntu.goalnetdesigner.utility.UIUtility.Navigation;


public class MainPageController {    
	@FXML
    private MenuItem fileMenuOpenLocal;
	
    @FXML
    private MenuItem fileMenuSaveAsLocal;
    
    @FXML
    private ScrollPane propertyPane;
	
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
    private MenuItem editMenuDelete;

    @FXML
    private MenuItem helpMenuAbout;

    @FXML
    private MenuItem fileMenuExit;

    @FXML
    private MenuItem sessionMenuCurrentUser;

    @FXML
    private MenuItem editMenuUndo;

    @FXML
    private Tab functionTab;

    @FXML
    private TreeView<Transition> transitionTreeView;

    @FXML
    private Tab propertyTab;

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
    	ConsoleLogger.setOutputArea(this.eventLogField);
    }
    
    // It refreshes current view for a given GNet stored in cache.
    private void refreshTreeViewsAndDrawingPane(){
    	// Set drawing handler
    	if(DataSession.Cache.gnet == null){
    		drawingPane.setOnMouseClicked(null);
    	}
    	else {
    		drawingPane.setOnMouseClicked(drawingNewObjectHandler);
    	}
    	// set gnet related properties
    	refreshArcTreeView();
        refreshStateTreeView();
        refreshTransitionTreeView();
        // set global properties visible
    	refreshFunctionTreeView();
        refreshTaskTreeView();
        setTreeViewChangeHandler();
        UISession.isTreeViewRefreshing = false;
        ConsoleLogger.log("TreeViews and Drawing Pane refreshed");
        RenderManager rm = new RenderManager(drawingPane, propertyPane);
        rm.renderGNet(DataSession.Cache.gnet);
        ConsoleLogger.log("Successfully rendered existing objects");
    }
    
    // Treeview selection change handler
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void setTreeViewChangeHandler(){
    	arcTreeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				if (UISession.isTreeViewRefreshing)
			    	return;
			    UISession.setCurrentSelection(((TreeItem<Arc>) newValue).getValue());
			    try {
					propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.ARC_PROPERTY_PANE_PATH));
				} catch (IOException e) {
					e.printStackTrace();
				}
			    UISession.currentPaneController.refresh();
			}
    	 });
    	stateTreeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	if (UISession.isTreeViewRefreshing)
	            	return;
 	        	
 	            UISession.setCurrentSelection(((TreeItem<State>) newValue).getValue());
 	            try {
						propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.STATE_PROPERTY_PANE_PATH));
					} catch (IOException e) {
						e.printStackTrace();
					}
 	            UISession.currentPaneController.refresh();
 	        }
    	});
    	transitionTreeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	            
 	        	if (UISession.isTreeViewRefreshing)
	            	return;
 	        	
 	        	UISession.setCurrentSelection(((TreeItem<Transition>) newValue).getValue());
 	            try {
						propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.TRANSITION_PROPERTY_PANE_PATH));
					} catch (IOException e) {
						e.printStackTrace();
					}
 	            UISession.currentPaneController.refresh();
 	        }
 	    });
    	
    	// non-renderable objects selection.
    	// note: use direct assignment for currentSelection
    	functionTreeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	            
 	        	if (UISession.isTreeViewRefreshing)
	            	return;
 	        	
 	        	UISession.setCurrentSelection(((TreeItem<Method>) newValue).getValue());
 	            try {
						propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.FUNCTION_PROPERTY_PANE_PATH));
					} catch (IOException e) {
						e.printStackTrace();
					}
 	            UISession.currentPaneController.refresh();
 	        }
 	    });
    	
    	taskTreeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	            
 	        	if (UISession.isTreeViewRefreshing)
	            	return;
 	        	
 	        	UISession.setCurrentSelection(((TreeItem<Task>) newValue).getValue());
 	            try {
						propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.TASK_PROPERTY_PANE_PATH));
					} catch (IOException e) {
						e.printStackTrace();
					}
 	            UISession.currentPaneController.refresh();
 	        }
 	    });
    }
    
    // Treeview refresh handlers
	public void refreshTaskTreeView() {
        UISession.isTreeViewRefreshing = true;
		taskTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.tasks));
        taskTreeView.showRootProperty().set(false);
        UISession.isTreeViewRefreshing = false;
	}

	public void refreshFunctionTreeView() {
        UISession.isTreeViewRefreshing = true;
		functionTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.functions));
        functionTreeView.showRootProperty().set(false);
        UISession.isTreeViewRefreshing = false;
	}

	public void refreshTransitionTreeView() {
        UISession.isTreeViewRefreshing = true;
		transitionTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.transitions));
        transitionTreeView.showRootProperty().set(false);
        UISession.isTreeViewRefreshing = false;
	}

	public void refreshStateTreeView() {
        UISession.isTreeViewRefreshing = true;
		stateTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.states));
        stateTreeView.showRootProperty().set(false);
        UISession.isTreeViewRefreshing = false;
	}

	public void refreshArcTreeView() {
        UISession.isTreeViewRefreshing = true;
		arcTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.arcs));
    	arcTreeView.showRootProperty().set(false);
    	UISession.isTreeViewRefreshing = false;
	}

	// Drawing handler
	// This happens after onclick event on individual Renderable object
    public EventHandler<MouseEvent> drawingNewObjectHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent me) 
    	{
    		// Prevent additional object while clicking on existing object
    		if (UISession.isInRenderedObject){
    			UISession.isInRenderedObject = false;
    			// if this is a drawing arc state
    			if (DataSession.currentDrawingMode == CurrentDrawingMode.ARC &&
    					UISession.objectsForArc.size() == 2){
    				ConsoleLogger.log("Draw an arc");
    		    	RenderManager rm = new RenderManager(drawingPane, propertyPane);
    		    	Renderable s = UISession.objectsForArc.poll();
    		    	Renderable e = UISession.objectsForArc.poll();
    		    	RenderedArc a = rm.drawNewArcByStartAndEnd(s, e);
    				if (a!= null){
	    				drawingPane.getChildren().addAll(a.getShape());
	    				drawingPane.getChildren().addAll(a.getShape().getArrow());
    				}
    			}
    			return;
    		}
    		// Drag state or transition
			ConsoleLogger.log("User Click on " + me.getX() + "," + me.getY());
			RenderManager rm = new RenderManager(drawingPane, propertyPane);
			Renderable object = rm.drawNewStateOrTransition(me.getX(), me.getY());
			if (object != null)
				drawingPane.getChildren().addAll(object.getDisplay());
    	}
    };
    
    @FXML
    void newFunctionButtonOnClick(ActionEvent event) {
    	FunctionManager.newInstance();
    }
    
    @FXML
    void newTaskButtonOnClick(ActionEvent event) {
    	TaskManager.newInstance();
    }
    
    // Menu click handlers
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
    	SaveManager sm = new SaveManager();
    	sm.saveToDatabase(DataSession.Cache.gnet);
    }

    @FXML
    void fileMenuNSaveAsClicked(ActionEvent event) {
    	// save as to database
    }

    @FXML
    void fileMenuOpenLocalClicked(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	File file = fileChooser.showOpenDialog(UISession.primaryStage);
    	if (file != null) {
	    	SaveManager sm = new SaveManager();
	    	DataSession.Cache.setGNet(sm.openLocally(file.getPath()));
	    	if (DataSession.Cache.gnet != null)
	    		this.refreshTreeViewsAndDrawingPane();
    	}
    }

    @FXML
    void fileMenuSaveAsLocalClicked(ActionEvent event) {
    	String input = Dialogs.showInputDialog(UISession.primaryStage, "Enter file name:", "Save Local", "Save Local");
    	if (input != null){
	    	SaveManager sm = new SaveManager();
	    	sm.saveLocally(input, DataSession.Cache.gnet);
    	}
    }
    @FXML
    void fileMenuCloseClicked(ActionEvent event) {
    	DialogResponse response = Dialogs.showConfirmDialog(UISession.primaryStage, "Do you want to close current dialog?", 
    	        "Close current Goal Net", "Close", DialogOptions.OK_CANCEL);
    	if (response == DialogResponse.OK){
	        DataSession.Cache.setGNet(null);
	        this.refreshTreeViewsAndDrawingPane();
    	}
    }

    @FXML
    void fileMenuPrintClicked(ActionEvent event) {
    	// print
    }

    @FXML
    void fileMenuExitClicked(ActionEvent event) {
    	DataService.rollback();
    	//UISession.primaryStage.close();
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
    	Dialogs.showInformationDialog(UISession.primaryStage, "Server Address: " + LoginSession.serverAddress, 
    		    "Current User ID: " + LoginSession.user.getId(), "Current User Information");
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

    
    // Drawing state selection. Done.
    @FXML
    void simpleStateButtonClicked(ActionEvent event) {
    	DataSession.currentDrawingMode = CurrentDrawingMode.STATE;
    }

    @FXML
    void compositeStateButtonClicked(ActionEvent event) {
    	DataSession.currentDrawingMode = CurrentDrawingMode.COMPOSITE_STATE;
    }

    @FXML
    void transitionButtonClicked(ActionEvent event) {
    	DataSession.currentDrawingMode = CurrentDrawingMode.TRANSITION;
    }

    @FXML
    void arcButtonClicked(ActionEvent event) {
    	DataSession.currentDrawingMode = CurrentDrawingMode.ARC;
    }
    
}