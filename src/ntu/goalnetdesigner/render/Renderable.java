package ntu.goalnetdesigner.render;

import java.util.ArrayList;
import java.util.List;

import ntu.goalnetdesigner.render.customcontrol.BidirectionalStackPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Renderable {

	protected RenderableMouseEventHandler meh;
	protected Text text;
	protected Shape shape;
	protected BidirectionalStackPane display;
	protected Drawable baseObject;
	private List<RenderedArc> associatedRenderedArcs = new ArrayList<RenderedArc>();
	
	public List<RenderedArc> getAssociatedRenderedArcs() {
		return associatedRenderedArcs;
	}
	
	public Renderable(){
		this.display = new BidirectionalStackPane(this);
	}
	
	public BidirectionalStackPane getDisplay() {
		return display;
	}

	public void setDisplay(BidirectionalStackPane stackPane) {
		this.display = stackPane;
	}

	public RenderableMouseEventHandler getMeh() {
		return meh;
	}

	public void setMeh(RenderableMouseEventHandler meh) {
		this.meh = meh;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
}
