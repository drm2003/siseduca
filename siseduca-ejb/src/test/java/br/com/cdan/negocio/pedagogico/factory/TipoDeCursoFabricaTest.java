package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.TipoDeCurso;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.TipoDeCursoDao;

public class TipoDeCursoFabricaTest extends FabricaTest {
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
		t.setDescricao(criarStringDinamicaPorTamanho(100));
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
