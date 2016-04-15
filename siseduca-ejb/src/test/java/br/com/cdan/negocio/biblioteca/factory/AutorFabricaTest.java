package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Autor;
import br.com.cdan.negocio.biblioteca.AutorDao;

public class AutorFabricaTest {
	private static AutorFabricaTest instance = null;

	public static synchronized AutorFabricaTest getInstance() {
		if (instance == null) {
			instance = new AutorFabricaTest();
		}
		return instance;
	}

	public Autor criaAutor() {
		Autor a = new Autor();
		a.setAtivo(Boolean.TRUE);
		a.setCompartilhado(Boolean.TRUE);
		a.setNome("Teste " + Math.random() * 1000);
		return a;
	}

	public Autor criaAutorPersistido(EntityManager em) {
		AutorDao dao = new AutorDao();
		dao.setEntityManager(em);
		//
		Autor a = criaAutor();
		dao.persist(a);
		return a;
	}
}