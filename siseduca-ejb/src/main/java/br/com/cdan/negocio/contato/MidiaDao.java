package br.com.cdan.negocio.contato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.contato.Midia;

public class MidiaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MidiaDao() {
	}

	public MidiaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Midia midia = (Midia) obj;
		midia.setAtivo(false);
		getEntityManager().merge(midia);
	}
}
