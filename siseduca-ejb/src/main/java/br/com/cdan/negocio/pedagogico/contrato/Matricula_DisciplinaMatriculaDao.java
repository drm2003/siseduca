package br.com.cdan.negocio.pedagogico.contrato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.Matricula_DisciplinaMatricula;

public class Matricula_DisciplinaMatriculaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public Matricula_DisciplinaMatriculaDao() {
	}

	public Matricula_DisciplinaMatriculaDao(EntityManager em) {
		this.setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Matricula_DisciplinaMatricula m = (Matricula_DisciplinaMatricula) obj;
		m.setAtivo(false);
		getEntityManager().merge(m);
	}
}
