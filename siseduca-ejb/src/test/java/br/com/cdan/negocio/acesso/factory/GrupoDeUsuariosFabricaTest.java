package br.com.cdan.negocio.acesso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.GrupoDeUsuarios;
import br.com.cdan.negocio.acesso.GrupoDeUsuariosDao;

public class GrupoDeUsuariosFabricaTest {
	private static GrupoDeUsuariosFabricaTest instance = null;

	public static synchronized GrupoDeUsuariosFabricaTest getInstance() {
		if (instance == null) {
			instance = new GrupoDeUsuariosFabricaTest();
		}
		return instance;
	}

	public GrupoDeUsuarios criaGrupoDeUsuarios() {
		GrupoDeUsuarios g = new GrupoDeUsuarios();
		g.setNome("nome" + Math.random() * 10000);
		g.setAtivo(Boolean.TRUE);
		g.setProfessor(Boolean.TRUE);
		return g;
	}

	public GrupoDeUsuarios criaGrupoDeUsuariosPersistido(EntityManager em) {
		GrupoDeUsuarios g = criaGrupoDeUsuarios();
		GrupoDeUsuariosDao dao = new GrupoDeUsuariosDao(em);
		dao.persist(g);
		return g;
	}
}
