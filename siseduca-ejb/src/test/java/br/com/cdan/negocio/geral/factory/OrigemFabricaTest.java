package br.com.cdan.negocio.geral.factory;

import br.com.cdan.model.geral.Origem;

public class OrigemFabricaTest {
	private static OrigemFabricaTest instance = null;

	public static synchronized OrigemFabricaTest getInstance() {
		if (instance == null) {
			instance = new OrigemFabricaTest();
		}
		return instance;
	}

	public Origem criaOrigem() {
		// TODO Auto-generated method stub
		return null;
	}

}
