package ntu.goalnetdesigner.render.customcontrol;



import ntu.goalnetdesigner.render.Renderable;
import ntu.goalnetdesigner.utility.Resource;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Arrow extends Line{
	private Path arrow;
	
	private Renderable parentRenderable;
	
	public Renderable getParentRenderable() {
		return parentRenderable;
	}

	public void setParentRenderable(Renderable parent) {
		this.parentRenderable = parent;
	}
	
	public Path getArrow() {
		return arrow;
	}

	public void setArrow(Path arrow) {
		this.arrow = arrow;
	}

	public Arrow (double sx, double sy, double ex, double ey){
		super(sx, sy, ex, ey);
		arrow = new Path();
		this.setStrokeWidth(Resource.NORMAL_STROKE_WIDTH);
		arrow.setStrokeWidth(Resource.NORMAL_STROKE_WIDTH);
		this.handleChange();
	}
	
	// change arrow direction accordingly based on new X and Y. 
	public void handleChange(){
		double sx = this.getStartX();
		double sy = this.getStartY();
		double ex = this.getEndX();
		double ey = this.getEndY();
		double length = Math.sqrt((sx - ex)*(sx - ex) + (sy - ey)*(sy - ey)); 
		Point2D ori = new Point2D(ex, ey);
		Point2D tan = (new Point2D((sx-ex)/length, (sy-ey)/length));
		arrow.getElements().clear();
		arrow.getElements().add(new MoveTo(ori.getX()+10*tan.getX()-10*tan.getY(),
	                                        ori.getY()+10*tan.getY()+10*tan.getX()));
		arrow.getElements().add(new LineTo(ori.getX(), ori.getY()));
		arrow.getElements().add(new LineTo(ori.getX()+10*tan.getX()+10*tan.getY(),
	                                        ori.getY()+10*tan.getY()-10*tan.getX()));
	}
}
