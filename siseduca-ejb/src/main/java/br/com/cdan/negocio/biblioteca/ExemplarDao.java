package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Exemplar;

public class ExemplarDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ExemplarDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Exemplar exemplar = (Exemplar) obj;
		exemplar.setAtivo(false);
		getEntityManager().merge(exemplar);
	}
}
