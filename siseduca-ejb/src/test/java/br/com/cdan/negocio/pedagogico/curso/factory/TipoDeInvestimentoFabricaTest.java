package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.TipoDeInvestimento;
import br.com.cdan.negocio.pedagogico.curso.TipoDeInvestimentoDao;

public class TipoDeInvestimentoFabricaTest {
	private static TipoDeInvestimentoFabricaTest instance = null;

	public static synchronized TipoDeInvestimentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeInvestimentoFabricaTest();
		}
		return instance;
	}

	public TipoDeInvestimento criaTipoDeInvestimento() {
		TipoDeInvestimento t = new TipoDeInvestimento();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		return t;
	}

	public TipoDeInvestimento criaTipoDeInvestimentoPersistido(EntityManager em) {
		TipoDeInvestimento t = criaTipoDeInvestimento();
		TipoDeInvestimentoDao dao = new TipoDeInvestimentoDao(em);
		//
		dao.persist(t);
		return t;
	}
}
