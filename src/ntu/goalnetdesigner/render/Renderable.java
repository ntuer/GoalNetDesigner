package ntu.goalnetdesigner.render;

public abstract class Renderable {

	protected MouseEventHandler meh;

	public MouseEventHandler getMeh() {
		return meh;
	}

	public void setMeh(MouseEventHandler meh) {
		this.meh = meh;
	}
	
	
}
