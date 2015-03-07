package ntu.goalnetdesigner.data.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ntu.goalnetdesigner.data.persistence.ActionLog;
import ntu.goalnetdesigner.data.persistence.Arc;
import ntu.goalnetdesigner.data.persistence.FeedbackLog;
import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.Method;
import ntu.goalnetdesigner.data.persistence.Property;
import ntu.goalnetdesigner.data.persistence.Question;
import ntu.goalnetdesigner.data.persistence.State;
import ntu.goalnetdesigner.data.persistence.StateFunction;
import ntu.goalnetdesigner.data.persistence.Task;
import ntu.goalnetdesigner.data.persistence.TaskFunction;
import ntu.goalnetdesigner.data.persistence.Tasklist;
import ntu.goalnetdesigner.data.persistence.TasklistTask;
import ntu.goalnetdesigner.data.persistence.Transition;
import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.persistence.UserGnet;
import ntu.goalnetdesigner.session.LoginSession;

public class DataService {
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	
	@SuppressWarnings("unchecked")
	public static EntityManagerFactory getEntityManagerFactory(){
		if (emf == null){
			@SuppressWarnings("rawtypes")
			Map properties = new HashMap();
			properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
			properties.put("javax.persistence.jdbc.url", "jdbc:mysql://" + LoginSession.serverAddress);
			properties.put("javax.persistence.jdbc.user", "root");
			properties.put("javax.persistence.jdbc.password", "root");
			properties.put("eclipselink.logging.level", "FINE");
			//properties.put("eclipselink.query-results-cache", "false");
			emf = Persistence.createEntityManagerFactory("GoalNetDesigner",properties);
		}
		return emf;
	}
	
	public static EntityManager getEntityManager(){
		if (em == null){
			em = getEntityManagerFactory().createEntityManager();
		}
		return em;
	}
	
	public static void begin(){
		em.getTransaction().begin();
	}
	
	public static void commit(){
		em.getTransaction().commit();
	}
	
	public static void flush(){
		em.flush();
	}
	
	public static void rollback(){
		em.getTransaction().rollback();
	}
	
	public static DataServiceUnit<ActionLog> actionLog = new DataServiceUnit<ActionLog>(ActionLog.class);
	public static DataServiceUnit<FeedbackLog> feedbackLog = new DataServiceUnit<FeedbackLog>(FeedbackLog.class);
	public static DataServiceUnit<Question> question = new DataServiceUnit<Question>(Question.class);
	public static DataServiceUnit<Arc> arc = new DataServiceUnit<Arc>(Arc.class);
	public static DataServiceUnit<Method> method = new DataServiceUnit<Method>(Method.class);
	public static DataServiceUnit<Gnet> gnet = new DataServiceUnit<Gnet>(Gnet.class);
	public static DataServiceUnit<Property> property = new DataServiceUnit<Property>(Property.class);
	public static DataServiceUnit<State> state = new DataServiceUnit<State>(State.class);
	public static DataServiceUnit<Transition> transition = new DataServiceUnit<Transition>(Transition.class);
	public static DataServiceUnit<Task> task = new DataServiceUnit<Task>(Task.class);
	public static DataServiceUnit<Tasklist> tasklist = new DataServiceUnit<Tasklist>(Tasklist.class);
	public static DataServiceUnit<User> user = new DataServiceUnit<User>(User.class);
	
	public static AssociationDataServiceUnit<StateFunction> stateFunction = new AssociationDataServiceUnit<StateFunction>(StateFunction.class);
	public static AssociationDataServiceUnit<TaskFunction> taskFunction = new AssociationDataServiceUnit<TaskFunction>(TaskFunction.class);
	public static AssociationDataServiceUnit<TasklistTask> tasklistTask = new AssociationDataServiceUnit<TasklistTask>(TasklistTask.class);
	public static AssociationDataServiceUnit<UserGnet> userGnet = new AssociationDataServiceUnit<UserGnet>(UserGnet.class);
}
