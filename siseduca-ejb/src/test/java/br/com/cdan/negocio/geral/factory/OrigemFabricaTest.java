package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Origem;
import br.com.cdan.negocio.biblioteca.OrigemDao;

public class OrigemFabricaTest {
	private static OrigemFabricaTest instance = null;

	public static synchronized OrigemFabricaTest getInstance() {
		if (instance == null) {
			instance = new OrigemFabricaTest();
		}
		return instance;
	}

	public Origem criaOrigem() {
		Origem o = new Origem();
		o.setAtivo(Boolean.TRUE);
		o.setCompartilhado(Boolean.TRUE);
		o.setDescricao("descricao " + Math.random() * 10000);
		return o;
	}

	public Origem criaOrigemPersistido(EntityManager em) {
		Origem o = criaOrigem();
		OrigemDao dao = new OrigemDao(em);
		dao.persist(o);
		return o;
	}

}
