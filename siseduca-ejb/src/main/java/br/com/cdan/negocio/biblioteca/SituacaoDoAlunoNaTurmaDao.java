package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.SituacaoDoAlunoNaTurma;

public class SituacaoDoAlunoNaTurmaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public SituacaoDoAlunoNaTurmaDao() {
	}

	public SituacaoDoAlunoNaTurmaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		SituacaoDoAlunoNaTurma situacaoDoAlunoNaTurma = (SituacaoDoAlunoNaTurma) obj;
		situacaoDoAlunoNaTurma.setAtivo(false);
		getEntityManager().merge(situacaoDoAlunoNaTurma);
	}
}
