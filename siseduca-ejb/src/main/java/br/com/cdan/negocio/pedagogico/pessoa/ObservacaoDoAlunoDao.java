package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.ObservacaoDoAluno;

public class ObservacaoDoAlunoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ObservacaoDoAlunoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ObservacaoDoAluno observacaoDoAluno = (ObservacaoDoAluno) obj;
		observacaoDoAluno.setAtivo(false);
		getEntityManager().merge(observacaoDoAluno);
	}
}
