package ntu.goalnetdesigner.render;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Renderable {

	protected MouseEventHandler meh;
	protected Text text;
	protected Shape shape;
	protected StackPane display;
	protected IDrawable baseObject;

	public Renderable(){
		this.display = new StackPane();
	}
	
	public StackPane getDisplay() {
		return display;
	}

	public void setDisplay(StackPane stackPane) {
		this.display = stackPane;
	}

	public MouseEventHandler getMeh() {
		return meh;
	}

	public void setMeh(MouseEventHandler meh) {
		this.meh = meh;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
}
