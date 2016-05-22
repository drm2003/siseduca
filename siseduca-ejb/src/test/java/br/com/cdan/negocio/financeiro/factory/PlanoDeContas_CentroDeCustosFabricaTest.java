package br.com.cdan.negocio.financeiro.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.PlanoDeContas_CentroDeCustos;
import br.com.cdan.model.financeiro.PlanoDeContas_CentroDeCustosPK;
import br.com.cdan.negocio.financeiro.PlanoDeContas_CentroDeCustosDao;

public class PlanoDeContas_CentroDeCustosFabricaTest {
	private static PlanoDeContas_CentroDeCustosFabricaTest instance = null;

	public static synchronized PlanoDeContas_CentroDeCustosFabricaTest getInstance() {
		if (instance == null) {
			instance = new PlanoDeContas_CentroDeCustosFabricaTest();
		}
		return instance;
	}

	public PlanoDeContas_CentroDeCustos criaPlanoDeContas_CentroDeCustos(EntityManager em) {
		PlanoDeContas_CentroDeCustos p = new PlanoDeContas_CentroDeCustos();
		p.setAtivo(Boolean.TRUE);
		p.setCentroDeCustos(CentroDeCustosFabricaTest.getInstance().criaCentroDeCustosPersistido(em));
		p.setPlanoDeContas(PlanoDeContaFabricaTest.getInstance().criaPlanoDeContasPersistido(em));
		p.setPercentual(BigDecimal.valueOf(50.0));
		p.setId(new PlanoDeContas_CentroDeCustosPK(p.getCentroDeCustos().getId(), p.getPlanoDeContas().getId()));
		return p;
	}

	public PlanoDeContas_CentroDeCustos criaPlanoDeContas_CentroDeCustosPersistido(EntityManager em) {
		PlanoDeContas_CentroDeCustos p = criaPlanoDeContas_CentroDeCustos(em);
		PlanoDeContas_CentroDeCustosDao dao = new PlanoDeContas_CentroDeCustosDao(em);
		//
		dao.persist(p);
		return p;
	}
}
