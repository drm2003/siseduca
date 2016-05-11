package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.ModalidadeBaseICMS;
import br.com.cdan.model.financeiro.NfeLayout;
import br.com.cdan.negocio.financeiro.ModalidadeBaseICMSDao;
import br.com.cdan.negocio.financeiro.NfeLayoutDao;

public class ModalidadeBaseICMSFabricaTest {
	private static ModalidadeBaseICMSFabricaTest instance = null;

	public static synchronized ModalidadeBaseICMSFabricaTest getInstance() {
		if (instance == null) {
			instance = new ModalidadeBaseICMSFabricaTest();
		}
		return instance;
	}

	public ModalidadeBaseICMS criaModalidadeBaseICMS() {
		ModalidadeBaseICMS m = new ModalidadeBaseICMS();
		m.setAtivo(Boolean.TRUE);
		m.setDescricao("descricao");
		m.setNfeLayout(NfeLayoutFabricaTest.getInstance().criaNfeLayout());
		return m;
	}

	public ModalidadeBaseICMS criaModalidadeBaseICMSPersistido(EntityManager em) {
		ModalidadeBaseICMS m = criaModalidadeBaseICMS();
		ModalidadeBaseICMSDao dao = new ModalidadeBaseICMSDao(em);
		//
		NfeLayoutDao nfeLayoutDao = new NfeLayoutDao(em);
		NfeLayout nfeLayout = m.getNfeLayout();
		nfeLayoutDao.persist(nfeLayout);
		m.setNfeLayout(nfeLayout);
		//
		dao.persist(m);
		return m;
	}
}
