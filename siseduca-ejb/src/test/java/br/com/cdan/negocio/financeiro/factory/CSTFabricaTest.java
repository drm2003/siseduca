package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.CST;
import br.com.cdan.model.financeiro.NfeLayout;
import br.com.cdan.negocio.financeiro.CSTDao;
import br.com.cdan.negocio.financeiro.NfeLayoutDao;

public class CSTFabricaTest {
	private static CSTFabricaTest instance = null;

	public static synchronized CSTFabricaTest getInstance() {
		if (instance == null) {
			instance = new CSTFabricaTest();
		}
		return instance;
	}

	public CST criaCST() {
		CST c = new CST();
		c.setAtivo(Boolean.TRUE);
		c.setCodigoReceita(Long.valueOf(10));
		c.setDescricao("descricao");
		c.setNfeLayout(NfeLayoutFabricaTest.getInstance().criaNfeLayout());
		return c;
	}

	public CST criaCSTPersistido(EntityManager em) {
		CST c = criaCST();
		CSTDao dao = new CSTDao(em);
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
