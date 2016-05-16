package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.TipoDeSala;
import br.com.cdan.negocio.pedagogico.TipoDeSalaDao;

public class TipoDeSalaFabricaTest {
	private static TipoDeSalaFabricaTest instance = null;

	public static synchronized TipoDeSalaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeSalaFabricaTest();
		}
		return instance;
	}

	public TipoDeSala criaTipoDeSala() {
		TipoDeSala t = new TipoDeSala();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		//
		return t;
	}

	public TipoDeSala criaTipoDeSalaPersistido(EntityManager em) {
		TipoDeSala t = criaTipoDeSala();
		TipoDeSalaDao dao = new TipoDeSalaDao(em);
		//
		dao.persist(t);
		return t;
	}
}
