package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.OrigemMercadoria;
import br.com.cdan.negocio.financeiro.OrigemMercadoriaDao;

public class OrigemMercadoriaFabricaTest {
	private static OrigemMercadoriaFabricaTest instance = null;

	public static synchronized OrigemMercadoriaFabricaTest getInstance() {
		if (instance == null) {
			instance = new OrigemMercadoriaFabricaTest();
		}
		return instance;
	}

	public OrigemMercadoria criaOrigemMercadoria() {
		OrigemMercadoria o = new OrigemMercadoria();
		o.setAtivo(Boolean.TRUE);
		//
		o.setDescricao("descricao" + Math.random() * 10000);
		return o;
	}

	public OrigemMercadoria criaOrigemMercadoriaPersistido(EntityManager em) {
		OrigemMercadoria o = criaOrigemMercadoria();
		OrigemMercadoriaDao dao = new OrigemMercadoriaDao(em);
		//
		dao.persist(o);
		return o;
	}
}