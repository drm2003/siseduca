package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.geral.Telefone;

public class TelefoneFabricaTest {
	private static TelefoneFabricaTest instance = null;

	public static TelefoneFabricaTest getInstance() {
		if (instance == null) {
			instance = new TelefoneFabricaTest();
		}
		return instance;
	}

	public Telefone criaTelefone() {
		Telefone telefone = new Telefone();
		telefone.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento());
		telefone.setAtivo(Boolean.TRUE);
		telefone.setBanco(BancoFabricaTest.getInstance().criaBanco());
		telefone.setEditora(EditoraFabricaTest.getInstance().criaEditora());
		telefone.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresa());
		telefone.set
		return null;
	}

}
