package ntu.goalnetdesigner.console;

import java.util.List;

import ntu.goalnetdesigner.data.persistence.*;
import ntu.goalnetdesigner.data.service.*;

public class DatabaseAccessConsole {

	public static void main(String[] args) {
		DataServiceUnit<Arc> pm = DataService.arc;
		Arc arc = new Arc();
		arc.setId("11111");
		arc.setGNetID("1");
		arc.setDescription("s");
		//arc.setIsDirect((byte) 1);
		//arc.setDirection((byte) 1);
		arc.setInputID("1");
		arc.setName("sd");
		arc.setOutputID("1");
		pm.insert(arc);
		
		List<Arc> a = pm.findAll();
		
		Arc arc2 = (Arc) pm.find("11111");
		arc2.setGNetID("2");
		pm.update(arc2);
		arc2.setGNetID("4");
		pm.update(arc2);
		pm.delete(arc2);
	}
}
