package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.TIPI;

public class TIPIDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TIPIDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TIPI t = (TIPI) obj;
		t.setAtivo(false);
		getEntityManager().merge(t);
	}
}
