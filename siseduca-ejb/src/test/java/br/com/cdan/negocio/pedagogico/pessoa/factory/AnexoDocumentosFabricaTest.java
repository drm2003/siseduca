package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.AnexoDocumentos;
import br.com.cdan.negocio.pedagogico.pessoa.AnexoDocumentosDao;

public class AnexoDocumentosFabricaTest {
	private static AnexoDocumentosFabricaTest instance = null;

	public static synchronized AnexoDocumentosFabricaTest getInstance() {
		if (instance == null) {
			instance = new AnexoDocumentosFabricaTest();
		}
		return instance;
	}

	public AnexoDocumentos criaAnexoDocumentos(EntityManager em) {
		AnexoDocumentos a = new AnexoDocumentos();
		a.setArquivo(new Byte[100]);
		a.setPessoa(PessoaFabricaTest.getInstance().criaPessoaPersistido(em));
		return a;
	}

	public AnexoDocumentos criaAnexoDocumentosPersistido(EntityManager em) {
		AnexoDocumentos a = criaAnexoDocumentos(em);
		AnexoDocumentosDao anexoDocumentosDao = new AnexoDocumentosDao(em);
		//
		anexoDocumentosDao.persist(a);
		return a;
	}
}