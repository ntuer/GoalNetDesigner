package ntu.goalnetdesigner.render;


public abstract class Drawable{
	protected Renderable renderedObject = null;

	public Renderable getRenderedObject() {
		return renderedObject;
	}

	public void setRenderedObject(Renderable renderedObject) {
		this.renderedObject = renderedObject;
	}
}
