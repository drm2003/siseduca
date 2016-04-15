package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.biblioteca.Setor;

public class SetorFabricaTest {
	private static SetorFabricaTest instance = null;

	public static synchronized SetorFabricaTest getInstance() {
		if (instance == null) {
			instance = new SetorFabricaTest();
		}
		return instance;
	}

	public Setor criaSetor() {
		// TODO Auto-generated method stub
		return null;
	}

}
