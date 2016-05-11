package br.com.cdan.negocio.estoque;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.estoque.TipoDeSaida;

public class TipoDeSaidaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeSaidaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeSaida t = (TipoDeSaida) obj;
		t.setAtivo(false);
		getEntityManager().merge(t);
	}
}
