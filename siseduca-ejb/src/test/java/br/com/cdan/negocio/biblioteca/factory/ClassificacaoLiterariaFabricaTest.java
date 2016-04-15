package br.com.cdan.negocio.biblioteca.factory;

import java.util.Set;

import br.com.cdan.model.biblioteca.ClassificacaoLiteraria;

public class ClassificacaoLiterariaFabricaTest {
	private static ClassificacaoLiterariaFabricaTest instance = null; 

	public static synchronized ClassificacaoLiterariaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ClassificacaoLiterariaFabricaTest();
		}
		return instance;		
	}	
	public Set<ClassificacaoLiteraria> criaClassificacaoLiteraria() {		
		return null;
	}

}
