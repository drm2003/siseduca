package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.EstadoCivil;
import br.com.cdan.negocio.biblioteca.EstadoCivilDao;

public class EstadoCivilFabricaTest {
	private static EstadoCivilFabricaTest instance = null;

	public static synchronized EstadoCivilFabricaTest getInstance() {
		if (instance == null) {
			instance = new EstadoCivilFabricaTest();
		}
		return instance;
	}

	public EstadoCivil criaEstadoCivil() {
		EstadoCivil e = new EstadoCivil();
		//
		e.setAtivo(Boolean.TRUE);
		e.setDescricao("descricao");
		//
		return e;
	}

	public EstadoCivil criaEstadoCivilPersistido(EntityManager em) {
		EstadoCivil e = criaEstadoCivil();
		EstadoCivilDao dao = new EstadoCivilDao();
		//

		dao.persist(e);
		return e;
	}

}
