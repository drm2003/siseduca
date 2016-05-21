package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.TipoDeEmail;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.TipoDeEmailDao;

public class TipoDeEmailFabricaTest extends FabricaTest {
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
		t.setDescricao(criarStringDinamicaPorTamanho(100));
		return t;
	}

	public TipoDeEmail criaTipoDeEmailPersistido(EntityManager em) {
		TipoDeEmail t = criaTipoDeEmail();
		TipoDeEmailDao dao = new TipoDeEmailDao(em);
		dao.persist(t);
		return t;
	}
}
