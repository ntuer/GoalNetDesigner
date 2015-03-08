package ntu.goalnetdesigner.fxcontrol;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;

import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.logger.ConsoleLogger;
import ntu.goalnetdesigner.logger.StatusBarLogger;
import ntu.goalnetdesigner.logic.AuthorizationManager;
import ntu.goalnetdesigner.logic.FunctionManager;
import ntu.goalnetdesigner.logic.RenderManager;
import ntu.goalnetdesigner.logic.SaveManager;
import ntu.goalnetdesigner.logic.TaskManager;
import ntu.goalnetdesigner.logic.ValidationManager;
import ntu.goalnetdesigner.render.Drawable;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.render.customcontrol.RubberBandSelection;
import ntu.goalnetdesigner.run.ObjectStringPair;
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
    private MenuItem fileMenuExport;

    @FXML
    private MenuItem fileMenuClose;

    @FXML
    private MenuItem helpMenuFeedback;

    @FXML
    private Tab stateTab;

    @FXML
    private TreeView<Arc> arcTreeView;
    
    @FXML
    private TreeView<String> teamTreeView;

	@FXML
	private CheckMenuItem runMenuDisplayWarning;

	@FXML
	private CheckMenuItem viewMenuArc;
	
	@FXML
	private CheckMenuItem viewMenuFunction;
	
	@FXML
	private CheckMenuItem viewMenuState;
	
	@FXML
	private CheckMenuItem viewMenuTask;
	
	@FXML
	private CheckMenuItem viewMenuTransition;
	
	@FXML
	private CheckMenuItem viewMenuTeam;
	
	@FXML
	private CheckMenuItem viewMenuProblem;
	
	@FXML
	private CheckMenuItem viewMenuOutput;
	
	@FXML
	private CheckMenuItem viewMenuEventLog;
	
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
    private TabPane lowerRightPane;
    
    @FXML
    private TabPane upperRightPane;
    
    @FXML
    private TabPane lowerLeftPane;
    
    @FXML
    private ToggleGroup drawingStateToggleGroup;
    
    @FXML
    private Tab problemTab;
    
    @FXML
    private TableView<ObjectStringPair> problemTableView;
    
    @FXML
    private TableColumn<ObjectStringPair, String> objectColumn;
    
    @FXML
    private TableColumn<ObjectStringPair, String> messageColumn;
    
    // One group selection model
    private RubberBandSelection groupObjectSelector;
    
    @FXML
    public void initialize() {
    	// Set Logger
    	ConsoleLogger.setOutputArea(this.eventLogField);
    	StatusBarLogger.setStatusLabel(statusLabel);
    	UIUtility.Draw.renderManager = new RenderManager(this.drawingPane, this.propertyPane);
    	setViewMenuHandlers();
    	setProblemTableSelectionHandler();
    	setCurrentDrawingModeSelectionHandlers();
    	groupObjectSelector = new RubberBandSelection(drawingPane);
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setProblemTableSelectionHandler() {
		problemTableView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
			@Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	ObjectStringPair nv = (ObjectStringPair) newValue;
 	        	if (nv.getObject() == null){
 	        		return;
 	        	}
 	            try {
 	            	if (nv.getObject() instanceof State){
 	            		UISession.setCurrentSelection((Drawable)nv.getObject());
 	            		propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.STATE_PROPERTY_PANE_PATH));
 	            	} else if (nv.getObject() instanceof Transition){
 	            		UISession.setCurrentSelection((Drawable)nv.getObject());
 	            		propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.TRANSITION_PROPERTY_PANE_PATH));
 	            	} else if (nv.getObject() instanceof Task){
 	            		UISession.setCurrentSelection(nv.getObject());
 	            		propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.TASK_PROPERTY_PANE_PATH));
 	            	} else if (nv.getObject() instanceof Method){
 	            		UISession.setCurrentSelection(nv.getObject());
 	            		propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.FUNCTION_PROPERTY_PANE_PATH));
 	            	}
 	            } catch (IOException e) {
					e.printStackTrace();
				}
 	            UISession.currentPaneController.refresh();
 	        }
 	    });
	}

	private void setCurrentDrawingModeSelectionHandlers() {
		drawingStateToggleGroup.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle oldValue, Toggle newValue) {
						groupObjectSelector
								.enableGroupSelectionEventHandler(false);
						// selection mode
						if (newValue == null) {
							UISession.currentDrawingMode = null;
							StatusBarLogger.log("Click to select");
						}
						// drawing mode
						else {
							ToggleButton selected = (ToggleButton) newValue
									.getToggleGroup().getSelectedToggle();
							if (selected.getId().equals("simpleStateButton"))
								UISession.currentDrawingMode = CurrentDrawingMode.STATE;
							else if (selected.getId().equals(
									"compositeStateButton"))
								UISession.currentDrawingMode = CurrentDrawingMode.COMPOSITE_STATE;
							else if (selected.getId()
									.equals("transitionButton"))
								UISession.currentDrawingMode = CurrentDrawingMode.TRANSITION;
							else if (selected.getId()
									.equals("reasoningButton"))
								UISession.currentDrawingMode = CurrentDrawingMode.REASONING_TRANSITION;
							else if (selected.getId().equals("arcButton"))
								UISession.currentDrawingMode = CurrentDrawingMode.ARC;
							else if (selected.getId().equals(
									"groupSelectionButton")) {
								UISession.currentDrawingMode = null;
								groupObjectSelector
										.enableGroupSelectionEventHandler(true);
							}
							try {
								StatusBarLogger.log("Click to draw "
										+ UISession.currentDrawingMode
												.toString());
							} catch (NullPointerException npe) {
								StatusBarLogger
										.log("Drag to select multiple objects");
							}
							// clear cache of some other states
							UISession.currentGroupSelection.clear();
							UISession.objectsForArc.clear();
						}
					}
				});
	}
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void setViewMenuHandlers(){
    	this.viewMenuArc.selectedProperty().addListener(new ChangeListener() {
 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	if ((boolean)newValue)
 	        		upperRightPane.getTabs().add(arcTab);
 	        	else
 	        		upperRightPane.getTabs().remove(arcTab);
 	        }
 	    });
    	this.viewMenuFunction.selectedProperty().addListener(new ChangeListener() {
 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	if ((boolean)newValue)
 	        		upperRightPane.getTabs().add(functionTab);
 	        	else
 	        		upperRightPane.getTabs().remove(functionTab);
 	        }
 	    });
    	this.viewMenuState.selectedProperty().addListener(new ChangeListener() {
 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	if ((boolean)newValue)
 	        		upperRightPane.getTabs().add(stateTab);
 	        	else
 	        		upperRightPane.getTabs().remove(stateTab);
 	        }
 	    });
    	this.viewMenuTask.selectedProperty().addListener(new ChangeListener() {
 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	if ((boolean)newValue)
 	        		upperRightPane.getTabs().add(taskTab);
 	        	else
 	        		upperRightPane.getTabs().remove(taskTab);
 	        }
 	    });
    	this.viewMenuTransition.selectedProperty().addListener(new ChangeListener() {
 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	if ((boolean)newValue)
 	        		upperRightPane.getTabs().add(transitionTab);
 	        	else
 	        		upperRightPane.getTabs().remove(transitionTab);
 	        }
 	    });
    	this.viewMenuTeam.selectedProperty().addListener(new ChangeListener() {
 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	if ((boolean)newValue)
 	        		lowerRightPane.getTabs().add(teamTab);
 	        	else
 	        		lowerRightPane.getTabs().remove(teamTab);
 	        }
 	    });
    	this.viewMenuOutput.selectedProperty().addListener(new ChangeListener() {
 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	if ((boolean)newValue)
 	        		lowerLeftPane.getTabs().add(outputTab);
 	        	else
 	        		lowerLeftPane.getTabs().remove(outputTab);
 	        }
 	    });
    	this.viewMenuEventLog.selectedProperty().addListener(new ChangeListener() {
 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	if ((boolean)newValue)
 	        		lowerLeftPane.getTabs().add(eventLogTab);
 	        	else
 	        		lowerLeftPane.getTabs().remove(eventLogTab);
 	        }
 	    });
    	this.viewMenuProblem.selectedProperty().addListener(new ChangeListener() {
 	        @Override
 	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
 	        	if ((boolean)newValue)
 	        		lowerLeftPane.getTabs().add(problemTab);
 	        	else
 	        		lowerLeftPane.getTabs().remove(problemTab);
 	        }
 	    });
    }
    
    private void setDrawingHandler(){
    	if(DataSession.Cache.gnet == null){
    		drawingPane.setOnMouseClicked(null);
    	}
    	else {
    		drawingPane.setOnMouseClicked(clickToDrawHandler);
			StatusBarLogger.log("Click to select");
    	}
		groupObjectSelector.enableGroupSelectionEventHandler(false);
		UISession.currentDrawingMode = null;
    }

	// It refreshes current view for a given GNet stored in cache.
    private void refreshTreeViewsAndDrawingPane(){
    	setDrawingHandler();
    	// set gnet related properties
    	refreshArcTreeView();
        refreshStateTreeView();
        refreshTransitionTreeView();
        // set global properties visible
    	refreshFunctionTreeView();
        refreshTaskTreeView();
        setTreeViewChangeHandler();
        refreshTeamTreeView();
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
	
	@SuppressWarnings("unchecked")
	public void refreshTeamTreeView() {
		if (DataSession.Cache.gnet == null)
			return;
		TreeItem<String> dummyRoot = new TreeItem<>();
		AuthorizationManager am = new AuthorizationManager();
		TreeItem<String> admin = UIUtility.TreeView.convertToTreeItem(am.getUsersOfGnetByAccessLevel(DataSession.Cache.gnet, Resource.UserGnetAccessLevel.ADMIN));
		admin.setValue(Resource.UserGnetAccessLevel.ADMIN);
		TreeItem<String> read = UIUtility.TreeView.convertToTreeItem(am.getUsersOfGnetByAccessLevel(DataSession.Cache.gnet, Resource.UserGnetAccessLevel.READ));
		read.setValue(Resource.UserGnetAccessLevel.READ);
		TreeItem<String> write = UIUtility.TreeView.convertToTreeItem(am.getUsersOfGnetByAccessLevel(DataSession.Cache.gnet, Resource.UserGnetAccessLevel.WRITE));
		write.setValue(Resource.UserGnetAccessLevel.WRITE);
		dummyRoot.getChildren().addAll(admin, read, write);
		teamTreeView.setRoot(dummyRoot);
    	teamTreeView.showRootProperty().set(false);
	}
	
	// Drawing handler
	// This happens after onclick event on individual Renderable object
    public EventHandler<MouseEvent> clickToDrawHandler = new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent me) 
    	{
    		// Prevent additional object while clicking on existing object
    		if (UISession.isInRenderedObject){
    			UISession.isInRenderedObject = false;
    			// if this is a drawing arc state
    			if (UISession.currentDrawingMode == CurrentDrawingMode.ARC){
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
    		// If there is no selection, don't draw anything
    		if (UISession.currentDrawingMode == null)
    			return;
    		// Drag state or transition
			ConsoleLogger.log("User Click on " + me.getX() + "," + me.getY());
			Renderable object = UIUtility.Draw.renderManager.drawNewStateOrTransition(me.getX(), me.getY());
			if (object != null)
				drawingPane.getChildren().addAll(object.getDisplay());
    	}
    };
    
    private void closeOpenedGnet(){
    	if (DataSession.Cache.gnet != null){
	    	DialogResponse response = Dialogs.showConfirmDialog(UISession.primaryStage, 
	    		    "Click Yes to save, No to discard.", "Do you want to save your latest changes since last save?", "Exit", DialogOptions.YES_NO);
	    	if (response == DialogResponse.YES){
	    		DataService.commit();
	    	} else if (response == DialogResponse.NO){
	    		DataService.rollback();
	    	}
	        DataSession.setGNetCache(null);
	        this.refreshTreeViewsAndDrawingPane();
    	}
    }
    
    @FXML
    void newFunctionButtonOnClick(ActionEvent event) {
    	FunctionManager.newInstance();
    	refreshFunctionTreeView();
    }
    
    @FXML
    void newTaskButtonOnClick(ActionEvent event) {
    	TaskManager.newInstance();
    	refreshTaskTreeView();
    }
    
    // Menu click handlers
    @FXML
    void fileMenuNewClicked(ActionEvent event) throws Exception{
    	closeOpenedGnet();
    	UIUtility.Navigation.popUp(Resource.NEW_GNET_PATH, UISession.primaryStage);
    	if (DataSession.Cache.gnet != null)
    		this.refreshTreeViewsAndDrawingPane();
    }

    @FXML
    void fileMenuOpenClicked(ActionEvent event) throws Exception{
    	closeOpenedGnet();
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
	    	DataSession.setGNetCache(sm.openLocally(file.getPath()));
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
    	closeOpenedGnet();
    }

    @FXML
    void fileMenuExportClicked(ActionEvent event) {
    	String input = Dialogs.showInputDialog(UISession.primaryStage, "Enter file name:", "Export as png", "Export");
    	if (input != null){
	    	int maxX = 0, maxY = 0;
	    	for (State s: DataSession.Cache.states){
	    		if (maxX < s.getX())
	    			maxX = s.getX();
	    		if (maxY < s.getY())
	    			maxY = s.getY();
	    	}
	    	
	    	for (Transition s: DataSession.Cache.transitions){
	    		if (maxX < s.getX())
	    			maxX = s.getX();
	    		if (maxY < s.getY())
	    			maxY = s.getY();
	    	}
	    	
	    	WritableImage wi = new WritableImage(maxX + Resource.EXPORT_PICTURE_BORDER_SIZE, 
	    			maxY + Resource.EXPORT_PICTURE_BORDER_SIZE);
	    	
	    	WritableImage image = drawingPane.snapshot(new SnapshotParameters(), wi);
	        File file = new File(input + ".png");
	
	        try {
	            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
	        } catch (IOException e) {
	            ConsoleLogger.log("Failed to export GNet. " + e.getMessage());
	        }
    	}
    }

    @FXML
    void fileMenuExitClicked(ActionEvent event) {
    	closeOpenedGnet();
		UISession.primaryStage.close();
    }

    @FXML
    void editMenuDeleteClicked(ActionEvent event) {
    	// clear property pane
    	UISession.currentPaneController = null;
    	propertyPane.setContent(null);
    	if (UISession.currentGroupSelection.size() > 0){
    		for(Object obj : UISession.currentGroupSelection.getSelection()){
    			UIUtility.Draw.renderManager.delete(obj);
    		}
    	} else {
    		UIUtility.Draw.renderManager.delete(UISession.currentSelection);
    	}
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
    	this.outputField.appendText("===================== Validation Started =====================\n");
    	this.outputField.appendText("Validating Goal Net: " + DataSession.Cache.gnet.getName() + "\n");
    	this.outputField.appendText("Start Time: " + new Date() + "\n");
    	
    	ValidationManager vm = new ValidationManager();
    	vm.validate();
    	this.outputField.appendText(vm.outputErrors());
    	if (this.runMenuDisplayWarning.isSelected()){
    		this.outputField.appendText(vm.outputWarnings());
    	}
    	this.outputField.appendText(vm.getErrors().size() + " Error" + ((vm.getErrors().size() > 1) ? "s, " : ", "));
    	this.outputField.appendText(vm.getWarnings().size() + " Warning" + ((vm.getWarnings().size() > 1) ? "s. " : ". "));
    	this.outputField.appendText("End Time: " + new Date() + "\n");
    	
    	ObservableList<ObjectStringPair> list = FXCollections.observableArrayList(vm.getErrors());
    	if (this.runMenuDisplayWarning.isSelected()){
    		list.addAll(vm.getWarnings());
    	}
    	this.problemTableView.setItems(list);
    	this.objectColumn.setCellValueFactory(new PropertyValueFactory<ObjectStringPair, String>("object"));
    	this.messageColumn.setCellValueFactory(new PropertyValueFactory<ObjectStringPair, String>("string"));
    }

    @FXML
    void runMenuRunClicked(ActionEvent event) {
    	
    }
    
    @FXML
    void userShareGoalNetClicked(ActionEvent event) throws Exception {
    	Navigation.popUp(Resource.SHARE_GNET_PATH, UISession.primaryStage);
    }

    @FXML
    void userShareFunctionClicked(ActionEvent event) throws Exception {
    	Navigation.popUp(Resource.SHARE_FUNCTION_PATH, UISession.primaryStage);
    }
    
    @FXML
    void userShareTaskClicked(ActionEvent event) throws Exception {
    	Navigation.popUp(Resource.SHARE_TASK_PATH, UISession.primaryStage);
    }
    
    @FXML
    void userMenuCurrentUserClicked(ActionEvent event) {
    	Dialogs.showInformationDialog(UISession.primaryStage, "Server Address: " + LoginSession.serverAddress, 
    		    "Current User ID: " + LoginSession.user.getId(), "Current User Information");
    }

    @FXML
    void userMenuLogOutClicked(ActionEvent event) throws Exception{
    	closeOpenedGnet();
    	Navigation.switchTo(Resource.LOGIN_PATH, UISession.primaryStage);
    	LoginSession.isLoggedIn = false;
    	LoginSession.user = null;
    }

    @FXML
    void helpMenuAboutClicked(ActionEvent event) throws Exception{
    	Navigation.popUp(Resource.ABOUT_PATH, UISession.primaryStage);
    }

    @FXML
    void helpMenuFeedbackClicked(ActionEvent event) throws Exception{
    	Navigation.popUp(Resource.FEEDBACK_PATH, UISession.primaryStage);
    }
}