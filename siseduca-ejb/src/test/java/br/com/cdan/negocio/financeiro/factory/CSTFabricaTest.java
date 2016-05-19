package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.CST;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.financeiro.CSTDao;

public class CSTFabricaTest extends FabricaTest {
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
		c.setCodigoReceita(Long.valueOf(criarStringDinamicaPorTamanho(10)));
		c.setDescricao("descricao" + Math.random() * 10000);
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
