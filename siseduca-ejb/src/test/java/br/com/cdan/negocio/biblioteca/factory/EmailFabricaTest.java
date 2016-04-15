package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Email;
import br.com.cdan.negocio.biblioteca.EmailDao;

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
		a.setEmpresa(new EmpresaFabricaTest().criaEmpresa());
		a.setPessoa(PessoaFabricaTest().criaPessoa());
		a.setPrincipal(true);
		a.setUsuario(UsuarioFabricaTest().criaUsuario());		
		return a;
	}

	public Email criaEmailPersistido(EntityManager em) {
		EmailDao dao = new EmailDao();
		dao.setEntityManager(em);
		//
		Email a = criaEmail();
		dao.persist(a);
		return a;
	}
}