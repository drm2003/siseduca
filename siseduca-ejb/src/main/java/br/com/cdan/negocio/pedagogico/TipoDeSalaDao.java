package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.TipoDeSala;

public class TipoDeSalaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeSalaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeSala t = (TipoDeSala) obj;
		t.setAtivo(false);
		getEntityManager().merge(t);
	}
}
