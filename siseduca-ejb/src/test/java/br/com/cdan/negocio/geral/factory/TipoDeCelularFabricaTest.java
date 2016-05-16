package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.TipoDeCelular;
import br.com.cdan.negocio.geral.TipoDeCelularDao;

public class TipoDeCelularFabricaTest {
	private static TipoDeCelularFabricaTest instance = null;

	public static TipoDeCelularFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeCelularFabricaTest();
		}
		return instance;
	}

	public TipoDeCelular criaTipoDeCelular() {
		TipoDeCelular t = new TipoDeCelular();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		//
		return t;
	}

	public TipoDeCelular criaTipoDeCelularPersistido(EntityManager em) {
		TipoDeCelular t = criaTipoDeCelular();
		TipoDeCelularDao dao = new TipoDeCelularDao(em);
		//
		dao.persist(t);
		return t;
	}
}
