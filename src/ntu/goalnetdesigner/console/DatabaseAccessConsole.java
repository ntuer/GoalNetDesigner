package ntu.goalnetdesigner.console;

import java.util.List;

import ntu.goalnetdesigner.data.manager.*;
import ntu.goalnetdesigner.data.persistence.*;

public class DatabaseAccessConsole {

	public static void main(String[] args) {
		ArcManager am = new ArcManager();
		
		Arc arc = new Arc();
		arc.setId("11111");
		arc.setGNetID("1");
		arc.setDescription("s");
		//arc.setIsDirect((byte) 1);
		//arc.setDirection((byte) 1);
		arc.setInputID("1");
		arc.setName("sd");
		arc.setOutputID("1");
		am.insert(arc);
		
		List<Arc> a = am.findAll();
		
		arc = am.find("11111");
		arc.setGNetID("2");
		am.update(arc);
		arc.setGNetID("4");
		am.update(arc);
		am.delete(arc);
	}
}
