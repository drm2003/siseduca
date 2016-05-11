package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.Metodologia;
import br.com.cdan.negocio.pedagogico.curso.Disciplina_MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.curso.MetodologiaDao;

public class MetodologiaFabricaTest {
	private static MetodologiaFabricaTest instance = null;

	public static synchronized MetodologiaFabricaTest getInstance() {
		if (instance == null) {
			instance = new MetodologiaFabricaTest();
		}
		return instance;
	}

	public Metodologia criaMetodologia() {
		Metodologia m = new Metodologia();
		m.setAtividadesPraticas("atividadesPraticas");
		m.setAtivo(Boolean.TRUE);
		m.setBibliografiaBasica("bibliografiaBasica");
		m.setBibliografiaComplementar("bibliografiaComplementar");
		m.setCriteriosDeAvaliacao("criteriosDeAvaliacao");
		m.setDisciplina_MatrizCurricular(
				Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricular());
		m.setRecursosDidaticos("recursosDidaticos");
		m.setTecnicasDeEnsino("tecnicasDeEnsino");
		return m;
	}

	public Metodologia criaMetodologiaPersistido(EntityManager em) {
		Metodologia m = criaMetodologia();
		MetodologiaDao dao = new MetodologiaDao(em);
		//
		Disciplina_MatrizCurricularDao disciplina_MatrizCurricularDao = new Disciplina_MatrizCurricularDao(em);
		Disciplina_MatrizCurricular disciplina_MatrizCurricular = m.getDisciplina_MatrizCurricular();
		disciplina_MatrizCurricularDao.persist(disciplina_MatrizCurricular);
		m.setDisciplina_MatrizCurricular(disciplina_MatrizCurricular);
		//
		dao.persist(m);
		return m;
	}
}
