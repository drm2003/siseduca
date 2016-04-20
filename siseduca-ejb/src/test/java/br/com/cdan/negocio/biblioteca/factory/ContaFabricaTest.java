package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Caixa;
import br.com.cdan.model.financeiro.Conta;
import br.com.cdan.model.financeiro.ContasAReceber;

public class ContaFabricaTest {
	private static ContaFabricaTest instance = null;

	public static synchronized ContaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContaFabricaTest();
		}
		return instance;
	}

	public Conta criaConta() {
		Conta c = new Conta();
		c.setAtivo(Boolean.TRUE);
		c.setBanco(BancoFabricaTest.getInstance().criaBanco());
		// Caixas
		Set<Caixa> caixas = new LinkedHashSet<>();
		caixas.add(CaixaFabricaTest.getInstance().criaCaixa());
		caixas.add(CaixaFabricaTest.getInstance().criaCaixa());
		c.setCaixas(caixas);
		// Contas a receber
		Set<ContasAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
		contasAReceber.add(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
		c.setContasAReceber(contasAReceber);
		// Empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		c.setEmpresa(empresas);
		//
		c.setEncerrada(Boolean.FALSE);
		c.setNumero("numero");
		c.setTitular("titular");
	}
}
