package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.ExameFinal;

public class ExameFinalDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ExameFinalDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ExameFinal exameFinal = (ExameFinal) obj;
		exameFinal.setAtivo(false);
		getEntityManager().merge(exameFinal);
	}
}
