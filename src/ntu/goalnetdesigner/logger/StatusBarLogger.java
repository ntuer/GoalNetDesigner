package ntu.goalnetdesigner.logger;

import java.util.Date;

import javafx.scene.control.Label;

public class StatusBarLogger {
	private static Label statusLabel;

	public static Label getStatusLabel() {
		return statusLabel;
	}

	public static void setStatusLabel(Label statusLabel) {
		StatusBarLogger.statusLabel = statusLabel;
	}
	
	public static void log(Object message){
		statusLabel.setText(message.toString());
	}

	public static void clear() {
		statusLabel.setText("");
	}
}
