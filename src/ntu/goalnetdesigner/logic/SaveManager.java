package ntu.goalnetdesigner.logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ntu.goalnetdesigner.data.persistence.Gnet;
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
		DataService.gnet.update(DataSession.Cache.gnet);
		
		
	}
}
