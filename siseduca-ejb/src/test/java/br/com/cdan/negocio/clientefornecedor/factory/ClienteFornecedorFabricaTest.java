package br.com.cdan.negocio.clientefornecedor.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDePessoa;
import br.com.cdan.model.clientefornecedor.ClienteFornecedor;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.negocio.clientefornecedor.ClienteFornecedorDao;
import br.com.cdan.negocio.empresa.EmpresaDao;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.PessoaDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.PessoaFabricaTest;

public class ClienteFornecedorFabricaTest {
	private static ClienteFornecedorFabricaTest instance = null;

	public static synchronized ClienteFornecedorFabricaTest getInstance() {
		if (instance == null) {
			instance = new ClienteFornecedorFabricaTest();
		}
		return instance;
	}

	public ClienteFornecedor criaClienteFornecedor() {
		ClienteFornecedor c = new ClienteFornecedor();
		c.setAtivo(Boolean.TRUE);
		c.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresa());
		c.setNome("nome");
		c.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		c.setRazaoSocial("razaoSocial");
		c.setTipoPessoa(EnumTipoDePessoa.F);
		return c;
	}

	public ClienteFornecedor criaClienteFornecedorPersistido(EntityManager em) {
		ClienteFornecedor c = criaClienteFornecedor();
		ClienteFornecedorDao dao = new ClienteFornecedorDao(em);
		//
		EmpresaDao empresaDao = new EmpresaDao(em);
		Empresa empresa = c.getEmpresa();
		empresaDao.persist(empresa);
		c.setEmpresa(empresa);
		//
		PessoaDao pessoaDao = new PessoaDao(em);
		Pessoa pessoa = c.getPessoa();
		pessoaDao.persist(pessoa);
		c.setPessoa(pessoa);
		//
		dao.persist(c);
		return c;
	}

}
