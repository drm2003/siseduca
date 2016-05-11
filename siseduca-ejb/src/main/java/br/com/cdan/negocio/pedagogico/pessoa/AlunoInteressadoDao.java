package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Aluno;

public class AlunoInteressadoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public AlunoInteressadoDao(EntityManager em) {
		setEntityManager(em);
	}

	public AlunoInteressadoDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Aluno aluno = (Aluno) obj;
		aluno.setAtivo(false);
		getEntityManager().merge(aluno);
	}
}
