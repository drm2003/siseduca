package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.MatrizCurricular;

public class MatrizCurricularDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MatrizCurricularDao(EntityManager em) {
		setEntityManager(em);
	}

	public MatrizCurricularDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		MatrizCurricular matrizCurricular = (MatrizCurricular) obj;
		matrizCurricular.setAtivo(false);
		getEntityManager().merge(matrizCurricular);
	}
}
