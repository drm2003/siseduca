package br.com.cdan.negocio.geral.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.geral.Email;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.negocio.acesso.UsuarioDao;
import br.com.cdan.negocio.acesso.factory.UsuarioFabricaTest;
import br.com.cdan.negocio.empresa.EmpresaDao;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;
import br.com.cdan.negocio.geral.EmailDao;
import br.com.cdan.negocio.pedagogico.pessoa.PessoaDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.PessoaFabricaTest;

public class EmailFabricaTest {
	private static EmailFabricaTest instance = null;

	public static synchronized EmailFabricaTest getInstance() {
		if (instance == null) {
			instance = new EmailFabricaTest();
		}
		return instance;
	}

	public Email criaEmail() {
		Email a = new Email();
		a.setAtivo(Boolean.TRUE);
		a.setDescricao("Teste " + Math.random() * 1000);
		// empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		a.setEmpresas(empresas);
		//
		a.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		a.setPrincipal(true);
		a.setUsuario(UsuarioFabricaTest.getInstance().criaUsuario());
		return a;
	}

	public Email criaEmailPersistido(EntityManager em) {
		Email a = criaEmail();
		EmailDao dao = new EmailDao(em);
		// gravar empresas
		EmpresaDao empresaDao = new EmpresaDao(em);
		Set<Empresa> empresas = new LinkedHashSet<>();
		a.getEmpresas().forEach(e -> {
			empresaDao.persist(e);
			empresas.add(e);
		});
		// gravar pessoa
		PessoaDao pessoaDao = new PessoaDao(em);
		Pessoa pessoa = a.getPessoa();
		pessoaDao.persist(pessoa);
		// gravar usu�rio
		UsuarioDao usuarioDao = new UsuarioDao(em);
		Usuario usuario = a.getUsuario();
		usuarioDao.persist(usuario);
		//
		dao.persist(a);
		return a;
	}
}