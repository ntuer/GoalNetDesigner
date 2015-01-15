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
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.logic.SaveManager;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.render.RenderedIDrawableObjectFactory;
import ntu.goalnetdesigner.render.RenderedState;
import ntu.goalnetdesigner.render.RenderedTransition;
import ntu.goalnetdesigner.session.DataSession;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.CurrentGNetObjectSelection;
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
        UISession.isTreeVieewRefreshing = false;
        ConsoleLogger.log("TreeViews and Drawing Pane refreshed");
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void setTreeViewChangeHandler(){
    	 arcTreeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

    	        @Override
    	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {

    	            if (UISession.isTreeVieewRefreshing)
    	            	return;
    	            
    	            UISession.currentSelection = ((TreeItem<Arc>) newValue).getValue();
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
 	        	if (UISession.isTreeVieewRefreshing)
	            	return;
 	        	
 	            UISession.currentSelection = ((TreeItem<State>) newValue).getValue();
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
 	            
 	        	if (UISession.isTreeVieewRefreshing)
	            	return;
 	        	
 	        	UISession.currentSelection = ((TreeItem<Transition>) newValue).getValue();
 	            try {
						propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.TRANSITION_PROPERTY_PANE_PATH));
					} catch (IOException e) {
						e.printStackTrace();
					}
 	            UISession.currentPaneController.refresh();
 	        }

 	      });
    }
    
    
	public void refreshTaskTreeView() {
        UISession.isTreeVieewRefreshing = true;
		taskTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.tasks));
        taskTreeView.showRootProperty().set(false);
        UISession.isTreeVieewRefreshing = false;
	}

	public void refreshFunctionTreeView() {
        UISession.isTreeVieewRefreshing = true;
		functionTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.functions));
        functionTreeView.showRootProperty().set(false);
        UISession.isTreeVieewRefreshing = false;
	}

	public void refreshTransitionTreeView() {
        UISession.isTreeVieewRefreshing = true;
		transitionTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.transitions));
        transitionTreeView.showRootProperty().set(false);
        UISession.isTreeVieewRefreshing = false;
	}

	public void refreshStateTreeView() {
        UISession.isTreeVieewRefreshing = true;
		stateTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.states));
        stateTreeView.showRootProperty().set(false);
        UISession.isTreeVieewRefreshing = false;
	}

	public void refreshArcTreeView() {
        UISession.isTreeVieewRefreshing = true;
		arcTreeView.setRoot(UIUtility.TreeView.convertToTreeItem(DataSession.Cache.arcs));
    	arcTreeView.showRootProperty().set(false);
    	UISession.isTreeVieewRefreshing = false;
	}

    
    public EventHandler<MouseEvent> drawingNewObjectHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent me) 
    	{
    		// Prevent additional object while clicking on existing object
    		if (UISession.isInRenderedObject){
    			
    			UISession.isInRenderedObject = false;
    			
    			// if this is a drawing arc state
    			if (DataSession.currentGNetObjectSelection == CurrentGNetObjectSelection.ARC &&
    					UISession.objectsForArc.size() == 2){
    				RenderedArc a = drawArc();
    				if (a!= null){
	    				drawingPane.getChildren().addAll(a.getShape());
	    				drawingPane.getChildren().addAll(a.getShape().getArrow());
    				}
    			}
    			
    			return;
    		}
    		try {
    			ConsoleLogger.log("User Click on " + me.getX() + "," + me.getY());
    			// generate an object in factory according to current selection
    			Renderable object = RenderedIDrawableObjectFactory.getRenderedObject(
    					me.getX(), me.getY(), propertyPane, drawingPane);
    			// display it on screen
    			drawingPane.getChildren().addAll(object.getDisplay());
    		} catch (Exception e) {
    			ConsoleLogger.log("Error when creating RenderableObject " 
    					+ DataSession.currentGNetObjectSelection.toString());
    		}
    	}
    };
    
    private RenderedArc drawArc(){
    	ConsoleLogger.log("ARC DRAW");
    	Renderable s = UISession.objectsForArc.poll();
    	Renderable e = UISession.objectsForArc.poll();
    	RenderedArc a = null;
    	if (s instanceof RenderedState){
    		if (!(e instanceof RenderedTransition)){
    			UISession.objectsForArc.clear();
    			return a;
    		}
    		try {
				a = RenderedIDrawableObjectFactory.getRenderedObject(
						((RenderedState) s).getDisplay().getTranslateX(), ((RenderedState) s).getDisplay().getTranslateY(),
						((RenderedTransition) e).getDisplay().getTranslateX(), ((RenderedTransition) e).getDisplay().getTranslateY(),
						propertyPane, drawingPane);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		a.getBaseObject().setDirection(true);
    		a.getBaseObject().setInputID(((RenderedState) s).getBaseObject().getId());
    		a.getBaseObject().setOutputID(((RenderedTransition) e).getBaseObject().getId());
    		
    	} else if (s instanceof RenderedTransition){
    		if (!(e instanceof RenderedState)){
    			UISession.objectsForArc.clear();
    			return a;
    		}
    		try {
				a = RenderedIDrawableObjectFactory.getRenderedObject(
						((RenderedTransition) s).getDisplay().getTranslateX(), ((RenderedTransition) s).getDisplay().getTranslateY(),
						((RenderedState) e).getDisplay().getTranslateX(), ((RenderedState) e).getDisplay().getTranslateY(),
						propertyPane, drawingPane);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		a.getBaseObject().setDirection(false);
    		a.getBaseObject().setInputID(((RenderedTransition) s).getBaseObject().getId());
    		a.getBaseObject().setOutputID(((RenderedState) e).getBaseObject().getId());
    	} 
    	
    	s.getAssociatedRenderedArcs().add(a);
    	e.getAssociatedRenderedArcs().add(a);
    	a.getBaseObject().setGnet(DataSession.Cache.gnet);
    	a.getBaseObject().setIsDirect(true);
    	
    	UISession.objectsForArc.clear();
    	return a;
    }
    
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
    	SaveManager sm = new SaveManager();
    	sm.saveLocally(input, DataSession.Cache.gnet);
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