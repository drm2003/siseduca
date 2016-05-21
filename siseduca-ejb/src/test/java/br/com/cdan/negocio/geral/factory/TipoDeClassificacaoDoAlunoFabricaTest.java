package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.TipoDeClassificacaoDoAluno;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.TipoDeClassificacaoDoAlunoDao;

public class TipoDeClassificacaoDoAlunoFabricaTest extends FabricaTest {
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
		t.setDescricao(criarStringDinamicaPorTamanho(30));
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
