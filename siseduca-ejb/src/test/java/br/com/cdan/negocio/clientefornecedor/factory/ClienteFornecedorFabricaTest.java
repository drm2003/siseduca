package br.com.cdan.negocio.clientefornecedor.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDePessoa;
import br.com.cdan.model.clientefornecedor.ClienteFornecedor;
import br.com.cdan.negocio.clientefornecedor.ClienteFornecedorDao;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.PessoaFabricaTest;

public class ClienteFornecedorFabricaTest {
	private static ClienteFornecedorFabricaTest instance = null;

	public static synchronized ClienteFornecedorFabricaTest getInstance() {
		if (instance == null) {
			instance = new ClienteFornecedorFabricaTest();
		}
		return instance;
	}

	public ClienteFornecedor criaClienteFornecedor(EntityManager em) {
		ClienteFornecedor c = new ClienteFornecedor();
		c.setAtivo(Boolean.TRUE);
		c.setNome("nome" + Math.random() * 1000);
		c.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresaPersistido(em));
		c.setPessoa(PessoaFabricaTest.getInstance().criaPessoaPersistido(em));
		c.setRazaoSocial("razaoSocial");
		c.setTipoDePessoa(EnumTipoDePessoa.F);
		c.setObservacao("observacao");
		return c;
	}

	public ClienteFornecedor criaClienteFornecedorPersistido(EntityManager em) {
		ClienteFornecedor c = criaClienteFornecedor(em);
		ClienteFornecedorDao dao = new ClienteFornecedorDao(em);
		//
		dao.persist(c);
		return c;
	}

}
