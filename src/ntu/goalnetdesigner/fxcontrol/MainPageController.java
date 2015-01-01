package ntu.goalnetdesigner.fxcontrol;


import com.sun.corba.se.spi.orbutil.fsm.State;

import ntu.goalnetdesigner.render.RenderedIDrawableObjectFactory;
import ntu.goalnetdesigner.render.RenderedState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import javafx.scene.shape.Circle;

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
    private AnchorPane drawingPane;

    @FXML
    private TreeView<?> functionTreeView;

    @FXML
    public void initialize() {
    	drawingPane.setOnMouseClicked(mouseHandler);
        TreeItem<String> rootItem = new TreeItem<String> ("Arcs");
        rootItem.setExpanded(true);
        for (int i = 1; i < 60; i++) {
            TreeItem<String> item = new TreeItem<String> ("Arc" + i);            
            rootItem.getChildren().add(item);
        }
        arcTreeView.setRoot(rootItem);
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
}
