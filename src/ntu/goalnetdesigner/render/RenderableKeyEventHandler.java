package ntu.goalnetdesigner.render;

import ntu.goalnetdesigner.data.service.DataService;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class RenderableKeyEventHandler {
	
	// TODO
	private Renderable renderedObject;
	
	private EventHandler<KeyEvent> deleteKeyHandler = new EventHandler<KeyEvent>()
	{
		@Override
		public void handle( final KeyEvent keyEvent ){
			if ( keyEvent.getCode().equals( KeyCode.DELETE ) ){
				if (renderedObject instanceof RenderedState){
					DataService.state.delete(((RenderedState) renderedObject).getBaseObject());
				}
			}
		}
	};
	
}
