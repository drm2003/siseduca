package br.com.cdan.negocio.pedagogico.contrato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.Matricula;

public class MatriculaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MatriculaDao(EntityManager em) {
		setEntityManager(em);
	}

	public MatriculaDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Matricula matricula = (Matricula) obj;
		matricula.setAtivo(false);
		getEntityManager().merge(matricula);
	}
}
