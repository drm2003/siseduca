package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.CFOP;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.financeiro.CFOPDao;

public class CFOPFabricaTest extends FabricaTest {
	private static CFOPFabricaTest instance = null;

	public static synchronized CFOPFabricaTest getInstance() {
		if (instance == null) {
			instance = new CFOPFabricaTest();
		}
		return instance;
	}

	public CFOP criaCFOP(EntityManager em) {
		CFOP c = new CFOP();
		c.setAtivo(Boolean.TRUE);
		c.setCodigoReceita(Long.parseLong(criarStringDinamicaPorTamanho(10)));
		c.setDescricao("descricao " + Math.random() * 10000);
		return c;
	}

	public CFOP criaCFOPPersistido(EntityManager em) {
		CFOP c = criaCFOP(em);
		CFOPDao dao = new CFOPDao(em);
		//
		dao.persist(c);
		return c;
	}
}
