package br.com.cdan.negocio.estoque.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.estoque.ClassificacaoDeItens;
import br.com.cdan.negocio.estoque.ClassificacaoDeItensDao;

public class ClassificacaoDeItensFabricaTest {
	private static ClassificacaoDeItensFabricaTest instance = null;

	public static synchronized ClassificacaoDeItensFabricaTest getInstance() {
		if (instance == null) {
			instance = new ClassificacaoDeItensFabricaTest();
		}
		return instance;
	}

	public ClassificacaoDeItens criaClassificacaoDeItens() {
		ClassificacaoDeItens c = new ClassificacaoDeItens();
		c.setAtivo(Boolean.TRUE);
		c.setDescricao("descricao");
		return c;
	}

	public ClassificacaoDeItens criaClassificacaoDeItensPersistido(EntityManager em) {
		ClassificacaoDeItens c = criaClassificacaoDeItens();
		ClassificacaoDeItensDao dao = new ClassificacaoDeItensDao(em);
		dao.persist(c);
		return c;
	}
}
