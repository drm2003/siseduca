package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.SituacaoDaTurma;

public class SituacaoDaTurmaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public SituacaoDaTurmaDao() {
	}

	public SituacaoDaTurmaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		SituacaoDaTurma situacaoDaTurma = (SituacaoDaTurma) obj;
		situacaoDaTurma.setAtivo(false);
		getEntityManager().merge(situacaoDaTurma);
	}
}
