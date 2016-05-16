package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Email;
import br.com.cdan.negocio.acesso.factory.UsuarioFabricaTest;
import br.com.cdan.negocio.geral.EmailDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.PessoaFabricaTest;

public class EmailFabricaTest {
	private static EmailFabricaTest instance = null;

	public static synchronized EmailFabricaTest getInstance() {
		if (instance == null) {
			instance = new EmailFabricaTest();
		}
		return instance;
	}

	public Email criaEmail(EntityManager em) {
		Email a = new Email();
		a.setAtivo(Boolean.TRUE);
		a.setDescricao("Teste " + Math.random() * 1000);
		a.setPessoa(PessoaFabricaTest.getInstance().criaPessoaPersistido(em));
		a.setPrincipal(true);
		a.setUsuario(UsuarioFabricaTest.getInstance().criaUsuarioPersistido(em));
		return a;
	}

	public Email criaEmailPersistido(EntityManager em) {
		Email a = criaEmail(em);
		EmailDao dao = new EmailDao(em);
		//
		dao.persist(a);
		return a;
	}
}