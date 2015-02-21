package ntu.goalnetdesigner.logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.IDataServiceUnitSubscriber;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.Tasklist;
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
		DataService.arc.begin();
		DataService.state.begin();
		DataService.transition.begin();
		DataService.method.begin();
		DataService.task.begin();
		DataService.tasklist.begin();
		for (Object obj: DataSession.Diff.newObjects){
			if (obj instanceof Arc){
				DataService.arc.persist((Arc) obj);
			} else if (obj instanceof State){
				DataService.state.persist((State) obj);
			} else if (obj instanceof Transition){
				DataService.transition.persist((Transition) obj);
			} else if (obj instanceof Method){
				DataService.method.persist((Method) obj);
			} else if (obj instanceof Task){
				DataService.task.persist((Task) obj);
			} else if (obj instanceof Tasklist){
				DataService.tasklist.persist((Tasklist) obj);
			}
		}
		
		for (Object obj: DataSession.Diff.deletedObjects){
			if (obj instanceof Arc){
				DataService.arc.remove((Arc) obj);
			} else if (obj instanceof State){
				DataService.state.remove((State) obj);
			} else if (obj instanceof Transition){
				DataService.transition.remove((Transition) obj);
			} else if (obj instanceof Method){
				DataService.method.remove((Method) obj);
			} else if (obj instanceof Task){
				DataService.task.remove((Task) obj);
			} else if (obj instanceof Tasklist){
				DataService.tasklist.remove((Tasklist) obj);
			}
		}
		DataService.arc.commit();
		DataService.state.commit();
		DataService.transition.commit();
		DataService.method.commit();
		DataService.task.commit();
		DataService.tasklist.commit();
		DataService.gnet.update(gnet);
		
		// Now everything is managed
		// clear local cache
		DataSession.Diff.clear();
	}
}
