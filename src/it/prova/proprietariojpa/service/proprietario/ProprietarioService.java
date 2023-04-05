package it.prova.proprietariojpa.service.proprietario;

import java.util.List;

import it.prova.proprietariojpa.dao.proprietario.ProprietarioDAO;
import it.prova.proprietariojpa.model.Proprietario;


public interface ProprietarioService {
	public List<Proprietario> listAllProprietari() throws Exception;

	public Proprietario caricaSingoloProprietario(Long id) throws Exception;

	public void aggiorna(Proprietario abitanteInstance) throws Exception;

	public void inserisciNuovo(Proprietario abitanteInstance) throws Exception;

	public void rimuovi(Long idAbitanteInstance) throws Exception;
	
	public void setProprietarioDao(ProprietarioDAO proprietarioDao) ;
}
