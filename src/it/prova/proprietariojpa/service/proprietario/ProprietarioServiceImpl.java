package it.prova.proprietariojpa.service.proprietario;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.proprietariojpa.dao.EntityManagerUtil;
import it.prova.proprietariojpa.dao.proprietario.ProprietarioDAO;
import it.prova.proprietariojpa.model.Proprietario;

public class ProprietarioServiceImpl implements ProprietarioService {

	private ProprietarioDAO proprietarioDao;

	public List<Proprietario> listAllProprietari() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			// uso l'injection per il dao
			proprietarioDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return proprietarioDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	public Proprietario caricaSingoloProprietario(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void aggiorna(Proprietario proprietarioInstance) throws Exception {
		// TODO Auto-generated method stub

	}

	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			entityManager.getTransaction().begin();
			proprietarioDao.setEntityManager(entityManager);
			proprietarioDao.insert(proprietarioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public void rimuovi(Long idProprietarioInstance) throws Exception {
		// TODO Auto-generated method stub

	}

	public void setProprietarioDao(ProprietarioDAO proprietarioDao){
		this.proprietarioDao = proprietarioDao;

	}

}
