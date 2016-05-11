package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.CFOP;
import br.com.cdan.model.financeiro.NfeLayout;
import br.com.cdan.negocio.financeiro.CFOPDao;
import br.com.cdan.negocio.financeiro.NfeLayoutDao;

public class CFOPFabricaTest {
	private static CFOPFabricaTest instance = null;

	public static synchronized CFOPFabricaTest getInstance() {
		if (instance == null) {
			instance = new CFOPFabricaTest();
		}
		return instance;
	}

	public CFOP criaCFOP() {
		CFOP c = new CFOP();
		c.setAtivo(Boolean.TRUE);
		c.setCodigoReceita(Long.valueOf(10));
		c.setDescricao("descricao");
		c.setNfeLayout(NfeLayoutFabricaTest.getInstance().criaNfeLayout());
		return c;
	}

	public CFOP criaCFOPPersistido(EntityManager em) {
		CFOP c = criaCFOP();
		CFOPDao dao = new CFOPDao(em);
		//
		NfeLayoutDao nfeLayoutDao = new NfeLayoutDao(em);
		NfeLayout nfeLayout = c.getNfeLayout();
		nfeLayoutDao.persist(nfeLayout);
		c.setNfeLayout(nfeLayout);
		//
		dao.persist(c);
		return c;
	}
}
