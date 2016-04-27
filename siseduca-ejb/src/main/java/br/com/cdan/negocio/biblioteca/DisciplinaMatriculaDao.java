package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.DisciplinaMatricula;

public class DisciplinaMatriculaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DisciplinaMatriculaDao() {
	}

	public DisciplinaMatriculaDao(EntityManager em) {
		this.setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		DisciplinaMatricula disciplinaMatricula = (DisciplinaMatricula) obj;
		disciplinaMatricula.setAtivo(false);
		getEntityManager().merge(disciplinaMatricula);
	}
}
