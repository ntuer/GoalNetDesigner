package ntu.goalnetdesigner.render.customcontrol;

import ntu.goalnetdesigner.utility.Resource;
import javafx.scene.shape.Polygon;

public class TransitionPolygon extends Polygon{
	public TransitionPolygon(String type){
		super();
		setShape(type);
	}
	
	public void transformTo(String type){
		this.getPoints().clear();
		setShape(type);
	}
	
	private void setShape(String type){
		if (type.equals("diamond"))
			this.getPoints().addAll(new Double[]{
				0.0, Resource.TRANSITION_HEIGHT/2,
				Resource.TRANSITION_WIDTH/2, 0.0,
				Resource.TRANSITION_WIDTH, Resource.TRANSITION_HEIGHT/2,
				Resource.TRANSITION_WIDTH/2, Resource.TRANSITION_HEIGHT,
			});
		else 
			this.getPoints().addAll(new Double[]{
					0.0, 0.0,
					Resource.TRANSITION_WIDTH, 0.0,
					Resource.TRANSITION_WIDTH, Resource.TRANSITION_HEIGHT,
					0.0, Resource.TRANSITION_HEIGHT,
				});
	}
}
