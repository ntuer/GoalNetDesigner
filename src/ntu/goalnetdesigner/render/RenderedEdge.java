package ntu.goalnetdesigner.render;

import ntu.goalnetdesigner.render.customcontrol.Arrow;

public class RenderedEdge extends Renderable{
	
	public void update(double sx, double sy, double ex, double ey){
		this.getShape().setStartX(sx);
		this.getShape().setStartY(sy);
		this.getShape().setEndX(ex);
		this.getShape().setEndY(ey);
		this.getShape().handleChange();
	}
	
	public Arrow getShape() {
		return (Arrow)shape;
	}

	public void setShape(Arrow shape) {
		this.shape = shape;
	}
}
