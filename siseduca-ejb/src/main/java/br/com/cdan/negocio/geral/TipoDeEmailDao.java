package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.TipoDeEmail;

public class TipoDeEmailDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeEmailDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeEmail t = (TipoDeEmail) obj;
		t.setAtivo(false);
		getEntityManager().merge(t	);
	}
}
