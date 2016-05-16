package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Metodologia;
import br.com.cdan.negocio.pedagogico.curso.MetodologiaDao;

public class MetodologiaFabricaTest {
	private static MetodologiaFabricaTest instance = null;

	public static synchronized MetodologiaFabricaTest getInstance() {
		if (instance == null) {
			instance = new MetodologiaFabricaTest();
		}
		return instance;
	}

	public Metodologia criaMetodologia(EntityManager em) {
		Metodologia m = new Metodologia();
		m.setAtividadesPraticas("atividadesPraticas");
		m.setAtivo(Boolean.TRUE);
		m.setBibliografiaBasica("bibliografiaBasica");
		m.setBibliografiaComplementar("bibliografiaComplementar");
		m.setCriteriosDeAvaliacao("criteriosDeAvaliacao");
		m.setDisciplina_MatrizCurricular(
				Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricularPersistido(em));
		m.setRecursosDidaticos("recursosDidaticos");
		m.setTecnicasDeEnsino("tecnicasDeEnsino");
		return m;
	}

	public Metodologia criaMetodologiaPersistido(EntityManager em) {
		Metodologia m = criaMetodologia(em);
		MetodologiaDao dao = new MetodologiaDao(em);
		//
		dao.persist(m);
		return m;
	}
}
