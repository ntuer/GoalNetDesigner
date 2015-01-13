package ntu.goalnetdesigner.render.customcontrol;

import ntu.goalnetdesigner.render.Renderable;
import javafx.scene.layout.StackPane;

public class BidirectionalStackPane extends StackPane{
	private Renderable parentRenderable;

	public Renderable getParentRenderable() {
		return parentRenderable;
	}

	public void setParentRenderable(Renderable parent) {
		this.parentRenderable = parent;
	}
	
	public BidirectionalStackPane(Renderable parent){
		super();
		this.parentRenderable = parent;
	}
}
