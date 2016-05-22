package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Categoria;
import br.com.cdan.negocio.geral.CategoriaDao;

public class CategoriaFabricaTest {
	private static CategoriaFabricaTest instance = null;

	public static synchronized CategoriaFabricaTest getInstance() {
		if (instance == null) {
			instance = new CategoriaFabricaTest();
		}
		return instance;
	}

	public Categoria criaCategoria() {
		Categoria c = new Categoria();
		c.setAtivo(Boolean.TRUE);
		c.setDescricao("descricao" + Math.random() * 1000);
		return c;
	}

	public Categoria criaCategoriaPersistido(EntityManager em) {
		Categoria c = criaCategoria();
		CategoriaDao dao = new CategoriaDao(em);
		dao.persist(c);
		return c;
	}
}
