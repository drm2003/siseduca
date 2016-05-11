package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.NfeLayout;
import br.com.cdan.model.financeiro.OrigemMercadoria;
import br.com.cdan.negocio.financeiro.NfeLayoutDao;
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
		o.setDescricao("descricao");
		o.setNfeLayout(NfeLayoutFabricaTest.getInstance().criaNfeLayout());
		return o;
	}

	public OrigemMercadoria criaOrigemMercadoriaPersistido(EntityManager em) {
		OrigemMercadoria o = criaOrigemMercadoria();
		OrigemMercadoriaDao dao = new OrigemMercadoriaDao(em);
		//
		NfeLayoutDao nfeLayoutDao = new NfeLayoutDao(em);
		NfeLayout nfeLayout = o.getNfeLayout();
		nfeLayoutDao.persist(nfeLayout);
		o.setNfeLayout(nfeLayout);
		//
		dao.persist(o);
		return o;
	}
}