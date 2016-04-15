package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.biblioteca.SerieColecaoLiteral;

public class SerieColecaoLiteralFabricaTest {
	private static SerieColecaoLiteralFabricaTest instance;

	public static synchronized SerieColecaoLiteralFabricaTest getInstance() {	
		if (instance == null) {
			instance = new SerieColecaoLiteralFabricaTest();
		}
		return instance;
	}

	public SerieColecaoLiteral criaSerieColecaoLitera() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
