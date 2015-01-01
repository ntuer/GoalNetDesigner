package ntu.goalnetdesigner.data.service;

import java.util.HashMap;
import java.util.Map;

import ntu.goalnetdesigner.data.persistence.*;
import ntu.goalnetdesigner.session.LoginSession;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataService {
	private static EntityManagerFactory emf = null;
	
	@SuppressWarnings("unchecked")
	public static EntityManagerFactory getEntityManagerFactory(){
		if (emf == null){
			@SuppressWarnings("rawtypes")
			Map properties = new HashMap();
			properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
			properties.put("javax.persistence.jdbc.url", "jdbc:mysql://" + LoginSession.serverAddress);
			properties.put("javax.persistence.jdbc.user", "root");
			properties.put("javax.persistence.jdbc.password", "root");
			emf = Persistence.createEntityManagerFactory("GoalNetDesigner",properties);
		}
		return emf;
	}
	
	// TODO: change to singleton for safety
	public static DataServiceUnit<Arc> arc = new DataServiceUnit<Arc>(Arc.class);
	public static DataServiceUnit<Function> function = new DataServiceUnit<Function>(Function.class);
	public static DataServiceUnit<Gnet> gnet = new DataServiceUnit<Gnet>(Gnet.class);
	public static DataServiceUnit<Property> property = new DataServiceUnit<Property>(Property.class);
	public static DataServiceUnit<State> state = new DataServiceUnit<State>(State.class);
	public static DataServiceUnit<Task> task = new DataServiceUnit<Task>(Task.class);
	public static DataServiceUnit<Tasklist> tasklist = new DataServiceUnit<Tasklist>(Tasklist.class);
	public static DataServiceUnit<User> user = new DataServiceUnit<User>(User.class);
	public static DataServiceUnit<Usergroup> usergroup = new DataServiceUnit<Usergroup>(Usergroup.class);
}
