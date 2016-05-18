package br.com.cdan.negocio.estoque.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.estoque.UnidadeDeMedida;
import br.com.cdan.negocio.estoque.UnidadeDeMedidaDao;

public class UnidadeDeMedidaFabricaTest {
	private static UnidadeDeMedidaFabricaTest instance = null;

	public static synchronized UnidadeDeMedidaFabricaTest getInstance() {
		if (instance == null) {
			instance = new UnidadeDeMedidaFabricaTest();
		}
		return instance;
	}

	public UnidadeDeMedida criaUnidadeDeMedida() {
		UnidadeDeMedida u = new UnidadeDeMedida();
		u.setAtivo(Boolean.TRUE);
		u.setDescricao("descricao" + Math.random() * 10000);
		//
		return u;
	}

	public UnidadeDeMedida criaUnidadeDeMedidaPersistido(EntityManager em) {
		UnidadeDeMedida u = criaUnidadeDeMedida();
		UnidadeDeMedidaDao dao = new UnidadeDeMedidaDao(em);
		//
		dao.persist(u);
		return u;
	}
}
