package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.CentroDeCustos;
import br.com.cdan.model.financeiro.PlanoDeContas_CentroDeCustos;
import br.com.cdan.negocio.biblioteca.CentroDeCustosDao;
import br.com.cdan.negocio.biblioteca.PlanoDeContas_CentroDeCustosDao;

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
		Set<PlanoDeContas_CentroDeCustos> planosDeContas_CentrosDeCustos = new LinkedHashSet<>();
		planosDeContas_CentrosDeCustos
				.add(PlanoDeContas_CentroDeCustosFabricaTest.getInstance().criaPlanoDeContas_CentroDeCustos());
		planosDeContas_CentrosDeCustos
				.add(PlanoDeContas_CentroDeCustosFabricaTest.getInstance().criaPlanoDeContas_CentroDeCustos());
		c.setPlanoDeContas_CentroDeCustos(planosDeContas_CentrosDeCustos);
		return c;
	}

	public CentroDeCustos criaCentroDeCustosPersistido(EntityManager em) {
		CentroDeCustos c = criaCentroDeCustos();
		CentroDeCustosDao dao = new CentroDeCustosDao(em);
		//
		Set<PlanoDeContas_CentroDeCustos> planosDeContas_CentrosDeCustos = new LinkedHashSet<>();
		PlanoDeContas_CentroDeCustosDao planoDeContas_CentroDeCustosDao = new PlanoDeContas_CentroDeCustosDao(em);
		c.getPlanoDeContas_CentroDeCustos().forEach(planoDeContas_CentroDeCustos -> {
			planoDeContas_CentroDeCustosDao.persist(planoDeContas_CentroDeCustos);
			planosDeContas_CentrosDeCustos.add(planoDeContas_CentroDeCustos);
		});
		c.setPlanoDeContas_CentroDeCustos(planosDeContas_CentrosDeCustos);
		//
		dao.persist(c);
		return c;
	}
}
