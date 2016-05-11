package br.com.cdan.negocio.contato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.contato.Midia;
import br.com.cdan.negocio.contato.MidiaDao;

public class MidiaFabricaFabricaTest {
	private static MidiaFabricaFabricaTest instance = null;

	public static synchronized MidiaFabricaFabricaTest getInstance() {
		if (instance == null) {
			instance = new MidiaFabricaFabricaTest();
		}
		return instance;
	}

	public Midia criaMidia() {
		Midia m = new Midia();
		m.setAtivo(Boolean.TRUE);
		m.setDescricao("descricao");
		return m;
	}

	public Midia criaMidiaPersistido(EntityManager em) {
		Midia m = criaMidia();
		MidiaDao dao = new MidiaDao(em);
		//
		dao.persist(m);
		return m;
	}
}
