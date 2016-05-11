package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.PlanoDeEnsino;
import br.com.cdan.negocio.pedagogico.curso.Disciplina_MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.curso.PlanoDeEnsinoDao;

public class PlanoDeEnsinoFabricaTest {
	private static PlanoDeEnsinoFabricaTest instance = null;

	public static synchronized PlanoDeEnsinoFabricaTest getInstance() {
		if (instance == null) {
			instance = new PlanoDeEnsinoFabricaTest();
		}
		return instance;
	}

	public PlanoDeEnsino criaPlanoDeEnsino() {
		PlanoDeEnsino p = new PlanoDeEnsino();
		p.setAtivo(Boolean.TRUE);
		p.setContribuicaoParaAInstituicao("contribuicaoParaAInstituicao");
		p.setContribuicaoParaOEgresso("contribuicaoParaOEgresso");
		p.setDisciplina_MatrizCurricular(
				Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricular());
		p.setEmenta("ementa");
		p.setInterdisciplinariedade("interdisciplinariedade");
		p.setObjetivosEspecificos("objetivosEspecificos");
		p.setObjetivosGerais("objetivosGerais");
		return p;
	}

	public PlanoDeEnsino criaPlanoDeEnsinoPersistido(EntityManager em) {
		PlanoDeEnsino p = criaPlanoDeEnsino();
		PlanoDeEnsinoDao dao = new PlanoDeEnsinoDao(em);
		//
		Disciplina_MatrizCurricularDao disciplina_MatrizCurricularDao = new Disciplina_MatrizCurricularDao(em);
		Disciplina_MatrizCurricular disciplina_MatrizCurricular = p.getDisciplina_MatrizCurricular();
		disciplina_MatrizCurricularDao.persist(disciplina_MatrizCurricular);
		p.setDisciplina_MatrizCurricular(disciplina_MatrizCurricular);
		//
		dao.persist(p);
		return p;
	}
}