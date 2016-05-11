package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.NfeLayout;
import br.com.cdan.model.financeiro.TIPI;
import br.com.cdan.negocio.financeiro.NfeLayoutDao;
import br.com.cdan.negocio.financeiro.TIPIDao;

public class TIPIFabricaTest {
	private static TIPIFabricaTest instance = null;

	public static synchronized TIPIFabricaTest getInstance() {
		if (instance == null) {
			instance = new TIPIFabricaTest();
		}
		return instance;
	}

	public TIPI criaTIPI() {
		TIPI t = new TIPI();
		t.setAtivo(Boolean.TRUE);
		t.setCodigoReceita(Long.valueOf(10));
		t.setDescricao("descricao");
		t.setNfeLayout(NfeLayoutFabricaTest.getInstance().criaNfeLayout());
		return t;
	}

	public TIPI criaTIPIPersistido(EntityManager em) {
		TIPI t = criaTIPI();
		TIPIDao dao = new TIPIDao(em);
		//
		NfeLayoutDao nfeLayoutDao = new NfeLayoutDao(em);
		NfeLayout nfeLayout = t.getNfeLayout();
		nfeLayoutDao.persist(nfeLayout);
		t.setNfeLayout(nfeLayout);
		//
		dao.persist(t);
		return t;
	}
}
