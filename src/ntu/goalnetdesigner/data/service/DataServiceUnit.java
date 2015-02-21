package ntu.goalnetdesigner.data.service;

import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ntu.goalnetdesigner.data.persistence.IDataServiceUnitSubscriber;

public class DataServiceUnit<T extends IDataServiceUnitSubscriber> {
	protected EntityManagerFactory emf;
	protected EntityManager em;
	private final Class<T> type;
	
	public DataServiceUnit(Class<T> type){
		this.type = type;
		emf = DataService.getEntityManagerFactory();
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
		return result;
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
			if (a == null)
				em.persist(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public void begin(){
		em.getTransaction().begin();
	}
	
	public void commit(){
		em.getTransaction().commit();
	}
	
	public void persist(T t){
		em.persist(t);
	}
	public void remove(T t){
		em.remove(t);
	}
}
