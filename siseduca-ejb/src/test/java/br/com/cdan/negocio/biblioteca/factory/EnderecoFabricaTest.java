package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.geral.Endereco;

public class EnderecoFabricaTest {
	private static EnderecoFabricaTest instance = null;

	public static synchronized EnderecoFabricaTest getInstance() {
		if (instance == null) {
			instance = new EnderecoFabricaTest();
		}
		return instance;
	}

	public Endereco criaEndereco() {
		Endereco e = new Endereco();
		e.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento());
		e.setAtivo(Boolean.TRUE);
		e.setBairro(bairro);
		e.setCep(cep);
		e.setCidade(cidade);
		e.setComplemento("teste");
		e.setEditora(EditoraFabricaTest.getInstance().criaEditora());
		
		return e;
	}

}
