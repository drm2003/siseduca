package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.AnexoDocumentos;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.negocio.biblioteca.AnexoDocumentosDao;
import br.com.cdan.negocio.biblioteca.PessoaDao;

public class AnexoDocumentosFabricaTest {
	private static AnexoDocumentosFabricaTest instance = null;

	public static synchronized AnexoDocumentosFabricaTest getInstance() {
		if (instance == null) {
			instance = new AnexoDocumentosFabricaTest();
		}
		return instance;
	}

	public AnexoDocumentos criaAnexoDocumentos() {
		AnexoDocumentos a = new AnexoDocumentos();
		a.setArquivo(new Byte[100]);
		a.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		return a;
	}

	public AnexoDocumentos criaAnexoDocumentosPersistido(EntityManager em) {
		AnexoDocumentos a = criaAnexoDocumentos();
		AnexoDocumentosDao anexoDocumentosDao = new AnexoDocumentosDao(em);
		//
		PessoaDao pessoaDao = new PessoaDao(em);
		Pessoa pessoa = a.getPessoa();
		pessoaDao.persist(pessoa);

		anexoDocumentosDao.persist(pessoa);
		a.setPessoa(pessoa);
		return a;
	}
}