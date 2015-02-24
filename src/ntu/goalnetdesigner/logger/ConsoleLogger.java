package ntu.goalnetdesigner.logger;

import java.util.Date;

import javafx.scene.control.TextArea;

public class ConsoleLogger {
	
	private static TextArea outputArea;
	
	public static TextArea getOutputArea() {
		return ConsoleLogger.outputArea;
	}

	public static void setOutputArea(TextArea outputArea) {
		ConsoleLogger.outputArea = outputArea;
	}
	
	public static void log(Object message){
		outputArea.appendText(new Date() + " " + message + "\n");
	}
}
