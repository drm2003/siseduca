package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Nivel;
import br.com.cdan.negocio.biblioteca.NivelDao;

public class NivelFabricaTest {
	private static NivelFabricaTest instance;

	public static NivelFabricaTest getInstance() {
		if (instance == null) {
			instance = new NivelFabricaTest();
		}
		return instance;
	}

	public Nivel criaNivel() {
		Nivel n = new Nivel();
		n.setAtivo(Boolean.TRUE);
		n.setCompartilhado(Boolean.TRUE);
		n.setDescricao("descricao" + Math.random() * 10000);
		return n;
	}

	public Nivel criaNivelPersistido(EntityManager em) {
		Nivel n = criaNivel();
		NivelDao dao = new NivelDao(em);
		dao.persist(n);
		return n;
	}

}
