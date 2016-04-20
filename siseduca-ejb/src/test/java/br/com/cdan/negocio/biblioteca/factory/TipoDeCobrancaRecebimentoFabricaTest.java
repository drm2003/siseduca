package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.financeiro.Caixa;
import br.com.cdan.model.financeiro.ContasAReceber;
import br.com.cdan.model.financeiro.TipoDeCobrancaRecebimento;

public class TipoDeCobrancaRecebimentoFabricaTest {
	private static TipoDeCobrancaRecebimentoFabricaTest instance = null;

	public static synchronized TipoDeCobrancaRecebimentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeCobrancaRecebimentoFabricaTest();
		}
		return instance;
	}

	public TipoDeCobrancaRecebimento criaTipoDeCobrancaRecebimento() {
		TipoDeCobrancaRecebimento t = new TipoDeCobrancaRecebimento();
		t.setAtivo(Boolean.TRUE);
		// Caixas
		Set<Caixa> caixas = new LinkedHashSet<>();
		caixas.add(CaixaFabricaTest.getInstance().criaCaixa());
		caixas.add(CaixaFabricaTest.getInstance().criaCaixa());
		t.setCaixas(caixas);
		// Contas a receber
		Set<ContasAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
		contasAReceber.add(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
		t.setContasAReceber(contasAReceber);
		//
		t.setDescricao("descricao");
		return t;
	}

}
