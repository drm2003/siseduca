package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.OperadoraDeCelular;

public class OperadoraDeCelularDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public OperadoraDeCelularDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		OperadoraDeCelular o = (OperadoraDeCelular) obj;
		o.setAtivo(false);
		getEntityManager().merge(o);
	}
}
