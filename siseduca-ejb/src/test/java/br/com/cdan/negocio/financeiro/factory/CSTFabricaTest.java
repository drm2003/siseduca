package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.CST;
import br.com.cdan.negocio.financeiro.CSTDao;

public class CSTFabricaTest {
	private static CSTFabricaTest instance = null;

	public static synchronized CSTFabricaTest getInstance() {
		if (instance == null) {
			instance = new CSTFabricaTest();
		}
		return instance;
	}

	public CST criaCST(EntityManager em) {
		CST c = new CST();
		c.setAtivo(Boolean.TRUE);
		c.setCodigoReceita(Long.valueOf(10));
		c.setDescricao("descricao");
		c.setNfeLayout(NfeLayoutFabricaTest.getInstance().criaNfeLayoutPersistido(em));
		return c;
	}

	public CST criaCSTPersistido(EntityManager em) {
		CST c = criaCST(em);
		CSTDao dao = new CSTDao(em);
		//
		dao.persist(c);
		return c;
	}
}
