package ntu.goalnetdesigner.logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.session.DataSession;

public class SaveManager {
	
	public void saveLocally(String fileName, Gnet gnet){
		ObjectOutputStream out = null;
		try
		{
			out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(gnet);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Gnet openLocally(String fileName){
		Gnet returnObject = null;
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			returnObject = (Gnet) in.readObject();
			
			in.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return returnObject;
	}
	
	public void saveToDatabase(Gnet gnet){
		for(State s: DataSession.Cache.states){
			DataService.state.merge(s);
		}
		for (Transition t : DataSession.Cache.transitions){
			DataService.transition.merge(t);
		}
		for (Arc a : DataSession.Cache.arcs){
			DataService.arc.merge(a);
		}
		for (Method m : DataSession.Cache.functions){
			DataService.method.merge(m);
		}
		for (Task t : DataSession.Cache.tasks){
			DataService.task.merge(t);
		}
		DataService.gnet.merge(gnet);
	}
}
