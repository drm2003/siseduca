package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Classificacao;
import br.com.cdan.negocio.pedagogico.pessoa.ClassificacaoDao;

public class ClassificacaoFabricaTest {
	private static ClassificacaoFabricaTest instance = null;

	public static synchronized ClassificacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new ClassificacaoFabricaTest();
		}
		return instance;
	}

	public Classificacao criaClassificacao(EntityManager em) {
		Classificacao c = new Classificacao();
		c.setAtivo(Boolean.TRUE);
		c.setDescricao("descricao");
		return c;
	}

	public Classificacao criaClassificacaoPersistido(EntityManager em) {
		Classificacao c = criaClassificacao(em);
		ClassificacaoDao dao = new ClassificacaoDao(em);
		//
		dao.persist(c);
		return c;
	}
}
