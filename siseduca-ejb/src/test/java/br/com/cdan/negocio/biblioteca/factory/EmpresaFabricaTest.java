package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.financeiro.ContasAPagar;
import br.com.cdan.model.geral.cep.CEP;

public class EmpresaFabricaTest {
	private static EmpresaFabricaTest instance = null;

	public static synchronized EmpresaFabricaTest getInstance() {
		if (instance == null) {
			instance = new EmpresaFabricaTest();
		}
		return instance;
	}

	public Empresa criaEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setAtivo(Boolean.TRUE);
		// Bolsas
		Set<Bolsa> bolsas = new LinkedHashSet<>();
		bolsas.add(BolsaFabricaTest.getInstance().criaBolsa());
		bolsas.add(BolsaFabricaTest.getInstance().criaBolsa());
		empresa.setBolsas(bolsas);
		// CEPs
		Set<CEP> ceps = new LinkedHashSet<>();
		ceps.add(CEPFabricaTest.getInstance().criaCEP());
		ceps.add(CEPFabricaTest.getInstance().criaCEP());
		empresa.setCep(ceps);
		//
		empresa.setCnpj("cnpj");
		empresa.setCodigoArea("codigo Area");
		// Contas a pagar
		Set<ContasAPagar> contasAPagar = new LinkedHashSet<>();
		contasAPagar.add(ContasAPagarFabricaTest.getInstance().criaContasAPagar());
		empresa.setContasAPagar(contasAPagar);
		return empresa;
	}

}
