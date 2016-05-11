package br.com.cdan.negocio.contato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.contato.TipoDeContato;
import br.com.cdan.negocio.contato.TipoDeContatoDao;

public class TipoDeContatoFabricaTest {
	private static TipoDeContatoFabricaTest instance = null;

	public static synchronized TipoDeContatoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeContatoFabricaTest();
		}
		return instance;
	}

	public TipoDeContato criaTipoDeContato() {
		TipoDeContato t = new TipoDeContato();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		return t;
	}

	public TipoDeContato criaTipoDeContatoPersistido(EntityManager em) {
		TipoDeContato t = criaTipoDeContato();
		TipoDeContatoDao dao = new TipoDeContatoDao(em);
		dao.persist(t);
		return t;
	}
}
