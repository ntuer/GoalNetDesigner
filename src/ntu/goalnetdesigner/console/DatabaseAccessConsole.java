package ntu.goalnetdesigner.console;

import ntu.goalnetdesigner.data.*;
import ntu.goalnetdesigner.data.persistence.*;

public class DatabaseAccessConsole {

	public static void main(String[] args) {
		TestManager tm = new TestManager();
		System.out.println(tm.fetchFirstArc().getGNetID());
	}
}
