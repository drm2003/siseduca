package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.HorarioDeAula;
import br.com.cdan.negocio.pedagogico.HorarioDeAulaDao;

public class HorarioDeAulaFabricaTest {
	private static HorarioDeAulaFabricaTest instance = null;

	public static synchronized HorarioDeAulaFabricaTest getInstance() {
		if (instance == null) {
			instance = new HorarioDeAulaFabricaTest();
		}
		return instance;
	}

	public HorarioDeAula criaHorarioDeAula() {
		HorarioDeAula h = new HorarioDeAula();
		h.setAtivo(Boolean.TRUE);
		h.setDescricao("descricao" + Math.random() * 10000);
		//
		h.setQuantidadeDeAula(Long.valueOf("10"));

		return h;
	}

	public HorarioDeAula criaHorarioDeAulaPersistido(EntityManager em) {
		HorarioDeAula h = criaHorarioDeAula();
		HorarioDeAulaDao dao = new HorarioDeAulaDao(em);
		dao.persist(h);
		return h;
	}

}
