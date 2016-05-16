package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.TipoDeCurso;
import br.com.cdan.negocio.pedagogico.TipoDeCursoDao;
import br.com.cdan.negocio.pedagogico.curso.factory.CursoFabricaTest;

public class TipoDeCursoFabricaTest {
	private static TipoDeCursoFabricaTest instance = null;

	public static synchronized TipoDeCursoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeCursoFabricaTest();
		}
		return instance;
	}

	public TipoDeCurso criaTipoDeCurso(EntityManager em) {
		TipoDeCurso t = new TipoDeCurso();
		t.setAtivo(Boolean.TRUE);
		t.setCompartilhado(Boolean.TRUE);
		t.setCurso(CursoFabricaTest.getInstance().criaCursoPersistido(em));
		t.setDescricao("descricao");
		//
		t.setReconhecidoPeloMec(Boolean.TRUE);
		t.setTemMatrizCurricular(Boolean.TRUE);
		//
		return t;
	}

	public TipoDeCurso criaTipoDeCursoPersistido(EntityManager em) {
		TipoDeCurso t = criaTipoDeCurso(em);
		TipoDeCursoDao dao = new TipoDeCursoDao(em);
		dao.persist(t);
		return t;
	}

}
