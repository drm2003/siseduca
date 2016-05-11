package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.Sala;

public class SalaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public SalaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Sala s = (Sala) obj;
		s.setAtivo(false);
		getEntityManager().merge(s);
	}
}
