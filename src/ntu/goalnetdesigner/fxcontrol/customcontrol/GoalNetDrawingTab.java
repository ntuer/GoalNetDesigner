package ntu.goalnetdesigner.fxcontrol.customcontrol;

import javafx.scene.control.Tab;
import ntu.goalnetdesigner.data.persistence.Gnet;

public class GoalNetDrawingTab extends Tab{
	// handle multiple gnets
	private Gnet gnet;
	public GoalNetDrawingTab(Gnet gnet){
		this.gnet = gnet;
	}
	public Gnet getGnet() {
		return gnet;
	}
	public void setGnet(Gnet gnet) {
		this.gnet = gnet;
	}
}
