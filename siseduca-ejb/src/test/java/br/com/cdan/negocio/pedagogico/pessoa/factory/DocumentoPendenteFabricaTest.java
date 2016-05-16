package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.DocumentoPendente;
import br.com.cdan.negocio.pedagogico.pessoa.DocumentoPendenteDao;

public class DocumentoPendenteFabricaTest {
	private static DocumentoPendenteFabricaTest instance = null;

	public static synchronized DocumentoPendenteFabricaTest getInstance() {
		if (instance == null) {
			instance = new DocumentoPendenteFabricaTest();
		}
		return instance;
	}

	public DocumentoPendente criaDocumentoPendente(EntityManager em) {
		DocumentoPendente d = new DocumentoPendente();
		d.setAtivo(Boolean.TRUE);
		d.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		//
		d.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		d.setObservacoes("observacoes");
		return d;
	}

	public DocumentoPendente criaDocumentoPendentePersistido(EntityManager em) {
		DocumentoPendente d = criaDocumentoPendente(em);
		DocumentoPendenteDao dao = new DocumentoPendenteDao(em);
		//
		dao.persist(d);
		return d;
	}

}
