package br.com.cdan.negocio.geral.factory;

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

	public SituacaoDoAluno criaSituacaoDoAlunoPersistido() {
		SituacaoDoAlunoDao dao = new SituacaoDoAlunoDao();
		SituacaoDoAluno s = criaSituacaoDoAluno();
		dao.persist(s);
		return s;
	}
}
