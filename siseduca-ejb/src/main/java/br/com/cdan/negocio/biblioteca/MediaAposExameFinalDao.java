package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.MediaAposExameFinal;

public class MediaAposExameFinalDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MediaAposExameFinalDao() {
	}

	public MediaAposExameFinalDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		MediaAposExameFinal mediaAposExameFinal = (MediaAposExameFinal) obj;
		mediaAposExameFinal.setAtivo(false);
		getEntityManager().merge(mediaAposExameFinal);
	}
}
