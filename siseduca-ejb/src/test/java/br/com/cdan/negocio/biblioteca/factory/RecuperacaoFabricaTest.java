package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDeRecuperacao;
import br.com.cdan.model.pedagogico.MediaAposRecuperacao;
import br.com.cdan.model.pedagogico.Recuperacao;
import br.com.cdan.model.pedagogico.SistemaDeAvaliacao;
import br.com.cdan.negocio.biblioteca.MediaAposRecuperacaoDao;
import br.com.cdan.negocio.biblioteca.RecuperacaoDao;
import br.com.cdan.negocio.biblioteca.SistemaDeAvaliacaoDao;

public class RecuperacaoFabricaTest {
	private static RecuperacaoFabricaTest instance = null;

	public static synchronized RecuperacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new RecuperacaoFabricaTest();
		}
		return instance;
	}

	public Recuperacao criaRecuperacao() {
		Recuperacao r = new Recuperacao();
		r.setAtivo(Boolean.TRUE);
		r.setEnumTipoDeRecuperacao(EnumTipoDeRecuperacao.RECUPERACAOACADAPERIODO);
		r.setMediaAposRecuperacao(MediaAposRecuperacaoFabricaTest.getInstance().criaMediaAposRecuperacao());
		r.setPesoDaRecuperacao(Long.valueOf(1));
		r.setSistemaDeAvaliacao(SistemaDeAvaliacaoFabricaTest.getInstance().criaSistemaDeAvaliacao());
		r.setValorMediaAposRecuperacao(BigDecimal.valueOf(7.0));
		return r;
	}

	public Recuperacao criaRecuperacaoPersisito(EntityManager em) {
		Recuperacao r = criaRecuperacao();
		RecuperacaoDao dao = new RecuperacaoDao(em);
		//
		MediaAposRecuperacaoDao mediaAposRecuperacaoDao = new MediaAposRecuperacaoDao(em);
		MediaAposRecuperacao mediaAposRecuperacao = r.getMediaAposRecuperacao();
		mediaAposRecuperacaoDao.persist(mediaAposRecuperacao);
		r.setMediaAposRecuperacao(mediaAposRecuperacao);
		//
		SistemaDeAvaliacaoDao sistemaDeAvaliacaoDao = new SistemaDeAvaliacaoDao(em);
		SistemaDeAvaliacao sistemaDeAvaliacao = r.getSistemaDeAvaliacao();
		sistemaDeAvaliacaoDao.persist(sistemaDeAvaliacao);
		r.setSistemaDeAvaliacao(sistemaDeAvaliacao);
		//
		dao.persist(r);
		return r;
	}
}
