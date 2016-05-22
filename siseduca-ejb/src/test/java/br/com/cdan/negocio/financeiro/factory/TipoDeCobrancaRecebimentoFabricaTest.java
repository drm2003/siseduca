package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.TipoDeCobrancaRecebimento;
import br.com.cdan.negocio.financeiro.TipoDeCobrancaRecebimentoDao;

public class TipoDeCobrancaRecebimentoFabricaTest {
	private static TipoDeCobrancaRecebimentoFabricaTest instance = null;

	public static synchronized TipoDeCobrancaRecebimentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeCobrancaRecebimentoFabricaTest();
		}
		return instance;
	}

	public TipoDeCobrancaRecebimento criaTipoDeCobrancaRecebimento() {
		TipoDeCobrancaRecebimento t = new TipoDeCobrancaRecebimento();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao" + Math.random() * 10000);
		return t;
	}

	public TipoDeCobrancaRecebimento criaTipoDeCobrancaRecebimentoPersistido(EntityManager em) {
		TipoDeCobrancaRecebimento t = criaTipoDeCobrancaRecebimento();
		TipoDeCobrancaRecebimentoDao dao = new TipoDeCobrancaRecebimentoDao(em);
		//
		dao.persist(t);
		return t;
	}
}
