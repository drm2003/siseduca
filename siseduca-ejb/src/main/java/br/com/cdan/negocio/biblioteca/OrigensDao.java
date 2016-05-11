package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Origens;

public class OrigensDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public OrigensDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Origens o = (Origens) obj;
		o.setAtivo(false);
		getEntityManager().merge(o);
	}
}
