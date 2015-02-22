package ntu.goalnetdesigner.data.service;

import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ntu.goalnetdesigner.data.persistence.IAssociationDataServiceUnitSubscriber;
import ntu.goalnetdesigner.data.persistence.IDataServiceUnitSubscriber;

public class AssociationDataServiceUnit<T extends IAssociationDataServiceUnitSubscriber> {
	protected EntityManager em;
	private final Class<T> type;
	
	public AssociationDataServiceUnit(Class<T> type){
		this.type = type;
		em = DataService.getEntityManager();
	}
	
	public Class<T> getType(){
		return this.type;
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
	
	public void persist(T t){
		em.persist(t);
		em.flush();
	}
	public void remove(T t){
		em.remove(t);
		em.flush();
	}
}
