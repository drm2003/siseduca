package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Cargo;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.CargoDao;

public class CargoFabricaTest extends FabricaTest {
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
		c.setDescricao(criarStringDinamicaPorTamanho(100));
		return c;
	}

	public Cargo criaCargoPersistido(EntityManager em) {
		Cargo c = criaCargo();
		CargoDao dao = new CargoDao(em);
		dao.persist(c);
		return c;
	}
}
