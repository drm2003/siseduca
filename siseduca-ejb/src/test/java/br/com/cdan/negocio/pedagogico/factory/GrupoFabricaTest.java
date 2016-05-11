package br.com.cdan.negocio.pedagogico.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.AvaliacaoPadrao;
import br.com.cdan.model.pedagogico.Grupo;
import br.com.cdan.negocio.pedagogico.AvaliacaoPadraoDao;
import br.com.cdan.negocio.pedagogico.GrupoDao;

public class GrupoFabricaTest {
	private static GrupoFabricaTest instance = null;

	public static synchronized GrupoFabricaTest getInstance() {
		if (instance == null) {
			instance = new GrupoFabricaTest();
		}
		return instance;
	}

	public Grupo criaGrupo() {
		Grupo g = new Grupo();
		g.setAtivo(Boolean.TRUE);
		g.setDescricao("descricao");
		//
		Set<AvaliacaoPadrao> avaliacoesPadrao = new LinkedHashSet<>();
		avaliacoesPadrao.add(AvaliacaoPadraoFabricaTest.getInstance().criaAvaliacaoPadrao());
		avaliacoesPadrao.add(AvaliacaoPadraoFabricaTest.getInstance().criaAvaliacaoPadrao());
		g.setAvaliacoesPadrao(avaliacoesPadrao);
		//
		g.setPeso(Long.valueOf(1));
		return g;
	}

	public Grupo criaGrupoPersistido(EntityManager em) {
		Grupo g = criaGrupo();
		GrupoDao dao = new GrupoDao(em);
		//
		AvaliacaoPadraoDao avaliacaoPadraoDao = new AvaliacaoPadraoDao(em);
		Set<AvaliacaoPadrao> avaliacoesPadrao = new LinkedHashSet<>();
		g.getAvaliacoesPadrao().forEach(avaliacaoPadrao -> {
			avaliacaoPadraoDao.persist(avaliacaoPadrao);
		});
		g.setAvaliacoesPadrao(avaliacoesPadrao);
		//
		dao.persist(g);
		return g;
	}
}
