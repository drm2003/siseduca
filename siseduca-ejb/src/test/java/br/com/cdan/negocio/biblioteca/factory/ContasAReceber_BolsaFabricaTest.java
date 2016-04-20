package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.financeiro.ContasAReceber_Bolsa;

public class ContasAReceber_BolsaFabricaTest {
	private static ContasAReceber_BolsaFabricaTest instance = null;

	public static synchronized ContasAReceber_BolsaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContasAReceber_BolsaFabricaTest();
		}
		return instance;
	}
	
	public ContasAReceber_Bolsa criaContasAReceber_BolsaFabricaTest(){
		ContasAReceber_Bolsa c = new ContasAReceber_Bolsa();
		c.setBolsa(BolsaFabricaTest.getInstance().criaBolsa());
		c.setContasAReceber(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
	}

}
