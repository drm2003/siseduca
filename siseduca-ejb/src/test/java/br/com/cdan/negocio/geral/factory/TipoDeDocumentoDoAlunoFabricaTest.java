package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.TipoDeDocumentoDoAluno;
import br.com.cdan.negocio.geral.TipoDeDocumentoDoAlunoDao;

public class TipoDeDocumentoDoAlunoFabricaTest {
	private static TipoDeDocumentoDoAlunoFabricaTest instance = null;

	public static synchronized TipoDeDocumentoDoAlunoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeDocumentoDoAlunoFabricaTest();
		}
		return instance;
	}

	public TipoDeDocumentoDoAluno criaTipoDeDocumentoDoAluno() {
		TipoDeDocumentoDoAluno t = new TipoDeDocumentoDoAluno();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		return t;
	}

	public TipoDeDocumentoDoAluno criaTipoDeDocumentoDoAlunoPersistido(EntityManager em) {
		TipoDeDocumentoDoAluno t = criaTipoDeDocumentoDoAluno();
		TipoDeDocumentoDoAlunoDao dao = new TipoDeDocumentoDoAlunoDao(em);
		dao.persist(t);
		return t;
	}
}
