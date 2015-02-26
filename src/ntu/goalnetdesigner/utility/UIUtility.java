package ntu.goalnetdesigner.utility;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ntu.goalnetdesigner.logic.RenderManager;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.session.UISession;

public class UIUtility {
	
	public static class TreeView{
		public static <I> TreeItem<I> convertToTreeItem(List<I> model){
			if (model == null)
				return null;
			TreeItem<I> rootNode = new TreeItem<I>();
	        rootNode.setExpanded(true);
	        for(I i: model){
	        	TreeItem<I> leaf = new TreeItem<I>(i);
	        	rootNode.getChildren().add(leaf);
	        }
	        return rootNode;
		}
	}
	
	public static class Navigation{
		public static void switchTo(String fxmlPath, Stage targetStage) throws Exception{
			Scene scene = Resource.getInstance().getSceneByFxml(fxmlPath);
	//        if (scene == null) {
	//            scene = new Scene(page);
	//            stage.setScene(scene);
	//        } else {
	//            stage.getScene().setRoot(page);
	//        }
			targetStage.setScene(scene);
			targetStage.sizeToScene();
		}
		
		public static Stage popUp(String fxmlPath, Stage ownerStage) throws Exception{
			Stage st = new Stage();
			
			// Maintain reference to pop up stage
			UISession.secondaryStage = st;
			st.setOnHiding(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent event) {
					UISession.secondaryStage = null;
				}
			});
			
			st.initModality(Modality.APPLICATION_MODAL);
			st.initOwner(ownerStage.getScene().getWindow());
	        Scene scene = Resource.getInstance().getSceneByFxml(fxmlPath);
	        st.setScene(scene);
	        st.showAndWait();
	        return st;
		}
		
		public static void closeContainingStage(Button bt){
			Stage stage = (Stage) bt.getScene().getWindow();
	        stage.close();
		}

//		private Parent replaceSceneContent(String fxml) throws Exception {
//	        Parent page = (Parent) FXMLLoader.load(getClass().getResource(fxml), null, new JavaFXBuilderFactory());
//	        Scene scene = stage.getScene();
//	        if (scene == null) {
//	            scene = new Scene(page);
//	            stage.setScene(scene);
//	        } else {
//	            stage.getScene().setRoot(page);
//	        }
//	        stage.sizeToScene();
//	        return page;
//	    }
	}
	
	public static class Draw{
		public static RenderManager renderManager = null;
		
		public static void setBoldBorder(Renderable r){
			r.getShape().setStrokeWidth(5);
		}
		
		public static void restoreBorder(Renderable r){
			r.getShape().setStrokeWidth(1);
		}
	}
	public static class GroupSelection{
		public static boolean isRenderableInSelectionRectangle(Renderable r, Rectangle rect){
			double x = rect.getTranslateX();
			double y = rect.getTranslateY();
			double xMax = x + rect.getWidth();
			double yMax = y + rect.getHeight();
			double rx = r.getDisplay().getTranslateX();
			double ry = r.getDisplay().getTranslateY();
			
			if (rx > x && rx <xMax && ry > y && ry < yMax)
				return true;
			return false;
		}
	}
	
}
