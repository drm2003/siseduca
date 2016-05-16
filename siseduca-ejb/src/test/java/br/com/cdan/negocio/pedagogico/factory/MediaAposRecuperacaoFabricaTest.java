package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumMediaAposRecuperacao;
import br.com.cdan.model.pedagogico.MediaAposRecuperacao;
import br.com.cdan.negocio.pedagogico.MediaAposRecuperacaoDao;

public class MediaAposRecuperacaoFabricaTest {
	private static MediaAposRecuperacaoFabricaTest instance = null;

	public static synchronized MediaAposRecuperacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new MediaAposRecuperacaoFabricaTest();
		}
		return instance;
	}

	public MediaAposRecuperacao criaMediaAposRecuperacao(EntityManager em) {
		MediaAposRecuperacao m = new MediaAposRecuperacao();
		m.setAtivo(Boolean.TRUE);
		m.setDesconsiderarNotaMenorQueSete(Boolean.FALSE);
		m.setDesconsiderarNotaRecuperacaoAposAMedia(Boolean.FALSE);
		m.setDesconsiderarNotaRecuperacaoMenor(Boolean.FALSE);
		m.setEnumMediaAposRecuperacao(EnumMediaAposRecuperacao.MEDIAENTREANOTAEARECUPERACAO);
		m.setRecuperacao(RecuperacaoFabricaTest.getInstance().criaRecuperacaoPersisito(em));
		return m;
	}

	public MediaAposRecuperacao criaMediaAposRecuperacaoPersistido(EntityManager em) {
		MediaAposRecuperacao m = criaMediaAposRecuperacao(em);
		MediaAposRecuperacaoDao dao = new MediaAposRecuperacaoDao(em);
		dao.persist(m);
		return m;
	}

}
