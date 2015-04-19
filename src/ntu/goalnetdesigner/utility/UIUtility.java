package ntu.goalnetdesigner.utility;

import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.logic.RenderManager;
import ntu.goalnetdesigner.render.Drawable;
import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.render.RenderedArc;
import ntu.goalnetdesigner.render.RenderedState;
import ntu.goalnetdesigner.render.RenderedTransition;
import ntu.goalnetdesigner.render.customcontrol.Arrow;
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
	}
	
	public static class Draw {
		public static RenderManager renderManager = null;
		
		public static void setBoldBorder(Renderable r){
			r.getShape().setStrokeWidth(Resource.SELECTED_STROKE_WIDTH);
			if (r.getShape() instanceof Arrow)
				(((Arrow)r.getShape()).getArrow()).setStrokeWidth(Resource.SELECTED_STROKE_WIDTH);
		}
		
		public static void restoreBorder(Renderable r){
			r.getShape().setStrokeWidth(Resource.NORMAL_STROKE_WIDTH);
			if (r.getShape() instanceof Arrow)
				(((Arrow)r.getShape()).getArrow()).setStrokeWidth(Resource.NORMAL_STROKE_WIDTH);
		}
		
		private static boolean renderableContainsPoint(Renderable r, javafx.geometry.Point2D point){
			if (r instanceof RenderedTransition){
				double x = r.getDisplay().getTranslateX();
				double y = r.getDisplay().getTranslateY();
				double xMax = x + Resource.TRANSITION_WIDTH;
				double yMax = y + Resource.TRANSITION_HEIGHT;
				double rx = point.getX();
				double ry = point.getY();
				if (rx > x && rx <xMax && ry > y && ry < yMax)
					return true;
			}
			if (r instanceof RenderedState){
				double x = ((State)r.getBaseObject()).getX();
				double y = ((State)r.getBaseObject()).getY();
				if (point.distance(x, y) <= Resource.STATE_RADIUS)
					return true;
			}
			return false;
		}
		
		// a inside, b outside
		private static Point2D findBoundaryPoint(Point2D a, Point2D b, Renderable s){
			Point2D m = UIUtility.Point.midPoint(a, b);
			if (a.distance(b) < 1.0)
				return m;
			else {
	            if (UIUtility.Draw.renderableContainsPoint(s, m)) {
	                return findBoundaryPoint(m, b, s);
	            } else {
	                return findBoundaryPoint(a, m, s);
	            }
	        }
		}
		
		public static Point2D findPointOnBorderForFirstRenderable(Renderable s, Renderable t){
			Point2D a, b;
			if (s instanceof RenderedState){
				a = new Point2D(s.getDisplay().getTranslateX() + Resource.STATE_RADIUS,
						s.getDisplay().getTranslateY() + Resource.STATE_RADIUS);
				b = new Point2D(t.getDisplay().getTranslateX() + Resource.TRANSITION_WIDTH/2,
						t.getDisplay().getTranslateY() + Resource.TRANSITION_HEIGHT/2);
			} else {
				a = new Point2D(s.getDisplay().getTranslateX() + Resource.TRANSITION_WIDTH/2,
						s.getDisplay().getTranslateY() + Resource.TRANSITION_HEIGHT/2);
				b = new Point2D(t.getDisplay().getTranslateX() + Resource.STATE_RADIUS,
						t.getDisplay().getTranslateY() + Resource.STATE_RADIUS);
			}
			return findBoundaryPoint(a, b, s);
		}
	}
	
	public static class Point{
		public static javafx.geometry.Point2D midPoint(javafx.geometry.Point2D a, javafx.geometry.Point2D b){
			 return new javafx.geometry.Point2D(
					 a.getX() + (b.getX() - a.getX()) / 2.0,
					 a.getY() + (b.getY() - a.getY()) / 2.0);
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
	
	public static class CurrentSelectionProperty extends SimpleObjectProperty<Object>{
		ScrollPane propertyPane;
		public CurrentSelectionProperty() {
			super();
			
			this.addListener(new ChangeListener<Object>() {
				@Override
				public void changed(ObservableValue<? extends Object> arg0,
						Object oldPropertyValue, Object newPropertyValue) {
					// cancel old selection
					if (oldPropertyValue != null){
						Renderable or = null;
						if (oldPropertyValue instanceof Renderable){
							or = (Renderable) oldPropertyValue;
						} else if (oldPropertyValue instanceof Drawable){
							or = ((Drawable) oldPropertyValue).getRenderedObject();
						}
						if (or != null){
							UIUtility.Draw.restoreBorder(or);
						}
					}
					// select new object
					if (newPropertyValue != null){
						
						Renderable nr = null;
						if (newPropertyValue instanceof Renderable){
							nr = (Renderable) newPropertyValue;
						} else if (newPropertyValue instanceof Drawable){
							nr = ((Drawable) newPropertyValue).getRenderedObject();
						}
						if (nr != null){
							UIUtility.Draw.setBoldBorder(nr);
							try{
								if (nr instanceof RenderedState){
									CurrentSelectionProperty.this.propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.STATE_PROPERTY_PANE_PATH));
								} else if (nr instanceof RenderedTransition){
									CurrentSelectionProperty.this.propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.TRANSITION_PROPERTY_PANE_PATH));
								} else if (nr instanceof RenderedArc){
									CurrentSelectionProperty.this.propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.ARC_PROPERTY_PANE_PATH));
								}
							} catch (Exception e){
								e.printStackTrace();
							}
						} else {
							try{
								if (newPropertyValue instanceof Method){
									CurrentSelectionProperty.this.propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.FUNCTION_PROPERTY_PANE_PATH));
								} else if (newPropertyValue instanceof Task){
									CurrentSelectionProperty.this.propertyPane.setContent(Resource.getInstance().getPaneByFxml(Resource.TASK_PROPERTY_PANE_PATH));
								}
							} catch (Exception e){
								e.printStackTrace();
							}
						}
						UISession.currentPaneController.refresh();
					} else {
						UISession.currentPaneController = null;
						CurrentSelectionProperty.this.propertyPane.setContent(null);
					}
				}
			});
		}
		public ScrollPane getPropertyPane() {
			return propertyPane;
		}
		public void setPropertyPane(ScrollPane propertyPane) {
			this.propertyPane = propertyPane;
		}
	}

}
