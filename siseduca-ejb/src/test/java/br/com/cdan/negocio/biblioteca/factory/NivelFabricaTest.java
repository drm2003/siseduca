package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.biblioteca.Nivel;

public class NivelFabricaTest {
	private static NivelFabricaTest instance;

	public static NivelFabricaTest getInstance() {
		if (instance == null) {
			instance = new NivelFabricaTest();
		}
		return instance;
	}

	public Nivel criaNivel() {
		// TODO Auto-generated method stub
		return null;
	}

}
