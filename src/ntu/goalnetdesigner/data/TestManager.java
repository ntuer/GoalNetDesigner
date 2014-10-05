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
		return em.find(Arc.class, BigInteger.valueOf(1));
	}
	
}
