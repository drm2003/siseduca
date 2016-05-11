package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.NfeLayout;

public class NfeLayoutDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public NfeLayoutDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		NfeLayout n = (NfeLayout) obj;
		n.setAtivo(false);
		getEntityManager().merge(n);
	}
}
