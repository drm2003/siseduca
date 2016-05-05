package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;

public class Disciplina_MatrizCurricularDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public Disciplina_MatrizCurricularDao() {

	}

	public Disciplina_MatrizCurricularDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Disciplina_MatrizCurricular disciplina_MatrizCurricular = (Disciplina_MatrizCurricular) obj;
		disciplina_MatrizCurricular.setAtivo(false);
		getEntityManager().merge(disciplina_MatrizCurricular);
	}
}
