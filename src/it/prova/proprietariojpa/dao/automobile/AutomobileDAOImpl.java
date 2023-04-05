package it.prova.proprietariojpa.dao.automobile;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.query.NativeQuery;

import it.prova.proprietariojpa.dao.EntityManagerUtil;
import it.prova.proprietariojpa.model.Automobile;

public class AutomobileDAOImpl implements AutomobileDAO {

	private EntityManager entityManager;

	public List<Automobile> list() throws Exception {
		
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();
	}

	public Automobile get(Long id) throws Exception {
		
		return entityManager.find(Automobile.class, id);
	}

	public void update(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null)
			throw new Exception("Problema con valore input");
		automobileInstance = entityManager.merge(automobileInstance);
	}

	public void insert(Automobile automobileInstance) throws Exception {
		if(automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(automobileInstance);
	}

	public void delete(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null)
			throw new Exception("Problema con valore input");
		 entityManager.remove(entityManager.merge(automobileInstance));

	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	public List<Automobile> cercaErrori() {
		TypedQuery<Automobile> query = entityManager.createQuery("from Automobile a where a.proprietario.dataDiNascita > ?1 ", Automobile.class);
		return query.setParameter(1, LocalDate.now().minusYears(18)).getResultList();
		
	}

	@Override
	public List<Automobile> listaAutoICuiProprietariHannoCFCheIniziaPer(String inizialeCf) {
		TypedQuery<Automobile> query = entityManager.createQuery("from Automobile a where a.proprietario.cf like ?1 ", Automobile.class);
		return query.setParameter(1, inizialeCf +"%").getResultList();
	}

	@Override
	public Long contaAutoConProprietariNatiDal(int anno) {
		Long result = 0L;
		Query query = entityManager.createQuery("select count(a.id) from Automobile a where year(a.proprietario.dataDiNascita) >= ?1 ");
		query.setParameter(1, anno);
		result = (Long)query.getSingleResult();
		return result; 
		
	}
	

}
