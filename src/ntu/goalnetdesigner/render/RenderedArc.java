package ntu.goalnetdesigner.render;

import ntu.goalnetdesigner.data.persistence.State;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RenderedArc extends Renderable{
	private Circle shape;
	private State baseState;
	
	public RenderedArc (){
		this.shape = new Circle();
		this.baseState = new State();
	}

	public Circle getShape() {
		return shape;
	}

	public void setShape(Circle shape) {
		this.shape = shape;
	}

	public State getBaseState() {
		return baseState;
	}

	public void setBaseState(State baseState) {
		this.baseState = baseState;
	}
	
	@Override
	public void setMeh(MouseEventHandler meh){
		this.meh = meh;
		this.shape.setOnMouseClicked(meh.mouseOnClickHandler);
	}
	
}
