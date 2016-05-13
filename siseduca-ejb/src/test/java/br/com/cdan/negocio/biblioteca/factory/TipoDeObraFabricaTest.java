package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.TipoDeObra;
import br.com.cdan.negocio.biblioteca.TipoDeObraDao;

public class TipoDeObraFabricaTest {
	private static TipoDeObraFabricaTest instance = null;

	public static synchronized TipoDeObraFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeObraFabricaTest();
		}
		return instance;
	}

	public TipoDeObra criaTipoDeObra() {
		TipoDeObra t = new TipoDeObra();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		return t;
	}

	public TipoDeObra criaTipoDeObraPersistido(EntityManager em) {
		TipoDeObra t = criaTipoDeObra();
		TipoDeObraDao dao = new TipoDeObraDao(em);
		dao.persist(t);
		return t;
	}

}
