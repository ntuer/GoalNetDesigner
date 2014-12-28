package ntu.goalnetdesigner.data.service;

import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ntu.goalnetdesigner.data.persistence.*;

public class DataServiceUnit<T> {
	protected EntityManagerFactory emf = null;
	protected EntityManager em = null;
	private final Class<T> type;
	
	public DataServiceUnit(Class<T> type){
		this.type = type;
		emf = Persistence.createEntityManagerFactory("GoalNetDesigner");
		em = emf.createEntityManager();
	}
	
	public Class<T> getType(){
		return this.type;
	}
	
	public T find(String primaryKey){
		TypedQuery<T> query = em.createNamedQuery(
		   		this.type.getSimpleName() + ".findById", this.type);
		query.setParameter("id", primaryKey);
		List<T> result = query.getResultList();
		if (result.size() != 0)
			return query.getResultList().get(0);
		else
			return null;
	}
	
	public List<T> findAll(){
		TypedQuery<T> query =
				em.createNamedQuery(this.type.getSimpleName() + ".findAll", this.type);
		List<T> result = query.getResultList();
		if (result.size() != 0)
			return query.getResultList();
		else
			return null;
	}
	
	public void insert(T t){
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}
	
	public void update(T t){
		try {
			Method method = this.type.getMethod("getId", null);
			String id = (String) method.invoke(t, null);
			em.getTransaction().begin();
			T a = em.find(this.type, id);
			if (a != null){
				a = t;
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	
	public void delete(T t){
		try {
			Method method = this.type.getMethod("getId", null);
			String id = (String) method.invoke(t, null);
			em.getTransaction().begin();
			T a = em.find(this.type, id);
			if (a != null){
				em.remove(a);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

}
