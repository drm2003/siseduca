package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.ClassificacaoLiteraria;
import br.com.cdan.negocio.biblioteca.ClassificacaoLiterariaDao;

public class ClassificacaoLiterariaFabricaTest {
	private static ClassificacaoLiterariaFabricaTest instance = null;

	public static synchronized ClassificacaoLiterariaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ClassificacaoLiterariaFabricaTest();
		}
		return instance;
	}

	public ClassificacaoLiteraria criaClassificacaoLiteraria() {
		ClassificacaoLiteraria c = new ClassificacaoLiteraria();
		c.setAtivo(Boolean.TRUE);
		c.setCompartilhado(Boolean.TRUE);
		c.setDescricao("descricao" + Math.random() * 10000);
		return c;
	}

	public ClassificacaoLiteraria criaClassificacaoLiterariaPersistido(EntityManager em) {
		ClassificacaoLiteraria c = criaClassificacaoLiteraria();
		ClassificacaoLiterariaDao dao = new ClassificacaoLiterariaDao(em);
		//
		dao.persist(c);
		return c;
	}
}