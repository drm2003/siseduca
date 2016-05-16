package br.com.cdan.negocio.acesso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.HorarioDeAcesso;
import br.com.cdan.negocio.acesso.HorarioDeAcessoDao;

public class HorarioDeAcessoFabricaTest {
	private static HorarioDeAcessoFabricaTest instance = null;

	public static synchronized HorarioDeAcessoFabricaTest getInstance() {
		if (instance == null) {
			instance = new HorarioDeAcessoFabricaTest();
		}
		return instance;
	}

	public HorarioDeAcesso criaHorarioDeAcesso() {
		HorarioDeAcesso h = new HorarioDeAcesso();
		h.setAtivo(Boolean.TRUE);
		//
		return h;
	}

	public HorarioDeAcesso criaHorarioDeAcessoPersistido(EntityManager em) {
		HorarioDeAcesso h = criaHorarioDeAcesso();
		HorarioDeAcessoDao dao = new HorarioDeAcessoDao(em);
		dao.persist(h);
		return h;
	}
}
