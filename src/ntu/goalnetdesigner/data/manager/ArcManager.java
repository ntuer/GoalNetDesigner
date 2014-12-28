package ntu.goalnetdesigner.data.manager;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ntu.goalnetdesigner.data.persistence.*;

public class ArcManager extends PersistenceManager<Arc>{

	@Override
	public List<Arc> findAll() {
		TypedQuery<Arc> query =
				em.createNamedQuery("Arc.findAll", Arc.class);
		return query.getResultList();
	}

	@Override
	public Arc find(String primaryKey) {
		TypedQuery<Arc> query = em.createNamedQuery(
		   		"Arc.findById", Arc.class);
		query.setParameter("id", primaryKey);
		return query.getResultList().get(0);
	}

	@Override
	public void update(Arc t) {
		em.getTransaction().begin();
		Arc arc = em.find(Arc.class, t.getId());
		arc = t;
		em.getTransaction().commit();
	}

	@Override
	public void delete(Arc t) {
		em.getTransaction().begin();
		Arc arc = em.find(Arc.class, t.getId());
		em.remove(arc);
		em.getTransaction().commit();
	}

}
