package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Documento;
import br.com.cdan.negocio.pedagogico.pessoa.DocumentoDao;

public class DocumentoFabricaTest {
	private static DocumentoFabricaTest instance = null;

	public static synchronized DocumentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new DocumentoFabricaTest();
		}
		return instance;
	}

	public Documento criaDocumento(EntityManager em) {
		Documento d = new Documento();
		d.setAtivo(Boolean.TRUE);
		d.setDataEntrega(Calendar.getInstance());
		d.setDataLimite(Calendar.getInstance());
		d.setDocumentoPendente(DocumentoPendenteFabricaTest.getInstance().criaDocumentoPendentePersistido(em));
		d.setEntregue(Boolean.TRUE);
		d.setNome("nome");
		return d;
	}

	public Documento criaDocumentoPersistido(EntityManager em) {
		Documento d = criaDocumento(em);
		DocumentoDao dao = new DocumentoDao(em);
		//
		dao.persist(d);
		return d;
	}

}
