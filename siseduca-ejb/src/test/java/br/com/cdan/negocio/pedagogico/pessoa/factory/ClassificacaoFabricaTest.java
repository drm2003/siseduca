package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Classificacao;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.negocio.pedagogico.pessoa.ClassificacaoDao;
import br.com.cdan.negocio.pedagogico.pessoa.PessoaDao;

public class ClassificacaoFabricaTest {
	private static ClassificacaoFabricaTest instance = null;

	public static synchronized ClassificacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new ClassificacaoFabricaTest();
		}
		return instance;
	}

	public Classificacao criaClassificacao() {
		Classificacao c = new Classificacao();
		c.setAtivo(Boolean.TRUE);
		c.setDescricao("descricao");
		c.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		return c;
	}

	public Classificacao criaClassificacaoPersistido(EntityManager em) {
		Classificacao c = criaClassificacao();
		ClassificacaoDao dao = new ClassificacaoDao(em);
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
