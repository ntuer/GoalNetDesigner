package ntu.goalnetdesigner.session;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.utility.CurrentGNetObjectSelection;

public class DataSession {
	public static String currentGoalNetID = null;
	public static CurrentGNetObjectSelection currentGNetObjectSelection = 
			CurrentGNetObjectSelection.STATE;
	
	public static Gnet currentGNet = null;
}
