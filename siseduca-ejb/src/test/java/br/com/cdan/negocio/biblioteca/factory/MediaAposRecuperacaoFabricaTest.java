package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumMediaAposRecuperacao;
import br.com.cdan.model.pedagogico.MediaAposRecuperacao;
import br.com.cdan.model.pedagogico.Recuperacao;
import br.com.cdan.negocio.biblioteca.MediaAposRecuperacaoDao;
import br.com.cdan.negocio.biblioteca.RecuperacaoDao;

public class MediaAposRecuperacaoFabricaTest {
	private static MediaAposRecuperacaoFabricaTest instance = null;

	public static synchronized MediaAposRecuperacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new MediaAposRecuperacaoFabricaTest();
		}
		return instance;
	}

	public MediaAposRecuperacao criaMediaAposRecuperacao() {
		MediaAposRecuperacao m = new MediaAposRecuperacao();
		m.setAtivo(Boolean.TRUE);
		m.setDesconsiderarNotaMenorQueSete(Boolean.FALSE);
		m.setDesconsiderarNotaRecuperacaoAposAMedia(Boolean.FALSE);
		m.setDesconsiderarNotaRecuperacaoMenor(Boolean.FALSE);
		m.setEnumMediaAposRecuperacao(EnumMediaAposRecuperacao.MEDIAENTREANOTAEARECUPERACAO);
		m.setRecuperacao(RecuperacaoFabricaTest.getInstance().criaRecuperacao());
		return m;
	}

	public MediaAposRecuperacao criaMediaAposRecuperacaoPersistido(EntityManager em) {
		MediaAposRecuperacao m = criaMediaAposRecuperacao();
		MediaAposRecuperacaoDao dao = new MediaAposRecuperacaoDao(em);
		//
		RecuperacaoDao recuperacaoDao = new RecuperacaoDao(em);
		Recuperacao recuperacao = m.getRecuperacao();
		recuperacaoDao.persist(recuperacao);
		m.setRecuperacao(recuperacao);
		//
		dao.persist(m);
		return m;
	}

}
