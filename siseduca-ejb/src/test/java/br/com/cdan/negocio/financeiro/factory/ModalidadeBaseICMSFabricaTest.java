package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.ModalidadeBaseICMS;
import br.com.cdan.negocio.financeiro.ModalidadeBaseICMSDao;

public class ModalidadeBaseICMSFabricaTest {
	private static ModalidadeBaseICMSFabricaTest instance = null;

	public static synchronized ModalidadeBaseICMSFabricaTest getInstance() {
		if (instance == null) {
			instance = new ModalidadeBaseICMSFabricaTest();
		}
		return instance;
	}

	public ModalidadeBaseICMS criaModalidadeBaseICMS(EntityManager em) {
		ModalidadeBaseICMS m = new ModalidadeBaseICMS();
		m.setAtivo(Boolean.TRUE);
		m.setDescricao("descricao");
		m.setNfeLayout(NfeLayoutFabricaTest.getInstance().criaNfeLayoutPersistido(em));
		return m;
	}

	public ModalidadeBaseICMS criaModalidadeBaseICMSPersistido(EntityManager em) {
		ModalidadeBaseICMS m = criaModalidadeBaseICMS(em);
		ModalidadeBaseICMSDao dao = new ModalidadeBaseICMSDao(em);
		//
		dao.persist(m);
		return m;
	}
}
