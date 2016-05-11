package br.com.cdan.negocio.financeiro.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDePlanoDeContas;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.financeiro.PlanoDeConta;
import br.com.cdan.model.financeiro.PlanoDeContas_CentroDeCustos;
import br.com.cdan.negocio.financeiro.ContaAReceberDao;
import br.com.cdan.negocio.financeiro.PlanoDeContaDao;
import br.com.cdan.negocio.financeiro.PlanoDeContas_CentroDeCustosDao;

public class PlanoDeContaFabricaTest {
	private static PlanoDeContaFabricaTest instance = null;

	public static synchronized PlanoDeContaFabricaTest getInstance() {
		if (instance == null) {
			instance = new PlanoDeContaFabricaTest();
		}
		return instance;
	}

	public PlanoDeConta criaPlanoDeContas() {
		PlanoDeConta p = new PlanoDeConta();
		p.setAtivo(Boolean.TRUE);
		p.setCodigo(Long.valueOf("1"));
		p.setCompartilhado(Boolean.TRUE);
		// Contas as receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		p.setContasAReceber(contasAReceber);
		//
		p.setItemApenasDeGrupo(Boolean.TRUE);
		p.setItemPermiteDesconto(Boolean.TRUE);
		p.setItemReferenteAMensalidades(Boolean.TRUE);
		p.setItemSujeitoAISS(Boolean.TRUE);
		p.setNome("nome");
		p.setOutrosDetalhes("outrosDetalhes");
		// Plano de contas e centro de custos
		Set<PlanoDeContas_CentroDeCustos> planoDeContas_centroDeCustos = new LinkedHashSet<>();
		planoDeContas_centroDeCustos
				.add(PlanoDeContas_CentroDeCustosFabricaTest.getInstance().criaPlanoDeContas_CentroDeCustos());
		planoDeContas_centroDeCustos
				.add(PlanoDeContas_CentroDeCustosFabricaTest.getInstance().criaPlanoDeContas_CentroDeCustos());
		p.setPlanoDeContas_centroDeCustos(planoDeContas_centroDeCustos);
		//
		p.setTipoDePlanoDeContas(EnumTipoDePlanoDeContas.ENTRADA);
		return p;
	}

	public PlanoDeConta criaPlanoDeContasPersistido(EntityManager em) {
		PlanoDeConta p = criaPlanoDeContas();
		PlanoDeContaDao dao = new PlanoDeContaDao(em);
		// Contas as receber
		ContaAReceberDao contaAReceberDao = new ContaAReceberDao();
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		contaAReceberDao.persist(contasAReceber);
		p.setContasAReceber(contasAReceber);
		// Plano de contas e centro de custos
		PlanoDeContas_CentroDeCustosDao planoDeContas_CentroDeCustosDao = new PlanoDeContas_CentroDeCustosDao(em);
		Set<PlanoDeContas_CentroDeCustos> planosDeContas_centrosDeCustos = new LinkedHashSet<>();
		p.getPlanoDeContas_centroDeCustos().forEach(planoDeContas_centroDeCustos -> {
			planoDeContas_CentroDeCustosDao.persist(planoDeContas_centroDeCustos);
			planosDeContas_centrosDeCustos.add(planoDeContas_centroDeCustos);
		});
		;
		p.setPlanoDeContas_centroDeCustos(planosDeContas_centrosDeCustos);
		//
		dao.persist(p);
		return p;
	}

}
