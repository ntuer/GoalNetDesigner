package ntu.goalnetdesigner.render;

import ntu.goalnetdesigner.data.persistence.IDrawable;

public abstract class Renderable<T extends IDrawable> {
	private int x;
	private int y;
	private T baseObject;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public T getBaseObject() {
		return baseObject;
	}
	public void setBaseObject(T baseObject) {
		this.baseObject = baseObject;
	}
	
}
