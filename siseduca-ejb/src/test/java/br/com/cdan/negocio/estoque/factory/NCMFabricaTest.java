package br.com.cdan.negocio.estoque.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.estoque.NCM;
import br.com.cdan.negocio.estoque.NCMDao;

public class NCMFabricaTest {
	private static NCMFabricaTest instance = null;

	public static synchronized NCMFabricaTest getInstance() {
		if (instance == null) {
			instance = new NCMFabricaTest();
		}
		return instance;
	}

	public NCM criaNCM() {
		NCM ncm = new NCM();
		ncm.setAtivo(Boolean.TRUE);
		ncm.setDescricao("descricao" + Math.random() * 10000);
		//
		return ncm;
	}

	public NCM criaNCMPersistido(EntityManager em) {
		NCM ncm = criaNCM();
		NCMDao dao = new NCMDao(em);
		//
		dao.persist(ncm);
		return ncm;
	}

}
