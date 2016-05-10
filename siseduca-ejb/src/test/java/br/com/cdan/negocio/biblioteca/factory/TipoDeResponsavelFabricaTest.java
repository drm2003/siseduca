package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.TipoDeResponsavel;
import br.com.cdan.negocio.biblioteca.TipoDeResponsavelDao;

public class TipoDeResponsavelFabricaTest {
	private static TipoDeResponsavelFabricaTest instance = null;

	public static synchronized TipoDeResponsavelFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeResponsavelFabricaTest();
		}
		return instance;
	}

	public TipoDeResponsavel criaTipoDeResponsavel() {
		TipoDeResponsavel t = new TipoDeResponsavel();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		return t;
	}

	public TipoDeResponsavel criaTipoDeResponsavelPersistido(EntityManager em) {
		TipoDeResponsavel t = new TipoDeResponsavel();
		TipoDeResponsavelDao dao = new TipoDeResponsavelDao(em);
		dao.persist(t);
		return t;
	}
}
