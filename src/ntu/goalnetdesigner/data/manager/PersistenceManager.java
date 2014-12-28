package ntu.goalnetdesigner.data.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ntu.goalnetdesigner.data.persistence.*;

public abstract class PersistenceManager<T> {
	protected EntityManagerFactory emf = null;
	protected EntityManager em = null;
	private final Class<T> type;
	
	public PersistenceManager(Class<T> type){
		this.type = type;
		emf = Persistence.createEntityManagerFactory("GoalNetDesigner");
		em = emf.createEntityManager();
	}
	
	public Class<T> getType(){
		return this.type;
	}
	
	public abstract T find(String primaryKey);
	
	public abstract List<T> findAll();
	
	public abstract void update(T t);
	
	public abstract void delete(T t);
	
	public void insert(T t){
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}
}
