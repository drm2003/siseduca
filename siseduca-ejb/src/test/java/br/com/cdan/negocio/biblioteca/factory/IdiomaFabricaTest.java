package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.biblioteca.Idioma;

public class IdiomaFabricaTest {
	private static IdiomaFabricaTest instance;

	public static synchronized IdiomaFabricaTest getInstance() {
		if (instance == null) {
			instance = new IdiomaFabricaTest();
		}
		return instance;
	}

	public Idioma criaIdioma() {
		// TODO Auto-generated method stub
		return null;
	}

}
