package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.TipoDeEmail;
import br.com.cdan.negocio.geral.TipoDeEmailDao;

public class TipoDeEmailFabricaTest {
	private static TipoDeEmailFabricaTest instance = null;

	public static synchronized TipoDeEmailFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeEmailFabricaTest();
		}
		return instance;
	}

	public TipoDeEmail criaTipoDeEmail() {
		TipoDeEmail t = new TipoDeEmail();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		return t;
	}

	public TipoDeEmail criaTipoDeEmailPersistido(EntityManager em) {
		TipoDeEmail t = criaTipoDeEmail();
		TipoDeEmailDao dao = new TipoDeEmailDao(em);
		dao.persist(t);
		return t;
	}
}
