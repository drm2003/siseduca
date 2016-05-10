package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.SeriePadrao;

public class SeriePadraoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public SeriePadraoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		SeriePadrao seriePadrao = (SeriePadrao) obj;
		seriePadrao.setAtivo(false);
		getEntityManager().merge(seriePadrao);
	}
}
