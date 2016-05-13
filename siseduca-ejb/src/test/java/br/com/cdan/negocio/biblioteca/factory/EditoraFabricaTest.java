package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Editora;
import br.com.cdan.negocio.biblioteca.EditoraDao;

public class EditoraFabricaTest {
	private static EditoraFabricaTest instance = null;

	public static synchronized EditoraFabricaTest getInstance() {
		if (instance == null) {
			instance = new EditoraFabricaTest();
		}
		return instance;
	}

	public Editora criaEditora() {
		Editora a = new Editora();
		a.setAtivo(Boolean.TRUE);
		a.setCompartilhado(Boolean.TRUE);
		a.setNome("Teste " + Math.random() * 1000);
		a.setHomePage("teste");
		a.setObservacoes("teste");
		//
		return a;
	}

	public Editora criaEditoraPersistido(EntityManager em) {
		EditoraDao dao = new EditoraDao(em);
		//
		Editora a = criaEditora();
		// Gravando primeiramente os objetos
		dao.persist(a);
		return a;
	}
}