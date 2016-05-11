package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.MediaAposRecuperacao;

public class MediaAposRecuperacaoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MediaAposRecuperacaoDao() {
	}

	public MediaAposRecuperacaoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		MediaAposRecuperacao mediaAposRecuperacao = (MediaAposRecuperacao) obj;
		mediaAposRecuperacao.setAtivo(false);
		getEntityManager().merge(mediaAposRecuperacao);
	}
}
