package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Setor;
import br.com.cdan.negocio.biblioteca.SetorDao;

public class SetorFabricaTest {
	private static SetorFabricaTest instance = null;

	public static synchronized SetorFabricaTest getInstance() {
		if (instance == null) {
			instance = new SetorFabricaTest();
		}
		return instance;
	}

	public Setor criaSetor() {
		Setor s = new Setor();
		s.setAtivo(Boolean.TRUE);
		s.setCompartilhado(Boolean.TRUE);
		s.setDescricao("descricao");
		return s;
	}

	public Setor criaSetorPersistido(EntityManager em) {
		Setor s = criaSetor();
		SetorDao dao = new SetorDao(em);
		dao.persist(s);
		return s;
	}

}
