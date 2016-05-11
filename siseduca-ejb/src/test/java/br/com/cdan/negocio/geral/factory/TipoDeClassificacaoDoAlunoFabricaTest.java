package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.TipoDeClassificacaoDoAluno;
import br.com.cdan.negocio.geral.TipoDeClassificacaoDoAlunoDao;

public class TipoDeClassificacaoDoAlunoFabricaTest {
	private static TipoDeClassificacaoDoAlunoFabricaTest instance = null;

	public static synchronized TipoDeClassificacaoDoAlunoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeClassificacaoDoAlunoFabricaTest();
		}
		return instance;
	}

	public TipoDeClassificacaoDoAluno criaTipoDeClassificacaoDoAluno() {
		TipoDeClassificacaoDoAluno t = new TipoDeClassificacaoDoAluno();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		t.setCompartilhado(Boolean.TRUE);
		t.setCor("cor");
		return t;
	}

	public TipoDeClassificacaoDoAluno criaTipoDeClassificacaoDoAlunoPersistido(EntityManager em) {
		TipoDeClassificacaoDoAluno t = criaTipoDeClassificacaoDoAluno();
		TipoDeClassificacaoDoAlunoDao dao = new TipoDeClassificacaoDoAlunoDao(em);
		dao.persist(t);
		return t;
	}
}
