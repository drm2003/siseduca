package br.com.cdan.negocio.estoque.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.estoque.TipoDeSaida;
import br.com.cdan.negocio.estoque.TipoDeSaidaDao;

public class TipoDeSaidaFabricaTest {
	private static TipoDeSaidaFabricaTest instance = null;

	public static synchronized TipoDeSaidaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeSaidaFabricaTest();
		}
		return instance;
	}

	public TipoDeSaida criaTipoDeSaida() {
		TipoDeSaida t = new TipoDeSaida();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		return t;
	}

	public TipoDeSaida criaTipoDeSaidaPersistido(EntityManager em) {
		TipoDeSaida t = criaTipoDeSaida();
		TipoDeSaidaDao dao = new TipoDeSaidaDao(em);
		dao.persist(t);
		return t;
	}

}
