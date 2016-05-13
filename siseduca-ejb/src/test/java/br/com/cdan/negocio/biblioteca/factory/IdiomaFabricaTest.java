package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Idioma;
import br.com.cdan.negocio.biblioteca.IdiomaDao;

public class IdiomaFabricaTest {
	private static IdiomaFabricaTest instance;

	public static synchronized IdiomaFabricaTest getInstance() {
		if (instance == null) {
			instance = new IdiomaFabricaTest();
		}
		return instance;
	}

	public Idioma criaIdioma() {
		Idioma i = new Idioma();
		i.setAtivo(Boolean.TRUE);
		i.setCompartilhado(Boolean.TRUE);
		i.setDescricao("descricao");
		return i;
	}

	public Idioma criaIdiomaPersistido(EntityManager em) {
		Idioma i = criaIdioma();
		IdiomaDao dao = new IdiomaDao(em);
		dao.persist(i);
		return i;
	}

}
