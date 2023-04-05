package it.prova.proprietariojpa.test;

import java.time.LocalDate;
import java.util.List;

import it.prova.proprietariojpa.dao.EntityManagerUtil;
import it.prova.proprietariojpa.model.Automobile;
import it.prova.proprietariojpa.model.Proprietario;
import it.prova.proprietariojpa.service.MyServiceFactory;
import it.prova.proprietariojpa.service.automobile.AutomobileService;
import it.prova.proprietariojpa.service.proprietario.ProprietarioService;

public class TestProprietario {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioService();
		AutomobileService automobileService = MyServiceFactory.getAutomobileService();

		try {
			
			
			
//			System.out.println("In tabella proprietario ci sono: "+proprietarioService.listAllProprietari().size +" elementi");
//			testInserisciNuovoProprietario(proprietarioService);
//			System.out.println("In tabella proprietario ci sono: "+proprietarioService.listAllProprietari().size() +" elementi");

//			testInserisciNuovoAutomobile(proprietarioService, automobileService);
//			System.out.println("In tabella proprietario ci sono: "+proprietarioService.listAllProprietari().size() +" elementi");
//			System.out.println("In tabella automobile ci sono: "+automobileService.listAllAutomobili().size() +" elementi");
			
//			testCercaErrori(automobileService);
//			System.out.println("In tabella automobile ci sono: "+automobileService.listAllAutomobili().size() +" elementi");
			
//			testListaAutoICuiProprietariHannoCfCheIniziaPer(automobileService);
//			System.out.println("In tabella automobile ci sono: "+automobileService.listAllAutomobili().size() +" elementi");
			
			testContaAutoConProprietariNatiDopoIl(automobileService);
			System.out.println("In tabella automobile ci sono: "+automobileService.listAllAutomobili().size() +" elementi");
			
		} catch (Throwable e) {
			e.printStackTrace(); 
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	public static void testInserisciNuovoProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println("......testInserisciNuovoProprietario INIZIO.....");
		Proprietario nuovoProprietario = new Proprietario("Mario", "Rossi", "AAAAAAA", LocalDate.of(1995, 12, 31));
		if (nuovoProprietario.getId() != null) {
			throw new RuntimeException("testInserisciNuovoProprietario Fallito: record già presente");
		}
		proprietarioService.inserisciNuovo(nuovoProprietario);
		if (nuovoProprietario.getId() == null) {
			throw new RuntimeException("testInserisciNuovoProprietario Fallito");
		}
		System.out.println(".....testInserisciNuovoProprietario PASSED......");
	}
	
	public static void testInserisciNuovoAutomobile (ProprietarioService proprietarioService, AutomobileService automobileService) throws Exception {
		System.out.println("......testInserisciNuovoAutomobile INIZIO.....");
		List<Proprietario> elencoVociPresenti = proprietarioService.listAllProprietari();
		if (elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testInserisciNuovoAutomobile Fallito:");
		}
		Proprietario primoProprietario = elencoVociPresenti.get(0);
		Automobile nuovaAutomobile = new Automobile("Toyota","Prius","ER433V");
		nuovaAutomobile.setProprietario(primoProprietario);
		automobileService.inserisciNuovo(nuovaAutomobile);
		if (nuovaAutomobile.getId() == null) {
			throw new RuntimeException("testInserisciNuovoAutomobile Fallito.....");
		}
		System.out.println(".....testInserisciNuovoAutomobile PASSED......");
	} 

	public static void testCercaErrori(AutomobileService automobileService) throws Exception {
		System.out.println("......testCercaErrori INIZIO.....");
		List<Automobile> elencoVociPresenti = automobileService.listAllAutomobili();
		if (elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testCercaErrori Fallito: non ci sono automobili nel db");
		}
		List<Automobile> listaErrori = automobileService.cercaErrori();
		if(listaErrori.size()<1) {
			throw new RuntimeException("testCercaErrori Fallito: non ci sono errori");
		}
		System.out.println(listaErrori);
		System.out.println(".....testCercaErrori PASSED......");
		
	}

	public static void testListaAutoICuiProprietariHannoCfCheIniziaPer (AutomobileService automobileService) throws Exception {
		System.out.println("......testListaAutoICuiProprietariHannoCfCheIniziaPer INIZIO.....");
		List<Automobile> elencoVociPresenti = automobileService.listAllAutomobili();
		if (elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testListaAutoICuiProprietariHannoCfCheIniziaPer Fallito: non ci sono automobili nel db");
		}
		String inizialeCf = "A";
		List<Automobile> listaAutoDaTest = automobileService.listaAutoICuiProprietariHannoCfCheIniziaPer(inizialeCf);
		if(listaAutoDaTest.size()<1) {
			throw new RuntimeException("testListaAutoICuiProprietariHannoCfCheIniziaPer Fallito: non ci sono errori");
		}
		System.out.println(listaAutoDaTest);
		System.out.println(".....testListaAutoICuiProprietariHannoCfCheIniziaPer PASSED......");
	}
	
	public static void testContaAutoConProprietariNatiDopoIl (AutomobileService automobileService) throws Exception {
		System.out.println("......testListaAutoICuiProprietariHannoCfCheIniziaPer INIZIO.....");
		List<Automobile> elencoVociPresenti = automobileService.listAllAutomobili();
		if (elencoVociPresenti.size() < 1) {
			throw new RuntimeException("testListaAutoICuiProprietariHannoCfCheIniziaPer Fallito: non ci sono automobili nel db");
		}
		int anno = 1990;
		Long contatore = automobileService.contaAutoConProprietariNatiDal(anno);
		System.out.println("Il contatore conta: "+contatore);
		System.out.println(".....testListaAutoICuiProprietariHannoCfCheIniziaPer PASSED......");
	}
	
}
