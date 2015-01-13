package ntu.goalnetdesigner.logger;

import java.util.Date;

import javafx.scene.control.TextArea;

public class UserConsoleLogger {
	
	private static TextArea outputArea;
	
	public static TextArea getOutputArea() {
		return UserConsoleLogger.outputArea;
	}

	public static void setOutputArea(TextArea outputArea) {
		UserConsoleLogger.outputArea = outputArea;
	}
	
	public static void log(String message){
		
		outputArea.appendText(new Date() + " " + message + "\n");
	}
}
