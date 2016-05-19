package br.com.cdan.negocio.geral.cep.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.cep.EstadoUF;
import br.com.cdan.negocio.geral.EstadoUFDao;

public class EstadoUFFabricaTest {
	private static EstadoUFFabricaTest instance = null;

	public static EstadoUFFabricaTest getInstance() {
		if (instance == null) {
			instance = new EstadoUFFabricaTest();
		}
		return instance;
	}

	public EstadoUF criaEstadoUF(EntityManager em) {
		EstadoUF estadoUF = new EstadoUF();
		estadoUF.setAtivo(Boolean.TRUE);
		estadoUF.setDescricao("teste" + Math.random() * 10000);
		//
		estadoUF.setPais(PaisFabricaTest.getInstance().criaPaisPersistido(em));
		return estadoUF;
	}

	public EstadoUF criaEstadoUFPersistido(EntityManager em) {
		EstadoUF e = criaEstadoUF(em);
		EstadoUFDao dao = new EstadoUFDao(em);
		//
		dao.persist(e);
		return e;
	}
}
