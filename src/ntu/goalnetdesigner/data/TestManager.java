package ntu.goalnetdesigner.data;

import javax.persistence.*;
import java.math.BigInteger;
import ntu.goalnetdesigner.data.persistence.*;

public class TestManager {
	
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	public TestManager(){
		emf = Persistence.createEntityManagerFactory("GoalNetDesigner");
		em = emf.createEntityManager();
	}
	
	public Arc fetchFirstArc(){
		return em.find(Arc.class, "1");
	}
	
	public void insertArc(){
		em.getTransaction().begin();
		Arc arc = new Arc();
		arc.setId("11111");
		arc.setGNetID("1");
		arc.setDescription("s");
		//arc.setIsDirect((byte) 1);
		//arc.setDirection((byte) 1);
		arc.setInputID("1");
		arc.setName("sd");
		arc.setOutputID("1");
		em.persist(arc);
		em.getTransaction().commit();
		em.close();
	}
	
	public void modifyArc(){
		em.getTransaction().begin();
		Arc arc = em.find(Arc.class, "11111");
		arc.setDescription("Updated Descr");
		em.getTransaction().commit();
		em.close();
	}
	
	public void removeArc(){
		em.getTransaction().begin();
		Arc arc = em.find(Arc.class, "11111");
		em.remove(arc);
		em.getTransaction().commit();
		em.close();
	}
}
