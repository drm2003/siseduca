package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.empresa.Empresa;

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
		empresa.setBolsas(Bosafabric);
		return empresa;
	}

}
