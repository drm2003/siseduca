package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.AvaliacaoPadrao;
import br.com.cdan.negocio.pedagogico.AvaliacaoPadraoDao;

public class AvaliacaoPadraoFabricaTest {
	private static AvaliacaoPadraoFabricaTest instance = null;

	public static synchronized AvaliacaoPadraoFabricaTest getInstance() {
		if (instance == null) {
			instance = new AvaliacaoPadraoFabricaTest();
		}
		return instance;
	}

	public AvaliacaoPadrao criaAvaliacaoPadrao(EntityManager em) {
		AvaliacaoPadrao a = new AvaliacaoPadrao();
		//
		a.setAtivo(Boolean.TRUE);
		a.setDescricao("descricao");
		a.setGrupo(GrupoFabricaTest.getInstance().criaGrupoPersistido(em));
		a.setOrdem(Long.valueOf(10));
		a.setPeso(Long.valueOf(1));
		//
		return a;
	}

	public AvaliacaoPadrao criaAvaliacaoPadraoPersistido(EntityManager em) {
		AvaliacaoPadrao a = criaAvaliacaoPadrao(em);
		AvaliacaoPadraoDao dao = new AvaliacaoPadraoDao(em);
		//
		dao.persist(a);
		return a;
	}
}
