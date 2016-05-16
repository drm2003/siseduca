package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.SituacaoDoAlunoNaTurma;
import br.com.cdan.negocio.pedagogico.SituacaoDoAlunoNaTurmaDao;

public class SituacaoDoAlunoNaTurmaFabricaTest {
	private static SituacaoDoAlunoNaTurmaFabricaTest instance = null;

	public static synchronized SituacaoDoAlunoNaTurmaFabricaTest getInstance() {
		if (instance == null) {
			instance = new SituacaoDoAlunoNaTurmaFabricaTest();
		}
		return instance;
	}

	public SituacaoDoAlunoNaTurma criaSituacaoDoAlunoNaTurma() {
		SituacaoDoAlunoNaTurma s = new SituacaoDoAlunoNaTurma();
		s.setAbreviatura("abreviatura");
		s.setAtivo(Boolean.TRUE);
		s.setCompartilhado(Boolean.TRUE);
		s.setDescricao("descricao");
		//
		return s;
	}

	public SituacaoDoAlunoNaTurma criaSituacaoDoAlunoNaTurmaPersistido(EntityManager em) {
		SituacaoDoAlunoNaTurma s = criaSituacaoDoAlunoNaTurma();
		SituacaoDoAlunoNaTurmaDao dao = new SituacaoDoAlunoNaTurmaDao(em);
		//
		dao.persist(s);
		return s;
	}
}
