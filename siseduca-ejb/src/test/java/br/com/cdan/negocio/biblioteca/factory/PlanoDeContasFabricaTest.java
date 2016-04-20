package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.comum.EnumTipoDePlanoDeContas;
import br.com.cdan.model.financeiro.ContasAReceber;
import br.com.cdan.model.financeiro.PlanoDeContas;
import br.com.cdan.model.financeiro.PlanoDeContas_CentroDeCustos;

public class PlanoDeContasFabricaTest {
	private static PlanoDeContasFabricaTest instance = null;

	public static synchronized PlanoDeContasFabricaTest getInstance() {
		if (instance == null) {
			instance = new PlanoDeContasFabricaTest();
		}
		return instance;
	}

	public PlanoDeContas criaPlanoDeContas() {
		PlanoDeContas p = new PlanoDeContas();
		p.setAtivo(Boolean.TRUE);
		p.setCodigo(Long.valueOf("1"));
		p.setCompartilhado(Boolean.TRUE);
		// Contas as receber
		Set<ContasAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
		contasAReceber.add(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
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

}
