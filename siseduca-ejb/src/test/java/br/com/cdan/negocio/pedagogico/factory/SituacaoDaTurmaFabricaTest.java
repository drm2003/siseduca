package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.SituacaoDaTurma;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.SituacaoDaTurmaDao;

public class SituacaoDaTurmaFabricaTest extends FabricaTest{
	private static SituacaoDaTurmaFabricaTest instance = null;

	public static synchronized SituacaoDaTurmaFabricaTest getInstance() {
		if (instance == null) {
			instance = new SituacaoDaTurmaFabricaTest();
		}
		return instance;
	}

	public SituacaoDaTurma criaSituacaoDaTurma() {
		SituacaoDaTurma s = new SituacaoDaTurma();
		s.setAtivo(Boolean.TRUE);
		s.setCompartilhado(Boolean.TRUE);
		s.setDescricao(criarStringDinamicaPorTamanho(100));
		return s;
	}

	public SituacaoDaTurma criaSituacaoDaTurmaPersistido(EntityManager em) {
		SituacaoDaTurma s = criaSituacaoDaTurma();
		SituacaoDaTurmaDao dao = new SituacaoDaTurmaDao(em);
		//
		dao.persist(s);
		return s;
	}
}
