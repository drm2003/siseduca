package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.SituacaoDoAluno;

public class SituacaoDoAlunoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public SituacaoDoAlunoDao() {
	}

	public SituacaoDoAlunoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		SituacaoDoAluno situacaoDoAluno = (SituacaoDoAluno) obj;
		situacaoDoAluno.setAtivo(false);
		getEntityManager().merge(situacaoDoAluno);
	}
}
