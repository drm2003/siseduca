package br.com.cdan.negocio.financeiro.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.CFOP;
import br.com.cdan.model.financeiro.CST;
import br.com.cdan.model.financeiro.ModalidadeBaseICMS;
import br.com.cdan.model.financeiro.NfeLayout;
import br.com.cdan.model.financeiro.TIPI;
import br.com.cdan.negocio.financeiro.CFOPDao;
import br.com.cdan.negocio.financeiro.CSTDao;
import br.com.cdan.negocio.financeiro.ModalidadeBaseICMSDao;
import br.com.cdan.negocio.financeiro.NfeLayoutDao;
import br.com.cdan.negocio.financeiro.TIPIDao;

public class NfeLayoutFabricaTest {
	private static NfeLayoutFabricaTest instance = null;

	public static synchronized NfeLayoutFabricaTest getInstance() {
		if (instance == null) {
			instance = new NfeLayoutFabricaTest();
		}
		return instance;
	}

	public NfeLayout criaNfeLayout() {
		NfeLayout n = new NfeLayout();
		n.setAtivo(Boolean.TRUE);
		n.setCfop(CFOPFabricaTest.getInstance().criaCFOP());
		n.setCst(CSTFabricaTest.getInstance().criaCST());
		n.setDescricao("descricao");
		n.setIcms(BigDecimal.valueOf(1.5));
		n.setIcmsST(BigDecimal.valueOf(2.3));
		n.setModalidadeBaseICMS(ModalidadeBaseICMSFabricaTest.getInstance().criaModalidadeBaseICMS());
		n.setTipi(TIPIFabricaTest.getInstance().criaTIPI());
		return n;
	}

	public NfeLayout criaNfeLayoutPersistido(EntityManager em) {
		NfeLayout n = criaNfeLayout();
		NfeLayoutDao dao = new NfeLayoutDao(em);
		//
		CFOPDao cfopDao = new CFOPDao(em);
		CFOP cfop = n.getCfop();
		cfopDao.persist(cfop);
		n.setCfop(cfop);
		//
		CSTDao cstDao = new CSTDao(em);
		CST cst = n.getCst();
		cstDao.persist(cst);
		n.setCst(cst);
		//
		ModalidadeBaseICMSDao modalidadeBaseICMSDao = new ModalidadeBaseICMSDao(em);
		ModalidadeBaseICMS modalidadeBaseICMS = n.getModalidadeBaseICMS();
		modalidadeBaseICMSDao.persist(modalidadeBaseICMS);
		n.setModalidadeBaseICMS(modalidadeBaseICMS);
		//
		TIPIDao tipiDao = new TIPIDao(em);
		TIPI tipi = n.getTipi();
		tipiDao.persist(tipi);
		n.setTipi(tipi);
		//
		dao.persist(n);
		return n;
	}
}