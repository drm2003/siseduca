package br.com.cdan.negocio.pedagogico.curso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.Curso_MatrizCurricular;

public class Curso_MatrizCurricularDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public Curso_MatrizCurricularDao(EntityManager em) {
		setEntityManager(em);
	}

	public Curso_MatrizCurricularDao() {

	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Curso_MatrizCurricular curso_MatrizCurricular = (Curso_MatrizCurricular) obj;
		curso_MatrizCurricular.setAtivo(false);
		getEntityManager().merge(curso_MatrizCurricular);
	}
}
