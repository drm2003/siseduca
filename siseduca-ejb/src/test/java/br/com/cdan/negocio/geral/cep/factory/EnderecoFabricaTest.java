package br.com.cdan.negocio.geral.cep.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Endereco;
import br.com.cdan.negocio.biblioteca.factory.EditoraFabricaTest;
import br.com.cdan.negocio.geral.EnderecoDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.AproveitamentoFabricaTest;

public class EnderecoFabricaTest {
	private static EnderecoFabricaTest instance = null;

	public static synchronized EnderecoFabricaTest getInstance() {
		if (instance == null) {
			instance = new EnderecoFabricaTest();
		}
		return instance;
	}

	public Endereco criaEndereco(EntityManager em) {
		Endereco e = new Endereco();
		e.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento(em));
		e.setAtivo(Boolean.TRUE);
		e.setBairro(BairroFabricaTest.getInstance().criaBairroPersistido(em));
		e.setCep(CEPFabricaTest.getInstance().criaCepPersistido(em));
		e.setCidade(CidadeFabricaTest.getInstance().criaCidadePersistido(em));
		e.setComplemento("teste");
		e.setEditora(EditoraFabricaTest.getInstance().criaEditoraPersistido(em));
		return e;
	}

	public Endereco criaEnderecoPersistido(EntityManager em) {
		Endereco e = criaEndereco(em);
		EnderecoDao dao = new EnderecoDao(em);
		dao.persist(e);
		return e;
	}
}
