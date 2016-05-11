package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.TipoDeCurso;

public class TipoDeCursoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeCursoDao() {
	}

	public TipoDeCursoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeCurso tipoDeCurso = (TipoDeCurso) obj;
		tipoDeCurso.setAtivo(false);
		getEntityManager().merge(tipoDeCurso);
	}
}
