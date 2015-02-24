package ntu.goalnetdesigner.fxcontrol;

import java.io.File;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.logger.StatusBarLogger;
import ntu.goalnetdesigner.logic.FunctionManager;
import ntu.goalnetdesigner.logic.RenderManager;
import ntu.goalnetdesigner.logic.SaveManager;
import ntu.goalnetdesigner.logic.TaskManager;
import ntu.goalnetdesigner.render.Drawable;
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
    private MenuItem editMenuGoalNetProperty;

    @FXML
    private MenuItem runMenuVerify;

    @FXML
    private MenuItem fileMenuOpen;

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
    private MenuItem editMenuClearObjectForArcs;

    @FXML
    private MenuItem helpMenuAbout;

    @FXML
    private MenuItem fileMenuExit;

    @FXML
    private MenuItem userMenuCurrentUser;

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
    private Tab teamTab;

    @FXML
    private Tab outputTab;

    @FXML
    private MenuItem runMenuRun;

    @FXML
    private MenuItem userMenuLogOut;

    @FXML
    private AnchorPane drawingPane;

    @FXML
    private TreeView<Method> functionTreeView;

    @FXML
    private Label statusLabel;
    
    @FXML
    public void initialize() {
    	// Set Logger
    	ConsoleLogger.setOutputArea(this.eventLogField);
    	StatusBarLogger.setStatusLabel(statusLabel);
    	UIUtility.Draw.renderManager = new RenderManager(this.drawingPane, this.propertyPane);
    	SetShortcutKeys();
    }
    
    private void SetShortcutKeys() {
    	this.fileMenuNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
    	this.fileMenuOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
    	this.fileMenuSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
    	this.fileMenuOpenLocal.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
    	this.fileMenuSaveAsLocal.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
    	this.fileMenuExit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
    	this.editMenuDelete.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
    	this.editMenuClearObjectForArcs.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));
    	this.editMenuUndo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
    	this.runMenuRun.setAccelerator(new KeyCodeCombination(KeyCode.F10, KeyCombination.CONTROL_DOWN));
    	this.runMenuVerify.setAccelerator(new KeyCodeCombination(KeyCode.F9, KeyCombination.CONTROL_DOWN));
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
        UIUtility.Draw.renderManager.renderGNet(DataSession.Cache.gnet);
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
    			if (DataSession.currentDrawingMode == CurrentDrawingMode.ARC){
    				if (UISession.objectsForArc.size() == 2) {
	    				ConsoleLogger.log("Draw an arc");
	    		    	Renderable s = UISession.objectsForArc.poll();
	    		    	Renderable e = UISession.objectsForArc.poll();
	    		    	RenderedArc a = UIUtility.Draw.renderManager.drawNewArcByStartAndEnd(s, e);
	    				if (a!= null){
		    				drawingPane.getChildren().addAll(a.getShape());
		    				drawingPane.getChildren().addAll(a.getShape().getArrow());
	    				}
	    				StatusBarLogger.clear();
    				} else if (UISession.objectsForArc.size() == 1){
    					StatusBarLogger.log("(ESC to cancel) Current selection of start of arc: " + UISession.getDrawableFromCurrentSelection());
    				}
    			}
    			return;
    		}
    		// Drag state or transition
			ConsoleLogger.log("User Click on " + me.getX() + "," + me.getY());
			Renderable object = UIUtility.Draw.renderManager.drawNewStateOrTransition(me.getX(), me.getY());
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
    	DialogResponse response = Dialogs.showConfirmDialog(UISession.primaryStage, 
    		    "Click Yes to save, No to discard.", "Do you want to save your latest changes since last save?", "Exit");
    	if (response == DialogResponse.YES){
    		DataService.commit();
    	} else if (response == DialogResponse.NO){
    		DataService.rollback();
    	}
    	if (response != DialogResponse.CANCEL){
	        DataSession.Cache.setGNet(null);
	        this.refreshTreeViewsAndDrawingPane();
    	}
    }

    @FXML
    void fileMenuPrintClicked(ActionEvent event) {
    	
    }

    @FXML
    void fileMenuExitClicked(ActionEvent event) {
    	DialogResponse response = Dialogs.showConfirmDialog(UISession.primaryStage, 
    		    "Click Yes to save, No to discard", "Do you want to save your latest changes since last save?", "Exit");
    	if (response == DialogResponse.YES){
    		DataService.commit();
    	} else if (response == DialogResponse.NO){
    		DataService.rollback();
    	}
		UISession.primaryStage.close();
    }

    @FXML
    void editMenuUndoClicked(ActionEvent event) {

    }

    @FXML
    void editMenuDeleteClicked(ActionEvent event) {
    	// TODO
    	// Delete rendered object
    	// Delete base object
    	if (UISession.currentSelection != null){
    		if (UISession.currentSelection instanceof Drawable){
    			
    		} else if (UISession.currentSelection instanceof Renderable){
    			
    		}
    			
    	}
    	// clear property pane
    	UISession.currentPaneController = null;
    	propertyPane.setContent(null);
    }

    @FXML
    void editMenuClearObjectForArcsClicked(ActionEvent event) {
    	UISession.objectsForArc.clear();
    	StatusBarLogger.clear();
    }
    
    @FXML
    void editMenuGoalNetPropertyClicked(ActionEvent event) throws Exception {
    	Navigation.popUp(Resource.EDIT_GNET_PROPERTY_PATH, UISession.primaryStage);
    }
    
    @FXML
    void runMenuVerifyClicked(ActionEvent event) {

    }

    @FXML
    void runMenuRunClicked(ActionEvent event) {
    	
    }
    
    @FXML
    void teamMenuUserGroupClicked(ActionEvent event) {

    }

    @FXML
    void teamMenuGNetVisibilityClicked(ActionEvent event) {
    	
    }
    
    @FXML
    void userMenuCurrentUserClicked(ActionEvent event) {
    	Dialogs.showInformationDialog(UISession.primaryStage, "Server Address: " + LoginSession.serverAddress, 
    		    "Current User ID: " + LoginSession.user.getId(), "Current User Information");
    }

    @FXML
    void userMenuLogOutClicked(ActionEvent event) throws Exception{
    	if (DataSession.Cache.gnet != null){
    		DialogResponse response = Dialogs.showConfirmDialog(UISession.primaryStage, 
        		    "Click Yes to save, No to discard", "Do you want to save your latest changes since last save?", "Log out");
        	if (response == DialogResponse.YES){
        		DataService.commit();
        	} else if (response == DialogResponse.NO){
        		DataService.rollback();
        	}
    	}
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
    	StatusBarLogger.log("Click to draw simple state");
    	DataSession.currentDrawingMode = CurrentDrawingMode.STATE;
    	UISession.objectsForArc.clear();
    }

    @FXML
    void compositeStateButtonClicked(ActionEvent event) {
    	StatusBarLogger.log("Click to draw composite state");
    	DataSession.currentDrawingMode = CurrentDrawingMode.COMPOSITE_STATE;
    	UISession.objectsForArc.clear();
    }

    @FXML
    void transitionButtonClicked(ActionEvent event) {
    	StatusBarLogger.log("Click to draw transition");
    	DataSession.currentDrawingMode = CurrentDrawingMode.TRANSITION;
    	UISession.objectsForArc.clear();
    }

    @FXML
    void arcButtonClicked(ActionEvent event) {
    	StatusBarLogger.log("Click to draw arc");
    	DataSession.currentDrawingMode = CurrentDrawingMode.ARC;
    }
    
}