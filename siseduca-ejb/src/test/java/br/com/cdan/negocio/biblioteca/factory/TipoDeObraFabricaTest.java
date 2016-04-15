package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.biblioteca.TipoDeObra;

public class TipoDeObraFabricaTest {
	private static TipoDeObraFabricaTest instance = null;

	public static synchronized TipoDeObraFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeObraFabricaTest();
		}
		return instance;
	}

	public TipoDeObra criaTipoDeObra() {
		// TODO Auto-generated method stub
		return null;
	}

}
