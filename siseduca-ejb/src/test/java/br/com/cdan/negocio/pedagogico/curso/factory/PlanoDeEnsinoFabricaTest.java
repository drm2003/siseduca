package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.PlanoDeEnsino;
import br.com.cdan.negocio.pedagogico.curso.PlanoDeEnsinoDao;

public class PlanoDeEnsinoFabricaTest {
	private static PlanoDeEnsinoFabricaTest instance = null;

	public static synchronized PlanoDeEnsinoFabricaTest getInstance() {
		if (instance == null) {
			instance = new PlanoDeEnsinoFabricaTest();
		}
		return instance;
	}

	public PlanoDeEnsino criaPlanoDeEnsino(EntityManager em) {
		PlanoDeEnsino p = new PlanoDeEnsino();
		p.setAtivo(Boolean.TRUE);
		p.setContribuicaoParaAInstituicao("contribuicaoParaAInstituicao");
		p.setContribuicaoParaOEgresso("contribuicaoParaOEgresso");
		p.setDisciplina_MatrizCurricular(
				Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricularPersistido(em));
		p.setEmenta("ementa");
		p.setInterdisciplinariedade("interdisciplinariedade");
		p.setObjetivosEspecificos("objetivosEspecificos");
		p.setObjetivosGerais("objetivosGerais");
		return p;
	}

	public PlanoDeEnsino criaPlanoDeEnsinoPersistido(EntityManager em) {
		PlanoDeEnsino p = criaPlanoDeEnsino(em);
		PlanoDeEnsinoDao dao = new PlanoDeEnsinoDao(em);
		//
		dao.persist(p);
		return p;
	}
}