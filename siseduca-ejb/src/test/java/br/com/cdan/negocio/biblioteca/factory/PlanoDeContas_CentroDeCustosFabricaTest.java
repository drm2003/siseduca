package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.CentroDeCustos;
import br.com.cdan.model.financeiro.PlanoDeConta;
import br.com.cdan.model.financeiro.PlanoDeContas_CentroDeCustos;
import br.com.cdan.negocio.biblioteca.CentroDeCustosDao;
import br.com.cdan.negocio.biblioteca.PlanoDeContaDao;
import br.com.cdan.negocio.biblioteca.PlanoDeContas_CentroDeCustosDao;

public class PlanoDeContas_CentroDeCustosFabricaTest {
	private static PlanoDeContas_CentroDeCustosFabricaTest instance = null;

	public static synchronized PlanoDeContas_CentroDeCustosFabricaTest getInstance() {
		if (instance == null) {
			instance = new PlanoDeContas_CentroDeCustosFabricaTest();
		}
		return instance;
	}

	public PlanoDeContas_CentroDeCustos criaPlanoDeContas_CentroDeCustos() {
		PlanoDeContas_CentroDeCustos p = new PlanoDeContas_CentroDeCustos();
		p.setAtivo(Boolean.TRUE);
		p.setCentroDeCustos(CentroDeCustosFabricaTest.getInstance().criaCentroDeCustos());
		p.setPercentual(BigDecimal.valueOf(50.0));
		p.setPlanoDeContas(PlanoDeContasFabricaTest.getInstance().criaPlanoDeContas());
		return p;
	}

	public PlanoDeContas_CentroDeCustos criaPlanoDeContas_CentroDeCustosPersistido(EntityManager em) {
		PlanoDeContas_CentroDeCustos p = criaPlanoDeContas_CentroDeCustos();
		PlanoDeContas_CentroDeCustosDao dao = new PlanoDeContas_CentroDeCustosDao(em);
		//
		CentroDeCustosDao centroDeCustosDao = new CentroDeCustosDao(em);
		CentroDeCustos centroDeCustos = p.getCentroDeCustos();
		centroDeCustosDao.persist(centroDeCustos);
		p.setCentroDeCustos(centroDeCustos);
		//
		PlanoDeContaDao planoDeContaDao = new PlanoDeContaDao(em);
		PlanoDeConta planoDeContas = p.getPlanoDeContas();
		planoDeContaDao.persist(planoDeContas);
		p.setPlanoDeContas(planoDeContas);
		//
		dao.persist(p);
		return p;
	}
}
