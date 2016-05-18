package br.com.cdan.negocio.contato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.contato.Midia;
import br.com.cdan.negocio.contato.MidiaDao;

public class MidiaFabricaTest {
	private static MidiaFabricaTest instance = null;

	public static synchronized MidiaFabricaTest getInstance() {
		if (instance == null) {
			instance = new MidiaFabricaTest();
		}
		return instance;
	}

	public Midia criaMidia() {
		Midia m = new Midia();
		m.setAtivo(Boolean.TRUE);
		m.setDescricao("descricao" + Math.random() * 10000);
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
