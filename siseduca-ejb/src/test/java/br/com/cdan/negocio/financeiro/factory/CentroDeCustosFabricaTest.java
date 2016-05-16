package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.CentroDeCustos;
import br.com.cdan.negocio.financeiro.CentroDeCustosDao;

public class CentroDeCustosFabricaTest {
	private static CentroDeCustosFabricaTest instance = null;

	public static synchronized CentroDeCustosFabricaTest getInstance() {
		if (instance == null) {
			instance = new CentroDeCustosFabricaTest();
		}
		return instance;
	}

	public CentroDeCustos criaCentroDeCustos() {
		CentroDeCustos c = new CentroDeCustos();
		c.setAtivo(Boolean.TRUE);
		c.setCompartilhado(Boolean.TRUE);
		c.setNome("nome");
		//
		return c;
	}

	public CentroDeCustos criaCentroDeCustosPersistido(EntityManager em) {
		CentroDeCustos c = criaCentroDeCustos();
		CentroDeCustosDao dao = new CentroDeCustosDao(em);
		//
		dao.persist(c);
		return c;
	}
}
