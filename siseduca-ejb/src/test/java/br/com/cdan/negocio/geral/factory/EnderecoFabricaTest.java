package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Endereco;
import br.com.cdan.negocio.biblioteca.factory.EditoraFabricaTest;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.EnderecoDao;
import br.com.cdan.negocio.geral.cep.factory.BairroFabricaTest;
import br.com.cdan.negocio.geral.cep.factory.CEPFabricaTest;
import br.com.cdan.negocio.geral.cep.factory.CidadeFabricaTest;

public class EnderecoFabricaTest extends FabricaTest {
	private static EnderecoFabricaTest instance = null;

	public static synchronized EnderecoFabricaTest getInstance() {
		if (instance == null) {
			instance = new EnderecoFabricaTest();
		}
		return instance;
	}

	public Endereco criaEndereco(EntityManager em) {
		Endereco e = new Endereco();
		e.setAtivo(Boolean.TRUE);
		e.setBairro(BairroFabricaTest.getInstance().criaBairroPersistido(em));
		e.setCep(CEPFabricaTest.getInstance().criaCepPersistido(em));
		e.setCidade(CidadeFabricaTest.getInstance().criaCidadePersistido(em));
		e.setComplemento("teste");
		e.setEditora(EditoraFabricaTest.getInstance().criaEditoraPersistido(em));
		e.setLogradouro(criarStringDinamicaPorTamanho(100));
		return e;
	}

	public Endereco criaEnderecoPersistido(EntityManager em) {
		Endereco e = criaEndereco(em);
		EnderecoDao dao = new EnderecoDao(em);
		dao.persist(e);
		return e;
	}
}
