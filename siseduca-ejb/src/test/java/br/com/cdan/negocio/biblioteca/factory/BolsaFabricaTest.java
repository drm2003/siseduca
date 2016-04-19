package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.financeiro.Bolsa;

public class BolsaFabricaTest {
	private static BolsaFabricaTest instance = null;

	public static synchronized BolsaFabricaTest getInstance() {
		if (instance == null) {
			instance = new BolsaFabricaTest();
		}
		return instance;
	}

	public Bolsa criaBolsa() {
		// TODO Auto-generated method stub
		return null;
	}

}
