package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.SituacaoDoAluno;
import br.com.cdan.negocio.geral.SituacaoDoAlunoDao;

public class SituacaoDoAlunoFabricaTest {
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
		s.setDescricao("descricao");
		return s;
	}

	public SituacaoDoAluno criaSituacaoDoAlunoPersistido(EntityManager em) {
		SituacaoDoAlunoDao dao = new SituacaoDoAlunoDao(em);
		SituacaoDoAluno s = criaSituacaoDoAluno();
		dao.persist(s);
		return s;
	}
}
