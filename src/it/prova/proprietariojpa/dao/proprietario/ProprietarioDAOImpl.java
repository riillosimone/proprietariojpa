package it.prova.proprietariojpa.dao.proprietario;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.proprietariojpa.model.Proprietario;

public class ProprietarioDAOImpl implements ProprietarioDAO {

	private EntityManager entityManager;

	public List<Proprietario> list() throws Exception {
		return entityManager.createQuery("from Proprietario", Proprietario.class).getResultList();
	}

	public Proprietario get(Long id) throws Exception {
		
		return entityManager.find(Proprietario.class, id);
	}

	public void update(Proprietario proprietarioInstance) throws Exception {
		if(proprietarioInstance == null)
			throw new Exception("Problema con valore input");
		proprietarioInstance = entityManager.merge(proprietarioInstance);

	}

	public void insert(Proprietario proprietarioInstance) throws Exception {
		if(proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(proprietarioInstance);
		
	}

	public void delete(Proprietario proprietarioInstance) throws Exception {
		if(proprietarioInstance == null)
			throw new Exception("Problema con valore input");
		entityManager.remove(entityManager.merge(proprietarioInstance));

	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

}
