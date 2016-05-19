package br.com.cdan.negocio.financeiro.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.NfeLayout;
import br.com.cdan.negocio.financeiro.NfeLayoutDao;

public class NfeLayoutFabricaTest {
	private static NfeLayoutFabricaTest instance = null;

	public static synchronized NfeLayoutFabricaTest getInstance() {
		if (instance == null) {
			instance = new NfeLayoutFabricaTest();
		}
		return instance;
	}

	public NfeLayout criaNfeLayout(EntityManager em) {
		NfeLayout n = new NfeLayout();
		n.setAtivo(Boolean.TRUE);
		n.setCfop(CFOPFabricaTest.getInstance().criaCFOPPersistido(em));
		n.setCst(CSTFabricaTest.getInstance().criaCSTPersistido(em));
		n.setDescricao("descricao" + Math.random() * 10000);
		n.setIcms(BigDecimal.valueOf(1.5));
		n.setIcmsST(BigDecimal.valueOf(2.3));
		n.setModalidadeBaseICMS(ModalidadeBaseICMSFabricaTest.getInstance().criaModalidadeBaseICMSPersistido(em));
		n.setTipi(TIPIFabricaTest.getInstance().criaTIPIPersistido(em));
		n.setOrigemMercadoria(OrigemMercadoriaFabricaTest.getInstance().criaOrigemMercadoriaPersistido(em));
		return n;
	}

	public NfeLayout criaNfeLayoutPersistido(EntityManager em) {
		NfeLayout n = criaNfeLayout(em);
		NfeLayoutDao dao = new NfeLayoutDao(em);
		//
		dao.persist(n);
		return n;
	}
}