package br.com.cdan.negocio.pedagogico.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDeRecuperacao;
import br.com.cdan.model.pedagogico.Recuperacao;
import br.com.cdan.negocio.pedagogico.RecuperacaoDao;

public class RecuperacaoFabricaTest {
	private static RecuperacaoFabricaTest instance = null;

	public static synchronized RecuperacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new RecuperacaoFabricaTest();
		}
		return instance;
	}

	public Recuperacao criaRecuperacao(EntityManager em) {
		Recuperacao r = new Recuperacao();
		r.setAtivo(Boolean.TRUE);
		r.setEnumTipoDeRecuperacao(EnumTipoDeRecuperacao.RECUPERACAOACADAPERIODO);
		r.setMediaAposRecuperacao(MediaAposRecuperacaoFabricaTest.getInstance().criaMediaAposRecuperacaoPersistido(em));
		r.setPesoDaRecuperacao(Long.valueOf(1));
		r.setSistemaDeAvaliacao(SistemaDeAvaliacaoFabricaTest.getInstance().criaSistemaDeAvaliacaoPersistido(em));
		r.setValorMediaAposRecuperacao(BigDecimal.valueOf(7.0));
		return r;
	}

	public Recuperacao criaRecuperacaoPersisito(EntityManager em) {
		Recuperacao r = criaRecuperacao(em);
		RecuperacaoDao dao = new RecuperacaoDao(em);
		//
		dao.persist(r);
		return r;
	}
}
