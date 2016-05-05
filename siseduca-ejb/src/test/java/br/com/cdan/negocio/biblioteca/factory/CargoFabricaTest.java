package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Cargo;
import br.com.cdan.negocio.biblioteca.CargoDao;

public class CargoFabricaTest {
	private static CargoFabricaTest instance = null;

	public static synchronized CargoFabricaTest getInstance() {
		if (instance == null) {
			instance = new CargoFabricaTest();
		}
		return instance;
	}

	public Cargo criaCargo() {
		Cargo c = new Cargo();
		c.setAtivo(Boolean.TRUE);
		c.setDescricao("descricao");
		return c;
	}

	public Cargo criaCargoPersistido(EntityManager em) {
		Cargo c = new Cargo();
		CargoDao dao = new CargoDao(em);
		dao.persist(c);
		return c;
	}
}
