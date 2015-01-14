package ntu.goalnetdesigner.logic;

import javafx.scene.layout.AnchorPane;
import ntu.goalnetdesigner.data.persistence.Gnet;

public class RenderManager {
	
	private AnchorPane drawingPane;

	public RenderManager(AnchorPane drawingPane) {
		super();
		this.drawingPane = drawingPane;
	}

	public AnchorPane getDrawingPane() {
		return drawingPane;
	}

	public void setDrawingPane(AnchorPane drawingPane) {
		this.drawingPane = drawingPane;
	}
	
	
	public void render(Gnet gnet){
		
	}
	
	
}
