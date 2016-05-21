package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.SituacaoDoAluno;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.SituacaoDoAlunoDao;

public class SituacaoDoAlunoFabricaTest extends FabricaTest {
	private static SituacaoDoAlunoFabricaTest instance = null;

	public static synchronized SituacaoDoAlunoFabricaTest getInstance() {
		if (instance == null) {
			instance = new SituacaoDoAlunoFabricaTest();
		}
		return instance;
	}

	public SituacaoDoAluno criaSituacaoDoAluno() {
		SituacaoDoAluno s = new SituacaoDoAluno();
		s.setAtivo(Boolean.TRUE);
		s.setDescricao(criarStringDinamicaPorTamanho(100));
		return s;
	}

	public SituacaoDoAluno criaSituacaoDoAlunoPersistido(EntityManager em) {
		SituacaoDoAlunoDao dao = new SituacaoDoAlunoDao(em);
		SituacaoDoAluno s = criaSituacaoDoAluno();
		dao.persist(s);
		return s;
	}
}
