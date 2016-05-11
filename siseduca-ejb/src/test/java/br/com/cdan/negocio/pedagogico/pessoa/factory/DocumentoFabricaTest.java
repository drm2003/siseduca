package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Documento;
import br.com.cdan.model.pessoa.DocumentoPendente;
import br.com.cdan.negocio.pedagogico.pessoa.DocumentoDao;
import br.com.cdan.negocio.pedagogico.pessoa.DocumentoPendenteDao;

public class DocumentoFabricaTest {
	private static DocumentoFabricaTest instance = null;

	public static synchronized DocumentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new DocumentoFabricaTest();
		}
		return instance;
	}

	public Documento criaDocumento() {
		Documento d = new Documento();
		d.setAtivo(Boolean.TRUE);
		d.setDataEntrega(Calendar.getInstance());
		d.setDataLimite(Calendar.getInstance());
		d.setDocumentoPendente(DocumentoPendenteFabricaTest.getInstance().criaDocumentoPendente());
		d.setEntregue(Boolean.TRUE);
		d.setNome("nome");
		return d;
	}

	public Documento criaDocumentoPersistido(EntityManager em) {
		Documento d = criaDocumento();
		DocumentoDao dao = new DocumentoDao(em);
		//
		DocumentoPendente documentoPendente = d.getDocumentoPendente();
		DocumentoPendenteDao documentoPendenteDao = new DocumentoPendenteDao(em);
		documentoPendenteDao.persist(documentoPendente);
		d.setDocumentoPendente(documentoPendente);
		//
		dao.persist(d);
		return d;
	}

}
